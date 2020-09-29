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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.common.Common;
import tc.common.CommonUtil;
import tc.dao.Student_Dao;
import tc.dto.Class_Student;
import tc.dto.Student;

public class Class_Student_Detail implements Initializable{

	@FXML
	private SVGPath back;

	@FXML
	private Button cancle;

	@FXML
	private Button save;

	@FXML
	private Label fees;

	@FXML
	private ComboBox<Student> id;

	@FXML
	private TextField paid;
	
	private Class_Student class_student;
	private Consumer<Class_Student> consumer;
	private Student_Dao student_dao;
	
	private void close() {
		back.getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		student_dao = Student_Dao.getInstance();
		
		back.setOnMouseClicked(e -> {
			close();
			ClassView.showView();
		});
		
		cancle.setOnMouseClicked(e -> {
			close();
			ClassView.showView();
		});
		
		save.setOnMouseClicked(e -> {
			try {
				createItemFromViewData();
				consumer.accept(class_student);
				close();
				ClassView.showView();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		fees.setText(String.valueOf(Common.getClass_name().getCourse().getFees()));
		id.setValue(null);
		id.getItems().addAll(student_dao.findById(0));
	}
	
	public static void showView(Class_Student class_student,Consumer<Class_Student>consumer) {
		try {
			FXMLLoader loader = new FXMLLoader(Class_Student_Detail.class.getResource("Class_Student_Detail.fxml"));
			Parent root = loader.load();
			Class_Student_Detail controller = loader.getController();
			boolean isNew = null == class_student;
			
			controller.class_student = class_student;
			controller.consumer = consumer;
			
			
			if(!isNew) {
//				controller.fees.setEditable(false);
				controller.id.setEditable(false);
				controller.setDataToView();
			}
				
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createItemFromViewData() {
		if(null == class_student) {
			class_student = new Class_Student();
		}
		
		class_student.setStudent_id(id.getValue());
		class_student.setPaid(CommonUtil.convertStringToInt(paid.getText()));
	}

	private void setDataToView() {
		id.setValue(class_student.getStudent_id());
		paid.setText(String.valueOf(class_student.getPaid()));
	}

}
