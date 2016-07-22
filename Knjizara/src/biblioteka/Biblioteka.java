package biblioteka;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;



public class Biblioteka {
	private ArrayList<Knjiga> lista;
	
	//konstruktor bez parametara
	public Biblioteka() {
		lista = new ArrayList<Knjiga>();
	}
	public ArrayList<Knjiga> getLista() {
		return lista;
	}
	public void setLista(ArrayList<Knjiga> lista) {
		this.lista = lista;
	}
	
	//metoda dodavanja u listu
	public boolean dodajKnjigu(Knjiga knjiga) {
		for(int i = 0; i < this.lista.size(); i++) {
			if ((this.lista.get(i)).getBroj() == knjiga.getBroj()) {
				return false;
			}
		}
		Knjiga novaKnjiga = new Knjiga(knjiga.getBroj(), knjiga.getIme(), knjiga.getAutor(),knjiga.getPolica(),knjiga.getGodina());
		boolean daLiJeDodat = this.lista.add(novaKnjiga);
		return daLiJeDodat;
	}
	//metoda za ispis inventara
	public void ispisKnjiga() {
		System.out.printf("%15s %20s %20s %15s %15s \n", "Broj", "Naslov", "Autor", "Polica", "Godina");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < this.lista.size(); i++) {
			Knjiga knjiga = lista.get(i);
			System.out.println(knjiga);
		}
	}
	//metoda brisanja knjiga
	public Knjiga brisanjeKnjiga(int broj) {
		int index = -1;
		for(int i = 0; i < this.lista.size(); i++) {
			Knjiga knjigaIzListe = this.lista.get(i);
			int brojKnjigeIzListe = knjigaIzListe.getBroj();
			if(brojKnjigeIzListe == broj) {
				index = i;
			}
	}
		if(index != -1) {
			Knjiga knjigaKojaSeBrise = this.lista.remove(index);
			return knjigaKojaSeBrise;
		}
	return null;
	}
	//metoda save
	public void save(String path) {
		ArrayList<String> lines = new ArrayList<String>();
		for(int i = 0; i < this.lista.size(); i++) {
			Knjiga knjiga = this.lista.get(i);
			int identifikacioniBroj = knjiga.getBroj();
			String imeKnjige = knjiga.getIme();
			String autorKnjige = knjiga.getAutor();
			String polica = knjiga.getPolica();
			int godina = knjiga.getGodina();
			String line = identifikacioniBroj + ";" + imeKnjige + ";" + autorKnjige + ";" + polica + ";" + godina;
			lines.add(line);
		}
		try {
			if(!Files.exists(Paths.get(path))){
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE_NEW);
			} else {
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
			e.printStackTrace();
		}
	}
	//metoda load
	public void load(String path) {
			
		this.lista = new ArrayList<Knjiga>();
			
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			for (String line: lines) {
				String[] attributes = line.split(";");
				int broj = Integer.parseInt(attributes[0]); 
				String ime = attributes[1];
				String autor = attributes[2]; 
				String polica = attributes[3]; 
				int godina = Integer.parseInt(attributes[4]);
				Knjiga knjiga = new Knjiga(broj, ime, autor, polica, godina);
				this.lista.add(knjiga);
					
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
			e.printStackTrace();
		}
	}
	// Metoda menja inventar sa prosledjenim identifikacionim brojem, moze imati sve nove vrednosti osim id-a
	public Knjiga izmenaKnjiga(int broj, Knjiga knjiga) {
		for (int i = 0; i < this.lista.size(); i++) {
			if((this.lista.get(i)).getBroj() == broj) {
				return this.lista.set(i, knjiga);
		   }
	   }
		return null;
	}
	//metoda knjiga po imenu
	public void knjigaPoImenu(String ime) {
		System.out.printf("%15s %20s %20s %15s %15s \n", "Broj", "Naslov", "Autor", "Polica", "Godina");
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getIme().equalsIgnoreCase(ime)) {
				System.out.println(this.lista.get(i));
			}
		}
	}
	//metoda knjiga po autoru
	public void knjigaPoAutoru(String autor) {
		System.out.printf("%15s %20s %20s %15s %15s \n", "Broj", "Naslov", "Autor", "Polica", "Godina");
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getAutor().equalsIgnoreCase(autor)) {
				System.out.println(this.lista.get(i));
			}
		}
	}
	//metoda knjiga po polici i godini proseka
	public void knjigaPoPoliciGodini(String polica, double minVrednost, double maxVrednost) {
		System.out.printf("%15s %20s %20s %15s %15s \n", "Broj", "Naslov", "Autor", "Polica", "Godina");
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getPolica().equalsIgnoreCase(polica) &&
					this.lista.get(i).getGodina() >= minVrednost &&
					this.lista.get(i).getGodina() <= maxVrednost) {
				System.out.println(this.lista.get(i));
			}
		}
	}
	//Metoda sortira knjige po broju u rastucem redosledu.
	public void sortiraj() {
			for(int i = 0; i < this.lista.size()-1; i++) {
				for(int j = i; j < this.lista.size(); j++) {
					if(this.lista.get(i).getGodina() > this.lista.get(j).getGodina()){
						Knjiga temp = this.lista.get(i);
						this.lista.set(i, this.lista.get(j));
						this.lista.set(j, temp);
					}
				}
			}
		}
	
	
	
	
	
	

}
