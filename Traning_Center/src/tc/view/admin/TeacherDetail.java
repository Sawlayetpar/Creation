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
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.dto.Teacher;

public class TeacherDetail implements Initializable{

	@FXML
	private SVGPath back;

	@FXML
	private TextField name;

	@FXML
	private Button cancle;

	@FXML
	private Button save;

	@FXML
	private TextField phone;
	
	private Teacher teacher;
	private Consumer<Teacher> handler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cancle.setOnMouseClicked(e -> {
			name.getScene().getWindow().hide();
			TeacherList.showView();
		});
		
		back.setOnMouseClicked(e -> {
			name.getScene().getWindow().hide();
			TeacherList.showView();
		});
		
		save.setOnMouseClicked(e -> {
			try {
				createItemFromViewData();
				handler.accept(teacher);
				name.getScene().getWindow().hide();
				TeacherList.showView();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void createItemFromViewData() {
		if(null == teacher) {
			teacher = new Teacher();
		}
		teacher.setName(name.getText());
		teacher.setPhone(phone.getText());
	}

	public static void showView(Teacher teacher,Consumer<Teacher> handler) {

		try {
			FXMLLoader loader = new FXMLLoader(TeacherDetail.class.getResource("TeacherDetail.fxml"));
			Parent root = loader.load();
			TeacherDetail controller = loader.getController();
			boolean isNew = null == teacher;

			controller.handler = handler;
			controller.teacher = teacher;

			if (!isNew) {
				controller.name.setEditable(false);
				controller.phone.setEditable(false);
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
		name.setText(teacher.getName());
		phone.setText(teacher.getPhone());
	}
}
