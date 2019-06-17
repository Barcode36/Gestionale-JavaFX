package eventi;

import gestioneDB.GestioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Anello;
import models.MATERIALE;

public class ModificaDatiAnelloController 
{
	private Anello anello;
	
	@FXML
    private ComboBox<MATERIALE> materialeComboBox;
	
	@FXML
    private ComboBox<String> genereComboBox;

    @FXML
    private Button submitButton;

    @FXML
    private TextField diametroTextField;

    @FXML
    private TextField prezzoTextField;

    @FXML
    private TextField nomeGioielloTextField;

    @FXML
    private TextField pesoTextField;

    @FXML
    private ComboBox<String> pietraComboBox;

    @FXML
    private TextArea descrizioneTextArea;
	
    public void initialize(Anello gioiello)
    {
    	this.anello = gioiello;
    	
    	ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		materialeComboBox.setItems(opzioni);
		materialeComboBox.setValue(gioiello.getMateriale());
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Si","No");
		pietraComboBox.setItems(opzioni2);
		if(gioiello.isPietra()) pietraComboBox.setValue("Si");
		else pietraComboBox.setValue("No");
		
		ObservableList<String> opzioni3 = FXCollections.observableArrayList("Maschile","Femminile");
		genereComboBox.setItems(opzioni3);
		genereComboBox.setValue(gioiello.getGenere());
		
    	diametroTextField.setText(Double.toString(gioiello.getRaggio()));
    	prezzoTextField.setText(Double.toString(gioiello.getPrezzo())); //
    	nomeGioielloTextField.setText(gioiello.getNomeGioiello()); //
    	pesoTextField.setText(Double.toString(gioiello.getPeso()));
    	descrizioneTextArea.setText(gioiello.getDescrizione());
    }
    
    @FXML
    void submit(ActionEvent event) 
    {
    	anello.setPrezzo(Double.parseDouble(prezzoTextField.getText()));
    	anello.setNomeGioiello(nomeGioielloTextField.getText());
    	anello.setPeso(Double.parseDouble(pesoTextField.getText()));
    	anello.setDescrizione(descrizioneTextArea.getText());
    	
    	GestioneQuery database = new GestioneQuery();
    	database.modificaDatiGioiello(anello);
    	database.chiudiConnessione();
    }
    
}
