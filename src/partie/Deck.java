/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

import carte.Carte;
import carte.Monstre;
import carte.Objet;
import carte.Sort;
import comportement.Action;
import comportement.ChangerNiveau;
import comportement.PiocherCarte;
import comportement.classes.Condition;
import comportement.classes.IncidentFacheux;
import comportement.classes.MonstreVaincu;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author washi
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
        /**
         * Premiere action pour le monstre test
         */
        actionTabIncident.add(new PiocherCarte(Constante.PIOCHE_DONJON, 1));
        /**
         * Deuxième action pour le monstre test
         */
        actionTabIncident.add(new ChangerNiveau(5));
        /**
         * Ajout de la carte avec les actions définies au dessus
         */
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("MonstreTest", "La description du monstre", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Morpions", "Impossible de déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Rat Musclé", "Créature de l'enfer +3 contre les prÃªtres", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Mucus Baveux", "Beerk! +4 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Grenouilles volantes", "Malus de 1 pour déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Poulet élevé aux stérroïdes", "Frit c'est délicieux", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Mr Nonos", "Si vous devez vous enfuir, vous perdez un niveau, mÃªme si vous arrivez à  déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Octaèdre gélatineux", "+1 au jet pour déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Pit Bull", "Si vous ne pouvez le vaincre, vous pouvez le distraire(vous déguerpissez automatiquement) en lachant une baguette un baton ou une lance. Va chercher Médor!", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("Harpies", "Résistent à  la magie, +5 Contre les magiciens", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("Cheval Zombi", "+5 contre les nains", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("Escargot sous acide", "-2 pour déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("Lépreuxchaun", "Mais il est dégueu! +5 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,6), new ChangerNiveau(2));
        cartes.add(new Monstre("Manticorenithorynque", "Résiste à  la magie, +6 contre les magiciens", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,6), new ChangerNiveau(2));
        cartes.add(new Monstre("Gerbausore", "Vous gagnez un niveau supplémentaire si vous le tuer seul et sans utiliser de bonus", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,6), new ChangerNiveau(2));
        cartes.add(new Monstre("Huissier", "N'attaque pas les voleurs (entres confrères...). Un voleur qui rencontre un huissier peut choisir de défausser deux cartes trésors et en tirer deux nouvelles", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,6), new ChangerNiveau(2));
        cartes.add(new Monstre("Binoclar Hurleur", "+6 contre les guerriers", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,8), new ChangerNiveau(2));
        cartes.add(new Monstre("Suceur de tÃªte", "C'est dégueu! +6 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,8), new ChangerNiveau(2));
        cartes.add(new Monstre("Vamps...Ires!?!", "Aucun objet ne vous servira contre elles, vous combattez uniquement avec votre niveau de personnage", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,8), new ChangerNiveau(2));
        cartes.add(new Monstre("Belvédère Sauvage", "Nul ne peut vous aider, vous devez affronter seul le Belvédère", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,8), new ChangerNiveau(2));
        cartes.add(new Monstre("Amazone", "N'attaque ni les joueur féminin, ni les joueur masculin qui ont changé de sexe, mais se content de leur donner un trésor", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,10), new ChangerNiveau(3));
        cartes.add(new Monstre("3872 Orques", "+6 contre les nains en raison d'une rancune obscure, certes, mais millénaire", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,10), new ChangerNiveau(3));
        cartes.add(new Monstre("Trôliste", "Il n'a aucun pouvoir et à§a le rend furax", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,10), new ChangerNiveau(3));
        cartes.add(new Monstre("Nez Flottant", "Si vous ne voulez pas combattre le Nez Flottant, achetez le avec un objet qui vaut au moins 200 pièces d'or. Il vous laissera partir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,12), new ChangerNiveau(3));
        cartes.add(new Monstre("Fan de Vampire", "Au lieu de le combattre, un prÃªtre peut chasser le fan de vampire en criant simplement \"bouga bouga!\" et s'emparer de son trésor. Dans ce cas il ne gagne aucun niveau.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,12), new ChangerNiveau(3));
        cartes.add(new Monstre("Succube Langue-de-Belle-Mère", "Créature de l'enfer, +4 contre les prÃªtres. Vous devez défausser un objet (que vous choisissez) avant le combat", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,12), new ChangerNiveau(3));
        cartes.add(new Monstre("Bigfoot, Alias Grand-Pied", "+3 Contre les nains et les Halfelins", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,14), new ChangerNiveau(4));
        cartes.add(new Monstre("Horreur non euclidienne indicible", "+4 contre les guerriers", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,14), new ChangerNiveau(4));
        cartes.add(new Monstre("Golem Fracassé", "Vous pouvez combattre ce golem complètement défoncé ou vous contenter de lui faire coucou et lui laisser son trésor. (Exception: Les savoureux halfelins doivent combattre)", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,14), new ChangerNiveau(4));
        cartes.add(new Monstre("Représentant en assurance", "Votre niveau ne compte pas, vous le combattez uniquement avec vos bonus", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));

        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Tut-Tuuut-Ankh-Ammon", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,16), new ChangerNiveau(4));
        cartes.add(new Monstre("René Crophage et fils, dépanneurs en chirurgie", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,16), new ChangerNiveau(4));
        cartes.add(new Monstre("Hippogriffe", "Ne poursuit aucun personnage de niveau 3 ou inférieur.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,18), new ChangerNiveau(4));
        cartes.add(new Monstre("Céphalopodzilla", "C'est gluant! Les elfes combattent à  -4. Ne poursuivra aucun personnage de niveau 4 ou moins, sauf si c'est un elfe.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,18), new ChangerNiveau(5));
        cartes.add(new Monstre("Balrog Charolais", "Ne poursuit aucun personnage de niveau 4 ou inférieur.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,20), new ChangerNiveau(5));
        cartes.add(new Monstre("Dragon de plutonium", "Ne poursuit aucun personnage de niveau 5 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.PIOCHE_TRESOR,20), new ChangerNiveau(5));
        cartes.add(new Monstre("Dieudonné", "Tout ça parce que je suis blanc! +10 contre les chefs", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        cartes.add(new Objet("Objet1", "Le premier objet", null));
        
        cartes.add(new Objet("Objet1", "Le deuxième objet", null));
        
        cartes.add(new Sort("Sort1", "Le premier sort", null));
    }

	private void nouvellesActionsMonstreVaincu(ArrayList<Action> actionTabMonstreVaincu, PiocherCarte piocherCarte,
			ChangerNiveau changerNiveau) 
	{
		actionTabMonstreVaincu.clear();
        actionTabMonstreVaincu.add(new PiocherCarte(Constante.PIOCHE_TRESOR,1));
        actionTabMonstreVaincu.add(new ChangerNiveau(1));
	}
}
