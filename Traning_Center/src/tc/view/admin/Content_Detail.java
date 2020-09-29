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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tc.dto.Content;

public class Content_Detail implements Initializable {

	@FXML
	private VBox back;

	@FXML
	private Button cancle;

	@FXML
	private Button save;

	@FXML
	private TextField name;

	@FXML
	private TextField description;

	private Consumer<Content> consumer;
	private Content content;

	private void close() {
		name.getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		save.setOnMouseClicked(e -> {
			try {
				createItemFromViewData();
				consumer.accept(content);
				close();
				ContentView.showView();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		cancle.setOnMouseClicked(e -> {
			close();
			ContentView.showView();
		});

		back.setOnMouseClicked(e -> {
			close();
			ContentView.showView();
		});

	}

	private void createItemFromViewData() {
		if (null == content) {
			content = new Content();
		}

		content.setName(name.getText());
		content.setDescription(description.getText());
	}

	public static void showView(Content content,Consumer<Content> consumer) {
		try {
			FXMLLoader loader = new FXMLLoader(Content_Detail.class.getResource("Content_Detail.fxml"));
			Parent root = loader.load();
			Content_Detail controller = loader.getController();

			controller.content = content;
			controller.consumer = consumer;
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
