package carte;

import action.DefausserCarte;
import comportement.ComportementDefausserCarte;
import comportement.Condition;
import comportement.Equipement;
import comportement.IncidentDeguerpir;
import comportement.IncidentFacheux;
import comportement.MonstreVaincu;
import comportement.Sortilege;
import comportement.UtiliserCarte;
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
     */
    protected IncidentFacheux incidentFacheux;

    /**
     * Equipement d'une carte => valable seulement si c'est un objet
     * Résultat du design pattern Strategy
     */
    protected Equipement equipement;

    /**
     * Sortilege d'une carte => valable seulement si c'est un Sort
     * Résultat du design pattern Strategy
     */
    protected Sortilege sortilege;

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
    
    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public  Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
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
    public String appliquerIncidentFacheux(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours){
        if(this.incidentFacheux != null)
            return this.incidentFacheux.actionIncidentFacheux(joueurImpacte, nomPhase, joueurEnCours);
        else
            return "Cette carte n'a pas d'incident facheux\n";
    }

    /**
     * Permet de lancer le comportement equipement d'une carte objet
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param equipement
     */
    public String equiper(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours){
        if(this.equipement != null)
            return equipement.equipe(joueurImpacte, nomPhase, joueurEnCours);
        else
            return "Cette carte n'a pas d'équipement\n";
    }

    /**
     * Permet de lancer le comportement condition d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerCondition(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours){
        if(this.condition != null)
            return this.condition.mettreCondition(joueurImpacte, nomPhase, joueurEnCours);
        else
            return "Cette carte n'a pas de condition\n";
    }

    /**
     * Permet de lancer le comportement Sortilege d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * TODO : Faire un hashmap de cibles/destinations => Personnages + Monstres
     * ex   : Joueur A lance le sortilège qui s'applique à tous les personnages sauf lui
     * ex 2 : Joueur B lance le sortilège qui s'applique à tous les monstres en jeu
     * @param joueurImpacte
     */
    public String appliquerSortilege(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours){
        if(this.sortilege != null)
            return this.sortilege.actionSortilege(joueurImpacte, nomPhase, joueurEnCours);
        else
            return "Cette carte n'a pas de malus\n";
    }

    /**
     * Permet de lancer le comportement monstreVaincu d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * @param joueurImpacte
     */
    public String appliquerMonstreVaincu(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours){
        String out = "";
    	if(this.monstreVaincu != null)
            out += this.monstreVaincu.actionMonstreVaincu(joueurImpacte, nomPhase, joueurEnCours);
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
