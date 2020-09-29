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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.common.Common;
import tc.dao.Course_Dao;
import tc.dto.Course;

public class Home implements Initializable, Consumer<Course> {

	@FXML
	private TextField course_search;

	@FXML
	private Button studetn_list;

	@FXML
	private Button teacher_list;

	@FXML
	private Button classes;

	@FXML
	private SVGPath add_new;

	@FXML
	private Label total_course;

	@FXML
	private TableView<Course> course_table;

	@FXML
	private HBox paid_list;

	private Course_Dao dao;

	private void close() {
		total_course.getScene().getWindow().hide();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = Course_Dao.getIntstance();
		total_course.setText(String.valueOf(dao.getCount()));

		studetn_list.setOnMouseClicked(e -> {
			StudentList.showView();
			close();
		});
		teacher_list.setOnMouseClicked(e -> {
			TeacherList.showView();
			close();
		});
		classes.setOnMouseClicked(e -> {
			ClassList.showView();
			close();
		});
		add_new.setOnMouseClicked(e -> {
			CourseDetail.showView(null, this);
			close();
		});

		paid_list.setOnMouseClicked(e -> {
			PaidList.showView();
			close();
		});

		MenuItem content = new MenuItem("Content");
		course_table.setContextMenu(new ContextMenu(content));
		content.setOnAction(e -> {
			Course course = new Course();
			course =course_table.getSelectionModel().getSelectedItem();
			Common.setCourse_name(course);
			close();
			ContentView.showView();
		});
		
		course_search.setText(null);
		course_search.textProperty().addListener((a, b, c) -> search());
		search();

	}

	public static void showView() {
		try {
			FXMLLoader loader = new FXMLLoader(Home.class.getResource("Home.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UTILITY);
			stage.setFullScreen(false);
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void accept(Course t) {
		dao.create(t);
		search();
	}

	private void search() {

		CourseSearchService service = new CourseSearchService();

		service.setOnSucceeded(e -> {
			course_table.getItems().clear();
			course_table.getItems().addAll(service.getValue());
		});
		service.start();
	}

	private class CourseSearchService extends Service<List<Course>> {

		@Override
		protected Task<List<Course>> createTask() {
			return new Task<List<Course>>() {
				@Override
				protected List<Course> call() throws Exception {
					return dao.findByName(course_search.getText());
				}
			};
		}

	}

//	private class CourseView extends VBox {
//		@SuppressWarnings("unused")
//		Course course;
//
//		CourseView(Course course) {
//			this.course = course;
//			HBox hbox1 = new HBox(20);
//			Label name = new Label(course.getName());
////			name.setWrapText(true);
//			hbox1.getChildren().addAll(name);
//			
//			HBox hbox2 = new HBox(5);
//			Label duration_label = new Label("Duration : ");
//			Label duration = new Label(String.valueOf(course.getDuration()) + "months ");
////			duration.setWrapText(true);
//			hbox2.getChildren().addAll(duration_label, duration);
//
//			HBox hbox3 = new HBox(20);
//			Label fees_label = new Label("Fees : ");
//			Label fees = new Label(course.getFees() + " Ks ");
//			hbox3.getChildren().addAll(fees_label, fees);
//			
//			
//			HBox hbox4 = new HBox(20);
//			Label detail = new Label("+");
//			hbox4.getChildren().addAll(detail);
//			hbox4.getStyleClass().add("center");
////			detail.setOnAction(this :: details);
//
////			vBox.getChildren().addAll(name, duration, hbox);
////			vBox.getStyleClass().add("center");
//
//			getChildren().addAll(hbox1,hbox2,hbox3,hbox4);
//			setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//			getStyleClass().add("course");
//		}
//		
//	}
}
