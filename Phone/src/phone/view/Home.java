package phone.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;

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
		exit.setOnMouseClicked(e -> Platform.exit());
//		number.setText(Security.getPhoneNo().getPhone_no());
//		balance.setText(Security.getPhoneBal().getBalance());
	}
    
}
