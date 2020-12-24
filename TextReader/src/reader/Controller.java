package reader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	@FXML
	private TextArea txt;

	@FXML
	private Button btn;

	StringBuilder sb = new StringBuilder();
	FileChooser chooser = new FileChooser();
	
	private void text() throws IOException {
		Stage stage = (Stage) txt.getScene().getWindow();
		chooser.getExtensionFilters().addAll(
				new ExtensionFilter("Java", " *.java"),
				new ExtensionFilter("Text", " *.txt"),
				new ExtensionFilter("fxml", " *.fxml")
				);
		File file = chooser.showOpenDialog(stage);
		
		if(file.canRead()) {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()) {
				sb.append(scanner.nextLine());
				sb.append("\n");
			}
			scanner.close();
			txt.setText(sb.toString());
			sb.delete(0, sb.length()-1);
		}else {
			txt.setText("Sorry File is not readable file");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn.setOnAction(e -> {
			try {
				text();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
	}
}
