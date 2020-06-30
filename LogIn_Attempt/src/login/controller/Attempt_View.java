package login.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.dao.AttemptDao;
import login.dto.User;

public class Attempt_View implements Initializable{
	
	@FXML
	private TableView<User> userView;
	
	@FXML
	private StackPane pane;
	
	private AttemptDao dao;
	
	public static void showView() {
		try {
			FXMLLoader loader = new FXMLLoader(Attempt_View.class.getResource("Attempt_View.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
			pane.setOpacity(0);
			fadeTransaction();
			
			dao = AttemptDao.getInstance();
			userView.getItems().clear();
			userView.getItems().add(dao.save());
	}
	
	private void fadeTransaction() {
			FadeTransition fade = new FadeTransition(Duration.millis(1000));
			fade.setNode(pane);
			fade.setFromValue(0);
			fade.setToValue(1);
			fade.play();
	}

}
