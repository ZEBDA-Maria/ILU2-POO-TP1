package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;


public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.nom = nom;
		this.marche= new Marche(nbEtal);
		villageois = new Gaulois[nbVillageoisMaximum];
		
	}

	private static class Marche {
		private Etal[] etals;
		int nbEtal;
		
		public Marche(int nbElt) {
			etals = new Etal[nbElt];
			for (int i=0; i < nbElt; i++) {
				etals[i]=new Etal();
			}
		}

		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal < 0) {
			}
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		public int trouverEtalLibre() {
			int indiceEtalD=-1;
			for(int i=0 ; i < etals.length && indiceEtalD == -1 ; i++) {
				if(!etals[i].isEtalOccupe()) {
				indiceEtalD =i;
			}
				
		}
		return indiceEtalD;	
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtal =0;
			for (Etal etal :etals) {
				if (etal.isEtalOccupe() && etal.contientProduit(produit)) {
					nbEtal ++;
				}
			}
			Etal[] etalsProduitRecherche = new Etal[nbEtal];
			int nbTrouve =0;
			for (int i =0 ;i < etals.length && nbTrouve < nbEtal; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalsProduitRecherche[nbTrouve]= etals[i];
					nbTrouve ++;
				}
			}
			return etalsProduitRecherche;
		}
		
		 Etal trouverVendeur(Gaulois gaulois) {
			 for(int i=0 ; i < etals.length;i++) {
				 if (etals[i].getVendeur()==gaulois) {
					 return etals[i];
				 }
			 }
			 return null;
		 }
		 
		 private String afficherMarche(){
			int nbEtalVide = 0;
			StringBuilder chaine =new StringBuilder();
			for (int i =0 ;i<etals.length ;i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else {
					nbEtalVide ++;
				}
			}
			chaine.append("Il reste "+ nbEtalVide +" étals non utilisés dans le marché.\n");
			return chaine.toString();
		 }
	}
		
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtalL = marche.trouverEtalLibre();

		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");

		if (indiceEtalL != -1) {
			marche.utiliserEtal(indiceEtalL, vendeur, produit, nbProduit);
			chaine.append("le vendeur " + vendeur.getNom() + " vend des " + produit + " " + " à l'étal n° "
					+ (indiceEtalL + 1));

		} else {
			chaine.append("impossible de s'installer\n");
		}
		return chaine.toString();

	}
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
	    Etal[] vendeursProduit = marche.trouverEtals(produit);

	    if (vendeursProduit.length == 0) {
	        chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
	    } else if (vendeursProduit.length == 1) {
	        chaine.append("Seul le vendeur " + vendeursProduit[0].getVendeur().getNom() + " propose des " + produit + " au marché.\n");
	    } else {
	        chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
	        for (int i = 0; i < vendeursProduit.length; i++) {
	            chaine.append("- " + vendeursProduit[i].getVendeur().getNom() + "\n");
	        }
	    }

	    return chaine.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur);
		StringBuilder chaine = new StringBuilder();
		chaine.append(etal.libererEtal());
		return chaine.toString();

	}

	public String afficherMarche() {

		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village  le village des irréductibles possède plusieurs étals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (chef==null) {
			throw new IllegalArgumentException("le chef ne doit pas etre null");
		}
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		
		return chaine.toString();
	}
}