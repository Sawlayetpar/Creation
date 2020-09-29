package tc.view.admin;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.dao.Course_Dao;
import tc.dto.Class;
import tc.dto.Course;

public class ClassDetail implements Initializable {

	@FXML
	private SVGPath back;

	@FXML
	private Button cancle;

	@FXML
	private Button save;

	@FXML
	private DatePicker start_date;

	@FXML
	private DatePicker end_date;

	@FXML
	private ComboBox<Course> course;

	private Consumer<Class> classes;
	private Class class1;
	private Course_Dao dao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Course_Dao.getIntstance();
		course.getItems().addAll(dao.searchByCourse(null));

		cancle.setOnMouseClicked(e -> {
			course.getScene().getWindow().hide();
			ClassList.showView();
		});

		back.setOnMouseClicked(e -> {
			course.getScene().getWindow().hide();
			ClassList.showView();
		});

		save.setOnMouseClicked(e -> {
			try {
				createItemFromViewData();
				classes.accept(class1);
				course.getScene().getWindow().hide();
				ClassList.showView();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

	}

	private void createItemFromViewData() {
		if (null == class1) {
			class1 = new Class();
		}
		class1.setCourse(course.getValue());
		class1.setStart_date(Date.valueOf(start_date.getValue()));
		class1.setEnd_date(Date.valueOf(end_date.getValue()));
	}

	public static void showView(Class class1, Consumer<Class> classes) {

		try {
			FXMLLoader loader = new FXMLLoader(ClassDetail.class.getResource("ClassDetail.fxml"));
			Parent root = loader.load();
			ClassDetail controller = loader.getController();
//			boolean isNew = null == class1;

			controller.classes = classes;
			controller.class1 = class1;

//			if (!isNew) {
//				controller.name.setEditable(false);
//				controller.level.setEditable(false);
//				controller.duration.setEditable(false);
//				controller.fees.setEditable(false);
//				controller.setDataToView();
//			}

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
