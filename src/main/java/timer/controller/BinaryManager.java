package timer.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BinaryManager {

	private static final int FIGURE_MAX_VALUE = 9;
	private static final int BINARY_MAX_DIGIT_FOR_FIGURE = 4;

	private final int hoursUnity;
	private final int minutesDecade;
	private final int minutesUnity;
	private final int secondsDecade;
	private final int secondsUnity;
	private final int tenthSeconds;

	BinaryManager(int seconds, int tenthSeconds) {
		this.hoursUnity = calculateUnity(seconds / 3600);
		this.minutesDecade = calculateDecade((seconds - hoursUnity * 3600) / 60);
		this.minutesUnity =
				calculateUnity((seconds - hoursUnity * 3600 - minutesDecade * 10 * 60) / 60);
		this.secondsDecade = calculateDecade(
				seconds - hoursUnity * 3600 - minutesDecade * 10 * 60 - minutesUnity * 60);
		this.secondsUnity = calculateUnity(
				seconds - hoursUnity * 3600 - minutesDecade * 10 * 60 - minutesUnity * 60 -
						secondsDecade * 10);
		this.tenthSeconds = tenthSeconds;
	}


	private int calculateUnity(int number) {
		return number % 10;
	}

	private int calculateDecade(int number) {
		return calculateUnity(number / 10);
	}

	public List<Boolean> getListOfBoolean(TimeUnit unit) {
		List<Boolean> result = new ArrayList<>();
		switch (unit) {
			case HOURS_UNITY:
				result = convertBinaryToListOfBoolean(hoursUnity);
				break;
			case MINUTES_DECADE:
				result = convertBinaryToListOfBoolean(minutesDecade);
				break;
			case MINUTES_UNITY:
				result = convertBinaryToListOfBoolean(minutesUnity);
				break;
			case SECONDES_DECADE:
				result = convertBinaryToListOfBoolean(secondsDecade);
				break;
			case SECONDS_UNITY:
				result = convertBinaryToListOfBoolean(secondsUnity);
				break;
			case TENTH_SECONDES:
				result = convertBinaryToListOfBoolean(tenthSeconds);
				break;
		}

		Collections.reverse(result);
		return result;
	}

	List<Boolean> convertBinaryToListOfBoolean(int number) {
		if (number > FIGURE_MAX_VALUE) {
			throw new NotAFigureException(number);
		}

		List<Boolean> list = new ArrayList<>();

		String binaryString = Integer.toBinaryString(number);
		for (int i = binaryString.length() - 1; i >= 0; i--) {
			list.add(Integer.valueOf("" + binaryString.charAt(i)).equals(1));
		}

		while (list.size() < BINARY_MAX_DIGIT_FOR_FIGURE) {
			list.add(Boolean.FALSE);
		}

		return list;
	}

	public int getHoursUnity() {
		return hoursUnity;
	}

	public int getMinutesDecade() {
		return minutesDecade;
	}

	public int getMinutesUnity() {
		return minutesUnity;
	}

	public int getSecondsDecade() {
		return secondsDecade;
	}

	public int getSecondsUnity() {
		return secondsUnity;
	}

	public int getTenthSeconds() {
		return tenthSeconds;
	}
}
