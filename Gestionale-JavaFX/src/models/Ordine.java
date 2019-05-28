package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ordine 
{
	private long id;
	private Gioiello gioiello;
	private Date dataScadenza;
	private Date dataOrdine;
	private String tipologia; //riparazione o creazione
	private String descrizione;
	
	public Ordine(long id,String dataScadenza,Gioiello gioiello, String tipologia, String descrizione)
	{
		this.dataOrdine = new Date();
		this.tipologia = tipologia;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			this.dataScadenza = formatter.parse(dataScadenza);
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.id = id;
		this.gioiello = gioiello;
		this.descrizione = descrizione;
	}
	
	public long getId() { return this.id;}
	public void setId(long id) { this.id = id;}
	
	public Gioiello getGioiello() { return this.gioiello;}
	public void setGioiello(Gioiello g) { this.gioiello = g;}
	
	public Date getDataOrdine() { return this.dataOrdine;}
	
	public Date getDataScadenza() { return this.dataScadenza;}
	public void setDataScadenza(Date dataScadenza) { this.dataScadenza = dataScadenza;} 
	
	public String getDescrizione() { return this.descrizione; }
	public void setDescrizione(String commento) { this.descrizione = commento; }
	
	public String getTipologia() { return this.tipologia;}
	public void setTipologia(String tipologia) { this.tipologia = tipologia;}
	
	@Override
	public String toString()
	{
		return dataOrdine.toString();
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