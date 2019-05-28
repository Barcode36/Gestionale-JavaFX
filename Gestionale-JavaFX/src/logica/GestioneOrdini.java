package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import gestioneDB.GestioneQuery;
import models.Cliente;
import models.Ordine;


public class GestioneOrdini 
{
	private ArrayList <Cliente> clienti;
	private GestioneQuery database;
	private int clientiNelDatabase = 0;
	
	public GestioneOrdini()
	{
		try 
		{
			this.database = new GestioneQuery();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		this.clienti = new ArrayList<Cliente>();
	}
	
	public void caricaClienti()
	{
		clienti = database.caricaClienti();
		clientiNelDatabase = database.getClientiNelDB();
	}
	
	public void salvaClienti()
	{
		for(int i = clientiNelDatabase; i < clienti.size(); i++)
		{
			database.salvaClienti(clienti.get(i));
			clientiNelDatabase++;
		}
		System.out.println("Ora i clienti nel db sono "+clientiNelDatabase);
	}
	public ArrayList <Cliente> getClienti(){ return this.clienti;}
	public void aggiungiCliente(Cliente cliente) {this.clienti.add(cliente);}
	public Cliente getCliente(int index)
	{
		if(index < clienti.size()) return clienti.get(index);
		else return null;
	}
	
	public void aggiungiOrdine(Cliente cliente,Ordine o)
	{
		try 
		{
			database.salvaOrdine(o, cliente);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
