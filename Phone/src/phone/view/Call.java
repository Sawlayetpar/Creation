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
import javafx.scene.input.KeyCode;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import phone.common.Common;
import phone.common.Security;
import phone.dao.Dao;
import phone.dto.Phone;

public class Call implements Initializable,Consumer<Phone> {

	@FXML
	private Button one;

	@FXML
	private Button two;

	@FXML
	private Button three;

	@FXML
	private Button four;

	@FXML
	private Button five;

	@FXML
	private Button six;

	@FXML
	private Button seven;

	@FXML
	private Button eight;

	@FXML
	private Button nine;

	@FXML
	private Button zero;

	@FXML
	private Button hash;

	@FXML
	private Button star;

	@FXML
	private TextField screen;

	@FXML
	private SVGPath exit;

	private StringBuilder builder = new StringBuilder();
	private Dao dao;

	private void hide() {
		screen.getScene().getWindow().hide();
	}
	
	@FXML
    void history() {
		hide();
		History.show();
    }
	@FXML
	void call() {
		if (Common.validNumber(screen.getText())) {
			String num = screen.getText();
			Security.setNumber(num);
			if (Security.getPhone().getBalance() > 0) {
				Call_Process.show(Security.getPhone(),this);
				hide();
			} else {
				screen.setText("Sorry Your Balnce Is Low");
			}

		} else {
			screen.setText("invalid number");
		}

	}

	@FXML
	void delete() {
		if (builder.length() > 0) {
			int i = 0;
			while (i < builder.length() - 1) {
				i++;
			}
			builder.deleteCharAt(i);
			screen.setText(builder.toString());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Dao.getInstance();

		exit.setOnMouseClicked(e -> {
			hide();
			Home.show();
		});

		one.setOnAction(e -> {
			screen.setText(screen.getText() + "1");
			builder.append(1);
		});
		two.setOnAction(e -> {
			screen.setText(screen.getText() + "2");
			builder.append(2);
		});
		three.setOnAction(e -> {
			screen.setText(screen.getText() + "3");
			builder.append(3);
		});
		four.setOnAction(e -> {
			screen.setText(screen.getText() + "4");
			builder.append(4);
		});
		five.setOnAction(e -> {
			screen.setText(screen.getText() + "5");
			builder.append(5);
		});
		six.setOnAction(e -> {
			screen.setText(screen.getText() + "6");
			builder.append(6);
		});
		seven.setOnAction(e -> {
			screen.setText(screen.getText() + "7");
			builder.append(7);
		});
		eight.setOnAction(e -> {
			screen.setText(screen.getText() + "8");
			builder.append(8);
		});
		nine.setOnAction(e -> {
			screen.setText(screen.getText() + "9");
			builder.append(9);
		});
		zero.setOnAction(e -> {
			screen.setText(screen.getText() + "0");
			builder.append(0);
		});
		star.setOnAction(e -> {
			screen.setText(screen.getText() + "*");
			builder.append("*");
		});
		hash.setOnAction(e -> {
			screen.setText(screen.getText() + "#");
			builder.append("#");
		});

		screen.setEditable(false);
		screen.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				call();
			}
		});

	}

	public static void show() {
		try {
			Parent root = FXMLLoader.load(Call.class.getResource("Call.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.NONE);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void accept(Phone t) {
		dao.call(t);
	}
}
