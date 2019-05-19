package eventi;

import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logica.Bracciale;
import logica.Gioiello;
import logica.MATERIALE;

public class AggiungiBraccialeController extends Observable
{
	private Gioiello gioiello;
	
	@FXML
	private ComboBox<MATERIALE> selezionaMaterialeBox;
	
	@FXML
    private TextField lunghezzaTextField;

    @FXML
    private TextField nomeGioielloTextField;

    @FXML
    private TextField spessoreTextField;

    @FXML
    private TextField inserisciPesoTextField;

    @FXML
    private TextField larghezzaTextField;

    @FXML
    private TextField inserisciPrezzoTextField;

    @FXML
    private ComboBox<String> selezionaGenereBox;

    @FXML
    private TextArea descrizioneTextArea;
	
	public void initialize()
	{
		ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		selezionaMaterialeBox.setItems(opzioni);
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Maschile","Femminile");
		selezionaGenereBox.setItems(opzioni2);
	}
	
	public Gioiello getGioiello() { return this.gioiello; }
	
	@FXML
    void submit(ActionEvent event) 
	{
		double prezzo = Double.parseDouble(inserisciPrezzoTextField.getText());
		double peso = Double.parseDouble(inserisciPesoTextField.getText());
		MATERIALE materiale = selezionaMaterialeBox.getValue();
		String genere = selezionaGenereBox.getValue();
		boolean venduto = false;
		double lunghezza = Double.parseDouble(lunghezzaTextField.getText());
		double spessore = Double.parseDouble(spessoreTextField.getText());
		double larghezza = Double.parseDouble(larghezzaTextField.getText());
		String nomeGioiello = nomeGioielloTextField.getText();
		String descrizione = descrizioneTextArea.getText();
		gioiello = new Bracciale(0,prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione);
		setChanged();
		notifyObservers("Bracciale Creato");
    }
}
