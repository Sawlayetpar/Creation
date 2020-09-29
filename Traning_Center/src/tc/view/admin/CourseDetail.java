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
import tc.common.CommonUtil;
import tc.dto.Course;

public class CourseDetail implements Initializable {

	@FXML
	private SVGPath back;

	@FXML
	private TextField name;

	@FXML
	private Button cancle;

	@FXML
	private Button save;

	@FXML
	private TextField level;

	@FXML
	private TextField duration;

	@FXML
	private TextField fees;

	private Course course;
	private Consumer<Course> handler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cancle.setOnMouseClicked(e -> {
			name.getScene().getWindow().hide();
			Home.showView();
		});

		back.setOnMouseClicked(e -> {
			name.getScene().getWindow().hide();
			Home.showView();
		});

		save.setOnMouseClicked(e -> {
			try {
				createItemFromViewData();
				handler.accept(course);
				name.getScene().getWindow().hide();
				Home.showView();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

	}

	private void createItemFromViewData() {
		if (null == course) {
			course = new Course();
		}
		course.setName(name.getText());
		course.setLevel(CommonUtil.convertStringToInt(level.getText()));
		course.setDuration(CommonUtil.convertStringToInt(duration.getText()));
		course.setFees(CommonUtil.convertStringToInt(fees.getText()));
	}

	public static void showView(Course course, Consumer<Course> handler) {

		try {
			FXMLLoader loader = new FXMLLoader(TeacherDetail.class.getResource("CourseDetail.fxml"));
			Parent root = loader.load();
			CourseDetail controller = loader.getController();
			boolean isNew = null == course;

			controller.handler = handler;
			controller.course = course;

			if (!isNew) {
				controller.name.setEditable(false);
				controller.level.setEditable(false);
				controller.duration.setEditable(false);
				controller.fees.setEditable(false);
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
		name.setText(course.getName());
		level.setText(String.valueOf(course.getLevel()));
		duration.setText(String.valueOf(course.getDuration()));
		fees.setText(String.valueOf(course.getFees()));
	}
}