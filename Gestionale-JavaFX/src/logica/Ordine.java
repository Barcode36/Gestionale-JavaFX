package logica;

import java.util.Date;

public class Ordine 
{
	private int id;
	private String nomeCliente; //nome del cliente associato all'ordine
	private Gioiello gioiello;
	private Date dataScadenza;
	private Date dataOrdine;
	private String tipologia; //riparazione o creazione
	
	public Ordine(int id,Date dataScadenza, String nomeCliente, Gioiello gioiello, String tipologia)
	{
		this.dataOrdine = new Date();
		this.tipologia = tipologia;
		this.dataScadenza = dataScadenza;
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.gioiello = gioiello;
	}
	
	public int getId() { return this.id;}
	public void setId(int id) { this.id = id;}
	
	public String getNomeCliente() { return this.nomeCliente;}
	public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente;}
	
	public Gioiello getOrdine() { return this.gioiello;}
	public void setGioiello(Gioiello g) { this.gioiello = g;}
	
	public Date getDataOrdine() { return this.dataOrdine;}
	
	public Date getDataScadenza() { return this.dataScadenza;}
	public void setDataScadenza(Date dataScadenza) { this.dataScadenza = dataScadenza;} 
	
	@Override
	public String toString()
	{
		return id + ";" + nomeCliente + ";" + gioiello + ";" + dataScadenza.toString() + ";" + dataOrdine.toString() + ";" + tipologia;
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