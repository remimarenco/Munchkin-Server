package joueur;

public class Joueur {
	
	private Main main;
	private Jeu jeu;
        private String nom;
	private Personnage personnage;

        
        public Joueur() {
            this.main = new Main();
            this.jeu = new Jeu();
            this.nom = new String();
            this.personnage = new Personnage();
        }
	
        
        
        
        
	public String getNom() {
		return nom;
	}
	

}
