package timer.controller;

class NotAFigureException extends RuntimeException {

	NotAFigureException(int number) {
		super("This is not a figure : "+number);
	}
}
