package main;

class NotFoundException extends RuntimeException {

	NotFoundException(String fileName) {
		super("File not found : " + fileName);
	}
}
