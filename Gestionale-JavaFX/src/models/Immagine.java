package models;

import java.io.FileInputStream;
import java.io.InputStream;
import javafx.scene.image.Image;

public class Immagine extends Image 
{
	private int idProdotto;
	private int idImmagine;
	
	public Immagine(InputStream is)
	{
		super(is);
	}
	
	public Immagine(FileInputStream is)
	{
		super(is);
	}
	
	public Immagine(InputStream is, int idProdotto, int idImmagine)
	{
		super(is);
		
		this.idProdotto = idProdotto;
		this.idImmagine = idImmagine;
	}

	public int getIdProdotto() { return idProdotto; }
	public void setIdProdotto(int idProdotto) { this.idProdotto = idProdotto; }

	public int getIdImmagine() { return idImmagine; }
	public void setIdImmagine(int idImmagine) { this.idImmagine = idImmagine; }
	
	

}
