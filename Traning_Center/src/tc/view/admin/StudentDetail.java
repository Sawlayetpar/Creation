package tc.view.admin;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.dto.Address;
import tc.dto.Gender;
import tc.dto.Student;

public class StudentDetail implements Initializable {

	@FXML
	private SVGPath back;

	@FXML
	private TextField name;

	@FXML
	private TextField street;

	@FXML
	private TextField township;

	@FXML
	private TextField city;

	@FXML
	private ComboBox<Gender> gender;

	@FXML
	private Button cancle;

	@FXML
	private Button save;

	@FXML
	private TextField phone;

	@FXML
	private TextField email;

	private Consumer<Student> handler;
	private Student student;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gender.setValue(null);
		gender.getItems().addAll(Gender.values());
		gender.setValue(Gender.Female);
		

		cancle.setOnMouseClicked(e -> {
			name.getScene().getWindow().hide();
			StudentList.showView();
		});

		back.setOnMouseClicked(e -> {
			name.getScene().getWindow().hide();
			StudentList.showView();
		});

		save.setOnMouseClicked(e -> {
			try {
				createItemFromViewData();
				handler.accept(student);
				name.getScene().getWindow().hide();
				StudentList.showView();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

	}

	private void createItemFromViewData() {
		if (null == student) {
			student = new Student();
		}

		student.setName(name.getText());
		student.setPhone(phone.getText());
		student.setEmail(email.getText());

		Address address = new Address();
		address.setCity(city.getText());
		address.setStreet(street.getText());
		address.setTownship(township.getText());

		student.setAddress(address);
		student.setGender(gender.getValue());
	}

	public static void showView(Student std, Consumer<Student> handler) {
		try {
			FXMLLoader loader = new FXMLLoader(StudentDetail.class.getResource("StudentDetail.fxml"));
			Parent root = loader.load();
			StudentDetail controller = loader.getController();
			boolean isNew = null == std;

			controller.handler = handler;
			controller.student = std;

			if (!isNew) {
				controller.name.setEditable(false);
				controller.phone.setEditable(false);
				controller.email.setEditable(false);
				controller.city.setEditable(false);
				controller.township.setEditable(false);
				controller.street.setEditable(false);
				controller.gender.setEditable(false);
				controller.setDataToView();
			}

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void setDataToView() {
		name.setText(student.getName());
		phone.setText(student.getPhone());
		email.setText(student.getEmail());
		gender.setValue(student.getGender());
		city.setText(student.getAddress().getCity());
		township.setText(student.getAddress().getTownship());
		street.setText(student.getAddress().getStreet());
	}
}
