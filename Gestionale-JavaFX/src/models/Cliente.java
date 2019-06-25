package models;

import java.util.ArrayList;
import gestioneDB.GestioneQuery;

public class Cliente 
{
	private int clienteId;
	private String nomeCliente;
	private String cognomeCliente;
	private String numeroTelefono;
	
	public Cliente(String nomeCliente, String cognomeCliente, String numeroTelefono)
	{
		GestioneQuery database = new GestioneQuery();
		this.clienteId = database.getIDultimoCliente() + 1;
		this.nomeCliente = nomeCliente;
		this.cognomeCliente = cognomeCliente;
		this.numeroTelefono = numeroTelefono;
	}
	
	public int getId() { return this.clienteId; }
	public void setId(int clienteId) { this.clienteId = clienteId; }
	
	public String getNomeCliente() { return this.nomeCliente;}
	public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente;}
	
	public String getCognomeCliente() { return this.cognomeCliente;}
	public void setCognomeCliente(String cognomeCliente) { this.cognomeCliente = cognomeCliente;}
	
	public String getNumeroTelefono() { return this.numeroTelefono;}
	public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono;}
	
	public String stampaCaratteristiche()
	{
		return " Nome Cliente: "+ nomeCliente +"\n"
				+" Cognome Cliente: "+cognomeCliente+"\n"
				+" Numero Di Telefono: "+numeroTelefono+"\n"
				+" ID: "+clienteId+"\n"
				+" Numero Ordini: "+ getNumeroOrdini();
	}
	
	public int getNumeroOrdini() 
	{
		int numeroOrdini = 0;
		GestioneQuery database = new GestioneQuery();
		numeroOrdini = database.getNumeroOrdiniCliente(this);
		database.chiudiConnessione();
		
		return numeroOrdini;
	}
	
	public void aggiungiOrdine(Ordine ordine)
	{
		GestioneQuery database = new GestioneQuery();
		database.salvaOrdine(ordine, this);
		database.chiudiConnessione();
	}
	
	public ArrayList<Ordine> getOrdini()
	{
		GestioneQuery database = new GestioneQuery();
		ArrayList<Ordine> ordini = database.caricaOrdini(this);
		database.chiudiConnessione();
		return ordini;
	}
	
	public static ArrayList<Cliente> caricaClienti()
	{
		GestioneQuery database = new GestioneQuery();
		System.out.println("sto caricando i clienti");
		ArrayList<Cliente> clienti = database.caricaClienti();
		database.chiudiConnessione();
		return clienti;
	}
	
	
	public void eliminaOrdine(Ordine ordine)
	{
		GestioneQuery database = new GestioneQuery();
		database.eliminaOrdine(ordine);
		database.chiudiConnessione();
	}
	
	public void eliminaCliente()
	{
		GestioneQuery database = new GestioneQuery();
		database.eliminaCliente(this);
		database.chiudiConnessione();
	}
	
	public void salva()
	{
		GestioneQuery database = new GestioneQuery();
		database.salvaClienti(this);
		database.chiudiConnessione();
	}
	
	@Override 
	public String toString()
	{
		return nomeCliente+" "+cognomeCliente;
	}
}
