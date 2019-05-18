package logica;

import java.util.ArrayList;


public class GestioneOrdini 
{
	private ArrayList <Cliente> clienti;
	
	public GestioneOrdini()
	{
		this.clienti = new ArrayList<Cliente>();
	}
	
	public ArrayList <Cliente> getClienti(){ return this.clienti;}
	public void aggiungiCliente(Cliente cliente) {this.clienti.add(cliente);}
	public Cliente getCliente(int index)
	{
		if(index < clienti.size()) return clienti.get(index);
		else return null;
	}
	
	public boolean rimuoviCliente(String nomeCliente, int numeroTelefono)
	{
		for(int i = 0; i < clienti.size(); i++)
		{
			if(clienti.get(i).getNomeCliente().equals(nomeCliente) && clienti.get(i).getNumeroTelefono() == numeroTelefono)
			{
				clienti.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public void salvaOrdini()
	{
		
	}
	
	public void caricaOrdini()
	{
		
	}
}
