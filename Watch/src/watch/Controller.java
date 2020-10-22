package watch;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller implements Initializable{

	@FXML
	private Text hour;

	@FXML
	private Text minute;

	@FXML
	private Text seconds;
	private int hr;
	private int min;
	private int sec;
	private int currentSeconds;
	private Thread thrd;

	@FXML
	void hour_decrease() {
		hr -= 1;
		if (hr < 0) {
			hr = 99;
			transition(hour);
			hour.setText(intToString(hr));
		}
		transition(hour);
		hour.setText(intToString(hr));
	}

	@FXML
	void hour_increase() {

		hr += 1;
		if (hr >= 99) {
			hr = 0;
			transition(hour);
			hour.setText(intToString(hr));
		}
		transition(hour);
		hour.setText(intToString(hr));
	}

	@FXML
	void minute_decrease() {
		min -= 1;
		if (min < 0) {
			min = 59;
			transition(minute);
			minute.setText(intToString(min));
		}
		transition(minute);
		minute.setText(intToString(min));
	}

	@FXML
	void minute_increase() {
		min += 1;
		if (min >= 59) {
			min = 0;
			transition(minute);
			minute.setText(intToString(min));
		}
		transition(minute);
		minute.setText(intToString(min));
	}

	@FXML
	void seconds_decrease() {
		sec -= 1;
		if (sec < 0) {
			sec = 59;
			transition(seconds);
			seconds.setText(intToString(sec));
		}
		transition(seconds);
		seconds.setText(intToString(sec));
	}

	@FXML
	void seconds_increase() {
		sec += 1;
		if (sec >= 59) {
			sec = 0;
			transition(seconds);
			seconds.setText(intToString(sec));
		}
		transition(seconds);
		seconds.setText(intToString(sec));
	}

	@FXML
	void play() {
		currentSeconds = hmsToseconds(hr, min, sec);
		run();
		thrd.start();
	}

	@SuppressWarnings("deprecation")
	@FXML
	void pause() {
		if (currentSeconds > 0) {
			thrd.stop();
		}

	}

	@FXML
	void reset() {
		if (null == thrd) {
			if (hr != 0 || min != 0 || sec != 0) {
				hr = 0;
				min = 0;
				sec = 0;
				hour.setText(intToString(hr));
				minute.setText(intToString(min));
				seconds.setText(intToString(sec));
			}
		}

	}

	private int hmsToseconds(int h, int m, int s) {
		int hToseconds = h * 3600;
		int mToseconds = m * 60;
		int total = hToseconds + mToseconds + s;
		return total;
	}

	private int sToH(int cseconds) {
		int h = cseconds / 3600;
		cseconds = cseconds % 3600;
		return h;
	}

	private int sToM(int cseconds) {
		int m = cseconds / 60;
		cseconds = cseconds % 60;
		return m;
	}

	private void outPut() {
		seconds_decrease();
		if ((sToM(currentSeconds) > 0) && sec == 59) {
			minute_decrease();
			if ((sToH(currentSeconds) > 0) && min == 59) {
				hour_decrease();
			}
		}
	}

	private void run() {
		thrd = new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {

				try {
					while (true) {
						outPut();
						currentSeconds--;
						Thread.sleep(1000);
						if (currentSeconds == 0) {
							thrd.stop();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String intToString(int i) {
		return String.valueOf(i);
	}

	private void transition(Node node) {
		FadeTransition fd = new FadeTransition();
		fd.setDuration(Duration.millis(500));
		fd.setNode(node);
		fd.setToValue(1);
		fd.setFromValue(0.0);
		fd.play();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hour.setText(intToString(hr));
		minute.setText(intToString(min));
		seconds.setText(intToString(sec));
	}

}
