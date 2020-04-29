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
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Call implements Initializable {

	@FXML
	private Button one;

	@FXML
	private Button two;

	@FXML
	private Button three;

	@FXML
	private Button four;

	@FXML
	private Button five;

	@FXML
	private Button six;

	@FXML
	private Button seven;

	@FXML
	private Button eight;

	@FXML
	private Button nine;

	@FXML
	private Button zero;

	@FXML
	private Button hash;

	@FXML
	private Button star;
	
	@FXML
	private TextField screen;
	
	@FXML
	private SVGPath exit;
	
	@FXML
	void call() {

	}

	@FXML
	void delete() {

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exit.setOnMouseClicked(e -> screen.getScene().getWindow().hide());
		
		one.setText("1");
		two.setText("2");
		three.setText("3");
		four.setText("4");
		five.setText("5");
		six.setText("6");
		seven.setText("7");
		eight.setText("8");
		nine.setText("9");
		zero.setText("0");
		star.setText("*");
		hash.setText("#");
		
	}
	
	public static void show() {
		try {
			Parent root =FXMLLoader.load(Call.class.getResource("Call.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.NONE);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
