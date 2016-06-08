package pl.edu.pwr.pp;

import java.awt.image.BufferedImage;
import java.io.File;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

public class ImageComponentTest {

	@Mock
	private BufferedImage image;
	private QualityEnum quality;
	private ImageWidthEnum widthEnum;

	private ImageComponent imageComponent;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		quality = QualityEnum.LOW;
		widthEnum = ImageWidthEnum.SIGNS160;
		imageComponent = new ImageComponent();
		Whitebox.setInternalState(imageComponent, "image", image);
		Whitebox.setInternalState(imageComponent, "quality", quality);
		Whitebox.setInternalState(imageComponent, "widthEnum", widthEnum);
	}

	@Test
	public void shouldRescaleImageWithPreservedRatio() {
		//given
		int height = 150;
		Mockito.when(image.getWidth()).thenReturn(320);
		Mockito.when(image.getHeight()).thenReturn(height);
		Mockito.when(image.getType()).thenReturn(BufferedImage.TYPE_BYTE_GRAY);
		//when
		BufferedImage resizeImage = imageComponent.resizeImage();
		int resultHeight = resizeImage.getHeight();
		int resultWidth = resizeImage.getWidth();
		//then
		Assert.assertThat(resultHeight, Matchers.is(Matchers.equalTo(height / 2)));
		Assert.assertThat(resultWidth, Matchers.is(Matchers.equalTo(160)));

		Mockito.verify(image).getWidth();
		Mockito.verify(image).getHeight();
		Mockito.verify(image).getType();

	}

	@Test
	public void shoudConvertRGBImageToIntesitiesArray() {
		//given
		int size = 5;
		Mockito.when(image.getWidth()).thenReturn(size);
		Mockito.when(image.getHeight()).thenReturn(size);
		Mockito.when(image.getRGB(Mockito.anyInt(), Mockito.anyInt())).thenReturn(10);
		//when
		int[][] convertToGrayIntensities = imageComponent.convertToGrayIntensities();
		//then
		Mockito.verify(image, Mockito.atLeastOnce()).getHeight();
		Mockito.verify(image, Mockito.atLeastOnce()).getWidth();
		Mockito.verify(image, Mockito.times(25)).getRGB(Mockito.anyInt(), Mockito.anyInt());
		int[] intensities = { 1, 1, 1, 1, 1 };
		for (int i = 0; i < size; ++i) {
			Assert.assertArrayEquals(intensities, convertToGrayIntensities[i]);
		}

	}
	@Test
	public void shouldSaveImageToFile(){
		//given
		int size = 5;
		Mockito.when(image.getWidth()).thenReturn(size);
		Mockito.when(image.getHeight()).thenReturn(size);
		Mockito.when(image.getType()).thenReturn(BufferedImage.TYPE_BYTE_GRAY);
		Mockito.when(image.getRGB(Mockito.anyInt(), Mockito.anyInt())).thenReturn(10);
		String path = "src/main/resources/plik.txt"; 
		//when
		imageComponent.saveImage(path);
		//then
		File file = new File(path);
		Assert.assertThat(file.exists(), Matchers.is(Matchers.equalTo(true)));
		Mockito.verify(image, Mockito.atLeastOnce()).getHeight();
		Mockito.verify(image, Mockito.atLeastOnce()).getWidth();
	}

	
	
}
