package eventi;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;
import gestioneDB.GestioneQuery;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import models.Gioiello;
import models.Ordine;

public class AggiungiOrdineController extends Observable
{
	private Ordine ordine;
	private Gioiello gioiello;
	private String data;
	
	@FXML
    private DatePicker selettoreDataConsegna;

    @FXML
    private ListView<Gioiello> gioielloListView;

    @FXML
    private ComboBox<String> tipologiaComboBox;

    @FXML
    private TextArea descrizioneTextArea;
	
    @FXML
    private Button bottone;
    
    public Ordine getOrdine() { return this.ordine; }
    
    public void initialize()
    {
    	ObservableList<String> opzioni = FXCollections.observableArrayList("Riparazione","Creazione","Altro");
    	tipologiaComboBox.setItems(opzioni);
    	
    	GestioneQuery database;
		try 
		{
			database = new GestioneQuery();
			ArrayList<Gioiello> gioielli = database.caricaGioielli();
			
			for(Gioiello g : gioielli)
			{
				gioielloListView.getItems().add(g);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gioielloListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	gioielloListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
    		
    		data = selettoreDataConsegna.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		gioiello = newVal;
		});
		
    }
    
    @FXML
    void bottonePremuto(ActionEvent event) 
    {
    	ordine = new Ordine(0,data,gioiello,tipologiaComboBox.getValue(),descrizioneTextArea.getText());
    	setChanged();
    	notifyObservers("Ordine Creato");
    }
}
