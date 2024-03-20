package histoire;

import personnages.Gaulois;
import villagegaulois.Etal ;
public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Gaulois obelix = new Gaulois("Obélix", 25);
		obelix=null;
		Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test");


		try {
			etal.acheterProduit(0, null);
			System.out.println("Fin du test");
		}
		catch (IllegalArgumentException e) {
			System.out.println("l'acheteur ne doit pas etre null");
		}
		try {
			etal.acheterProduit(1, obelix);
			System.out.println("Fin du test");
		}
		catch (IllegalArgumentException e2) {
			//e2.printStackTrace();
			System.out.println(e2.getMessage());
		}
		
	}
	
}
