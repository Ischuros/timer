package main;

class SoundFileItem {

	private final String nameToDisplay;
	private final String fileName;

	SoundFileItem(String nameToDisplay, String fileName) {
		this.nameToDisplay = nameToDisplay;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public String toString() {
		return nameToDisplay;
	}
}
