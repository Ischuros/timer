package main;

class NotAFigureException extends RuntimeException {

	NotAFigureException(int number) {
		super("This is not a figure : "+number);
	}
}
