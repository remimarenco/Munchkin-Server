package partie;

import action.Action;
import action.ChangerClasse;
import action.ChangerNiveau;
import action.ChangerRace;
import action.ChangerSexe;
import action.DefausserCarte;
import action.EquiperObjet;
import action.ModifDeguerpir;
import action.ModifNbMaxEquipement;
import action.ModifPuissanceCampChoisi;
import action.ModifPuissanceMonstre;
import action.ModifPuissancePersonnage;
import action.PiocherCarte;
import carte.Carte;
import carte.Monstre;
import carte.Objet;
import carte.Malediction;
import carte.Sort;
import comportement.ComportementDefausserCarte;
import comportement.Condition;
import comportement.Equipement;
import comportement.IncidentDeguerpir;
import comportement.IncidentFacheux;
import comportement.MonstreVaincu;
import comportement.Sortilege;
import comportement.UtiliserCarte;

import java.util.ArrayList;
import java.util.Collections;

import joueur.Classe;
import joueur.Race;


/**
 * Ensemble de toutes les cartes du jeu Munchkin
 * Permet de pouvoir accéder à toutes les cartes quelque soit l'endroit ou elles
 * se trouvent (main ou jeu de n'importe quel joueur, pioches, défausses)
 * @author Julien Rouvier
 */
public final class Deck {
    private static ArrayList<Carte> cartes;

    /**
     * Constructeur
     */
    public Deck() {
        cartes = new ArrayList<Carte>();
        this.load();            // Créé l'ensemble des cartes du jeu
        cartes = melanger();    // Mélange les cartes
    }

    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public static ArrayList<Carte> getCartes() {
        return cartes;
    }
    
    public static void setCartes(ArrayList<Carte> cartes) {
        Deck.cartes = cartes;
    }
    // ================================== //
    
    
    /**
     * Renvoit une carte à partir de son ID
     * @param id : ID de la carte recherchée
     * @return Carte : la carte recherchée, null si introuvable
     */
    public static Carte getCardById(Integer id){
        Carte ret = null;
        for(Carte c : cartes)
            if(c.getId().equals(id))
                ret=c;
        
        return ret;
    }
    
    
    /**
     * Mélange l'ensemble des cartes du deck
     * @return ArrayList : ensemble des cartes mélangées
     */
    public static ArrayList melanger(){
        ArrayList nouvelle = new ArrayList(cartes);
        Collections.shuffle(nouvelle);
        return nouvelle; 
    }

    
    /**
     * Permet de charger les cartes dans la piocheDonjon.
     * On définit :
     * - Un vecteur d'action, permettant de référencer les actions de la future carte
     * à  instancier
     * - On ajout à  ce vecteur
     */
    // TODO : Dans l'idéal, cette grosse méthode moche devrait être remplacée
    // par un chargement depuis un XML... Je dis ça je dis rien...
    private void load(){        
        /**
         * Permet de référencer les actions de la carte
         */
    	ArrayList<Action> actionTabCondition = new ArrayList<Action>();
        ArrayList<Action> actionTabIncident = new ArrayList<Action>();
        ArrayList<Action> actionTabMonstreVaincu = new ArrayList<Action>();
        ArrayList<Action> actionTabMalediction = new ArrayList<Action>();
        ArrayList<Action> actionEquipement = new ArrayList<Action>();
        ArrayList<Action> actionDeguerpir = new ArrayList<Action>();
        ArrayList<Action> actionUtiliser = new ArrayList<Action>();
        ArrayList<Action> actionDefausser = new ArrayList<Action>();
        ArrayList<Classe> tabClasse = new ArrayList<Classe>();
        ArrayList<Race> tabRace = new ArrayList<Race>();
        ArrayList<Class> tabClassCarte = new ArrayList<Class>();
        Monstre mstr;
        
        // ===============================
        // ============ DONJON ===========
        // ===============================
        
        // ================
        // === MONSTRES ===
        // ================
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        //nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(-1000, null, null, null, null));
        //cartes.add(new Monstre(1, "Morpions", "Impossible de déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(2, "Rat Musclé", "Créature de l'enfer +3 contre les prêtres", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 1);
        tabClasse.add(Constante.CLASSE_PRETRE);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 3, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);

        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        // FAUX !!
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new  Monstre(3, "Mucus Baveux", "Beerk! +4 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 1);
        tabRace.add(Constante.RACE_ELFE);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 4, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        //cartes.add(mstr);

        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(-1, null, null, null, null));
        cartes.add(new Monstre(4, "Grenouilles volantes", "Malus de 1 pour déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), null);
        cartes.add(new Monstre(5, "Poulet élevé aux stérroïdes", "Frit c'est délicieux", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), null);
        actionDeguerpir.clear();
        actionDeguerpir.add(new ChangerNiveau(-1));
        cartes.add(new Monstre(6, "Mr Nonos", "Si vous devez vous enfuir, vous perdez un niveau, même si vous arrivez à  déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(actionDeguerpir), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        // FAUX !!
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(1, null, null, null, null));
        cartes.add(new Monstre(7, "Octaèdre gélatineux", "+1 au jet pour déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), null);
        actionDeguerpir.clear();
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        actionDeguerpir.add(new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        cartes.add(new Monstre(8, "Pit Bull", "Si vous ne pouvez le vaincre, vous pouvez le distraire(vous déguerpissez automatiquement) en lachant une baguette un baton ou une lance. Va chercher Médor!", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(9, "Harpies", "Résistent à  la magie, +5 Contre les magiciens", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 4);
        tabClasse.add(Constante.CLASSE_MAGICIEN);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 5, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(10, "Cheval Zombi", "+5 contre les nains", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 4);
        tabRace.add(Constante.RACE_NAIN);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 5, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(tabClassCarte, Constante.NB_PAR_DE, Constante.TAS_CHOISIR));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(-2, null, null, null, null));
        cartes.add(new Monstre(11, "Escargot sous acide", "-2 pour déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, 2, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(12, "Lépreuxchaun", "Mais il est dégueu! +5 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 4);
        tabRace.add(Constante.RACE_ELFE);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 5, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        tabClassCarte.clear();
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), new DefausserCarte(tabClassCarte, Constante.NB_TOUT, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(13, "Manticorenithorynque", "Résiste à  la magie, +6 contre les magiciens", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6);
        tabClasse.add(Constante.CLASSE_MAGICIEN);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 6, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        tabClassCarte.clear();
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, Constante.NB_TOUT, Constante.MAIN));
        cartes.add(new Monstre(14, "Gerbausore", "Vous gagnez un niveau supplémentaire si vous le tuer seul et sans utiliser de bonus", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        tabClassCarte.clear();
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, Constante.NB_JOUEUR_MOINS_MOI, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        tabClasse.add(Constante.CLASSE_VOLEUR);
        actionTabCondition.add(new ModifDeguerpir(+1000, null, null, null, tabClasse));
        cartes.add(new Monstre(15, "Huissier", "N'attaque pas les voleurs (entres confrères...). Un voleur qui rencontre un huissier peut choisir de défausser deux cartes trésors et en tirer deux nouvelles", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        // TODO : Gérer correctemnt ce cas
        tabClassCarte.clear();
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(16, "Binoclar Hurleur", "+6 contre les guerriers", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6);
        tabClasse.add(Constante.CLASSE_GUERRIER);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 6, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(tabClassCarte, 1, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(17, "Suceur de tête", "C'est dégueu! +6 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 8);
        tabRace.add(Constante.RACE_ELFE);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 6, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifPuissancePersonnage(null, null, 0, true, false));
        cartes.add(new Monstre(18, "Vamps...Ires!?!", "Aucun objet ne vous servira contre elles, vous combattez uniquement avec votre niveau de personnage", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-3), null);
        // TODO après implémentation de l'aide
        cartes.add(new Monstre(19, "Belvédère Sauvage", "Nul ne peut vous aider, vous devez affronter seul le Belvédère", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        // TODO : Remettre le choix de supprimer CLASSE/RACE
        tabClassCarte.clear();
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(tabClassCarte, 1, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, null, Constante.SEXE_F, null, null));
        actionDeguerpir.clear();
        actionDeguerpir.add(new PiocherCarte(Constante.TRESOR, 1));
        cartes.add(new Monstre(20, "Amazone", "N'attaque ni les joueur féminin, ni les joueur masculin qui ont changé de sexe, mais se content de leur donner un trésor (fuir)", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(actionDeguerpir), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-Constante.NB_PAR_DE), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(21, "3872 Orques", "+6 contre les nains en raison d'une rancune obscure, certes, mais millénaire", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 10);
        tabRace.add(Constante.RACE_NAIN);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 6, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        // FAUX
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, Constante.NB_JOUEUR_MOINS_MOI, Constante.JEU));
        cartes.add(new Monstre(22, "Trôliste", "Il n'a aucun pouvoir et ça le rend furax", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 10));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-3), null);
        actionDeguerpir.clear();
        actionDeguerpir.add(new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        cartes.add(new Monstre(23, "Nez Flottant", "Si vous ne voulez pas combattre le Nez Flottant, achetez le avec un objet. Il vous laissera partir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(actionDeguerpir), 10));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-3), null);
        cartes.add(new Monstre(24, "Fan de Vampire", "Au lieu de le combattre, un prêtre peut chasser le fan de vampire en criant simplement \"bouga bouga!\" et s'emparer de son trésor. Dans ce cas il ne gagne aucun niveau.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 12));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), null);
        // TODO défausser objet a moins qu'on simplifie en mettant défausser a la fin
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(25, "Succube Langue-de-Belle-Mère", "Créature de l'enfer, +4 contre les prêtres. Vous devez défausser un objet (que vous choisissez) avant le combat", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 12);
        tabClasse.add(Constante.CLASSE_PRETRE);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 4, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(26, "Bigfoot, Alias Grand-Pied", "+3 Contre les nains et les Halfelins", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 12);
        tabRace.add(Constante.RACE_NAIN);
        tabRace.add(Constante.RACE_HALFELIN);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 3, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(27, "Horreur non euclidienne indicible", "+4 contre les guerriers", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 14);
        tabClasse.add(Constante.CLASSE_GUERRIER);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 4, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        tabRace.add(Constante.RACE_ELFE);
        tabRace.add(Constante.RACE_HUMAINE);
        tabRace.add(Constante.RACE_NAIN);
        actionTabCondition.add(new ModifDeguerpir(1000, null, null, tabRace, null));
        tabRace.clear();
        tabRace.add(Constante.RACE_HALFELIN);
        actionTabCondition.add(new ModifDeguerpir(-1000, null, null, tabRace, null));
        cartes.add(new Monstre(28, "Golem Fracassé", "Vous pouvez combattre ce golem complètement défoncé ou vous contenter de lui faire coucou et lui laisser son trésor. (Exception: Les savoureux halfelins doivent combattre)", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 14));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, Constante.NB_TOUT, Constante.TYPE_TAS_TOUT));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifPuissancePersonnage(null, null, 0, false, true));
        cartes.add(new Monstre(29, "Représentant en assurance", "Votre niveau ne compte pas, vous le combattez uniquement avec vos bonus", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 14));

        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, Constante.NB_TOUT, Constante.TYPE_TAS_TOUT));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 3, null, null, null));
        actionDeguerpir.clear();
        actionDeguerpir.add(new ChangerNiveau(-2, 3));
        cartes.add(new Monstre(30, "Tut-Tuuut-Ankh-Ammon", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux même si ils réussissent a déguerpir.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(actionDeguerpir), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 3, null, null, null));
        actionDeguerpir.clear();
        actionDeguerpir.add(new ChangerNiveau(-2, 3));
        cartes.add(new Monstre(31, "René Crophage et fils, dépanneurs en chirurgie", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(actionDeguerpir), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        // FAUX 
        tabClassCarte.clear();
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(tabClassCarte, Constante.NB_JOUEUR_MOINS_MOI, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 3, null, null, null));
        cartes.add(new Monstre(32, "Hippogriffe", "Ne poursuit aucun personnage de niveau 3 ou inférieur.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        tabRace.add(Constante.RACE_HALFELIN);
        tabRace.add(Constante.RACE_HUMAINE);
        tabRace.add(Constante.RACE_NAIN);
        actionTabCondition.add(new ModifDeguerpir(+1000, 3, null, tabRace, null));
        tabRace.clear();
        tabRace.add(Constante.RACE_ELFE);
        actionTabCondition.add(new ModifPuissancePersonnage(tabRace, null, -4));
        cartes.add(new Monstre(33, "Céphalopodzilla", "C'est gluant! Les elfes combattent à  -4. Ne poursuivra aucun personnage de niveau 4 ou moins, sauf si c'est un elfe.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 18));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,5), new ChangerNiveau(2));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 4, null, null, null));
        cartes.add(new Monstre(34, "Balrog Charolais", "Ne poursuit aucun personnage de niveau 4 ou inférieur.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 18));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,5), new ChangerNiveau(2));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 5, null, null, null));
        actionDeguerpir.clear();
        actionDeguerpir.add(new ChangerNiveau(-2, 5));
        //cartes.add(new Monstre(35, "Dragon de plutonium", "Ne poursuit aucun personnage de niveau 5 ou inférieur. Les autres perdent 2 niveaux même si ils réussissent a déguerpir.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(actionDeguerpir), 20));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, null, null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1, null, null, null, null));
        cartes.add(new Monstre(93, "Gobelin Estropié", "+1 au jet pour déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, null, null);
        cartes.add(new Monstre(95, "Plante d'ornement", "Les elfes tirent une carte Trésor supplémentaire après l'avoir vaincue.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 1));
	// ================
        // ================
        // ================
        
        
        // =============
        // === MALEDICTION ===
        // =============
      //TODO
        /*cartes.add(new Malediction(36, "Petite Amie", "Un autre monstre apparait, du même niveau, et avec les mêmes bonus. Si les monstres sont vaincus, tirez les cartes de trésor et gagnez des niveaux pour chacun d'entre eux.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(37, "Enragé", "+5 au niveau du monstre. A jouer pendant un combat. Si le monstre est vaincu, tirez 1 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(38, "Intelligent", "+5 au niveau du monstre. A jouer pendant un combat. Si le monstre est vaincu, tirez 1 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(39, "Vénérable", "+10 au niveau du monstre. A jouer pendant le combat. Si le monstre est vaincu, tirez 2 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(40, "Enoooorme", "+10 au niveau du monstre. A jouer pendant le combat. Si le monstre est vaincu, tirez 2 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(41, "Bébé", "-5 au niveau du monstre(niveau minimum: 1). A jouer pendant le combat. Si le monstre est vaincu, tirez 1 cartes trésor en moins (minimum 1).", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(42, "Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(43, "Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", new Sortilege(actionTabMalediction)));
        */
        //FIN_TODO
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ChangerNiveau(-1));
        cartes.add(new Malediction(44, "Malédiction!", "Perdez 1 niveau", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(45, "Malédiction!", "Perdez 1 niveau", new Sortilege(actionTabMalediction)));
        
        actionTabMalediction = new ArrayList<Action>();
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        actionTabMalediction.add(new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        cartes.add(new Malediction(46, "Malédiction!", "Perdez le couvre-chef que vous portez", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(47, "Malédiction!", "Perdez l'armure que vous portez", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(48, "Malédiction!", "Perdez les chaussures que vous portez", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(49, "Malédiction vraiment trop injuste!", "Perdez l'objet qui vous donne le plus haut bonus", new Sortilege(actionTabMalediction))); 
        // TODO : Manque la malédiction impot sur le revenu
        cartes.add(new Malediction(51, "Malédiction! Petite perte", "Choisissez un petit objet à défausser. Tout objet qui n'est pas spécifiquement désigné comme \"Gros\" est petit.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(52, "Malédiction! Petite perte", "Choisissez un petit objet à défausser. Tout objet qui n'est pas spécifiquement désigné comme \"Gros\" est petit.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(53, "Malédiction! Grosse perte", "Choisissez un gros objet à défausser.", new Sortilege(actionTabMalediction)));
        //TODO
        //cartes.add(new Malediction(50, "Malédiction! Impôt sur le revenu", "Défaussez un objet de votre choix. Chaque autre joueur doit maintenant défausser un ou des objets dont la valeur totale égale au moins celle du vôtre. Ceux qui n'ont pas assez pour payer doivent défausser tous leurs objets et perdre un niveau", new Sortilege(actionTabMalediction)));
        //FIN_TODO
        
        actionTabMalediction = new ArrayList<Action>();
        // TODO : Refaire cette fonction en tant que déséquipement
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        actionTabMalediction.add(new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        cartes.add(new Malediction(54, "Malédiction! Déclassé!", "Défaussez votre carte de Classe si vous en avez une. Si vous avez deux classes en jeu, vous en perdez une au choix. Si vous n'avez pas de Classe, vous perdez 1 niveau.", new Sortilege(actionTabMalediction)));
        
        
        actionTabMalediction = new ArrayList<Action>();
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        actionTabMalediction.add(new DefausserCarte(tabClassCarte, 1, Constante.JEU));
        cartes.add(new Malediction(55, "Malédiction! Commun des Mortels", "Défaussez toute carte de Race que vous avez en jeu et redevenez Humain.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_PRETRE);
        actionTabMalediction.add(new ChangerNiveau(1));
        //cartes.add(new Malediction(56, "Intervention Divine!", "Vous devez jouer cette carte immédiatement. Tous les Prêtres gagnent immédiatement 1 niveau. Si cela permet à un joueur de terminer la partie, il est autorisé à se gausser grassement et sans pitié des autres joueurs.", new Sortilege(actionTabMalediction)));
        
        actionTabMalediction = new ArrayList<Action>();
        //TODO
        //cartes.add(new Malediction(57, "Malédiction! Poulet sur la tête", "-1 à tous vos jets de dé. toute Malédiction ou Incident Fâcheux qui vous retire votre couvre-chef fera également disparaitre le poulet.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ChangerSexe());
        cartes.add(new Malediction(58, "Malédiction! Changement de sexe", "Vous êtes momentanément distrait par le changement pendant votre prochain combat (malus de -5). Après, il n'y a plus aucun malus. Toutefois le changement est permanent.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(59, "Malédiction! Changement de race", "Si vous n'avez pas encore de race, cette malédiction est sans effet. Sinon, regardez les cartes de la défausse, en commençant par la dernière posée. La première carte de race que vous trouvez remplace votre (ou vos) race(s) actuelle(s). Si la défausse n'en contient aucune, vous perdez simplement votre race.", new Sortilege(actionTabMalediction)));
        cartes.add(new Malediction(60, "Malédiction! Changement de classe", "Si vous n'avez pas encore de classe, cette malédiction est sans effet. Sinon, regardez les cartes de la défausse, en commençant par la dernière posée. La première carte de classe que vous trouvez remplace votre (ou vos) classe(s) actuelle(s). Si la défausse n'en contient aucune, vous perdez simplement votre classe.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        tabClassCarte.clear();
        tabClassCarte.add(Objet.class);
        actionTabMalediction.add(new DefausserCarte(tabClassCarte, 2, Constante.MAIN));
        cartes.add(new Malediction(61, "Malédiction! Perdez deux cartes", "Le joueur situé à la gauche de la victime prend une carte au hasard dans la main de cette dernière et la conserve. Le joueur situé à la droite de la victime fait ensuite de même", new Sortilege(actionTabMalediction)));
       //FIN_TODO
        
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ChangerNiveau(-2));
        cartes.add(new Malediction(62, "Malédiction! Canard de l'apocalypse", "Les aventuruers malins ne ramassent pas de canard dans les donjons. Perdez 2 niveaux", new Sortilege(actionTabMalediction)));
        //TODO
        //cartes.add(new Malediction(63, "Malédiction! Miroir perfide", "Vous êtes maudit! Lors de votre prochain combat uniquement, vous n'obtiendrez aucun bonus de vos objets à l'exception de votre armure. Si vous utilisez un Anneau de Souhait avant votre prochain combat, la malédiction est levée.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ModifDeguerpir(+1000, null, null, null, null));
        //cartes.add(new Malediction(64, "Pause déjeuner", "A jouer pendant n'importe quel combat. Les monstres de cette pièce font la pause. Le joueur qui affronte le ou les monstres les défausse tous et tire immédiatement 2 cartes trésor(fuir)", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(65, "Illusion", "A jouer pendant n'importe quel combat. Défaussez un monstre impliqué dans ce combat, ainsi que toutes les cartes jouées pour le modifier, et remplacez le par une carte par une carte de monstre tirée de votre main.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(66, "Tricheur!", "Vous pouvez posséder et utiliser les objets qui vous seraient normalement interdit par les règles. Posez cette carte a côté de l'objet que vous jouez de votre main ou que vous avez déja en jeu. Si vous perdez cette objet, cette carte est défaussée avec.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(67, "Tire moi de la!", "Jouez cette carte quand vous êtes en plein combat. Vous pouvez prendre un objet à n'importe quel autre joueur, à condition qu'il vous permette de gagner le combat alors que vous n'aviez aucune chance au moment ou vous jouez la carte. Vous pouvez défausser un de vos propres objets avant de prendre celui ci si vous le désirez", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(68, "Super Munchkin", "En tant que super grosbill, vous pouvez posséder 2 cartes de Classe, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une classe et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les Prêtres n'auront aucun bonus contre les super Prêtres). Vous perdez cette carte si vous perdez votre ou vos cartes de classe.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(69, "Super Munchkin", "En tant que super grosbill, vous pouvez posséder 2 cartes de Classe, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une classe et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les Prêtres n'auront aucun bonus contre les super Prêtres). Vous perdez cette carte si vous perdez votre ou vos cartes de classe.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(70, "Sang mêlé", "Vous pouvez avoir deux carte de race, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une race et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les elfes n'auront aucun bonus contre les demi-elfes). Vous perdez cette carte si vous perdez votre ou vos cartes de race.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(71, "Sang mêlé", "Vous pouvez avoir deux carte de race, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une race et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les elfes n'auront aucun bonus contre les demi-elfes). Vous perdez cette carte si vous perdez votre ou vos cartes de race.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Malediction(94, "Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", new Sortilege(actionTabMalediction)));
        
        // =============
        // =============
        
        // =============
        // === SORTS ===
        // =============
        
        actionTabMalediction.clear();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(2));
        cartes.add(new Sort(158, "Potion de bravoure hystérique", "A jouer pendant n'importe quel combat. Bonus de +2 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        actionTabMalediction.clear();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(3));
        cartes.add(new Sort(96, "Cotion de Ponfusion", "A jouer pendant n'imquorte pel combat. Bonus de +3 accordé à un champ au coix. Usique unage.", new Sortilege(actionTabMalediction)));
        
        //cartes.add(new Sort(97, "Flaque de colle", "A utiliser quand quelqu'un réussit à fuir le combat pour quelque raison que ce soit. La victime doit relancer les dés pour Déguerpir (même s'il s'agissait d'une réussite automatique la première fois). Usage unique.", new Sortilege(actionTabMalediction)));
        //FIN_TODO
        
        //actionTabMalediction = new ArrayList<Action>();
        //actionTabMalediction.add(new ModifDeguerpir(+1000, null, null, null, null));
        //cartes.add(new Sort(98, "Potion d'invisibilité", "A défausser après avoir raté votre jet pour Déguerpir. Vous vous enfuyez automatiquement. Usage unique.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Sort(103, "Mur instantané", "Permet à un ou deux personnages de fuir automatiquement n'importe quel combat. Usage unique.", new Sortilege(actionTabMalediction)));
        
        //TODO
        //cartes.add(new Sort(99, "Potion de poison enflammé", "A jouer pendant n'importe quel combat. Bonus de +3 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Sort(100, "Anneau de souhait", "Annule n'importe quelle Malédiction. Peut être jouée n'importe quand. Usage unique.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Sort(101, "Anneau de souhait", "Annule n'importe quelle Malédiction. Peut être jouée n'importe quand. Usage unique.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Sort(102, "Dé pipé", "A jouer après n'importe quel jet de dé. Vous choisissez vous-même le résultat de jet de dé. Usage unique.", new Sortilege(actionTabMalediction)));
        //FIN_TODO
        
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ChangerNiveau(1,true));
        cartes.add(new Sort(104, "Don de chips désintéressé au MJ", "Vous gagnez un niveau", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(157, "Erreur de calcul avantageuse", "Vous gagnez un niveau", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(107, "Potion de machisme triomphant", "Vous gagnez un niveau", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(108, "Invocation de règles obscures", "Vous gagnez un niveau", new Sortilege(actionTabMalediction)));
        //cartes.add(new Sort(105, "Pleurer dans les jupes du MJ", "Vous ne pouvez pas utiliser cette carte si vous êtes le joueur de plus haut niveau, ou ex-aequo avec celui-ci. Vous gagnez un niveau", new Sortilege(actionTabMalediction)));
        //cartes.add(new Sort(106, "Tuer le fidèle serviteur", "Vous ne pouvez utiliser cette carte que si le Fidèle Serviteur est en jeu (quel que soit le possesseur). Le Fidèle Serviteur est défaussé.\n Vous gagnez un niveau", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ChangerNiveau(-1,true));
        actionTabMalediction.add(new ChangerNiveau(1));
        cartes.add(new Sort(109, "Vol de niveau", "Choisissez un joueur auquel vous volez un niveau. Vous gagnez un niveau et il en perd un.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new PiocherCarte(Constante.TRESOR, 3));
        cartes.add(new Sort(110, "Pillaaaaaaage !", "Tirez immédiatement trois nouvelles cartes de trésor. Elles sont tirées face cachée si vous avez tiré cette carte face cachée, et face visible dans le cas contraire.", new Sortilege(actionTabMalediction)));
        // TODO : Implémentation de ces cartes
        /*cartes.add(new Sort(111, "Fidèle serviteur", "Ce laquais qui vous suit et vous sert de porteur vous permet de porter et d'utiliser un Gros objet supplémentaire, mais il ne se battra pas pour vous... si vous perdez votre serviteur, vous perdez aussi votre gros objet. Vous pouvez défausser votre serviteur pour vous permettre de fuir automatiquement contre n'importe quel monstre.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(112, "Lampe merveilleuse", "Vous ne pouvez utiliser la Lampe qu'à votre tour. Elle invoque un génie qui fait disparaitre un seul monstre, même s'il était sur le point de vous attraper après un jet de Déguerpir raté. S'il était seul contre vous, vous prenez son trésor mais sans gagner de niveau. Usage unique.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(113, "Brochette de rat", "Défaussez cette carte pour échapper automatiquement à n'importe quel monstre de niveau 8 ou inférieur. Bonus de +1", new Sortilege(actionTabMalediction)));
        
        
        cartes.add(new Sort(117, "Baguette de sourcier", "Parcourez les défausses pour trouver la carte de votre choix. Prenez-la et défaussez la baguette de sourcier", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(118, "Potion d'amitié", "A jouer pendant n'importe quel combat. Défaussez tous les monstres combattus. Aucun trésor n'est gagné, mais vous pouvez piller la pièce. Usage unique.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(119, "Potion de transfert", "A jouer pendant n'importe quel combat. Un autre joueur de votre choix combat le ou les monstres. Il peut demander de l'aide normalement, et obtient le trésor et les niveaux s'il l'emporte. Le joueur qui combattait à l'origine reprend alors son tour, et peut piller la pièce, que le combat ait été remporté ou perdu. Usage unique", new Sortilege(actionTabMalediction)));
        
        cartes.add(new Sort(121, "Potion de polly-morphie", "Utilisable une seule fois, pendant le combat. Transforme n'importe quel monstre en joli perroquet appelé Polly, qui s'envole en abandonnant son trésor (Pas de gain de niveau). Usage unique.", new Sortilege(actionTabMalediction)));
        
        cartes.add(new Sort(125, "Champagne", "A jouer pendant n'importe quel combat. Utilisable une fois et seulement sur les Elfes. Confère un bonus de +2 à chaque Elfe engagé dans la bataille.", new Sortilege(actionTabMalediction)));*/
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(5));
        cartes.add(new Sort(122, "Potion acide, radioactive et électrique", "A jouer pendant n'importe quel combat. Bonus de +5 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(3));
        cartes.add(new Sort(123, "Potion glaciale explosive", "A jouer pendant n'importe quel combat. Bonus de +3 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(2));
        cartes.add(new Sort(124, "Boisson énergisante éventée", "A jouer pendant n'importe quel combat. Bonus de +2 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(2));
        cartes.add(new Sort(120, "Potion de sommeil", "A jouer pendant n'importe quel combat. Bonus de +2 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(5));
        cartes.add(new Sort(114, "Missile magique", "A jouer pendant n'importe quel combat. Bonus de +5 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ModifPuissanceCampChoisi(5));
        cartes.add(new Sort(115, "Oh, les jolis ballons !", "A jouer pendant n'importe quel combat pour distraire l'ennemi. Bonus de +5 accordé à un camp au choix. Usage unique.", new Sortilege(actionTabMalediction)));
        //cartes.add(new Sort(116, "Doppelganger", "Crée votre double, qui combat à vos côtés : votre force de combat est doublée. Vous ne pouvez utiliser Doppelganger que si vous êtes le seul joueur à participer au combat. Usage unique", new Sortilege(actionTabMalediction)));
        // =============
        // =============
        
        
        
        // ==============
        // === OBJETS ===
        // ==============
        nouvelEquipementRace(actionEquipement, Constante.RACE_ELFE);
        cartes.add(new Objet(72, "Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_ELFE);
        cartes.add(new Objet(73, "Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_ELFE);
        cartes.add(new Objet(74, "Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));

        nouvelEquipementRace(actionEquipement, Constante.RACE_NAIN);
        cartes.add(new Objet(75, "Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_NAIN);
        cartes.add(new Objet(76, "Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_NAIN);
        cartes.add(new Objet(77, "Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_HALFELIN);
        cartes.add(new Objet(78, "Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_HALFELIN);
        cartes.add(new Objet(79, "Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_HALFELIN);
        cartes.add(new Objet(80, "Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_PRETRE);
        cartes.add(new Objet(81, "Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_PRETRE);
        cartes.add(new Objet(82, "Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_PRETRE);
        cartes.add(new Objet(83, "Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_VOLEUR);
        cartes.add(new Objet(84, "Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", new Equipement(actionEquipement), new UtiliserCarte(actionUtiliser), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_VOLEUR);
        cartes.add(new Objet(85, "Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", new Equipement(actionEquipement), new UtiliserCarte(actionUtiliser), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_VOLEUR);
        cartes.add(new Objet(86, "Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        /*actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 1, 0, 0));
        actionEquipement.add(new ChangerClasse(Constante.CLASSE_GUERRIER));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -1, 0, 0));*/
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_GUERRIER);
        cartes.add(new Objet(87, "Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_GUERRIER);
        cartes.add(new Objet(88, "Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_GUERRIER);
        cartes.add(new Objet(89, "Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_MAGICIEN);
        cartes.add(new Objet(90, "Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_MAGICIEN);
        cartes.add(new Objet(91, "Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_MAGICIEN);
        cartes.add(new Objet(92 ,"Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        /*actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 1, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -1, 0, -1));
        actionDefausser.add(new ModifDeguerpir(1000, null, null, null, null));
        cartes.add(new Objet(113, "Brochette de rat", "Défaussez cette carte pour échapper automatiquement à n'importe quel monstre. Bonus de +1", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        */
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_VOLEUR);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 4, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, tabClasse, false, -4, 0, -1));
        cartes.add(new Objet(126 ,"Cape d'ombre", "Réservé aux voleurs. Bonus +4", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 2, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -2, 0, -1));
        cartes.add(new Objet(127 ,"Epée (de) bâtard(e)", "Bonus de + 2", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(128 ,"Enorme rocher", "Bonus de + 3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 2, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -2, 0, -1));
        cartes.add(new Objet(129 ,"Targe d'inconscience suicidaire", "Bonus de + 2", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        actionEquipement.add(new EquiperObjet(null, null, true, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(130 ,"Gourdin de misogynie fracassante", "Réservé aux joueurs (ou joueuses) qui ont changé de sexe.Bonus +3.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        actionEquipement.add(new EquiperObjet(null, null, true, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(131 ,"Epée de féminisme exacerbé", "Réservé aux joueurs (ou joueuses) qui ont changé de sexe. Bonus +3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(132 ,"Titre qui en jette vraiment grave", "Bonus de +3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_PRETRE);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 4, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -4, 0, -1));
        cartes.add(new Objet(133 ,"Masse d'arme de répartie piquante", "Réservé aux prêtres. Bonus de +4", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_VOLEUR);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(134 ,"Dague de traitrise", "Réservé aux voleurs. Bonus de +3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 0, 2, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, 0, -2, -1));
        cartes.add(new Objet(135 ,"Bottes de déplacement frénétique.", "Confère un bonus de +2 pour déguerpir.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_GUERRIER);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 4, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -4, 0, -1));
        cartes.add(new Objet(136 ,"Bouclier surdimensionné", "Réservé aux guerriers. Bonus de +4", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_GUERRIER);
        tabClasse.add(Constante.CLASSE_PRETRE);
        tabClasse.add(Constante.CLASSE_VOLEUR);
        tabClasse.add(Constante.CLASSE_AUCUNE);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(137 ,"Armure de Mithril", "Interdites aux magiciens. Bonus de + 3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_MAGICIEN);
        tabClasse.add(Constante.CLASSE_PRETRE);
        tabClasse.add(Constante.CLASSE_VOLEUR);
        tabClasse.add(Constante.CLASSE_AUCUNE);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(138 ,"Collants de force de géant", "Interdit aux guerriers. Bonus de + 3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabClasse.clear();
        tabRace.add(Constante.RACE_NAIN);
        actionEquipement.add(new EquiperObjet(tabRace, null, false, 4, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -4, 0, -1));
        cartes.add(new Objet(139 ,"Marteau des rotules douloureuses", "Réservé aux nains. Bonus de + 4", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabRace.add(Constante.RACE_NAIN);
        actionEquipement.add(new EquiperObjet(tabRace, null, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(140 ,"Armure trapue", "Réservé aux nains. Bonus de + 3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_MAGICIEN);
        tabClasse.add(Constante.CLASSE_PRETRE);
        tabClasse.add(Constante.CLASSE_GUERRIER);
        tabClasse.add(Constante.CLASSE_AUCUNE);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 2, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -2, 0, -1));
        cartes.add(new Objet(141 ,"Epée Karaoké", "Interdite aux voleurs. Bonus de + 2", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_MAGICIEN);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 5, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -5, 0, -1));
        cartes.add(new Objet(142 ,"Bâton de napalm", "Réservé aux magiciens. Bonus de + 5", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabClasse.clear();
        tabClasse.add(Constante.CLASSE_MAGICIEN);
        actionEquipement.add(new EquiperObjet(null, tabClasse, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(143 ,"Chapeau pointu de Thaumaturgie", "réservé aux magiciens. Bonus de + 3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabRace.add(Constante.RACE_HUMAINE);
        actionEquipement.add(new EquiperObjet(tabRace, null, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -3, 0, -1));
        cartes.add(new Objet(144 ,"Bandanas de gros dur", "Réservé aux humains.\n Bonus de + 3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 1, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -1, 0, -1));
        cartes.add(new Objet(145 ,"Armure de cuir", "Bonus de + 1", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 1, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -1, 0, -1));
        cartes.add(new Objet(146 ,"Casque de courage", "Bonus de + 1", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 2, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -2, 0, -1));
        cartes.add(new Objet(147 ,"Armure de flammes", "Bonus de + 2", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        //cartes.add(new Objet(148 ,"Sandale de protection", "Les cartes de malédictions que vous tirez en défonçant les portes n'ont aucun effet. Les malédictions lancées sur vous par d'autres joueurs vous affectent cependant normalement.", new Equipement(actionEquipement), new UtiliserCarte(actionUtiliser), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 1, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -1, 0, -1));
        cartes.add(new Objet(149 ,"Armure gluante", "Bonus de + 1", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 1, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, -1, 0, -1));
        cartes.add(new Objet(150 ,"Genouillères perforantes", "Bonus de + 1", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabRace.add(Constante.RACE_ELFE);
        actionEquipement.add(new EquiperObjet(null, null, false, 1, 0, 1));
        actionEquipement.add(new EquiperObjet(tabRace, null, false, 2, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(tabRace, null, false, -2, 0, -1));
        cartes.add(new Objet(151 ,"Casque virilité ostentatoire", "Bonus de +1 +3 pour les elfes", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        actionEquipement.add(new EquiperObjet(null, null, false, 0, 3, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(null, null, false, 0, -3, -1));
        //cartes.add(new Objet(152 ,"Tuba d'envoutement", "Ce délicat instrument subjugue vos ennemis vous conférant un bonus de +3 pour déguerpir. Si vous réussissez à fuir, tirez une carte Trésor face cachée", new Equipement(actionEquipement), new UtiliserCarte(actionUtiliser), new ComportementDefausserCarte(actionDefausser)));
        cartes.add(new Objet(152 ,"Tuba d'envoutement", "Ce délicat instrument subjugue vos ennemis vous conférant un bonus de +3 pour déguerpir.", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        //cartes.add(new Objet(153 ,"Genouillères de séduction", "Interdit aux prêtres. Si vous demandez à quelqu'un d'un niveau supérieur au votre de vous aider à combattre un monstre, il ne peut ni refuser, ni exiger de paiement en retour. Vous ne pouvez pas gagner la partie si le monstre a été battu grâce à vos genouillères.", new Equipement(actionEquipement), new UtiliserCarte(actionUtiliser), new ComportementDefausserCarte(actionDefausser)));
        actionEquipement.clear();
        tabRace.clear();
        tabRace.add(Constante.RACE_HALFELIN);
        actionEquipement.add(new EquiperObjet(tabRace, null, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(tabRace, null, false, -3, 0, -1));
        cartes.add(new Objet(154 ,"Escabeau", "Réservé aux Hobbits. Bonus de +3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        actionEquipement.clear();
        tabRace.clear();
        tabRace.add(Constante.RACE_HALFELIN);
        actionEquipement.add(new EquiperObjet(tabRace, null, false, 3, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(tabRace, null, false, -3, 0, -1));
        cartes.add(new Objet(155 ,"Sandwich Chocolat-Moules-Anchois", "Réservé aux Halfelins. Bonus de + 3", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        actionEquipement.clear();
        tabRace.clear();
        tabRace.add(Constante.RACE_HUMAINE);
        actionEquipement.add(new EquiperObjet(tabRace, null, false, 4, 0, 1));
        actionDefausser.clear();
        actionDefausser.add(new EquiperObjet(tabRace, null, false, -4, 0, -1));
        cartes.add(new Objet(156 ,"Hallebarde suisse multifonctions", "Réservé aux Humains.\n Bonus de + 4", new Equipement(actionEquipement), new UtiliserCarte(null), new ComportementDefausserCarte(actionDefausser)));
        
        
        // ==============
        // ==============
        // ==============
        
    }

    
    /**
     * Méthode permettant de vider le tableau d'initialisation des incidents et de le remplir avec une nouvelle race
     * @param actionEquipement
     * @param race 
     */
    private void nouvelEquipementRace(ArrayList<Action> actionEquipement, Race race) {
    	actionEquipement.clear();
        actionEquipement.add(new ChangerRace(race));
	}


    /**
     * Méthode permettant de vider le tableau d'initialisation des equipements classes et de le remplir avec une nouvelle classe
     * @param actionEquipement
     * @param classe 
     */
    private void nouvelEquipementClasse(ArrayList<Action> actionEquipement, Classe classe) {
    	actionEquipement.clear();
        actionEquipement.add(new ChangerClasse(classe));
    }


    /**
     * Méthode permettant de vider le tableau d'initialisation des monstres vaincus et de le remplir avec les nouveaux paramètres
     * @param actionTabMonstreVaincu
     * @param piocherCarte
     * @param changerNiveau 
     */
    private void nouvellesActionsMonstreVaincu(ArrayList<Action> actionTabMonstreVaincu, PiocherCarte piocherCarte, ChangerNiveau changerNiveau){
        actionTabMonstreVaincu.clear();
        if(piocherCarte != null)
            actionTabMonstreVaincu.add(piocherCarte);
        if(changerNiveau != null)
        actionTabMonstreVaincu.add(changerNiveau);
    }
    
    
    /**
     * Méthode permettant de vider le tableau d'initialisation des incidents et de le remplir avec les nouveaux paramètres
     * @param actionTabIncident
     * @param changerNiveau
     * @param defausserCarte 
     */
    private void nouvellesActionsIncidentFacheux(ArrayList<Action> actionTabIncident, ChangerNiveau changerNiveau, DefausserCarte defausserCarte){
        actionTabIncident.clear();
        if(changerNiveau != null)
            actionTabIncident.add(changerNiveau);

        if(defausserCarte != null)
            actionTabIncident.add(defausserCarte);
    }

    
    /**
     * Méthode permettant de vider les tableaux utilisés dans l'initialisation des conditions
     * @param actionTabCondition
     * @param tabClasse
     * @param tabRace 
     */
    private void resetCondition(ArrayList<Action> actionTabCondition, ArrayList<Classe> tabClasse, ArrayList<Race> tabRace){
       actionTabCondition.clear();
       tabClasse.clear();
       tabRace.clear();
    }
}
