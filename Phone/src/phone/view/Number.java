package phone.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import phone.common.Security;
import phone.dto.Phone;

public class Number implements Initializable {

	@FXML
	private TextField number;
	
	@FXML
	private TextField amount;

	@FXML
	private Button cancle;

	private Phone phone;
	private Consumer<Phone> addhander;
	private int c_bal = Security.getPhone().getBalance();
	
	private void disAomunt() {
		int bal = Integer.parseInt(amount.getText());
		Security.getPhone().setBalance(bal + c_bal);
	}

	@FXML
	void confirm() {

		try {
			topUpNumber();
			addhander.accept(phone);
			disAomunt();
			cancle.getScene().getWindow().hide();
			Home.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cancle.setOnMouseClicked(e -> {
			number.getScene().getWindow().hide();
			Home.show();
		});
		number.setEditable(false);
		number.setText(Security.getPhone().getPhone_no());
	}

	public static void show(Phone phone, Consumer<Phone> handler) {
		try {
			FXMLLoader loader = new FXMLLoader(Number.class.getResource("Number.fxml"));
			Parent root = loader.load();
			Number controller = loader.getController();

			controller.phone = phone;
			controller.addhander = handler;

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void topUpNumber() {
		phone.setBalance(Integer.parseInt(amount.getText()));
	}
}
