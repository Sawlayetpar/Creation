package phone.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Number implements Initializable{

	@FXML
	private TextField number;

	@FXML
	private Button cancle;

	@FXML
	void confirm() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancle.setOnMouseClicked(e -> number.getScene().getWindow().hide());
	}
	
	public static void show() {
		try {
			Parent root = FXMLLoader.load(Number.class.getResource("Number.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
