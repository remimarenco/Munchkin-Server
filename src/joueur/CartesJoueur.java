package joueur;

import carte.Carte;
import carte.Monstre;
import java.util.ArrayList;
import java.util.HashMap;
import partie.Constante;

/**
 * Un ensemble de cartes pour les joueurs (main ou jeu)
 * @author Julien Rouvier
 */
public class CartesJoueur {
    
    protected ArrayList<Carte> cartes;
    protected Joueur joueur;

    
    /**
     * Constructeur
     * @param cartes
     * @param joueur 
     */
    public CartesJoueur(ArrayList<Carte> cartes, Joueur joueur) {
        this.cartes = cartes;
        this.joueur = joueur;
    }

    
    /**
     * Constructeur
     */
    public CartesJoueur() {
        cartes = new ArrayList<Carte>();
    }

    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public ArrayList<Carte> getCartes() {
        return cartes;
    }
    // ================================== //
    
    
    
    /**
     * Ajout d'une carte dans le tas
     * @param c : carte à ajouter
     * @return boolean : indique si l'ajout s'est bien passé
     */
    public boolean ajouterCarte(Carte c){
        cartes.add(c);
        return true;
    }
    
    
    /**
     * Supprime une carte du tas
     * @param c : carte a supprime
     * @return boolean : indique si la suppression s'est bien passé
     */
    public boolean supprimerCarte(Carte c){
        return cartes.remove(c);
    }
    
    
    /**
     * Retourne une carte aléatoire du tas
     * @return Carte : une carte du tas
     */
    public Carte getRandomCarte(){
        int valeur = Constante.nbAleatoire(0, cartes.size());
        // Si le joueur n'a plus de carte en main
        if(cartes.isEmpty())
            return null;
        return cartes.get(valeur);
    }    

    
    /**
     * Regarde si une carte appartient au tas
     * @param c : la carte que l'on cherche
     * @return boolean : true si la carte est trouvée, false sinon
     */
    public boolean contains(Carte c){
        return this.cartes.contains(c);
    }
    
    
    /**
     * Prépare les données pour envoi au client
     * @return HashMap<String,String> infos sur les cartes 
     */
    public HashMap<String,String> generateInfos(){
        HashMap<String,String> map = new HashMap<String, String>();
        for(Carte c : this.cartes){
            map.put(c.getId().toString(), c.getId().toString());           
        }
        return map;
    }
    
    
    /**
     * Retourne l'ensemble des cartes qu'un joueur peut poser (passer de sa main à son jeu)
     * // TODO : Vérifier le comportement, notamment pourquoi les race & classe sont
     * // renvoyées alors qu'avec ce test elles ne devraient pas l'être
     * @return 
     */
    public HashMap<String,String> getCartesPosables(){
        HashMap<String,String> map=new HashMap<String, String>();
        for(Carte c : this.cartes)
            if(c.getClass().getSimpleName().equals("Objet"))
                map.put(c.getId().toString(), c.getId().toString());
        return map;
    }
    /**
     * Retourne l'ensemble des cartes qu'un joueur peut jouer pour AIDER un autre joueur(passer de sa main à son jeu)
     * // TODO : Vérifier le comportement, notamment pourquoi les race & classe sont
     * // renvoyées alors qu'avec ce test elles ne devraient pas l'être
     * @return
     */
    public HashMap<String,String> getCartesJouablePourAide(){
        HashMap<String,String> map=new HashMap<String, String>();
        for(Carte c : this.cartes)
            if(c instanceof Monstre)
                map.put(c.getId().toString(), c.getId().toString());
        return map;
    }
    /**
     * Retourne l'ensemble des cartes qu'un joueur peut jouer pour POURRIR un autre joueur(passer de sa main à son jeu)
     * // TODO : Vérifier le comportement, notamment pourquoi les race & classe sont
     * // renvoyées alors qu'avec ce test elles ne devraient pas l'être
     * @return
     */
    public HashMap<String,String> getCartesJouablePourPourrir(){
        HashMap<String,String> map=new HashMap<String, String>();
        for(Carte c : this.cartes)
            if(c.getClass().getSimpleName().equals("Objet"))
                map.put(c.getId().toString(), c.getId().toString());
        return map;
    }
}