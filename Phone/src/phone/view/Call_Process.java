package phone.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import phone.common.ApplicationMessage;
import phone.common.Message;
import phone.common.Security;
import phone.dao.Call_Dao;
import phone.dto.Phone;
import phone.dto.Phone_Call;

public class Call_Process implements Initializable {

	@FXML
	private Text minute;

	@FXML
	private Text seconds;
	
	@FXML
	private Text reach;
	
	@FXML
	private Text not;

	@FXML
	private Label display_number;

	private Thread thrd;
	private Phone_Call call;
	private Call_Dao dao;
	private Phone phone;
	private Consumer<Phone> consumer;
	private int sec = 0;
	private int min = 0;
	private int sec_count = 0;
	private int balance = Security.getPhone().getBalance();
	private int cost;

	private void secUp() {
		sec_count++;
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
		if (sec == 0)
			minUp();
	}

	private int totalSeconds() {
		return sec_count;
	}

	@SuppressWarnings("deprecation")
	@FXML
	void call_end() {
		Security.getPhone().setBalance(balance - totalSeconds() * 10);
		minute.getScene().getWindow().hide();
		Call.show();
		if (thrd.isAlive()) {
			thrd.stop();
		}
		Security.setDuration(totalSeconds());
		if (null == call) {
			call = new Phone_Call();
			call.setAmount(Security.amount(Security.getDuration()));
			call.setDuration(totalSeconds());
		}
		dao.save(call);

		int b = balance;
		int c = Security.amount(totalSeconds());
		phone.setBalance(c);
		consumer.accept(phone);
		Security.getPhone().setBalance(b);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Call_Dao.instance();
		run();
		display_number.setText(String.valueOf(Security.getNumber()));
	}

	public static void show(Phone phone, Consumer<Phone> consumer) {
		try {
			FXMLLoader loader = new FXMLLoader(Call_Process.class.getResource("Call_Process.fxml"));
			Parent root = loader.load();
			Call_Process controller = loader.getController();

			controller.phone = phone;
			controller.consumer = consumer;

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
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				try {
					boolean bo = true;
					while (bo) {
						output();
						cost = 10;
						balance = balance - cost;
						Security.getPhone().setBalance(balance);
						Thread.sleep(1000);
						if(Security.getPhone().getBalance() == 0) {
							bo = false;
							thrd.stop();
							throw new ApplicationMessage("SEE");
						}
					}
				} catch (Exception e) {
					Message.showMessage(AlertType.WARNING, e.getMessage());
				}
				not.setText("Not");
				reach.setText("Reachable");
			}
		});
		thrd.start();
	}
}
