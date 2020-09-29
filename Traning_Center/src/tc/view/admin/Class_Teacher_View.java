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
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.common.Common;
import tc.dao.Class_Teacher_Dao;
import tc.dto.Class_Teacher;

public class Class_Teacher_View implements Initializable,Consumer<Class_Teacher>{

	@FXML
    private VBox back;

    @FXML
    private SVGPath add_new;

    @FXML
    private TableView<Class_Teacher> class_teacher_table;
    private Class_Teacher_Dao dao;

    private void close() {
    	add_new.getScene().getWindow().hide();
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Class_Teacher_Dao.getInstance();
		add_new.setOnMouseClicked(e -> {
			Class_Teacher_Detail.showView(null, this);
			close();
		});
		
		back.setOnMouseClicked(e -> {
			close();
			ClassView.showView();
		});
		
		search();
	}
    
    public static void showView() {

		try {
			FXMLLoader loader = new FXMLLoader(Class_Teacher_View.class.getResource("Class_Teacher_View.fxml"));
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
	public void accept(Class_Teacher t) {
		dao.create(t);
		search();
	}
	private void search() {

		TeacherSearchService service = new TeacherSearchService();

		service.setOnSucceeded(e -> {
			class_teacher_table.getItems().clear();
			class_teacher_table.getItems().addAll(service.getValue());
		});
		service.start();
	
	}
	
	private class TeacherSearchService extends Service<List<Class_Teacher>> {
		@Override
		protected Task<List<Class_Teacher>> createTask() {
			return new Task<List<Class_Teacher>>() {
				@Override
				protected List<Class_Teacher> call() throws Exception {
					return dao.searchByClass(Common.getClass_name());

				}
			};
		}

	}
}
