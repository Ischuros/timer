package main;

public class NotFoundException extends RuntimeException {

	public NotFoundException(String fileName) {
		super("File not found : " + fileName);
	}
}
