package main;

public class SoundFileItem {

	private String nameToDisplay;
	private String fileName;

	SoundFileItem(String nameToDisplay, String fileName) {
		this.nameToDisplay = nameToDisplay;
		this.fileName = fileName;
	}

	public String getNameToDisplay() {
		return nameToDisplay;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public String toString() {
		return nameToDisplay;
	}
}
