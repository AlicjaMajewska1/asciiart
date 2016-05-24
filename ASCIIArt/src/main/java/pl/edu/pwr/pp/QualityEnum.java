package pl.edu.pwr.pp;

public enum QualityEnum {

	 LOW("Niska"), HIGH("Wysoka");

	private final String qualityName;

	private QualityEnum(final String text) {
		this.qualityName = text;
	}

	@Override
	public String toString() {
		return qualityName;
	}
	

}
