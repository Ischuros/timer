package timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		final String timerIconPath =
				Objects.requireNonNull(getClass().getResource("/images/timer.ico"),
						"Icon path " + "must not be null.").toURI().toString();
		final String fontPath =
				Objects.requireNonNull(getClass().getResource("/fonts/lmroman12-regular.otf"),
						"Font path must not be null.").toURI().toURL().getPath();
		final URL fxmlPath = Objects.requireNonNull(getClass().getResource("/view/timer.fxml"),
				"FXML path must not be null");
		Font.loadFont(fontPath, 10);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlPath);
		Parent root = loader.load();
		primaryStage.setTitle("Timer");
		primaryStage.setScene(new Scene(root, 700, 150));
		primaryStage.getIcons().add(new Image(timerIconPath));
		primaryStage.show();
		primaryStage.setResizable(false);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
