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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.dao.Teacher_Dao;
import tc.dto.Class_Teacher;
import tc.dto.Teacher;

public class Class_Teacher_Detail implements Initializable {

	@FXML
	private VBox back;

	@FXML
	private ComboBox<Teacher> id;

	@FXML
	private CheckBox incharge;

	@FXML
	private Button save;

	@FXML
	private Button cancle;

	private Class_Teacher class_teacher;
	private Consumer<Class_Teacher> consumer;
	private Teacher_Dao dao;

	public static void showView(Class_Teacher class_teacher, Consumer<Class_Teacher> consumer) {
		try {
			FXMLLoader loader = new FXMLLoader(Class_Teacher_Detail.class.getResource("Class_Teacher_Detail.fxml"));
			Parent root = loader.load();
			Class_Teacher_Detail controller = loader.getController();

			controller.class_teacher = class_teacher;
			controller.consumer = consumer;

			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Teacher_Dao.getInstance();
		id.getItems().addAll(dao.findById(0));

		save.setOnMouseClicked(e -> {
			try {
				createItemFromViewData();
				consumer.accept(class_teacher);
				id.getScene().getWindow().hide();
				Class_Teacher_View.showView();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		back.setOnMouseClicked(e -> {
			id.getScene().getWindow().hide();
			ClassList.showView();
		});

		cancle.setOnMouseClicked(e -> {
			id.getScene().getWindow().hide();
			ClassList.showView();
		});
	}

	private void createItemFromViewData() {
		if (null == class_teacher)
			class_teacher = new Class_Teacher();

		class_teacher.setTeacher_id(id.getValue());
		class_teacher.setIncharge(incharge.isSelected());
	}
}
