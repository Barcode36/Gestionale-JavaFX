package logica;

public abstract class Gioiello 
{
	private String id;
	private double prezzo; 
	private double peso;
	private MATERIALE materiale;
	private boolean venduto;
	private String genere; //maschile,femminile
	protected String tipoGioiello;
	protected String nomeGioiello;
	
	public Gioiello(String id, double prezzo, double peso, MATERIALE materiale, String genere)
	{
		if(prezzo >= 0) this.prezzo = prezzo;
		if(peso >= 0) this.peso = peso;
		this.materiale = materiale;
		this.id = id;
		this.venduto = false;
		if(genere.equals("Maschile") || genere.equals("Femminile"))
			this.genere = genere;
	}
	
	public static Gioiello costruisciGioiello(String[] dati)
	{
		if(dati[0].equals("Anello"))
		{
			String id = dati[1];
			double prezzo = Double.parseDouble(dati[2]);
			double peso = Double.parseDouble(dati[3]);
			MATERIALE m = MATERIALE.valueOf(dati[4]);
			String genere = dati[5];
			double raggio = Double.parseDouble(dati[6]);
			boolean pietra = Boolean.parseBoolean(dati[7]);
			String nomeGioiello = dati[8];
			
			return new Anello(id,prezzo,peso,m,genere,raggio,pietra,nomeGioiello);
		}
		else if(dati[0].equals("Bracciale"))
		{
			String id = dati[1];
			double prezzo = Double.parseDouble(dati[2]);
			double peso = Double.parseDouble(dati[3]);
			MATERIALE m = MATERIALE.valueOf(dati[4]);
			String genere = dati[5];
			double lunghezza = Double.parseDouble(dati[6]);
			double spessore = Double.parseDouble(dati[7]);
			double larghezza = Double.parseDouble(dati[8]);
			String nomeGioiello = dati[9];
			
			return new Bracciale(id,prezzo,peso,m,genere,lunghezza,spessore,larghezza,nomeGioiello);
		}
		
		return null;
	}
	
	public String getNomeGioiello() { return this.nomeGioiello; }
	
	public boolean getVenduto() {return this.venduto;}
	public void setVenduto(boolean venduto) { this.venduto = venduto;}
	
	public String getId() { return this.id; }
	public void setId(String id) { this.id = id; }
	
	public double getPrezzo() { return prezzo; }
	public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

	public double getPeso() { return peso; }
	public void setPeso(double peso) { this.peso = peso; }

	public MATERIALE getMateriale() { return materiale; }
	public void setMateriale(MATERIALE materiale) { this.materiale = materiale; }

	public String getGenere() { return genere; }
	public void setGenere(String genere) { this.genere = genere; }
	
	public String getTipo() { return this.tipoGioiello;}
	
	public abstract String save();
}
