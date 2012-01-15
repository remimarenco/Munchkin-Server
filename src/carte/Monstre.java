package carte;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Combat;
import partie.Partie;
import comportement.Condition;
import comportement.IncidentDeguerpir;
import comportement.IncidentFacheux;
import comportement.MonstreVaincu;

/**
 * Classe Monstre.
 * Permet de créer des monstres.
 * Hérite de la classe Donjon
 * @author Rémi Marenco
 */
public class Monstre extends Donjon {
    
    protected int puissance;
    protected int bonusPuissance;

    /**
     * Constructeur de la classe Monstre. Hérite de la classe Donjon
     * @param nom
     * @param description
     * @param condition
     * @param incidentFacheux
     */
    public Monstre(int id,String nom, String description, Condition condition, IncidentFacheux incidentFacheux, MonstreVaincu monstreVaincu, IncidentDeguerpir incidentDeguerpir, int puissance) {
        super(id,nom, description);
        this.condition          = condition;
        this.incidentFacheux    = incidentFacheux;
        this.monstreVaincu      = monstreVaincu;
        this.incidentDeguerpir  = incidentDeguerpir;
        this.puissance          = puissance;
        this.bonusPuissance     = 0;
    }
    
    /**
     * IncidentFacheux d'une carte => valable seulement si c'est un monstre
     * Résultat du design pattern Strategy
     */
    protected IncidentFacheux incidentFacheux;
    
    /**
     * Condition d'une carte => valable seulement si c'est un monstre
     * Résultat du design pattern Strategy
     */
    protected Condition condition;
    
    /**
     * MonstreVaincu d'une carte
     * Résultat du design pattern Strategy
     */
    protected MonstreVaincu monstreVaincu;
    
    /**
     * IncidentDeguerpir d'une carte
     * Résultat du design pattern Strategy
     */
    protected IncidentDeguerpir incidentDeguerpir;

    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getBonusPuissance() {
        return bonusPuissance;
    }

    public void setBonusPuissance(int bonusPuissance) {
        this.bonusPuissance = bonusPuissance;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    public void setIncidentFacheux(IncidentFacheux incidentFacheux) {
        this.incidentFacheux = incidentFacheux;
    }
    // ================================== //
    
    /**
     * Permet de lancer le comportement incidentFacheux d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerIncidentFacheux(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie){
        if(this.incidentFacheux != null)
            return this.incidentFacheux.action(joueurEmetteur, joueurDestinataire, partie);
        else
            return "Cette carte n'a pas d'incident facheux\n";
    }
    
    /**
     * Permet de lancer le comportement condition d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerCondition(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie){
        if(this.condition != null)
            return this.condition.action(joueurEmetteur, joueurDestinataire, partie);
        else
            return "Cette carte n'a pas de condition\n";
    }
    
    /**
     * Permet de lancer le comportement monstreVaincu d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerMonstreVaincu(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie){
        String out = "";
    	if(this.monstreVaincu != null)
            out += this.monstreVaincu.action(joueurEmetteur, joueurDestinataire, partie);
        else
            out += "Cette carte n'a pas de résultat d'un monstre vaincu";
        return out;
    }
}