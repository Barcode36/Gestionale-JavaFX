package models;

public class Collana extends Gioiello 
{
	private double lunghezza;
	private double spessore;
	private boolean ciondolo;
	

	public Collana(double prezzo, double peso, MATERIALE materiale, String genere, boolean venduto, String descrizione, String nomeGioiello, double lunghezza, boolean ciondolo, double spessore) 
	{
		super(prezzo, peso, materiale, genere, venduto, descrizione);
		this.lunghezza = lunghezza;
		this.ciondolo = ciondolo;
		this.spessore = spessore;
		this.nomeGioiello = nomeGioiello;
	}

	public double getLunghezza() { return lunghezza; }
	public void setLunghezza(double lunghezza) { this.lunghezza = lunghezza; }
	
	public boolean isCiondolo() { return ciondolo; }
	public void setCiondolo(boolean ciondolo) { this.ciondolo = ciondolo; }
	
	public double getSpessore() { return this.spessore; }
	public void setSpessore(double spessore) { this.spessore = spessore; } 
	
	@Override
	public String stampaCaratteristiche() 
	{
		String ciondolo_;
		if(ciondolo) ciondolo_ = "Si";
		else ciondolo_ = "No";
		
		return " Id: " + getId() + "\n"
				+" Prezzo: "+getPrezzo()+"\n"
				+" Peso: "+getPeso()+"\n"
				+" Materiale: "+ getMateriale()+"\n"
				+" Genere: "+getGenere()+"\n"
				+" Lunghezza: "+ this.lunghezza + "\n"
				+" Ciondolo: "+ciondolo_+ "\n"
				+" Descrizione: \n"+" "+getDescrizione();
	}
	
	@Override
	public String toString() { return this.nomeGioiello; }
}
