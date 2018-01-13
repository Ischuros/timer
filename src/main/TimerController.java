package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;


public class TimerController {

	private static final int TICKING_DURATION_MILLIS = 100;
	private static final int TIME_AMOUNT_MILLIS = 5*60*1000;
	private static final String DEFAULT_SOUND_FILE = "dark_souls3.mp3";

	@FXML
	public Text timerText;

	@FXML
	public Pane paneCol0Row0;

	@FXML
	public Pane paneCol0Row1;

	@FXML
	public Pane paneCol0Row2;

	@FXML
	public Pane paneCol0Row3;

	@FXML
	public Pane paneCol1Row0;

	@FXML
	public Pane paneCol1Row1;

	@FXML
	public Pane paneCol1Row2;

	@FXML
	public Pane paneCol1Row3;

	@FXML
	public Pane paneCol2Row0;

	@FXML
	public Pane paneCol2Row1;

	@FXML
	public Pane paneCol2Row2;

	@FXML
	public Pane paneCol2Row3;

	@FXML
	public Pane paneCol3Row0;

	@FXML
	public Pane paneCol3Row1;

	@FXML
	public Pane paneCol3Row2;

	@FXML
	public Pane paneCol3Row3;

	@FXML
	public Pane paneCol4Row0;

	@FXML
	public Pane paneCol4Row1;

	@FXML
	public Pane paneCol4Row2;

	@FXML
	public Pane paneCol4Row3;

	@FXML
	public Pane paneCol5Row0;

	@FXML
	public Pane paneCol5Row1;

	@FXML
	public Pane paneCol5Row2;

	@FXML
	public Pane paneCol5Row3;

	private Timeline timeline;
	private java.time.Duration duration;
	private PaneLighter paneLighter;

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
		getPaneLighter().light((int)duration.getSeconds(), duration.getNano()/ 100_000_000);
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
}
