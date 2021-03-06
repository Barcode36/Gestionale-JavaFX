package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import gestioneDB.GestioneQuery;

public class Fattura 
{
	private String dataEmissione;
	private float importo;
	private int idOrdine;
	private int idCliente;
	private String nomeCliente;
	private String nomeGioiello;
	public Fattura(String dataEmissione, float importo, int idFattura, int idCliente, String nomeCliente,String nomeGioiello) 
	{
		this.dataEmissione = dataEmissione;
		this.importo = importo;
		this.idOrdine = idFattura;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.nomeGioiello = nomeGioiello;
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
		System.out.println("sto caricando le fatture");
		ArrayList<Fattura> fatture = database.caricaFatture();
		database.chiudiConnessione();
		return fatture;
	}
	
	public void fatturaToFile(String path)
	{
		try 
		{
			PDDocument document = new PDDocument();
			PDPage pagina = new PDPage();
			document.addPage(pagina);
			PDPageContentStream write = new PDPageContentStream(document, pagina);
			write.beginText();
			write.setLeading(20.5f);  
			write.setFont(PDType1Font.HELVETICA, 18);
			write.newLineAtOffset(2, 750);
			String dati = stampaFattura();
			for(String s : dati.split("\n"))
			{
				write.showText(s);
				write.newLine();
			}
			write.endText();
			write.close();
			
			document.save(path);
			document.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public String stampaFattura()
	{
		return " ID Fattura: "+idOrdine+"\n"
				+" Data Emissione: "+dataEmissione+"\n"
				+" Cliente: "+nomeCliente+"\n"
				+" ID Cliente: "+idCliente+"\n"
				+" Gioiello: "+nomeGioiello+"\n"
				+" Importo: € "+importo;
	}
	
	public void elimina()
	{
		GestioneQuery database = new GestioneQuery();
		database.eliminaFattura(this);
		database.chiudiConnessione();
	}
	
	@Override
	public String toString()
	{
		return "Fattura #"+idOrdine;
	}
}
