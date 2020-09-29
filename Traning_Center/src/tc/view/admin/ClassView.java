package tc.view.admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.common.Common;
import tc.dao.Class_Student_Dao;
import tc.dto.Class_Student;

public class ClassView implements Initializable,Consumer<Class_Student>{

    @FXML
    private Label course;

    @FXML
    private Label start_date;

    @FXML
    private Label end_date;
    
    @FXML
    private SVGPath add_new;
    
    @FXML
    private SVGPath back;

    @FXML
    private TableView<Class_Student> class_student_table;
    
    @FXML
    private Button class_teachers;
	
	private Class_Student_Dao class_student_dao;

	private void close() {
		add_new.getScene().getWindow().hide();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 class_student_dao = Class_Student_Dao.getInstance();
		 
		 class_teachers.setOnMouseClicked(e -> {
			 Class_Teacher_View.showView();
			 close();
		 });
		 add_new.setOnMouseClicked(e -> {
			 Class_Student_Detail.showView(null, this);
			 close();
		 });
		 
		 back.setOnMouseClicked(e -> {
			 ClassList.showView();
			 close();
		 });
		 
		 course.setText(Common.getClass_name().getCourse().getName());
		 start_date.setText(Common.getClass_name().getStart_date().toString());
		 end_date.setText(Common.getClass_name().getEnd_date().toString());
		
		 search();
	}
	
	public static void showView() {
		try {
			FXMLLoader loader = new FXMLLoader(ClassView.class.getResource("ClassView.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void accept(Class_Student t) {
		class_student_dao.create(t);
		search();
	}
	
	private void search() {
		StudentSearchService service = new StudentSearchService();

		service.setOnSucceeded(e -> {
			class_student_table.getItems().clear();
			class_student_table.getItems().addAll(service.getValue());
		});
		service.start();
	}

	private class StudentSearchService extends Service<List<Class_Student>> {
		@Override
		protected Task<List<Class_Student>> createTask() {
			return new Task<List<Class_Student>>() {
				@Override
				protected List<Class_Student> call() throws Exception {
					return class_student_dao.searchByClass(Common.getClass_name());

				}
			};
		}

	}
}
