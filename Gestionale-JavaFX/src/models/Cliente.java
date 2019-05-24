package models;

import java.util.ArrayList;

import gestioneDB.GestioneQuery;

public class Cliente 
{
	private int clienteId;
	private String nomeCliente;
	private String cognomeCliente;
	private String numeroTelefono;
	private ArrayList <Ordine> ordini;
	
	public Cliente(int clienteId,String nomeCliente, String cognomeCliente, String numeroTelefono, ArrayList<Ordine> ordini)
	{
		this.clienteId = clienteId;
		this.nomeCliente = nomeCliente;
		this.cognomeCliente = cognomeCliente;
		this.numeroTelefono = numeroTelefono;
		this.ordini = ordini;
	}
	
	public int getId() { return this.clienteId; }
	public void setId(int clienteId) { this.clienteId = clienteId; }
	
	public String getNomeCliente() { return this.nomeCliente;}
	public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente;}
	
	public String getCognomeCliente() { return this.cognomeCliente;}
	public void setCognomeCliente(String cognomeCliente) { this.cognomeCliente = cognomeCliente;}
	
	public String getNumeroTelefono() { return this.numeroTelefono;}
	public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono;}
	
	public ArrayList<Ordine> getOrdini() {return this.ordini;}
	public int getNumeroOrdini() { return this.ordini.size();}
	
	public void aggiungiOrdine(Ordine ordine) {this.ordini.add(ordine);}
	public Ordine getOrdine(int index)
	{
		if(index < ordini.size()) return ordini.get(index);
		else return null;
	}
	
	public String stampaCaratteristiche()
	{
		String numeroOrdini = null;
		if(ordini != null) numeroOrdini+=ordini.size();
		else numeroOrdini = "0";
		return "Nome Cliente: "+ nomeCliente +"\n"
				+"Cognome Cliente: "+cognomeCliente+"\n"
				+"Numero Di Telefono: "+numeroTelefono+"\n"
				+"Numero Ordini: "+numeroOrdini;
	}
	
	@Override 
	public String toString()
	{
		return nomeCliente+" "+cognomeCliente;
	}
}
