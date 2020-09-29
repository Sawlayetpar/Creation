package tc.view.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PaidList {

	public static void showView() {
		try {
			Parent root = FXMLLoader.load(PaidList.class.getResource("PaidList.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
