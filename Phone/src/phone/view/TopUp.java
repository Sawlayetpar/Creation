package phone.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TopUp implements Initializable{

	@FXML
    private SVGPath exit;

    @FXML
    private Button oneThousands;

    @FXML
    private Button threeThousands;

    @FXML
    private Button fiveThousands;

    @FXML
    private Button tenThousands;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exit.setOnMouseClicked(e -> oneThousands.getScene().getWindow().hide());
		oneThousands.setOnMouseClicked(e -> Number.show());
		threeThousands.setOnMouseClicked(e -> Number.show());
		fiveThousands.setOnMouseClicked(e -> Number.show());
		tenThousands.setOnMouseClicked(e -> Number.show());
	}
	
	public static void show() {
		try {
			Parent root = FXMLLoader.load(TopUp.class.getResource("TopUp.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
