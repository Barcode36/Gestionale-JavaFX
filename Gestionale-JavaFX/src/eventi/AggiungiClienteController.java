package eventi;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Cliente;

public class AggiungiClienteController extends Observable
{
	private Cliente cliente;
	
	@FXML
    private TextField inserisciNomeCliente;

    @FXML
    private TextField inserisciNumeroTelefono;

    @FXML
    private TextField inserisciCognomeCliente;
    
    @FXML
    private Button aggiungiButton;
    
    @FXML
    void AggiungiButtonPremuto(ActionEvent event) 
    {
    	Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher mat = pattern.matcher(inserisciCognomeCliente.getText());
		Matcher mat2 = pattern.matcher(inserisciNomeCliente.getText());
		
    	if(mat.matches() && mat2.matches() && Pattern.matches("[0-9]+", inserisciNumeroTelefono.getText()))
    	{
    		cliente = new Cliente(inserisciNomeCliente.getText(), inserisciCognomeCliente.getText(),inserisciNumeroTelefono.getText());
    		cliente.salva();
    		setChanged();
    		notifyObservers("Cliente creato");
    	}
    }
    
    public Cliente getCliente() { return this.cliente; }
    
}
