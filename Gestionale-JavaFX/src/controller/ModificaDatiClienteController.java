package controller;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Cliente;

public class ModificaDatiClienteController extends Observable
{
	private Cliente cliente;
	
	@FXML
    private JFXTextField inserisciNomeCliente;

    @FXML
    private JFXTextField inserisciNumeroTelefono;

    @FXML
    private JFXTextField inserisciCognomeCliente;

    @FXML
    private JFXButton aggiungiButton;
    
    public void initialize(Cliente c)
    {
    	cliente = c;
    	inserisciNomeCliente.setText(cliente.getNomeCliente());
    	inserisciCognomeCliente.setText(cliente.getCognomeCliente());
    	inserisciNumeroTelefono.setText(cliente.getNumeroTelefono());
    }
    
    public Cliente getCliente() { return this.cliente; }
    
    @FXML
    void aggiungiButtonPremuto(ActionEvent event) 
    {
    	Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher mat = pattern.matcher(inserisciCognomeCliente.getText());
		Matcher mat2 = pattern.matcher(inserisciNomeCliente.getText());
		
    	if(mat.matches() && mat2.matches() && Pattern.matches("[0-9]+", inserisciNumeroTelefono.getText()))
    	{
    		cliente.setNomeCliente(inserisciNomeCliente.getText());
    		cliente.setCognomeCliente(inserisciCognomeCliente.getText());
    		cliente.setNumeroTelefono(inserisciNumeroTelefono.getText());
    		cliente.modifica();
    		setChanged();
    		notifyObservers("Cliente modificato");
    	}
    }
}
