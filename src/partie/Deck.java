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
import comportement.classes.IncidentFacheux;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author washi
 */
public final class Deck {
    private static ArrayList<Carte> cartes;

    public Deck() {
        cartes = new ArrayList<Carte>();
        this.load();
        cartes = melanger();
    }

    public static ArrayList<Carte> getCartes() {
        return cartes;
    }

    public static void setCartes(ArrayList<Carte> cartes) {
        Deck.cartes = cartes;
    }
    
    public static ArrayList melanger(){
        ArrayList nouvelle = new ArrayList(cartes);
        Collections.shuffle(nouvelle);
        return nouvelle; 
    }

    /**
     * Permet de charger les cartes dans la piocheDonjon.
     * On définit :
     * - Un vecteur d'action, permettant de référencer les actions de la future carte
     * à instancier
     * - On ajout à ce vecteur
     */
    private void load(){
        /**
         * Permet de référencer les actions de la carte
         */
        ArrayList<Action> actionTab = new ArrayList<Action>();
        /**
         * Premiere action
         */
        actionTab.add(new PiocherCarte(PiocherCarte.PIOCHE_DONJON, 1));
        /**
         * Deuxième action
         */
        actionTab.add(new ChangerNiveau(5));
        /**
         * Ajout de la carte avec les actions définies au dessus
         */
        cartes.add(new Monstre("MonstreTest", "La description du monstre", null, new IncidentFacheux(actionTab), 1, 1, 1));
        cartes.add(new Monstre("Morpions", "Impossible de déguerpir", null, new IncidentFacheux(actionTab), 1, 1, 1));
        cartes.add(new Monstre("Rat Musclé", "Créature de l'enfer +3 contre les prêtres", null, new IncidentFacheux(actionTab), 1, 1, 1));
        cartes.add(new Monstre("Mucus Baveux", "Beerk! +4 contre les elfes", null, new IncidentFacheux(actionTab), 1, 1, 1));
        cartes.add(new Monstre("Grenouilles volantes", "Malus de 1 pour déguerpir", null, new IncidentFacheux(actionTab), 2, 1, 1));
        cartes.add(new Monstre("Poulet élevé aux stérroïdes", "Frit c'est délicieux", null, new IncidentFacheux(actionTab), 2, 1, 1));
        cartes.add(new Monstre("Mr Nonos", "Si vous devez vous enfuir, vous perdez un niveau, même si vous arrivez à déguerpir", null, new IncidentFacheux(actionTab), 2, 1, 1));
        cartes.add(new Monstre("Octaèdre gélatineux", "+1 au jet pour déguerpir", null, new IncidentFacheux(actionTab), 2, 1, 1));
        cartes.add(new Monstre("Pit Bull", "Si vous ne pouvez le vaincre, vous pouvez le distraire(vous déguerpissez automatiquement) en lachant une baguette un baton ou une lance. Va chercher Médor!", null, new IncidentFacheux(actionTab), 2, 1, 1));
        cartes.add(new Monstre("Harpies", "Résistent à la magie, +5 Contre les magiciens", null, new IncidentFacheux(actionTab), 4, 2, 1));
        cartes.add(new Monstre("Cheval Zombi", "+5 contre les nains", null, new IncidentFacheux(actionTab), 4, 2, 1));
        cartes.add(new Monstre("Escargot sous acide", "-2 pour déguerpir", null, new IncidentFacheux(actionTab), 4, 2, 1));
        cartes.add(new Monstre("Lépreuxchaun", "Mais il est dégueu! +5 contre les elfes", null, new IncidentFacheux(actionTab), 4, 2, 1));
        cartes.add(new Monstre("Manticorenithorynque", "Résiste à la magie, +6 contre les magiciens", null, new IncidentFacheux(actionTab), 6, 2, 1));
        cartes.add(new Monstre("Gerbausore", "Vous gagnez un niveau supplémentaire si vous le tuer seul et sans utiliser de bonus", null, new IncidentFacheux(actionTab), 6, 2, 1));
        cartes.add(new Monstre("Huissier", "N'attaque pas les voleurs (entres confrères...). Un voleur qui rencontre un huissier peut choisir de défausser deux cartes trésors et en tirer deux nouvelles", null, new IncidentFacheux(actionTab), 6, 2, 1));
        cartes.add(new Monstre("Binoclar Hurleur", "+6 contre les guerriers", null, new IncidentFacheux(actionTab), 6, 2, 1));
        cartes.add(new Monstre("Suceur de tête", "C'est dégueu! +6 contre les elfes", null, new IncidentFacheux(actionTab), 8, 2, 1));
        cartes.add(new Monstre("Vamps...Ires!?!", "Aucun objet ne vous servira contre elles, vous combattez uniquement avec votre niveau de personnage", null, new IncidentFacheux(actionTab), 8, 2, 1));
        cartes.add(new Monstre("Belvédère Sauvage", "Nul ne peut vous aider, vous devez affronter seul le Belvédère", null, new IncidentFacheux(actionTab), 8, 2, 1));
        cartes.add(new Monstre("Amazone", "N'attaque ni les joueur féminin, ni les joueur masculin qui ont changé de sexe, mais se content de leur donner un trésor", null, new IncidentFacheux(actionTab), 8, 2, 1));
        cartes.add(new Monstre("3872 Orques", "+6 contre les nains en raison d'une rancune obscure, certes, mais millénaire", null, new IncidentFacheux(actionTab), 10, 3, 1));
        cartes.add(new Monstre("Trôliste", "Il n'a aucun pouvoir et ça le rend furax", null, new IncidentFacheux(actionTab), 10, 3, 1));
        cartes.add(new Monstre("Nez Flottant", "Si vous ne voulez pas combattre le Nez Flottant, achetez le avec un objet qui vaut au moins 200 pièces d'or. Il vous laissera partir.", null, new IncidentFacheux(actionTab), 10, 3, 1));
        cartes.add(new Monstre("Fan de Vampire", "Au lieu de le combattre, un prêtre peut chasser le fan de vampire en criant simplement \"bouga bouga!\" et s'emparer de son trésor. Dans ce cas il ne gagne aucun niveau.", null, new IncidentFacheux(actionTab), 12, 3, 1));
        cartes.add(new Monstre("Succube Langue-de-Belle-Mère", "Créature de l'enfer, +4 contre les prêtres. Vous devez défausser un objet (que vous choisissez) avant le combat", null, new IncidentFacheux(actionTab), 12, 3, 1));
        cartes.add(new Monstre("Bigfoot, Alias Grand-Pied", "+3 Contre les nains et les Halfelins", null, new IncidentFacheux(actionTab), 12, 3, 1));
        cartes.add(new Monstre("Horreur non euclidienne indicible", "+4 contre les guerriers", null, new IncidentFacheux(actionTab), 14, 4, 1));
        cartes.add(new Monstre("Golem Fracassé", "Vous pouvez combattre ce golem complètement défoncé ou vous contenter de lui faire coucou et lui laisser son trésor. (Exception: Les savoureux halfelins doivent combattre)", null, new IncidentFacheux(actionTab), 14, 4, 1));
        cartes.add(new Monstre("Représentant en assurance", "Votre niveau ne compte pas, vous le combattez uniquement avec vos bonus", null, new IncidentFacheux(actionTab), 14, 4, 1));
        cartes.add(new Monstre("Tut-Tuuut-Ankh-Ammon", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux même si ils réussissent a déguerpir.", null, new IncidentFacheux(actionTab), 16, 4, 2));
        cartes.add(new Monstre("René Crophage et fils, dépanneurs en chirurgie", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux même si ils réussissent a déguerpir.", null, new IncidentFacheux(actionTab), 16, 4, 2));
        cartes.add(new Monstre("Hippogriffe", "Ne poursuit aucun personnage de niveau 3 ou inférieur.", null, new IncidentFacheux(actionTab), 16, 4, 2));
        cartes.add(new Monstre("Céphalopodzilla", "C'est gluant! Les elfes combattent à -4. Ne poursuivra aucun personnage de niveau 4 ou moins, sauf si c'est un elfe.", null, new IncidentFacheux(actionTab), 18, 4, 2));
        cartes.add(new Monstre("Balrog Charolais", "Ne poursuit aucun personnage de niveau 4 ou inférieur.", null, new IncidentFacheux(actionTab), 18, 5, 2));
        cartes.add(new Monstre("Dragon de plutonium", "Ne poursuit aucun personnage de niveau 5 ou inférieur. Les autres perdent 2 niveaux même si ils réussissent a déguerpir.", null, new IncidentFacheux(actionTab), 20, 5, 2));
        cartes.add(new Monstre("Dieudonné", "Tout ça parce que je suis blanc! +10 contre les chefs", null, new IncidentFacheux(actionTab), 20, 5, 2));
        cartes.add(new Objet("Objet1", "Le premier objet", null));
        cartes.add(new Objet("Objet1", "Le deuxième objet", null));
        cartes.add(new Sort("Sort1", "Le premier sort", null));
    }
}
