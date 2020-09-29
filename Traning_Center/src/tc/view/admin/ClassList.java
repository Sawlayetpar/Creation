package tc.view.admin;

import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.common.Common;
import tc.dao.Class_Dao;
import tc.dao.Course_Dao;
import tc.dto.Class;
import tc.dto.Course;

public class ClassList implements Initializable, Consumer<Class> {

	@FXML
	private SVGPath cancle;

	@FXML
	private SVGPath add_new;

	@FXML
	private ComboBox<Course> course_search;

	@FXML
	private TableView<Class> class_table;

	@FXML
	private Label number;

	private Class_Dao dao;
	private Course_Dao course_dao;

	public static void showView() {

		try {
			Parent root = FXMLLoader.load(ClassList.class.getResource("ClassList.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Class_Dao.getInstance();
		course_dao = Course_Dao.getIntstance();

		course_search.getItems().addAll(course_dao.findByName(null));

		add_new.setOnMouseClicked(e -> {
			ClassDetail.showView(null, this);
			number.getScene().getWindow().hide();
		});

		cancle.setOnMouseClicked(e -> {
			number.getScene().getWindow().hide();
			Home.showView();
		});

		course_search.setValue(null);
		course_search.valueProperty().addListener((a, b, c) -> search());
		search();

		MenuItem detail = new MenuItem("Detail");
		class_table.setContextMenu(new ContextMenu(detail));
		detail.setOnAction(e -> {
			Class classes = new Class();
			classes = class_table.getSelectionModel().getSelectedItem();
			Common.setClass_name(classes);
			ClassView.showView();
			number.getScene().getWindow().hide();
		});
	}

	@Override
	public void accept(Class t) {
		dao.create(t);
		search();
	}

	private void search() {

		SearchService service = new SearchService();

		service.setOnSucceeded(e -> {
			class_table.getItems().clear();
			class_table.getItems().addAll(service.getValue());
		});
		service.start();

	}

	private class SearchService extends Service<List<Class>> {
		@Override
		protected Task<List<Class>> createTask() {
			return new Task<List<Class>>() {
				@Override
				protected List<Class> call() throws Exception {
					return dao.findByName(course_search.getValue());

				}
			};
		}

	}

}
