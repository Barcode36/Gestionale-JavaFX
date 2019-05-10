package logica;

public class Bracciale extends Gioiello
{
	private double lunghezza;
	private double spessore;
	private double larghezza;
	
	
	public Bracciale(String id, double prezzo, double peso, MATERIALE materiale, String genere, double lunghezza, double spessore, double larghezza, String nomeGioiello)
	{
		super(id,prezzo,peso,materiale,genere);
		this.lunghezza = lunghezza;
		this.spessore = spessore;
		this.larghezza = larghezza;
		this.tipoGioiello = "Bracciale";
		this.nomeGioiello = nomeGioiello;
	}
	
	public double getLunghezza() { return lunghezza; }
	public void setLunghezza(double lunghezza) { this.lunghezza = lunghezza; }

	public double getSpessore() { return spessore; }
	public void setSpessore(double spessore) { this.spessore = spessore; }

	public double getLarghezza() { return larghezza; }
	public void setLarghezza(double larghezza) { this.larghezza = larghezza; }
	
	@Override
	public String toString()
	{
		return "Bracciale;"+super.getId() + ";" + super.getPrezzo() + ";" + super.getPeso() + ";" + super.getMateriale() + ";" + super.getGenere() + ";" + lunghezza + ";" + spessore + ";" + larghezza + ";" + nomeGioiello;
	}
	
}
