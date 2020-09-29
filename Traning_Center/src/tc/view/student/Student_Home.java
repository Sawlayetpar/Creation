package tc.view.student;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.dao.Course_Dao;
import tc.dto.Course;

public class Student_Home implements Initializable {

	@FXML
	private TextField course_search;

	@FXML
	private TilePane tile;

	@FXML
	private Label total_course;

	private Course_Dao dao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Course_Dao.getIntstance();
		total_course.setText(String.valueOf(dao.getCount()));
		
		course_search.setText(null);
		course_search.textProperty().addListener((a, b, c) -> search());
		search();
	}

	public static void showView() {
		try {
			FXMLLoader loader = new FXMLLoader(Student_Home.class.getResource("Student_Home.fxml"));
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
	
	private void search() {
		List<Course> courses = dao.findByName(course_search.getText());

		tile.getChildren().clear();
		courses.stream().map(CourseView::new).forEach(tile.getChildren()::add);
	}

	private class CourseView extends HBox {
		@SuppressWarnings("unused")
		Course course;

		CourseView(Course course) {
			this.course = course;
			VBox vbox1 = new VBox(20);
			HBox hbox1 = new HBox();
			Label name = new Label(course.getName());
			name.setWrapText(true);
			hbox1.getChildren().addAll(name);

			HBox hbox2 = new HBox();
			Label duration_label = new Label("Duration ");
			Label duration = new Label(String.valueOf(course.getDuration()) + "months ");
			duration.setWrapText(true);
			hbox2.getChildren().addAll(duration_label, duration);

			HBox hbox3 = new HBox();
			Label fees_label = new Label("Fees  ");
			Label fees = new Label(course.getFees() + " Ks ");
			hbox3.getChildren().addAll(fees_label, fees);
			
			vbox1.getChildren().addAll(hbox1,hbox2,hbox3);
			vbox1.getStyleClass().add("left");
			
			VBox vbox2 = new VBox(20);
			Button btn = new Button("+");
			btn.setOnAction(this::details);

			vbox2.getChildren().addAll(btn);
			vbox2.getStyleClass().add("righ");

			getChildren().addAll(vbox1, vbox2);
			setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			getStyleClass().add("course");
		}	
			void details(ActionEvent event) {
				
			}
		
	}
}
