package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import gestioneDB.GestioneQuery;

public class Ordine 
{
	private int id;
	private int idCliente;
	private Gioiello gioiello;
	private String dataScadenza;
	private String dataOrdine;
	private String tipologia; //riparazione o creazione
	private String descrizione;
	
	public Ordine(int id,String dataScadenza,Gioiello gioiello, String tipologia, String descrizione, int idCliente)
	{
		this.dataOrdine = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.dataScadenza = dataScadenza;
		this.tipologia = tipologia;
		this.id = id;
		this.gioiello = gioiello;
		this.descrizione = descrizione;
		this.idCliente = idCliente;
	}
	
	public int getId() { return this.id;}
	public void setId(int id) { this.id = id;}
	
	public Gioiello getGioiello() { return this.gioiello;}
	public void setGioiello(Gioiello g) { this.gioiello = g;}
	
	public String getDataOrdine() { return this.dataOrdine;}
	public void setDataOrdine(String dataOrdine) { this.dataOrdine = dataOrdine;}
	
	public String getDataScadenza() { return this.dataScadenza;}
	public void setDataScadenza(String dataScadenza) { this.dataScadenza = dataScadenza;} 
	
	public String getDescrizione() { return this.descrizione; }
	public void setDescrizione(String commento) { this.descrizione = commento; }
	
	public String getTipologia() { return this.tipologia;}
	public void setTipologia(String tipologia) { this.tipologia = tipologia;}
	
	public void setIdCLiente(int idCliente) { this.idCliente = idCliente;}
	public int getIdCliente() { return this.idCliente; }
	
	@Override
	public String toString()
	{
		if(gioiello != null) return gioiello.getNomeGioiello();
		return "Questo gioiello è stato eliminato";
	}
	
	public String getInformazioni()
	{
		return "Ordine numero: "+id+"\n"
				+ "Data Ordinazione: "+dataOrdine.toString()+"\n"
				+ "Data Scadenza: "+dataScadenza+"\n"
				+ "Tipologia Ordine: "+tipologia+"\n\n"
				+ "Descrizione:\n"+descrizione;
	}
	
	public void eliminaOrdine()
	{
		GestioneQuery database = new GestioneQuery();
		database.eliminaOrdine(this);
		database.chiudiConnessione();
	}
}


//SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy"); //formato data
//
//try 
//{
//	Date data2 = data.parse("12/11/1998");
//	System.out.println(data2);
//} 
//catch (ParseException e) 
//{
//	System.out.println("la data non corrisponde al formato corretto");
//	e.printStackTrace();
//}