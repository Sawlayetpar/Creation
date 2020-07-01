package phone.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
		Security.setNumber(phone);
		Security.setBalance(phone);
		Home.show();
		number.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancle.setOnMouseClicked(e -> Platform.exit());
		dao = Dao.getInstance();
	}

}
