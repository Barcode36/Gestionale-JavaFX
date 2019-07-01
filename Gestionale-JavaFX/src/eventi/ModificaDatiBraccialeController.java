package eventi;

import java.util.Observable;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Bracciale;
import models.Gioiello;
import models.MATERIALE;

public class ModificaDatiBraccialeController extends Observable
{
	private Bracciale bracciale;
	
	@FXML
	private JFXComboBox<MATERIALE> selezionaMaterialeBox;

	@FXML
	private JFXTextField lunghezzaTextField;

	@FXML
	private JFXTextField nomeGioielloTextField;

	@FXML
	private JFXTextField spessoreTextField;

	@FXML
	private JFXTextField inserisciPesoTextField;
	 
	@FXML
	private JFXTextField larghezzaTextField;
	 
	@FXML
	private JFXTextField inserisciPrezzoTextField;
	 
	@FXML
	private JFXComboBox<String> selezionaGenereBox;

	@FXML
	private JFXTextArea descrizioneTextArea;
	 
	public void initialize(Bracciale gioiello)
	{
		this.bracciale = gioiello;
		
		ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		selezionaMaterialeBox.setItems(opzioni);
		selezionaMaterialeBox.setValue(gioiello.getMateriale());
		
		ObservableList<String> opzioni3 = FXCollections.observableArrayList("Maschile","Femminile");
		selezionaGenereBox.setItems(opzioni3);
		selezionaGenereBox.setValue(gioiello.getGenere());
					
		spessoreTextField.setText(Double.toString(gioiello.getSpessore()));
		lunghezzaTextField.setText(Double.toString(gioiello.getLunghezza()));
		larghezzaTextField.setText(Double.toString(gioiello.getLarghezza()));
		inserisciPrezzoTextField.setText(Double.toString(gioiello.getPrezzo())); //
		nomeGioielloTextField.setText(gioiello.getNomeGioiello()); //
		inserisciPesoTextField.setText(Double.toString(gioiello.getPeso()));
		descrizioneTextArea.setText(gioiello.getDescrizione());
	}
	
	public Gioiello getGioiello()
	{
		return bracciale;
	}
	
	@FXML
	void submit(ActionEvent event) 
	{
		 bracciale.setSpessore(Double.parseDouble(spessoreTextField.getText()));
		 bracciale.setLunghezza(Double.parseDouble(lunghezzaTextField.getText()));
		 bracciale.setLarghezza(Double.parseDouble(larghezzaTextField.getText()));
		 bracciale.setPrezzo(Double.parseDouble(inserisciPrezzoTextField.getText()));
		 bracciale.setNomeGioiello(nomeGioielloTextField.getText());
		 bracciale.setPeso(Double.parseDouble(inserisciPesoTextField.getText()));
		 bracciale.setDescrizione(descrizioneTextArea.getText());
		 bracciale.setMateriale(selezionaMaterialeBox.getValue());
		 bracciale.setGenere(selezionaGenereBox.getValue());
		 
		 bracciale.modificaDati();
		 
		 setChanged();
		 notifyObservers("modificato bracciale");
	}
}
