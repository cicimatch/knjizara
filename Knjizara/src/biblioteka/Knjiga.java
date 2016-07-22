package biblioteka;

public class Knjiga {
	private int broj;
	private String ime;
	private String autor;
	private String polica;
	private int godina;
	//konstruktor bez parametara
	public Knjiga() {
		
	}
	//konstruktor sa parametrima
	public Knjiga(int broj, String ime, String autor, String polica, int godina) {
		this.broj = broj;
		this.ime = ime;
		this.autor = autor;
		this.polica = polica;
		this.godina = godina;
	}
	//geteri i seteri
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getPolica() {
		return polica;
	}
	public void setPolica(String polica) {
		this.polica = polica;
	}
	public int getGodina() {
		return godina;
	}
	public void setGodina(int godina) {
		this.godina = godina;
	}
	//toString opcija
	/*public String toString() {
		return broj + " " + ime + " " + autor + " " + polica + " " + godina  \n";
	}*/
	public String toString() {
		return String.format("%15s %20s %15s %15s %15s", broj, ime, autor, polica, godina);
	}
	public static void main(String[] args) {
		
		Knjiga knjiga = new Knjiga(1, "Na Drini Cuprija", "Ivo Andric", "13B", 1934);
		System.out.println(knjiga);

	}
	
	
	
	

}
