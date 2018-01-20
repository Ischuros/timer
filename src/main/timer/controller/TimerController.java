package main.timer.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.StringConverter;
import main.timer.Main;

import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TimerController implements Initializable {

	private static final int TICKING_DURATION_MILLIS = 100;
	private static final String DEFAULT_TIME_INPUT = "2:00.0";

	@FXML
	private Text timerDisplayText;

	@FXML
	private Pane paneCol0Row0;

	@FXML
	private Pane paneCol0Row1;

	@FXML
	private Pane paneCol0Row2;

	@FXML
	private Pane paneCol0Row3;

	@FXML
	private Pane paneCol1Row0;

	@FXML
	private Pane paneCol1Row1;

	@FXML
	private Pane paneCol1Row2;

	@FXML
	private Pane paneCol1Row3;

	@FXML
	private Pane paneCol2Row0;

	@FXML
	private Pane paneCol2Row1;

	@FXML
	private Pane paneCol2Row2;

	@FXML
	private Pane paneCol2Row3;

	@FXML
	private Pane paneCol3Row0;

	@FXML
	private Pane paneCol3Row1;

	@FXML
	private Pane paneCol3Row2;

	@FXML
	private Pane paneCol3Row3;

	@FXML
	private Pane paneCol4Row0;

	@FXML
	private Pane paneCol4Row1;

	@FXML
	private Pane paneCol4Row2;

	@FXML
	private Pane paneCol4Row3;

	@FXML
	private Pane paneCol5Row0;

	@FXML
	private Pane paneCol5Row1;

	@FXML
	private Pane paneCol5Row2;

	@FXML
	private Pane paneCol5Row3;

	@FXML
	private Button pauseButton;

	@FXML
	private ChoiceBox<SoundFileItem> soundPicker;

	@FXML
	private TextField timerStartValueTextField;

	private Timeline timeline;
	private java.time.Duration initialDuration;
	private java.time.Duration duration;
	private PaneLighter paneLighter;
	private boolean isTimerPaused;

	public void startTimer() {
		stopTimer();

		duration = createDuration();
		initialDuration = duration;

		timeline = new Timeline(
				new KeyFrame(Duration.millis(TICKING_DURATION_MILLIS), event -> updateDuration()));
		timeline.setCycleCount(
				StringToDurationCalculator.getMilliSeconds(timerStartValueTextField.getText()) /
						TICKING_DURATION_MILLIS + 1);
		timeline.setOnFinished(event -> playSound());
		timeline.playFromStart();
	}

	private java.time.Duration createDuration() {
		Instant now = Instant.now();
		return java.time.Duration.between(now, now.plus(
				StringToDurationCalculator.getMilliSeconds(timerStartValueTextField.getText()),
				ChronoUnit.MILLIS));
	}


	private void updateDuration() {
		updateTextDisplay(duration);
		this.duration = duration.minusMillis(TICKING_DURATION_MILLIS);
	}

	private void updateTextDisplayWithStartingDuration() {
		updateTextDisplay(createDuration());
	}

	private void updateTextDisplay(java.time.Duration duration) {
		timerDisplayText.setText(formatDuration(duration));
		getPaneLighter().light((int) duration.getSeconds(), duration.getNano() / 100_000_000);
	}

	private String formatDuration(java.time.Duration duration) {
		int nano = duration.getNano();
		long totalSeconds = duration.getSeconds();
		final long hours = totalSeconds / 3600;
		final long minutes = (totalSeconds % 3600) / 60;
		final int tenthSeconds = nano / 100_000_000;
		final long seconds = totalSeconds % 60;

		if (hours == 0) {
			if (minutes == 0) {
				if (seconds < 10) {
					return String.format("%d.%d", seconds, tenthSeconds);
				}

				return String.format("%02d.%d", seconds, tenthSeconds);
			}
			if (minutes < 10) {
				return String.format("%d:%02d.%d", minutes, seconds, tenthSeconds);
			}

			return String.format("%02d:%02d.%d", minutes, seconds, tenthSeconds);
		}

		return String.format("%d:%02d:%02d.%d", hours, minutes, seconds, tenthSeconds);
	}


	@FXML
	private void stopTimer() {
		if (timeline == null) {
			return;
		}
		timeline.stop();
		duration = createDuration();
		updateDuration();
		timeline = null;
		resetPauseButton();
	}

	private void playSound() {
		SoundFileItem selectedSoundItem = soundPicker.getSelectionModel().getSelectedItem();
		final String resource = "/resources/sounds/" + selectedSoundItem.getFileName();
		try {
			Media sound = new Media(Main.class.getResource(resource).toURI().toString());
			new MediaPlayer(sound).play();
		} catch (URISyntaxException e) {
			throw new NotFoundException(resource);
		}
	}

	private PaneLighter getPaneLighter() {
		if (paneLighter != null) {
			return paneLighter;
		}

		List<Pane> unityHoursList =
				Arrays.asList(paneCol0Row0, paneCol0Row1, paneCol0Row2, paneCol0Row3);
		List<Pane> decadeMinutesList =
				Arrays.asList(paneCol1Row0, paneCol1Row1, paneCol1Row2, paneCol1Row3);
		List<Pane> unityMinutesList =
				Arrays.asList(paneCol2Row0, paneCol2Row1, paneCol2Row2, paneCol2Row3);
		List<Pane> decadeSecondsList =
				Arrays.asList(paneCol3Row0, paneCol3Row1, paneCol3Row2, paneCol3Row3);
		List<Pane> unitySecondsList =
				Arrays.asList(paneCol4Row0, paneCol4Row1, paneCol4Row2, paneCol4Row3);
		List<Pane> tenthSecondsList =
				Arrays.asList(paneCol5Row0, paneCol5Row1, paneCol5Row2, paneCol5Row3);

		paneLighter = new PaneLighter(unityHoursList, decadeMinutesList, unityMinutesList,
				decadeSecondsList, unitySecondsList, tenthSecondsList);
		return paneLighter;
	}

	public void pauseTimer() {
		if (timeline == null) {
			return;
		}

		if (isTimerPaused) {
			final long initialDurationMillis = getDurationMillis(initialDuration);
			final long currentDurationMillis = getDurationMillis(duration);
			timeline.playFrom(Duration.millis(initialDurationMillis - currentDurationMillis));
			pauseButton.setText("Pause");
		} else {
			timeline.stop();
			pauseButton.setText("Resume");
		}
		isTimerPaused = !isTimerPaused;
	}

	private long getDurationMillis(java.time.Duration duration) {
		return duration.getNano() / 1_000_000 + duration.getSeconds() * 1000;
	}

	private void resetPauseButton() {
		isTimerPaused = false;
		pauseButton.setText("Pause");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createSoundChoiceBox();
		createTimeTextField();
		updateTextDisplayWithStartingDuration();
	}

	private void createTimeTextField() {

		StringConverter<String> stringFormatter = new StringConverter<>() {
			@Override
			public String toString(String object) {
				if (object == null) {
					return DEFAULT_TIME_INPUT;
				}

				final String parsedValue = parseValue(cleanTimeTextString(object));
				return StringToDurationCalculator.isValidStringDuration(parsedValue) ? parsedValue :
						DEFAULT_TIME_INPUT;
			}

			@Override
			public String fromString(String string) {
				return string;
			}
		};
		timerStartValueTextField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if (isNowFocused) {
				timerStartValueTextField.selectAll();
			} else {
				if (timeline == null || timeline.getStatus() == Animation.Status.STOPPED) {
					updateTextDisplayWithStartingDuration();
				}
			}
		});
		timerStartValueTextField.setTextFormatter(new TextFormatter<>(stringFormatter));
		timerStartValueTextField.setText("5:00.0");
	}

	private String cleanTimeTextString(String toClean) {
		if (toClean == null) {
			return "";
		}
		return removeStartingZero(toClean.replaceAll("[^\\d]", ""));
	}

	String parseValue(String toParse) {
		if (toParse == null || toParse.isEmpty()) {
			return "";
		}

		int indexToAddDelimiter = toParse.length() % 2 + 1;
		int indexToAddTenthDelimiter = toParse.length() - 1;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < toParse.length(); i++) {
			if (i == indexToAddDelimiter && i != indexToAddTenthDelimiter) {
				sb.append(":");
				indexToAddDelimiter += 2;
			}
			if (i == indexToAddTenthDelimiter) {
				sb.append(".");
			}

			sb.append(toParse.charAt(i));
		}

		String toReturn = sb.toString();
		return toReturn.startsWith(".") ? "0" + toReturn : toReturn;
	}

	String removeStartingZero(String toProceed) {
		Pattern pattern = Pattern.compile("^[0]*");
		Matcher matcher = pattern.matcher(toProceed);

		String cleaned = matcher.replaceFirst("");
		return cleaned.startsWith(".") ? "0" + cleaned : cleaned;
	}

	private void createSoundChoiceBox() {
		final SoundFileItem darkSouls3SoundItem =
				new SoundFileItem("Dark Souls III", "dark_souls3.mp3");
		ObservableList<SoundFileItem> soundsItems = FXCollections.observableArrayList();
		soundsItems.add(new SoundFileItem("Air horn", "air_horn.mp3"));
		soundsItems.add(new SoundFileItem("Bike horn", "bike_horn.mp3"));
		soundsItems.add(new SoundFileItem("Buzzer", "buzzer.mp3"));
		soundsItems.add(darkSouls3SoundItem);
		soundsItems.add(new SoundFileItem("Fire alarm", "fire_alarm.mp3"));
		soundsItems.add(new SoundFileItem("Fog horn", "fog_horn.mp3"));
		soundsItems.add(new SoundFileItem("Ship bell", "ship_bell.mp3"));
		soundsItems.add(new SoundFileItem("Temple bell", "temple_bell.mp3"));

		soundPicker.setItems(soundsItems);
		soundPicker.getSelectionModel().select(darkSouls3SoundItem);
	}
}
