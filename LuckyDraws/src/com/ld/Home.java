package com.ld;

import java.net.URL;
import java.util.ResourceBundle;

import com.ld.views.BikeView;
import com.ld.views.CarView;
import com.ld.views.CashView;
import com.ld.views.DellLaptop;
import com.ld.views.IphoneView;
import com.ld.views.IwatchView;
import com.ld.views.SonyTv;
import com.ld.views.Sorry;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class Home implements Number,Initializable{
	
	@FXML
    private TextField number;
	
    @FXML
    void go() {
    	check();
    }
    
    private void check() {
    	
    	try {
    		int no = Integer.parseInt(number.getText());
    		
			if(no >= 0) {
    		if(num[0] == no) {
    			CarView.showView();
    		}else if(num[1] == no) {
    			BikeView.showView();
    		}else if(num[2] == no) {
    			IphoneView.showView();
    		}else if(num[3] == no) {
    			IwatchView.showView();
    		}else if(num[4] == no) {
    			CashView.showView();
    		}else if(num[5] == no) {
    			DellLaptop.showView();
    		}else if(num[6] == no) {
    			SonyTv.showView();
    		}else {
    			Sorry.showView();
    		}
    	}    	
		} catch (Exception e) {
			System.out.println("Enter Digit Only");
		}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		number.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				go();}
				);
	}
}
