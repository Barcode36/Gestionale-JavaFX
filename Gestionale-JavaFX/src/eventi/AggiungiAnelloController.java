package eventi;

import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logica.Anello;
import logica.Gioiello;
import logica.MATERIALE;

public class AggiungiAnelloController extends Observable
{
	private Gioiello gioiello;
	
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
	
	public Gioiello getGioiello() { return this.gioiello; }
	
	public void initialize()
	{
		ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		materialeComboBox.setItems(opzioni);
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Si","No");
		pietraComboBox.setItems(opzioni2);
		
		ObservableList<String> opzioni3 = FXCollections.observableArrayList("Maschile","Femminile");
		genereComboBox.setItems(opzioni3);
	}
	
	@FXML
	void submit(ActionEvent event) //quando il tasto ok è premuto
    {
		double prezzo = Double.parseDouble(prezzoTextField.getText());
		double peso = Double.parseDouble(pesoTextField.getText());
		MATERIALE materiale = materialeComboBox.getValue();
		String genere = genereComboBox.getValue();
		boolean venduto = false;
		double raggio = Double.parseDouble(diametroTextField.getText());
		boolean pietra;
		
		if(pietraComboBox.getValue().equals("Si")) pietra = true;
		else pietra = false;
		
		String nomeGioiello = nomeGioielloTextField.getText();
		String descrizione = descrizioneTextArea.getText();
		
		gioiello = new Anello(0,prezzo,peso,materiale,genere,venduto,raggio,pietra,nomeGioiello,descrizione);
		setChanged();
		notifyObservers("Anello Creato");
    }
	
}
