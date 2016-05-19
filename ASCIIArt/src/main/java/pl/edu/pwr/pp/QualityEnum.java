package pl.edu.pwr.pp;

public enum QualityEnum {

	HIGH("Wysoka"), LOW("Niska");

	private final String qualityName;

	private QualityEnum(final String text) {
		this.qualityName = text;
	}

	@Override
	public String toString() {
		return qualityName;
	}

}
