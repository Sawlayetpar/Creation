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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.common.CommonUtil;
import tc.dao.Student_Dao;
import tc.dto.Student;

public class StudentList implements Initializable, Consumer<Student> {

	@FXML
	private SVGPath cancle;

	@FXML
	private TextField id;

	@FXML
	private SVGPath add_new;

	@FXML
	private TableView<Student> student_table;

	@FXML
	private Label number;

	private Student_Dao dao;

	public static void showView() {
		try {
			Parent root = FXMLLoader.load(StudentList.class.getResource("StudentList.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		add_new.setOnMouseClicked(e -> {
			StudentDetail.showView(null, this);
			id.getScene().getWindow().hide();
		});

		cancle.setOnMouseClicked(e -> {
			id.getScene().getWindow().hide();
			Home.showView();
		});

		dao = Student_Dao.getInstance();
		id.textProperty().addListener((a, b, c) -> search());
		search();

		MenuItem detail = new MenuItem("view detail");
		detail.setOnAction(e -> {
			StudentDetail.showView(student_table.getSelectionModel().getSelectedItem(), this::accept);
			id.getScene().getWindow().hide();
		});
		student_table.setContextMenu(new ContextMenu(detail));
		
		number.setText(String.valueOf(dao.getCount()));

	}

	@Override
	public void accept(Student t) {
		dao.create(t);
		search();
	}

	private void search() {
		StudentSearchService service = new StudentSearchService();

		service.setOnSucceeded(e -> {
			student_table.getItems().clear();
			student_table.getItems().addAll(service.getValue());
		});
		service.start();
	}

	private class StudentSearchService extends Service<List<Student>> {
		@Override
		protected Task<List<Student>> createTask() {
			return new Task<List<Student>>() {
				@Override
				protected List<Student> call() throws Exception {
					return dao.findById(CommonUtil.convertStringToInt(id.getText()));

				}
			};
		}

	}
}
