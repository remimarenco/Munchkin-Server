package carte;
import java.util.ArrayList;

import joueur.Joueur;
import partie.Combat;
import comportement.ComportementDefausserCarte;
import comportement.Equipement;
import comportement.UtiliserCarte;


/**
 * Classe Objet.
 * Hérite de la classe trésor.
 * Permet de renseigner les objets que les personnages pourront équiper
 * @author Rémi Marenco
 */
public class Objet extends Tresor {
	/**
     * Equipement d'une carte => valable seulement si c'est un objet
     * Résultat du design pattern Strategy
     */
    protected Equipement equipement;
    
    /**
     * Utilisation de compétence d'une carte
     * Résultat du design pattern Strategy
     */
    protected UtiliserCarte utiliserCarte;
    
    /**
     * Appliquer effet de la défausse d'une carte
     * Résultat du design pattern Strategy
     */
    protected ComportementDefausserCarte defausserCarte;
    
    /**
     * Constructeur de la classe objet.
     * Permet de renseigner les paramètres de la classe Trésor ainsi que l'équipement
     * apporté par la carte
     * @param id
     * @param nom
     * @param description
     * @param equipement
     * @param utiliser
     * @param defausser
     */
    public Objet(int id,String nom, String description, Equipement equipement, UtiliserCarte utiliser, ComportementDefausserCarte defausser) {
        super(id,nom, description);
        this.equipement     = equipement;
        this.utiliserCarte  = utiliser;
        this.defausserCarte = defausser;
    }
    
    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }
    
    /**
     * Permet de lancer le comportement equipement d'une carte objet
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param equipement
     */
    public String equiper(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Combat combatCible, int phaseTour, Joueur joueurTourEnCours){
        if(this.equipement != null)
            return equipement.equipe(joueurEmetteur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
        else
            return "Cette carte n'a pas d'équipement\n";
    }
    
    /**
     * Permet de lancer le comportement de desequipement d'une carte objet
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param equipement
     */
    public String desequiper(Joueur joueurDestinateur, ArrayList<Joueur> joueurDestinataire, Combat combatCible, int phaseTour, Joueur joueurTourEnCours){
        if(this.defausserCarte != null)
            return defausserCarte.defausserCarte(joueurDestinateur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
        else
            return "Cette carte n'a pas de comportement defausser carte\n";
    }
}
