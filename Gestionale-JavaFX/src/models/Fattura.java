package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3CharProc;
import org.apache.pdfbox.pdmodel.font.PDType3Font;

import com.sun.javafx.beans.IDProperty;

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
//		try 
//		{
//			BufferedWriter file = new BufferedWriter(new FileWriter("Fattura"+idOrdine+".txt"));
//			file.append(stampaFattura());
//			file.newLine();
//			file.close();
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
		
		
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
			
			document.save("Fattura #"+idOrdine+".pdf");
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
