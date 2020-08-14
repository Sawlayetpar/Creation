package ng.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Success_View implements Initializable {

	@FXML
	private VBox vbox;

	@FXML
	private Label decision;

	@FXML
	private Button btn;

	public static void showView() {
		try {
			FXMLLoader loader = new FXMLLoader(Success_View.class.getResource("Success_View.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FadeTransition transition = new FadeTransition(Duration.millis(2000),decision);
		transition.setFromValue(0);
		transition.setToValue(3);
		transition.play();
//		RotateTransition rotateTransition = new RotateTransition(Duration.millis(2000));
//		rotateTransition.setNode(vbox);
//		rotateTransition.setByAngle(180);
//		rotateTransition.setCycleCount(2);
//		rotateTransition.play();
		btn.setOnMouseClicked(e -> Platform.exit());
	}
}
