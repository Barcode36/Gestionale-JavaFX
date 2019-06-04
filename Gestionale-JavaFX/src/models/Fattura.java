package models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import gestioneDB.GestioneQuery;

public class Fattura 
{
	private String dataEmissione;
	private float importo;
	private int idOrdine;
	private int idCliente;
	private String nomeCliente;
	public Fattura(String dataEmissione, float importo, int idFattura, int idCliente, String nomeCliente) 
	{
		this.dataEmissione = dataEmissione;
		this.importo = importo;
		this.idOrdine = idFattura;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
	}
	public String getDataEmissione() { return dataEmissione; }
	public void setDataEmissione(String dataEmissione) { this.dataEmissione = dataEmissione; }
	
	public float getImporto() { return importo; }
	public void setImporto(float importo) { this.importo = importo; }
	
	public int getIdFattura() { return idOrdine; }
	public void setIdFattura(int idFattura) { this.idOrdine = idFattura; }
	
	public int getIdCliente() { return idCliente; }
	public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
	
	public String getNomeCliente() { return nomeCliente; }
	public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
	
	public static void emettiFattura(Ordine ordine) throws SQLException
	{
		GestioneQuery database = new GestioneQuery();
		database.emettiFattura(ordine);
		database.chiudiConnessione();
	}
	
	public static Fattura caricaFattura(Ordine ordine)
	{
		GestioneQuery database = new GestioneQuery();
		Fattura fattura = database.caricaFattura(ordine);
		database.chiudiConnessione();
		return fattura;
	}
	
	public static ArrayList<Fattura> caricaFatture()
	{
		GestioneQuery database = new GestioneQuery();
		ArrayList<Fattura> fatture = database.caricaFatture();
		database.chiudiConnessione();
		return fatture;
	}
	
	public void fatturaToFile()
	{
		try 
		{
			BufferedWriter file = new BufferedWriter(new FileWriter("Fattura"+idOrdine+".txt"));
			file.append(stampaFattura());
			file.newLine();
			file.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public String stampaFattura()
	{
		return "ID Fattura: "+idOrdine+"\n"
				+"Data Emissione: "+dataEmissione+"\n"
				+"Cliente: "+nomeCliente+"\n"
				+"ID Cliente: "+idCliente+"\n"
				+"Importo: "+importo;
	}
	
	@Override
	public String toString()
	{
		return "Fattura #"+idOrdine;
	}
}
