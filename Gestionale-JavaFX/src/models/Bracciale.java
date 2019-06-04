package models;

public class Bracciale extends Gioiello
{
	private double lunghezza;
	private double spessore;
	private double larghezza;
	
	
	public Bracciale(double prezzo, double peso, MATERIALE materiale, String genere,boolean venduto, double lunghezza, double spessore, double larghezza, String nomeGioiello,String descrizione)
	{
		super(prezzo,peso,materiale,genere,venduto,descrizione);
		this.lunghezza = lunghezza;
		this.spessore = spessore;
		this.larghezza = larghezza;
		this.nomeGioiello = nomeGioiello;
	}
	
	public double getLunghezza() { return lunghezza; }
	public void setLunghezza(double lunghezza) { this.lunghezza = lunghezza; }

	public double getSpessore() { return spessore; }
	public void setSpessore(double spessore) { this.spessore = spessore; }

	public double getLarghezza() { return larghezza; }
	public void setLarghezza(double larghezza) { this.larghezza = larghezza; }
	
	@Override
	public String toString() { return this.nomeGioiello; }

	@Override
	public String stampaCaratteristiche() 
	{
		String venduto;
		if(getVenduto()) venduto = "Si";
		else venduto = "No";
		
		return " Id: " + getId() + "\n"
				+" Prezzo: "+getPrezzo()+"\n"
				+" Peso: "+getPeso()+"\n"
				+" Materiale: "+ getMateriale()+"\n"
				+" Genere: "+getGenere()+"\n"
				+" Venduto: "+venduto+"\n"
				+" Lunghezza: "+lunghezza+"\n"
				+" Larghezza: "+larghezza+"\n"
				+" Spessore: "+spessore+"\n"
				+" Descrizione: \n"+" "+getDescrizione();
	}
}
