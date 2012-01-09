package carte;

import comportement.Condition;
import comportement.Equipement;
import comportement.IncidentDeguerpir;
import comportement.IncidentFacheux;
import comportement.MonstreVaincu;
import comportement.Sortilege;
import joueur.Joueur;


/**
 * Classe carte permettant de gérer toutes les cartes du jeu
 * @author Guillaume Renoult
 */
public abstract class Carte {
    
    protected Integer id;           // id de la carte, reference sur serveur et client
    protected String nom;
    protected String description;   // Description de la carte, correspondante à la réalité

    /**
     * IncidentFacheux d'une carte => valable seulement si c'est un monstre
     * Résultat du design pattern Strategy
     * TODO : Réfléchir si toujours d'actualité (Classe mère connait fille)
     */
    protected IncidentFacheux incidentFacheux;

    /**
     * Equipement d'une carte => valable seulement si c'est un objet
     * Résultat du design pattern Strategy
     * TODO : Réfléchir si toujours d'actualité (Classe mère connait fille)
     */
    protected Equipement equipement;

    /**
     * Sortilege d'une carte => valable seulement si c'est un Sort
     * Résultat du design pattern Strategy
     * TODO : Réfléchir si toujours d'actualité (Classe mère connait fille)
     */
    protected Sortilege sortilege;

    /**
     * Condition d'une carte => valable seulement si c'est un monstre
     * Résultat du design pattern Strategy
     * TODO : Réfléchir si toujours d'actualité (Classe mère connait fille)
     */
    protected Condition condition;

    /**
     * MonstreVaincu d'une carte => valable seulement si c'est un
     * Résultat du design pattern Strategy
     * TODO : Réfléchir si toujours d'actualité (Classe mère connait fille)
     */
    protected MonstreVaincu monstreVaincu;

    /**
     * IncidentDeguerpir d'une carte => valable seulement si c'est un
     * Résultat du design pattern Strategy
     * TODO : Réfléchir si toujours d'actualité (Classe mère connait fille)
     */
    protected IncidentDeguerpir incidentDeguerpir;

    
    /**
     * Constructeur de la carte
     * @param id de la carte
     * @param nom de la carte
     * @param description de la carte
     */
    public Carte(int id, String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.id = id;
    }
    
    /**
     * // TODO : Commenter
     * @return 
     */
    public  Integer getId() {
        return id;
    }

    /**
     * // TODO : Commenter
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /**
     * // TODO : Commenter
     * @return 
     */
    public String getNom() {
        return nom;
    }

    
    /**
     * // TODO : Commenter
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /**
     * // TODO : Commenter
     * @return 
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * // TODO : Commenter
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    /**
     * Permet de modifier le comportement equipement d'une carte objet
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param equipement
     */
    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    
    /**
     * Permet de modifier le comportement incidentFacheux d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param incidentFacheux
     */
    public void setIncidentFacheux(IncidentFacheux incidentFacheux) {
        this.incidentFacheux = incidentFacheux;
    }

    
    /**
     * Permet de lancer le comportement incidentFacheux d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerIncidentFacheux(Joueur joueurImpacte){
        if(this.incidentFacheux != null)
            return this.incidentFacheux.actionIncidentFacheux(joueurImpacte);
        else
            return "Cette carte n'a pas d'incident facheux\n";
    }

    /**
     * Permet de lancer le comportement equipement d'une carte objet
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param equipement
     */
    public String equiper(Joueur joueurImpacte){
        if(this.equipement != null)
            return equipement.equipe(joueurImpacte);
        else
            return "Cette carte n'a pas d'équipement\n";
    }

    /**
     * Permet de lancer le comportement condition d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerCondition(Joueur joueurImpacte){
        if(this.condition != null)
            return this.condition.mettreCondition(joueurImpacte);
        else
            return "Cette carte n'a pas de condition\n";
    }

    /**
     * Permet de lancer le comportement Sortilege d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerSortilege(Joueur joueurImpacte){
        if(this.sortilege != null)
            return this.sortilege.actionSortilege(joueurImpacte);
        else
            return "Cette carte n'a pas de malus\n";
    }

    /**
     * Permet de lancer le comportement monstreVaincu d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerMonstreVaincu(Joueur joueurImpacte){
        String out = "";
    	if(this.monstreVaincu != null)
            out += this.monstreVaincu.actionMonstreVaincu(joueurImpacte);
        else
            out += "Cette carte n'a pas de résultat d'un monstre vaincu";
        return out;
    }

    /**
     * Retourne le nom de la carte
     * @return this.nom
     */
    @Override
    public String toString(){
        return this.nom;
    }
}
