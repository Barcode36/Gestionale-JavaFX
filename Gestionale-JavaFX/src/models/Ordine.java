package models;

import java.time.LocalDate;

import gestioneDB.GestioneQuery;

public class Ordine 
{
	private long id;
	private Gioiello gioiello;
	private String dataScadenza;
	private LocalDate dataOrdine;
	private String tipologia; //riparazione o creazione
	private String descrizione;
	
	public Ordine(long id,String dataScadenza,Gioiello gioiello, String tipologia, String descrizione)
	{
		this.dataOrdine = LocalDate.now();
		this.dataScadenza = dataScadenza;
		this.tipologia = tipologia;
		this.id = id;
		this.gioiello = gioiello;
		this.descrizione = descrizione;
	}
	
	public long getId() { return this.id;}
	public void setId(long id) { this.id = id;}
	
	public Gioiello getGioiello() { return this.gioiello;}
	public void setGioiello(Gioiello g) { this.gioiello = g;}
	
	public LocalDate getDataOrdine() { return this.dataOrdine;}
	
	public String getDataScadenza() { return this.dataScadenza;}
	public void setDataScadenza(String dataScadenza) { this.dataScadenza = dataScadenza;} 
	
	public String getDescrizione() { return this.descrizione; }
	public void setDescrizione(String commento) { this.descrizione = commento; }
	
	public String getTipologia() { return this.tipologia;}
	public void setTipologia(String tipologia) { this.tipologia = tipologia;}
	
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