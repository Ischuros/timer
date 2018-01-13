package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Font.loadFont(
				Main.class.getResource("/resources/fonts/lmroman12-regular.otf").toURI().toURL
						().getPath(),
				10);

		Parent root = FXMLLoader.load(getClass().getResource("timer.fxml"));
		primaryStage.setTitle("Timer");
		primaryStage.setScene(new Scene(root, 600, 100));
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);
	}
}
