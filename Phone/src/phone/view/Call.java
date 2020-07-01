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
import javafx.scene.input.KeyCode;
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
		System.out.println("Call Processing");
	}

	@FXML
	void delete() {
		screen.clear();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exit.setOnMouseClicked(e -> screen.getScene().getWindow().hide());
		
		one.setOnAction(e -> screen.setText(screen.getText()+ "1"));
		two.setOnAction(e -> screen.setText(screen.getText()+ "2"));
		three.setOnAction(e -> screen.setText(screen.getText()+ "3"));
		four.setOnAction(e -> screen.setText(screen.getText()+ "4"));
		five.setOnAction(e -> screen.setText(screen.getText()+ "5"));
		six.setOnAction(e -> screen.setText(screen.getText()+ "6"));
		seven.setOnAction(e -> screen.setText(screen.getText()+ "7"));
		eight.setOnAction(e -> screen.setText(screen.getText()+ "8"));
		nine.setOnAction(e -> screen.setText(screen.getText()+ "9"));
		zero.setOnAction(e -> screen.setText(screen.getText()+ "0"));
		star.setOnAction(e -> screen.setText(screen.getText()+ "*"));
		hash.setOnAction(e -> screen.setText(screen.getText()+ "#"));
		
		screen.setEditable(false);
		screen.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				 call();
			}
		});
		
		
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
