package pl.edu.pwr.pp;

public enum ImageWidthEnum {

	ORIGINAL("Oryginalna"), SIGNS80("80 znaków"), SIGNS160("160 znaków"), SCREEN_WIDTH("Szerokoœæ ekranu");

	private final String widthEnumName;

	private ImageWidthEnum(final String text) {
		this.widthEnumName = text;
	}

	@Override
	public String toString() {
		return widthEnumName;
	}

}
