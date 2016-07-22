package biblioteka;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Test {
	
	public static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		Biblioteka biblioteka = new Biblioteka();
		if(Files.exists(Paths.get("knjige.txt"))) {
			biblioteka.load("knjige.txt");
		}
		String answer = null;
		
		do {
			
			System.out.println("Meni:");
			System.out.println("1. Unesi knjigu");
			System.out.println("2. Ispis svih knjiga");
			System.out.println("3. Brisanje knjiga");
			System.out.println("4. Izmena knjige");
			System.out.println("5. Ispis knjiga po imenu");
			System.out.println("6. Ispis knjige po autoru");
			System.out.println("7. Ispis knjige po polici, nazivu i godini");
			System.out.println("8. Ispis knjige po zadatom imenu i sortiranje po azbuci");
			System.out.println("x. Izlaz");
			
			answer = scanner.nextLine();
			
			switch (answer) {
			case "1":
				dodajKnjigu(biblioteka);
				break;
			case "2":
				ispisKnjiga(biblioteka);
				break;
			case "3":
				brisanjeKnjiga(biblioteka);
				break;
			case "4":
				izmenaKnjiga(biblioteka);
				break;
			case "5":
				knjigaPoImenu(biblioteka);
				break;
			case "6":
				knjigaPoAutoru(biblioteka);
				break;
			case "7":
				knjigaPoPoliciGodini(biblioteka);
				break;
			case "8":
				knjigaSortiraj(biblioteka);
				break;
			case "x":
				break;
			default:
				System.out.println("Pogresan izbor opcije. Pokusajte ponovo.");
			}
			
		} while (!answer.equals("x"));
		
		biblioteka.save("knjige.txt");
		scanner.close();	
	}
	//metoda da li je uneti string sa tastature ceo broj
	public static boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	//unos knjiga
	public static void dodajKnjigu(Biblioteka biblioteka) {
		int broj;
		String ime;
		String autor;
		String polica;
		int godina;
		String brojKnjige = null;
		do {
			System.out.println("Broj knjige: ");
			brojKnjige = scanner.nextLine();
		} while (!isNumber(brojKnjige));
		broj = Integer.parseInt(brojKnjige);
		System.out.println("Naslov, naziv: ");
		ime = scanner.nextLine();
		System.out.println("Ime autora: ");
		autor = scanner.nextLine();
		System.out.println("Polica na kojoj se nalazi: ");
		polica = scanner.nextLine();
		String godinaKnjige = null;
		do {
			System.out.print("Godina knjige: ");
			godinaKnjige = scanner.nextLine();
		} while(!isNumber(godinaKnjige));
		godina = Integer.parseInt(godinaKnjige);
				
		Knjiga novaKnjiga = new Knjiga(broj, ime, autor, polica, godina);
		boolean provera = biblioteka.dodajKnjigu(novaKnjiga);
		if(provera == true) {
			System.out.println("Knjiga je uspesno dodata.");
		} else {
			System.out.println("Knjiga nije dodata.");
		}
	}
	//ispis knjiga
		public static void ispisKnjiga(Biblioteka biblioteka) {
			biblioteka.ispisKnjiga();
		}
	//brisanje knjiga
	public static void brisanjeKnjiga(Biblioteka biblioteka) {
		int broj;
		String brojKnjige = null;
		do {
			System.out.println("Identifikacioni broj za brisanje");
			brojKnjige = scanner.nextLine();
		} while(!isNumber(brojKnjige));
		broj = Integer.valueOf(brojKnjige);
		Knjiga provera = biblioteka.brisanjeKnjiga(broj);
		if(provera == null) {
			System.out.println("Knjiga sa zadatim brojem ne postoji!");
		}
	}
	//izmena knjiga po broj-u, sa svim ostalim novim vrednostima
    public static void izmenaKnjiga(Biblioteka biblioteka) {
		
		String brojKnjiga = null;
		do {
			System.out.println("Unesite identifikacioni broj knjige: ");
			brojKnjiga = scanner.nextLine();
		} while(!isNumber(brojKnjiga));
		int broj = Integer.valueOf(brojKnjiga);
		String ime;
		String autor;
		String polica;
		int godina;
		System.out.print("Naslov knjige: ");
		ime = scanner.nextLine();
		System.out.print("Autor dela: ");
		autor = scanner.nextLine();
		System.out.print("Polica na kojoj se nalazi knjiga: ");
		polica = scanner.nextLine();
		String god = null;
		do {
			System.out.print("Godina proizvodnje knjige: ");
			god = scanner.nextLine();
		} while(!isNumber(god));
		godina = Integer.valueOf(god);
		Knjiga knjiga = new Knjiga(broj, ime, autor, polica, godina);
		Knjiga provera = biblioteka.izmenaKnjiga(broj, knjiga);
		if(provera != null) {
			System.out.println("Izmena je uspešno izvršena.");
		} else {
			System.out.println("Izmena je neuspešno izvršena.");
		}
	}
	//knjiga po imenu
	public static void knjigaPoImenu(Biblioteka biblioteka) {
		System.out.println("Unesite naziv dela: ");
		String ime = scanner.nextLine();
		biblioteka.knjigaPoImenu(ime);
	}
	//knjiga po autoru
	public static void knjigaPoAutoru(Biblioteka biblioteka) {
		System.out.println("Unesite ime pisca: ");
		String autor = scanner.nextLine();
		biblioteka.knjigaPoAutoru(autor);
	}
	//knjiga po polici minimalnoj i maximalnoj vrednosti godina
	public static void knjigaPoPoliciGodini(Biblioteka biblioteka) {
		
		System.out.println("Unesite broj police na kojoj se nalazi: ");
		String polica = scanner.nextLine();
		String minVrednost = null;
		do {
			System.out.println("Unesite donju granicu godine izdanja: ");
			minVrednost = scanner.nextLine();
		} while(!isNumber(minVrednost));
		int donjaProcenjenaVrednost = Integer.parseInt(minVrednost);
		String maxVrednost = null;
		do {
			System.out.println("Unesite gornju granicu godine izdanja: ");
			maxVrednost = scanner.nextLine();
		} while(!isNumber(maxVrednost));
		
		int gornjaProcenjenaVrednost = Integer.parseInt(maxVrednost);
		biblioteka.knjigaPoPoliciGodini(polica, donjaProcenjenaVrednost, gornjaProcenjenaVrednost);
	}
	//sortiranje knjiga po broju
	public static void knjigaSortiraj(Biblioteka biblioteka) {
		biblioteka.sortiraj();
	}
}
