package phone.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import phone.common.Security;
import phone.dao.Call_Dao;
import phone.dto.Phone_Call;

public class History implements Initializable{

	@FXML
	private VBox back;

	@FXML
	private TableView<Phone_Call> history_table;

	@FXML
	private Text number;
	
	private Call_Dao dao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Call_Dao.instance();
		
		number.setText(Security.getPhone().getPhone_no());
		back.setOnMouseClicked(e -> {
			back.getScene().getWindow().hide();
			Call.show();
		});
		search();
	}

	private void search() {
		SearchService service = new SearchService();
		service.setOnSucceeded(e -> {
			history_table.getItems().clear();
			history_table.getItems().addAll(service.getValue());
		});
		service.start();

	}

	private class SearchService extends Service<List<Phone_Call>> {
		@Override
		protected Task<List<Phone_Call>> createTask() {
			return new Task<List<Phone_Call>>() {
				@Override
				protected List<Phone_Call> call() throws Exception {
					return dao.history(Security.getPhone().getPhone_no().toString());
				}
			};
		}

	}

	public static void show() {
		try {
			Parent root = FXMLLoader.load(History.class.getResource("History.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.NONE);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
