 package tc;

import javafx.application.Application;
import javafx.stage.Stage;
import tc.view.admin.Home;

public class Training_Center_App extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
//		Student_Home.showView();
		Home.showView();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
