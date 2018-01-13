package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class TimerController {

	private static final int TICKING_DURATION_MILLIS = 100;
	private static final int TIME_AMOUNT_MILLIS = 1000;
	private static final String DEFAULT_SOUND_FILE = "dark_souls3.mp3";

	@FXML
	public Text timerText;

	private Timeline timeline;
	private java.time.Duration duration;

	public void startTimer() {
		stopTimer();
		Instant now = Instant.now();
		duration = java.time.Duration.between(now, now.plus(TIME_AMOUNT_MILLIS, ChronoUnit.MILLIS));

		timeline = new Timeline(
				new KeyFrame(Duration.millis(TICKING_DURATION_MILLIS), event -> updateDuration()));
		timeline.setCycleCount(TIME_AMOUNT_MILLIS / TICKING_DURATION_MILLIS + 1);
		timeline.setOnFinished(event -> playSound());
		timeline.play();
	}

	private void updateDuration() {
		timerText.setText(formatDuration(duration));
		this.duration = duration.minusMillis(TICKING_DURATION_MILLIS);
	}

	private String formatDuration(java.time.Duration duration) {
		int nano = duration.getNano();
		long seconds = duration.getSeconds();
		return String
				.format("%02d:%02d:%02d:%d", seconds / 3600, (seconds % 3600) / 60, seconds % 60,
						nano / 100_000_000);
	}

	public void stopTimer() {
		if (timeline == null) {
			return;
		}
		timeline.stop();
	}

	private void playSound() {
		final String resource = "/resources/sounds/" + DEFAULT_SOUND_FILE;
		try {
			Media sound = new Media(Main.class.getResource(resource).toURI().toString());
			new MediaPlayer(sound).play();
		} catch (URISyntaxException e) {
			throw new NotFoundException(resource);
		}
	}
}
