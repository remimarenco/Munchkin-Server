/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

import action.Action;
import action.ChangerClasse;
import action.ChangerNiveau;
import action.ChangerRace;
import action.DefausserCarte;
import action.ModifDeguerpir;
import action.ModifPuissanceMonstre;
import action.ModifPuissancePersonnage;
import action.PiocherCarte;
import carte.Carte;
import carte.Monstre;
import carte.Objet;
import carte.Sort;
import comportement.Condition;
import comportement.Equipement;
import comportement.IncidentDeguerpir;
import comportement.IncidentFacheux;
import comportement.MonstreVaincu;
import comportement.Sortilege;

import java.util.ArrayList;
import java.util.Collections;

import joueur.Classe;
import joueur.Race;

/**
 * 
 * @author Julien
 */
public final class Deck {
    private static ArrayList<Carte> cartes;

    /**
     * 
     */
    public Deck() {
        cartes = new ArrayList<Carte>();
        this.load();
        cartes = melanger();
    }

    /**
     * 
     * @return 
     */
    public static ArrayList<Carte> getCartes() {
        return cartes;
    }

    /**
     * 
     * @param cartes 
     */
    public static void setCartes(ArrayList<Carte> cartes) {
        Deck.cartes = cartes;
    }
    
    /**
     * 
     * @return 
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
        ArrayList<Classe> tabClasse = new ArrayList<Classe>();
        ArrayList<Race> tabRace = new ArrayList<Race>();
        Monstre mstr;
        
        // ===============================
        // ============ DONJON ===========
        // ===============================
        
        // ================
        // === MONSTRES ===
        // ================
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(-1000, null, null, null, null));
        cartes.add(new Monstre(1, "Morpions", "Impossible de déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 1));
        
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
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new  Monstre(3, "Mucus Baveux", "Beerk! +4 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 1);
        tabRace.add(Constante.RACE_ELFE);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 4, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);

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
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(1, null, null, null, null));
        cartes.add(new Monstre(7, "Octaèdre gélatineux", "+1 au jet pour déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), null);
        actionDeguerpir.clear();
        // TODO a faire comme il faut parce que la c'est a chier
        actionDeguerpir.add(new DefausserCarte());
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
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(Constante.CARTE_OBJET, Constante.NB_PAR_DE, Constante.TAS_CHOISIR));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(-2, null, null, null, null));
        cartes.add(new Monstre(11, "Escargot sous acide", "-2 pour déguerpir", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, 2, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(12, "Lépreuxchaun", "Mais il est dégueu! +5 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 4);
        tabRace.add(Constante.RACE_ELFE);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 5, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-2), new DefausserCarte(Constante.CARTE_OBJET, Constante.NB_TOUT, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(13, "Manticorenithorynque", "Résiste à  la magie, +6 contre les magiciens", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6);
        tabClasse.add(Constante.CLASSE_MAGICIEN);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 6, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, Constante.NB_TOUT, Constante.MAIN));
        cartes.add(new Monstre(14, "Gerbausore", "Vous gagnez un niveau supplémentaire si vous le tuer seul et sans utiliser de bonus", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, Constante.NB_TOUT, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        tabClasse.add(Constante.CLASSE_VOLEUR);
        actionTabCondition.add(new ModifDeguerpir(+1000, null, null, null, tabClasse));
        cartes.add(new Monstre(15, "Huissier", "N'attaque pas les voleurs (entres confrères...). Un voleur qui rencontre un huissier peut choisir de défausser deux cartes trésors et en tirer deux nouvelles", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_RACE, 1, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(16, "Binoclar Hurleur", "+6 contre les guerriers", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 6);
        tabClasse.add(Constante.CLASSE_GUERRIER);
        actionTabCondition.add(new ModifPuissanceMonstre(null, tabClasse, 6, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.MAIN));
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
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-1), new DefausserCarte(Constante.CARTE_CLASSE, 1, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, null, Constante.SEXE_F, null, null));
        actionDeguerpir.clear();
        actionDeguerpir.add(new PiocherCarte(Constante.TRESOR, 1));
        cartes.add(new Monstre(20, "Amazone", "N'attaque ni les joueur féminin, ni les joueur masculin qui ont changé de sexe, mais se content de leur donner un trésor", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(Constante.NB_PAR_DE), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(21, "3872 Orques", "+6 contre les nains en raison d'une rancune obscure, certes, mais millénaire", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 10);
        tabRace.add(Constante.RACE_NAIN);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 6, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        // FAUX
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.JEU));
        cartes.add(new Monstre(22, "Trôliste", "Il n'a aucun pouvoir et ça le rend furax", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 10));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-3), null);
        cartes.add(new Monstre(23, "Nez Flottant", "Si vous ne voulez pas combattre le Nez Flottant, achetez le avec un objet qui vaut au moins 200 pièces d'or. Il vous laissera partir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 10));
        
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
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        mstr = new Monstre(26, "Bigfoot, Alias Grand-Pied", "+3 Contre les nains et les Halfelins", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 12);
        tabRace.add(Constante.RACE_NAIN);
        tabRace.add(Constante.RACE_HALFELIN);
        actionTabCondition.add(new ModifPuissanceMonstre(tabRace, null, 3, mstr));
        mstr.setCondition(new Condition(actionTabCondition));
        cartes.add(mstr);
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(1));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), new DefausserCarte(Constante.CARTE_CLASSE, 1, Constante.JEU));
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
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, Constante.NB_TOUT, Constante.JEU));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifPuissancePersonnage(null, null, 0, false, true));
        cartes.add(new Monstre(29, "Représentant en assurance", "Votre niveau ne compte pas, vous le combattez uniquement avec vos bonus", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 14));

        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, Constante.NB_TOUT, Constante.MAIN));
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 3, null, null, null));
        cartes.add(new Monstre(30, "Tut-Tuuut-Ankh-Ammon", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux même si ils réussissent a déguerpir.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 3, null, null, null));
        cartes.add(new Monstre(31, "René Crophage et fils, dépanneurs en chirurgie", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        // FAUX 
        nouvellesActionsIncidentFacheux(actionTabIncident, null, new DefausserCarte(Constante.CARTE_OBJET, Constante.NB_TOUT, Constante.MAIN));
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
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 4, null, null, null));
        cartes.add(new Monstre(34, "Balrog Charolais", "Ne poursuit aucun personnage de niveau 4 ou inférieur.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 18));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,5), new ChangerNiveau(2));
        // FAUX !!
        nouvellesActionsIncidentFacheux(actionTabIncident, new ChangerNiveau(-10), null);
        resetCondition(actionTabCondition, tabClasse, tabRace);
        actionTabCondition.add(new ModifDeguerpir(+1000, 5, null, null, null));
        cartes.add(new Monstre(35, "Dragon de plutonium", "Ne poursuit aucun personnage de niveau 5 ou inférieur. Les autres perdent 2 niveaux même si ils réussissent a déguerpir.", new Condition(actionTabCondition), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), new IncidentDeguerpir(null), 20));
        
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
        // === SORTS ===
        // =============
        cartes.add(new Sort(36, "Petite Amie", "Un autre monstre apparait, du même niveau, et avec les mêmes bonus. Si les monstres sont vaincus, tirez les cartes de trésor et gagnez des niveaux pour chacun d'entre eux.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(37, "Enragé", "+5 au niveau du monstre. A jouer pendant un combat. Si le monstre est vaincu, tirez 1 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(38, "Intelligent", "+5 au niveau du monstre. A jouer pendant un combat. Si le monstre est vaincu, tirez 1 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(39, "Vénérable", "+10 au niveau du monstre. A jouer pendant le combat. Si le monstre est vaincu, tirez 2 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(40, "Enoooorme", "+10 au niveau du monstre. A jouer pendant le combat. Si le monstre est vaincu, tirez 2 cartes trésor supplémentaire.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(41, "Bébé", "-5 au niveau du monstre(niveau minimum: 1). A jouer pendant le combat. Si le monstre est vaincu, tirez 1 cartes trésor en moins (minimum 1).", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(42, "Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(43, "Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", new Sortilege(actionTabMalediction)));
        
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new ChangerNiveau(-1));
        cartes.add(new Sort(44, "Malédiction!", "Perdez 1 niveau", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(45, "Malédiction!", "Perdez 1 niveau", new Sortilege(actionTabMalediction)));
        
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new DefausserCarte(Constante.CARTE_OBJET, 1, Constante.JEU));
        cartes.add(new Sort(46, "Malédiction!", "Perdez le couvre-chef que vous portez", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(47, "Malédiction!", "Perdez l'armure que vous portez", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(48, "Malédiction!", "Perdez les chaussures que vous portez", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(49, "Malédiction vraiment trop injuste!", "Perdez l'objet qui vous donne le plus haut bonus", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(50, "Malédiction! Impôt sur le revenu", "Défaussez un objet de votre choix. Chaque autre joueur doit maintenant défausser un ou des objets dont la valeur totale égale au moins celle du vôtre. Ceux qui n'ont pas assez pour payer doivent défausser tous leurs objets et perdre un niveau", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(51, "Malédiction! Petite perte", "Choisissez un petit objet à défausser. Tout objet qui n'est pas spécifiquement désigné comme \"Gros\" est petit.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(52, "Malédiction! Petite perte", "Choisissez un petit objet à défausser. Tout objet qui n'est pas spécifiquement désigné comme \"Gros\" est petit.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(53, "Malédiction! Grosse perte", "Choisissez un gros objet à défausser.", new Sortilege(actionTabMalediction)));
        
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new DefausserCarte(Constante.CARTE_CLASSE, 1, Constante.JEU));
        cartes.add(new Sort(54, "Malédiction! Déclassé!", "Défaussez votre carte de Classe si vous en avez une. Si vous avez deux classes en jeu, vous en perdez une au choix. Si vous n'avez pas de Classe, vous perdez 1 niveau.", new Sortilege(actionTabMalediction)));
        
        actionTabMalediction = new ArrayList<Action>();
        actionTabMalediction.add(new DefausserCarte(Constante.CARTE_RACE, 1, Constante.JEU));
        cartes.add(new Sort(55, "Malédiction! Commun des Mortels", "Défaussez toute carte de Race que vous avez en jeu et redevenez Humain.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(56, "Intervention Divine!", "Vous devez jouer cette carte immédiatement. Tous les Prêtres gagnent immédiatement 1 niveau. Si cela permet à un joueur de terminer la partie, il est autorisé à se gausser grassement et sans pitié des autres joueurs.", new Sortilege(actionTabMalediction)));
        
        actionTabMalediction = new ArrayList<Action>();
        cartes.add(new Sort(57, "Malédiction! Poulet sur la tête", "-1 à tous vos jets de dé. toute Malédiction ou Incident Fâcheux qui vous retire votre couvre-chef fera également disparaitre le poulet.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(58, "Malédiction! Changement de sexe", "Vous êtes momentanément distrait par le changement pendant votre prochain combat (malus de -5). Après, il n'y a plus aucun malus. Toutefois le changement est permanent.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(59, "Malédiction! Changement de race", "Si vous n'avez pas encore de race, cette malédiction est sans effet. Sinon, regardez les cartes de la défausse, en commençant par la dernière posée. La première carte de race que vous trouvez remplace votre (ou vos) race(s) actuelle(s). Si la défausse n'en contient aucune, vous perdez simplement votre race.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(60, "Malédiction! Changement de classe", "Si vous n'avez pas encore de classe, cette malédiction est sans effet. Sinon, regardez les cartes de la défausse, en commençant par la dernière posée. La première carte de classe que vous trouvez remplace votre (ou vos) classe(s) actuelle(s). Si la défausse n'en contient aucune, vous perdez simplement votre classe.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(61, "Malédiction! Perdez deux cartes", "Le joueur situé à la gauche de la victime prend une carte au hasard dans la main de cette dernière et la conserve. Le joueur situé à la droite de la victime fait ensuite de même", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(62, "Malédiction! Canard de l'apocalypse", "Les aventuruers malins ne ramassent pas de canard dans les donjons. Perdez 2 niveaux", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(63, "Malédiction! Miroir perfide", "Vous êtes maudit! Lors de votre prochain combat uniquement, vous n'obtiendrez aucun bonus de vos objets à l'exception de votre armure. Si vous utilisez un Anneau de Souhait avant votre prochain combat, la malédiction est levée.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(64, "Pause déjeuner", "A jouer pendant n'importe quel combat. Les monstres de cette pièce font la pause. Le joueur qui affronte le ou les monstres les défausse tous et tire immédiatement 2 cartes trésor", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(65, "Illusion", "A jouer pendant n'importe quel combat. Défaussez un monstre impliqué dans ce combat, ainsi que toutes les cartes jouées pour le modifier, et remplacez le par une carte par une carte de monstre tirée de votre main.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(66, "Tricheur!", "Vous pouvez posséder et utiliser les objets qui vous seraient normalement interdit par les règles. Posez cette carte a côté de l'objet que vous jouez de votre main ou que vous avez déja en jeu. Si vous perdez cette objet, cette carte est défaussée avec.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(67, "Tire moi de la!", "Jouez cette carte quand vous êtes en plein combat. Vous pouvez prendre un objet à n'importe quel autre joueur, à condition qu'il vous permette de gagner le combat alors que vous n'aviez aucune chance au moment ou vous jouez la carte. Vous pouvez défausser un de vos propres objets avant de prendre celui ci si vous le désirez", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(68, "Super Munchkin", "En tant que super grosbill, vous pouvez posséder 2 cartes de Classe, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une classe et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les Prêtres n'auront aucun bonus contre les super Prêtres). Vous perdez cette carte si vous perdez votre ou vos cartes de classe.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(69, "Super Munchkin", "En tant que super grosbill, vous pouvez posséder 2 cartes de Classe, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une classe et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les Prêtres n'auront aucun bonus contre les super Prêtres). Vous perdez cette carte si vous perdez votre ou vos cartes de classe.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(70, "Sang mêlé", "Vous pouvez avoir deux carte de race, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une race et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les elfes n'auront aucun bonus contre les demi-elfes). Vous perdez cette carte si vous perdez votre ou vos cartes de race.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(71, "Sang mêlé", "Vous pouvez avoir deux carte de race, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une race et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les elfes n'auront aucun bonus contre les demi-elfes). Vous perdez cette carte si vous perdez votre ou vos cartes de race.", new Sortilege(actionTabMalediction)));
        cartes.add(new Sort(94, "Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", new Sortilege(actionTabMalediction)));
        // =============
        // =============
        // =============
        
        
        
        // ==============
        // === OBJETS ===
        // ==============
        nouvelEquipementRace(actionEquipement, Constante.RACE_ELFE);
        cartes.add(new Objet(72, "Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", new Equipement(actionEquipement)));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_ELFE);
        cartes.add(new Objet(73, "Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", null));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_ELFE);
        cartes.add(new Objet(74, "Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", null));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_NAIN);
        cartes.add(new Objet(75, "Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", null));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_NAIN);
        cartes.add(new Objet(76, "Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", null));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_NAIN);
        cartes.add(new Objet(77, "Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", null));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_HALFELIN);
        cartes.add(new Objet(78, "Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", null));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_HALFELIN);
        cartes.add(new Objet(79, "Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", null));
        
        nouvelEquipementRace(actionEquipement, Constante.RACE_HALFELIN);
        cartes.add(new Objet(80, "Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_PRETRE);
        cartes.add(new Objet(81, "Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_PRETRE);
        cartes.add(new Objet(82, "Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_PRETRE);
        cartes.add(new Objet(83, "Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_VOLEUR);
        cartes.add(new Objet(84, "Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_VOLEUR);
        cartes.add(new Objet(85, "Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_VOLEUR);
        cartes.add(new Objet(86, "Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_GUERRIER);
        cartes.add(new Objet(87, "Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_GUERRIER);
        cartes.add(new Objet(88, "Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_GUERRIER);
        cartes.add(new Objet(89, "Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_MAGICIEN);
        cartes.add(new Objet(90, "Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_MAGICIEN);
        cartes.add(new Objet(91, "Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", null));
        
        nouvelEquipementClasse(actionEquipement, Constante.CLASSE_MAGICIEN);
        cartes.add(new Objet(92 ,"Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", null));
        // ==============
        // ==============
        // ==============
        
    }

    private void nouvelEquipementRace(ArrayList<Action> actionEquipement,
			Race race) {
    	actionEquipement.clear();
        actionEquipement.add(new ChangerRace(race));
	}
    
    private void nouvelEquipementClasse(ArrayList<Action> actionEquipement,
			Classe classe) {
    	actionEquipement.clear();
        actionEquipement.add(new ChangerClasse(classe));
	}

    private void nouvellesActionsMonstreVaincu(ArrayList<Action> actionTabMonstreVaincu, PiocherCarte piocherCarte, ChangerNiveau changerNiveau){
        actionTabMonstreVaincu.clear();
        if(piocherCarte != null)
            actionTabMonstreVaincu.add(piocherCarte);
        if(changerNiveau != null)
        actionTabMonstreVaincu.add(changerNiveau);
    }
    
    private void nouvellesActionsIncidentFacheux(ArrayList<Action> actionTabIncident, ChangerNiveau changerNiveau, DefausserCarte defausserCarte){
        actionTabIncident.clear();
        if(changerNiveau != null)
            actionTabIncident.add(changerNiveau);

        if(defausserCarte != null)
            actionTabIncident.add(defausserCarte);

    }

    private void resetCondition(ArrayList<Action> actionTabCondition, ArrayList<Classe> tabClasse, ArrayList<Race> tabRace){
       actionTabCondition.clear();
       tabClasse.clear();
       tabRace.clear();
    }
}
