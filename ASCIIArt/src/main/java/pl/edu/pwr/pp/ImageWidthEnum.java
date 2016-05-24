package pl.edu.pwr.pp;

public enum ImageWidthEnum {

	ORIGINAL("Oryginalna"), SIGNS80("80 znak�w"), SIGNS160("160 znak�w"), SCREEN_WIDTH("Szeroko�� ekranu");

	private final String widthEnumName;

	private ImageWidthEnum(final String text) {
		this.widthEnumName = text;
	}

	@Override
	public String toString() {
		return widthEnumName;
	}

}
