package phone.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import phone.common.Security;

public class Call_Process implements Initializable {

	@FXML
	private Text minute;

	@FXML
	private Text seconds;

	@FXML
	private Label display_number;

	private Thread thrd;
	private int sec = 0;
	private int min = 0;

	private void secUp() {
		sec++;
		if (sec == 60)
			sec = 0;
		seconds.setText(String.valueOf(sec));
	}

	private void minUp() {
		min++;
		minute.setText(String.valueOf(min));
	}

	private void output() {
		secUp();
		if(sec == 0) {
			minUp();
		}
	}

	@SuppressWarnings("deprecation")
	@FXML
	void call_end() {
		minute.getScene().getWindow().hide();
		Call.show();
		if (thrd.isAlive()) {
			thrd.stop();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		run();
		thrd.start();
		display_number.setText(String.valueOf(Security.getNumber()));
	}

	public static void show() {
		try {
			Parent root = FXMLLoader.load(Call_Process.class.getResource("Call_Process.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.NONE);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run() {
		thrd = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						output();
						System.out.print(sec);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
