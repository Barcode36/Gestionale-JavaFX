package controller;

import java.io.File;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import models.Fattura;

public class ControllerEmettiFattura 
{
	private Fattura fattura;
	
	@FXML
    private JFXButton chiudiButton;

    @FXML
    private BorderPane borderPaneFattura;

    @FXML
    private JFXButton stampaButton;

    @FXML
    private JFXTextArea textAreaFattura;

    public void initialize(Fattura f)
    {
    	fattura = f;
    	textAreaFattura.setText(fattura.stampaFattura());
    }
    
    @FXML
    void stampaButtonPressed(ActionEvent event) 
    {
    	FileChooser percorsoSalvataggio = new FileChooser();
		percorsoSalvataggio.setTitle("Salva Fattura");
		percorsoSalvataggio.setInitialFileName("Fattura #"+fattura.getIdFattura()+".pdf");
		percorsoSalvataggio.getExtensionFilters().add(new ExtensionFilter("pdf file", ".pdf"));
		File file = percorsoSalvataggio.showSaveDialog(new Stage());
		if(file != null)
		{
			fattura.fatturaToFile(file.getPath());
		}
    }

    @FXML
    void chiudiButtonPressed(ActionEvent event) 
    {
    	Stage stage = (Stage) borderPaneFattura.getScene().getWindow();
    	stage.close();
    }
}
