package phone.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import phone.dao.Dao;
import phone.dto.Phone;

public class Home implements Initializable,Consumer<Phone>{

	@FXML
	private SVGPath exit;

	@FXML
	private Label balance;

	@FXML
	private Label number;
	
	@FXML
	private Label code;
	private Dao dao;
	
	private void hide() {
		code.getScene().getWindow().hide();
	}
	@FXML
	void call() {
		Call.show();
		hide();
	}

	@FXML
	void topup() {
		Number.show(Security.getPhone(),this);
		hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Dao.getInstance();
		balance.setText(String.valueOf(Security.getPhone().getBalance()));
		number.setText(Security.getPhone().getPhone_no());
		code.setText(Security.getPhone().getCode());
		exit.setOnMouseClicked(e -> number.getScene().getWindow().hide());
	}
	
	public static void show() {
		try {
			Parent root = FXMLLoader.load(Home.class.getResource("Home.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void accept(Phone t) {
		dao.topup(t);
	}
	
}
