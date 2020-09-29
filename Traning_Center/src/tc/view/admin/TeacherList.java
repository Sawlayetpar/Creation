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
import tc.dao.Teacher_Dao;
import tc.dto.Teacher;

public class TeacherList implements Initializable, Consumer<Teacher> {

	@FXML
	private SVGPath cancle;

	@FXML
	private TextField id;

	@FXML
	private SVGPath add_new;

	@FXML
	private TableView<Teacher> teacher_table;

	@FXML
	private Label number;

	private Teacher_Dao dao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		add_new.setOnMouseClicked(e -> {
			TeacherDetail.showView(null, this);
			id.getScene().getWindow().hide();
		});

		cancle.setOnMouseClicked(e -> {
			id.getScene().getWindow().hide();
			Home.showView();
		});

		dao = Teacher_Dao.getInstance();
		id.textProperty().addListener((a, b, c) -> search());
		search();

		MenuItem detail = new MenuItem("view detail");
		detail.setOnAction(e -> {
			TeacherDetail.showView(teacher_table.getSelectionModel().getSelectedItem(), this);
			id.getScene().getWindow().hide();
		});
		teacher_table.setContextMenu(new ContextMenu(detail));

		number.setText(String.valueOf(dao.getCount()));

	}

	@Override
	public void accept(Teacher t) {
		dao.create(t);
		search();
	}

	private void search() {
		TeacherSearchService service = new TeacherSearchService();

		service.setOnSucceeded(e -> {
			teacher_table.getItems().clear();
			teacher_table.getItems().addAll(service.getValue());
		});
		service.start();
	}

	private class TeacherSearchService extends Service<List<Teacher>> {
		@Override
		protected Task<List<Teacher>> createTask() {
			return new Task<List<Teacher>>() {
				@Override
				protected List<Teacher> call() throws Exception {
					return dao.findById(CommonUtil.convertStringToInt(id.getText()));

				}
			};
		}

	}

	public static void showView() {
		try {
			Parent root = FXMLLoader.load(TeacherList.class.getResource("TeacherList.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
