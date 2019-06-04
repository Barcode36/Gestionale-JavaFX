package models;

public class Anello extends Gioiello
{
	private double raggio;
	boolean pietra;
	public Anello(double prezzo, double peso, MATERIALE materiale, String genere,boolean venduto, double raggio, boolean pietra, String nomeGioiello,String descrizione)
	{
		super(prezzo,peso,materiale,genere,venduto,descrizione);
		this.raggio = raggio;
		this.pietra = pietra;
		this.nomeGioiello = nomeGioiello;
	}
	
	public double getRaggio() { return raggio; }
	public void setRaggio(double raggio) { this.raggio = raggio; }
	
	public boolean isPietra() { return pietra; }
	public void setPietra(boolean pietra) { this.pietra = pietra; }
	
	@Override
	public String toString() { return this.nomeGioiello; }
	
	@Override
	public String stampaCaratteristiche()
	{
		String venduto;
		if(getVenduto()) venduto = "Si";
		else venduto = "No";
		
		String haPietra;
		if(pietra) haPietra = "Si";
		else haPietra = "No";
		
		return "Id: " + getId() + "\n"
				+"Prezzo: "+getPrezzo()+"\n"
				+"Peso: "+getPeso()+"\n"
				+"Materiale: "+ getMateriale()+"\n"
				+"Genere: "+getGenere()+"\n"
				+"Venduto: "+venduto+"\n"
				+"Diametro: "+raggio+"\n"
				+"Pietra: "+haPietra+"\n"
				+"Descrizione: \n"+getDescrizione();
	}
}
