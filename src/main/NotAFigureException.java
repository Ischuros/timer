package main;

public class NotAFigureException extends RuntimeException {

	public NotAFigureException(int number) {
		super("This is not a figure : "+number);
	}
}
