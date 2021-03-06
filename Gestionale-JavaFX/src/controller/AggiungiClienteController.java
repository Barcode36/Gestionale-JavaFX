package controller;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Cliente;

public class AggiungiClienteController extends Observable
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
    
    @FXML
    void aggiungiButtonPremuto(ActionEvent event) 
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
