package models;

import java.sql.SQLException;
import java.util.ArrayList;

import gestioneDB.GestioneQuery;

public class Cliente 
{
	private int clienteId;
	private String nomeCliente;
	private String cognomeCliente;
	private String numeroTelefono;
	
	public Cliente(int clienteId,String nomeCliente, String cognomeCliente, String numeroTelefono)
	{
		this.clienteId = clienteId;
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
	
//	public ArrayList<Ordine> getOrdini() 
//	{
//		
//	}
//	
//	
//	public void aggiungiOrdine(Ordine ordine) {this.ordini.add(ordine);}
//	public Ordine getOrdine(int index)
//	{
//		if(index < ordini.size()) return ordini.get(index);
//		else return null;
//	}
	
	public String stampaCaratteristiche()
	{
		return "Nome Cliente: "+ nomeCliente +"\n"
				+"Cognome Cliente: "+cognomeCliente+"\n"
				+"Numero Di Telefono: "+numeroTelefono+"\n"
				+"Numero Ordini: "+ getNumeroOrdini();
	}
	
	public int getNumeroOrdini() 
	{
		int numeroOrdini = 0;
		
		try 
		{
			GestioneQuery database = new GestioneQuery();
			numeroOrdini = database.getNumeroOrdini(this);
			database.chiudiConnessione();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		return numeroOrdini;
	}
	
	public void aggiungiOrdine(Ordine ordine)
	{
		try 
		{
			GestioneQuery database = new GestioneQuery();
			database.salvaOrdine(ordine, this);
			database.chiudiConnessione();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override 
	public String toString()
	{
		return nomeCliente+" "+cognomeCliente;
	}
}
