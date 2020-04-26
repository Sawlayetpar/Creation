package com.ld.views;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DellLaptop implements Initializable {
	
	@FXML
	private SVGPath close;
	
	@FXML
	private Label msg;

	@FXML
	void ok() {
		 msg.getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		close.setOnMouseClicked(e -> Platform.exit());
	}
	
	public static void showView() {
		try {
			FXMLLoader loader = new FXMLLoader(DellLaptop.class.getResource("DellLaptop.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
