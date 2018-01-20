package main.timer.controller;

import java.util.ArrayList;
import java.util.List;

class StringToDurationCalculator {

	private StringToDurationCalculator() {

	}

	private static final int TIME_AMOUNT_MILLIS = 2 * 60 * 1000;

	public static int getMilliSeconds(String durationText) {
		if (durationText == null || durationText.isEmpty()) {
			return TIME_AMOUNT_MILLIS;
		}

		List<Integer> timeFields = calculateTimeFields(durationText);
		final boolean isValid = timeFields.stream().noneMatch(i -> i >= 60);
		if (!isValid) {
			return TIME_AMOUNT_MILLIS;
		}

		return (timeFields.get(0) * 3600 + timeFields.get(1) * 60 + timeFields.get(2)) * 1000 +
				timeFields.get(3) * 100;
	}

	public static boolean isValidStringDuration(String durationText) {
		List<Integer> timeFields = calculateTimeFields(durationText);

		return timeFields.get(0) <= 9 && timeFields.stream().noneMatch(i -> i >= 60);

	}

	private static List<Integer> calculateTimeFields(String durationText) {
		List<Integer> timeFields = new ArrayList<>();
		Integer tenthSeconds = Integer.valueOf(durationText.split("\\.")[1]);
		String[] secondsAndAboveFields = durationText.split("\\.")[0].split(":");

		for (String field : secondsAndAboveFields) {
			timeFields.add(Integer.valueOf(field));
		}
		timeFields.add(tenthSeconds);

		while (timeFields.size() < 4) {
			timeFields.add(0, 0);
		}
		return timeFields;
	}
}
