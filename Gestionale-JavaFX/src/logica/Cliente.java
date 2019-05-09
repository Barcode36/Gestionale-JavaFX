package logica;

import java.util.ArrayList;

public class Cliente 
{
	private String nomeCliente;
	private String cognomeCliente;
	private int numeroTelefono;
	private ArrayList <Ordine> ordini;
	
	public Cliente(String nomeCliente, String cognomeCliente, int numeroTelefono, ArrayList<Ordine> ordini)
	{
		this.nomeCliente = nomeCliente;
		this.cognomeCliente = cognomeCliente;
		this.numeroTelefono = numeroTelefono;
		this.ordini = ordini;
	}
	
	public String getNomeCliente() { return this.nomeCliente;}
	public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente;}
	
	public String getCognomeCliente() { return this.cognomeCliente;}
	public void setCognomeCliente(String cognomeCliente) { this.cognomeCliente = cognomeCliente;}
	
	public int getNumeroTelefono() { return this.numeroTelefono;}
	public void setNumeroTelefono(int numeroTelefono) { this.numeroTelefono = numeroTelefono;}
	
	public ArrayList<Ordine> getOrdini() {return this.ordini;}
	public int getNumeroOrdini() { return this.ordini.size();}
	public void aggiungiOrdine(Ordine ordine) {this.ordini.add(ordine);}
	public Ordine getOrdine(int index)
	{
		if(index < ordini.size()) return ordini.get(index);
		else return null;
	}
	
	public boolean eliminaOrdine(String id)
	{
		for(int i = 0; i < this.ordini.size(); i++)
		{
			if(this.ordini.get(i).getId().equals(id))
			{
				this.ordini.remove(i);
				return true;
			}
		}
		return false;
	}
}
