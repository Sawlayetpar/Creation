package phone.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import phone.common.Security;

public class Home implements Initializable{

	@FXML
	private SVGPath exit;

	@FXML
	private Label balance;

	@FXML
	private Label number;
	
	@FXML
	void call() {
		Call.show();
	}

	@FXML
	void topup() {
		TopUp.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		balance.setText(Security.getBalance().getBalance());
		number.setText(Security.getNumber().getPhone_no());
		exit.setOnMouseClicked(e -> number.getScene().getWindow().hide());
	}
	
	public static void show() {
		try {
			Parent root = FXMLLoader.load(TopUp.class.getResource("Home.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
