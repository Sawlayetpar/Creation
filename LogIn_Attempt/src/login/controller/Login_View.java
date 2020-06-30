package login.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.common.Security;
import login.dao.UserDao;
import login.dto.User;

public class Login_View implements Initializable {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	private UserDao dao;
	

	@FXML
	void granted() {
		try {
			User user = dao.login(username.getText(), password.getText());
			
			Security.setLoginUser(user);
			Attempt_View.showView();
			password.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void denied() {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = UserDao.getInstance();
	}
}
