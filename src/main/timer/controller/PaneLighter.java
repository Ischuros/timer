package main.timer.controller;

import javafx.scene.layout.Pane;

import java.util.List;

class PaneLighter {

	private final List<Pane> unityHoursList;
	private final List<Pane> decadeMinutesList;
	private final List<Pane> unityMinutesList;
	private final List<Pane> decadeSecondsList;
	private final List<Pane> unitySecondsList;
	private final List<Pane> tenthSecondsList;

	PaneLighter(List<Pane> unityHoursList, List<Pane> decadeMinutesList,
			List<Pane> unityMinutesList, List<Pane> decadeSecondsList, List<Pane> unitySecondsList,
			List<Pane> tenthSecondsList) {
		this.unityHoursList = unityHoursList;
		this.decadeMinutesList = decadeMinutesList;
		this.unityMinutesList = unityMinutesList;
		this.decadeSecondsList = decadeSecondsList;
		this.unitySecondsList = unitySecondsList;
		this.tenthSecondsList = tenthSecondsList;
	}

	public void light(int seconds, int tenthSeconds) {
		BinaryManager binaryManager = new BinaryManager(seconds, tenthSeconds);

		lightOneColumn(unityHoursList, binaryManager.getListOfBoolean(TimeUnit.HOURS_UNITY));
		lightOneColumn(decadeMinutesList, binaryManager.getListOfBoolean(TimeUnit.MINUTES_DECADE));
		lightOneColumn(unityMinutesList, binaryManager.getListOfBoolean(TimeUnit.MINUTES_UNITY));
		lightOneColumn(decadeSecondsList, binaryManager.getListOfBoolean(TimeUnit.SECONDES_DECADE));
		lightOneColumn(unitySecondsList, binaryManager.getListOfBoolean(TimeUnit.SECONDS_UNITY));
		lightOneColumn(tenthSecondsList, binaryManager.getListOfBoolean(TimeUnit.TENTH_SECONDES));
	}

	private void lightOneColumn(List<Pane> list, List<Boolean> toLight) {
		for(int i=0; i<list.size(); i++) {
			Pane pane = list.get(i);
			String styleToAdd = toLight.get(i) ? "panel-activated" : "panel-deactivated";
			pane.getStyleClass().clear();
			pane.getStyleClass().add(styleToAdd);
		}
	}
}
