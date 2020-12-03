package phone.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import phone.common.ApplicationMessage;
import phone.common.Message;
import phone.common.Security;
import phone.dao.Dao;
import phone.dto.Phone;

public class Login implements Initializable {

	@FXML
	private TextField number;

	@FXML
	private Button cancle;

	private Dao dao;
	@FXML
	void submit() {
		try {
		Phone phone = dao.login(number.getText());
		if(null == phone) {
			throw new ApplicationMessage("number not exist");
		}
		Security.setPhone(phone);
		Home.show();
		number.getScene().getWindow().hide();
		} catch (Exception e) {
			Message.showMessage(AlertType.WARNING, e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancle.setOnMouseClicked(e -> Platform.exit());
		dao = Dao.getInstance();
	}

}
