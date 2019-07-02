package controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class AboutController 
{
	@FXML
    private JFXTextArea textAreaAbout;
	
	public void initialize()
	{
		textAreaAbout.setText("This software is deleveloped under GNU GPL v3 license.\n"
				+ "Developer: Francesco Esposito.\n"
				+ "For suggestions please contact the developer at francesco.espositoy@gmail.com.\n"
				+ "GitHub project link https://github.com/FrancescoVengeance/Gestionale-JavaFX");
	}
}
