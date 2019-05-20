package logica;

public abstract class Gioiello 
{
	private int id;
	private double prezzo; 
	private double peso;
	private MATERIALE materiale;
	private boolean venduto;
	private String genere; //maschile,femminile
	protected String nomeGioiello;
	private String descrizione;
	
	public Gioiello(int id, double prezzo, double peso, MATERIALE materiale, String genere, boolean venduto, String descrizione)
	{
		if(prezzo >= 0) this.prezzo = prezzo;
		if(peso >= 0) this.peso = peso;
		this.materiale = materiale;
		this.id = id;
		if(genere.equals("Maschile") || genere.equals("Femminile"))
			this.genere = genere;
		this.venduto = venduto;
		this.descrizione = descrizione;
	}
	
	public String getNomeGioiello() { return this.nomeGioiello; }
	
	public boolean getVenduto() {return this.venduto;}
	public void setVenduto(boolean venduto) { this.venduto = venduto;}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public double getPrezzo() { return prezzo; }
	public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

	public double getPeso() { return peso; }
	public void setPeso(double peso) { this.peso = peso; }

	public MATERIALE getMateriale() { return materiale; }
	public void setMateriale(MATERIALE materiale) { this.materiale = materiale; }

	public String getGenere() { return genere; }
	public void setGenere(String genere) { this.genere = genere; }
	
	public String getDescrizione() { return this.descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	
	public abstract String save();
	public abstract String stampaCaratteristiche();
}
