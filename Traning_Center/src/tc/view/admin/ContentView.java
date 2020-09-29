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
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.common.Common;
import tc.dao.Content_Dao;
import tc.dto.Content;

public class ContentView implements Initializable,Consumer<Content>{

	@FXML
	private VBox back;

	@FXML
	private VBox add_new;

	@FXML
	private HBox numbers;
	
	@FXML
	private HBox view;
	
	private Content_Dao dao;
	public static void showView() {

		try {
			Parent root = FXMLLoader.load(ContentView.class.getResource("ContentView.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	 
	private void close() {
		back.getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = Content_Dao.getInstance();
		
		
		back.setOnMouseClicked(e -> {
			close();
			Home.showView();
		});
		
		add_new.setOnMouseClicked(e -> {
			close();
			Content_Detail.showView(null , this);
		});
		
		
	}

	@Override
	public void accept(Content t) {
		dao.create(t);
		search();
	}
	
	private void search() {
		SearchService service = new SearchService();

		service.setOnSucceeded(e -> {
			
		});
		service.start();
	}
	
	private class SearchService extends Service<List<Content>>{
		@Override
		protected Task<List<Content>> createTask() {
			return new Task<List<Content>>() {
				@Override
				protected List<Content> call() throws Exception {
					return dao.findByCourse(Common.getCourse_name());

				}
			};
		}

	}
}
