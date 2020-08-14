package ng.view;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login_View implements Initializable{

	@FXML
    private TextField code;

    @FXML
    private Button cancle;

    @FXML
    private Button generate;
    
    @FXML
    private Label generate_code;
    
    private int number;

    @FXML
    void login() {
    	if (Integer.parseInt(code.getText()) == number) {
    		Success_View.showView();
    		code.getScene().getWindow().hide();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancle.setOnMouseClicked(e -> Platform.exit());
		generate.setOnMouseClicked(e -> {
			generate_number();
			generate_code.setText(String.valueOf(number));
		});
		
	}
	
	private int generate_number() {
		number = new Random().nextInt(9999);
		if (number < 1000) {
			number = 6813;
			return number;
		}
		else {
			return number;
		}
	}

}
