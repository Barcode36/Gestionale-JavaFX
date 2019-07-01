package models;

public class Orecchino extends Gioiello
{
	private double spessore;
	private double altezza;
	private String tipologiaOrecchino;
	
	public Orecchino(double prezzo, double peso, MATERIALE materiale, String genere, boolean venduto,String descrizione, String nomeGioiello, double spessore,double altezza, String tipologiaOrecchino) 
	{
		super(prezzo, peso, materiale, genere, venduto, descrizione);
		this.nomeGioiello = nomeGioiello;
		this.tipologiaOrecchino = tipologiaOrecchino;
		this.spessore = spessore;
		this.altezza = altezza;
	}

	public double getSpessore() { return spessore; }
	public void setSpessore(double spessore) { this.spessore = spessore; }

	public String getTipologiaOrecchino() { return tipologiaOrecchino; }
	public void setTipologiaOrecchino(String tipologiaOrecchino) { this.tipologiaOrecchino = tipologiaOrecchino; }
	
	public double getAltezza() { return this.altezza; }
	public void setAltezza(double altezza) { this.altezza = altezza; }
	
	@Override
	public String stampaCaratteristiche() 
	{
		return " Id: " + getId() + "\n"
				+" Prezzo: "+getPrezzo()+"\n"
				+" Peso: "+getPeso()+"\n"
				+" Materiale: "+ getMateriale()+"\n"
				+" Genere: "+getGenere()+"\n"
				+" Altezza: "+ altezza + "\n"
				+" Spessore: "+spessore + "\n"
				+" Tipologia Orecchino: "+tipologiaOrecchino+"\n"
				+" Descrizione: \n"+" "+getDescrizione();
	}
}
