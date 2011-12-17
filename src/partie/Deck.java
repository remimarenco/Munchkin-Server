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
        actionTabIncident.add(new PiocherCarte(Constante.DONJON, 1));
        /**
         * Deuxième action pour le monstre test
         */
        actionTabIncident.add(new ChangerNiveau(5));
        /**
         * Ajout de la carte avec les actions définies au dessus
         */
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Morpions", "Impossible de déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Rat Musclé", "Créature de l'enfer +3 contre les prÃªtres", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Mucus Baveux", "Beerk! +4 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Grenouilles volantes", "Malus de 1 pour déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Poulet élevé aux stérroïdes", "Frit c'est délicieux", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Mr Nonos", "Si vous devez vous enfuir, vous perdez un niveau, mÃªme si vous arrivez à  déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Octaèdre gélatineux", "+1 au jet pour déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
        cartes.add(new Monstre("Pit Bull", "Si vous ne pouvez le vaincre, vous pouvez le distraire(vous déguerpissez automatiquement) en lachant une baguette un baton ou une lance. Va chercher Médor!", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Harpies", "Résistent à  la magie, +5 Contre les magiciens", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 4));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Cheval Zombi", "+5 contre les nains", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 4));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Escargot sous acide", "-2 pour déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 2));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Lépreuxchaun", "Mais il est dégueu! +5 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 4));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Manticorenithorynque", "Résiste à  la magie, +6 contre les magiciens", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Gerbausore", "Vous gagnez un niveau supplémentaire si vous le tuer seul et sans utiliser de bonus", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Huissier", "N'attaque pas les voleurs (entres confrères...). Un voleur qui rencontre un huissier peut choisir de défausser deux cartes trésors et en tirer deux nouvelles", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Binoclar Hurleur", "+6 contre les guerriers", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 6));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Suceur de te", "C'est dégueu! +6 contre les elfes", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Vamps...Ires!?!", "Aucun objet ne vous servira contre elles, vous combattez uniquement avec votre niveau de personnage", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Belvédère Sauvage", "Nul ne peut vous aider, vous devez affronter seul le Belvédère", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,2), new ChangerNiveau(1));
        cartes.add(new Monstre("Amazone", "N'attaque ni les joueur féminin, ni les joueur masculin qui ont changé de sexe, mais se content de leur donner un trésor", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 8));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        cartes.add(new Monstre("3872 Orques", "+6 contre les nains en raison d'une rancune obscure, certes, mais millénaire", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 10));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        cartes.add(new Monstre("Trôliste", "Il n'a aucun pouvoir et ça le rend furax", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 10));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        cartes.add(new Monstre("Nez Flottant", "Si vous ne voulez pas combattre le Nez Flottant, achetez le avec un objet qui vaut au moins 200 pièces d'or. Il vous laissera partir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 10));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        cartes.add(new Monstre("Fan de Vampire", "Au lieu de le combattre, un prÃªtre peut chasser le fan de vampire en criant simplement \"bouga bouga!\" et s'emparer de son trésor. Dans ce cas il ne gagne aucun niveau.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 12));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        cartes.add(new Monstre("Succube Langue-de-Belle-Mère", "Créature de l'enfer, +4 contre les prÃªtres. Vous devez défausser un objet (que vous choisissez) avant le combat", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 12));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,3), new ChangerNiveau(1));
        cartes.add(new Monstre("Bigfoot, Alias Grand-Pied", "+3 Contre les nains et les Halfelins", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 12));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(1));
        cartes.add(new Monstre("Horreur non euclidienne indicible", "+4 contre les guerriers", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 14));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(1));
        cartes.add(new Monstre("Golem Fracassé", "Vous pouvez combattre ce golem complètement défoncé ou vous contenter de lui faire coucou et lui laisser son trésor. (Exception: Les savoureux halfelins doivent combattre)", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 14));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(1));
        cartes.add(new Monstre("Représentant en assurance", "Votre niveau ne compte pas, vous le combattez uniquement avec vos bonus", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 14));

        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("Tut-Tuuut-Ankh-Ammon", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("René Crophage et fils, dépanneurs en chirurgie", "Ne poursuit aucun personnage de niveau 3 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("Hippogriffe", "Ne poursuit aucun personnage de niveau 3 ou inférieur.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 16));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,4), new ChangerNiveau(2));
        cartes.add(new Monstre("Céphalopodzilla", "C'est gluant! Les elfes combattent à  -4. Ne poursuivra aucun personnage de niveau 4 ou moins, sauf si c'est un elfe.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 18));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,5), new ChangerNiveau(2));
        cartes.add(new Monstre("Balrog Charolais", "Ne poursuit aucun personnage de niveau 4 ou inférieur.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 18));
        
        nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,5), new ChangerNiveau(2));
        cartes.add(new Monstre("Dragon de plutonium", "Ne poursuit aucun personnage de niveau 5 ou inférieur. Les autres perdent 2 niveaux mÃªme si ils réussissent a déguerpir.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 20));
        
        cartes.add(new Sort("Petite Amie", "Un autre monstre apparait, du même niveau, et avec les mêmes bonus. Si les monstres sont vaincus, tirez les cartes de trésor et gagnez des niveaux pour chacun d'entre eux.", null));
		cartes.add(new Sort("Enragé", "+5 au niveau du monstre. A jouer pendant un combat. Si le monstre est vaincu, tirez 1 cartes trésor supplémentaire.", null));
		cartes.add(new Sort("intelligent", "+5 au niveau du monstre. A jouer pendant un combat. Si le monstre est vaincu, tirez 1 cartes trésor supplémentaire.", null));
		cartes.add(new Sort("Vénérable", "+10 au niveau du monstre. A jouer pendant le combat. Si le monstre est vaincu, tirez 2 cartes trésor supplémentaire.", null));
		cartes.add(new Sort("Bébé", "-5 au niveau du monstre(niveau minimum: 1). A jouer pendant le combat. Si le monstre est vaincu, tirez 1 cartes trésor en moins (minimum 1).", null));
		cartes.add(new Sort("Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", null));
		cartes.add(new Sort("Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", null));
		cartes.add(new Sort("Malédiction!", "Perdez 1 niveau", null));
		cartes.add(new Sort("Malédiction!", "Perdez 1 niveau", null));
		cartes.add(new Sort("Malédiction!", "Perdez le couvre-chef que vous portez", null));
		cartes.add(new Sort("Malédiction!", "Perdez l'armure que vous portez", null));
		cartes.add(new Sort("Malédiction!", "Perdez les chaussures que vous portez", null));
		cartes.add(new Sort("Malédiction vraiment trop injuste!", "Perdez l'objet qui vous donne le plus haut bonus", null));
		cartes.add(new Sort("Malédiction! Impôt sur le revenu", "Défaussez un objet de votre choix. Chaque autre joueur doit maintenant défausser un ou des objets dont la valeur totale égale au moins celle du vôtre. Ceux qui n'ont pas assez pour payer doivent défausser tous leurs objets et perdre un niveau", null));
		cartes.add(new Sort("Malédiction! Petite perte", "Choisissez un petit objet à défausser. Tout objet qui n'est pas spécifiquement désigné comme \"Gros\" est petit.", null));
		cartes.add(new Sort("Malédiction! Petite perte", "Choisissez un petit objet à défausser. Tout objet qui n'est pas spécifiquement désigné comme \"Gros\" est petit.", null));
		cartes.add(new Sort("Malédiction! Grosse perte", "Choisissez un gros objet à défausser.", null));
		cartes.add(new Sort("Malédiction! Déclassé!", "Défaussez votre carte de Classe si vous en avez une. Si vous avez deux classes en jeu, vous en perdez une au choix. Si vous n'avez pas de Classe, vous perdez 1 niveau.", null));
		cartes.add(new Sort("Malédiction! Commun des Mortels", "Défaussez toute carte de Race que vous avez en jeu et redevenez Humain.", null));
		cartes.add(new Sort("Intervention Divine!", "Vous devez jouer cette carte immédiatement. Tous les Prêtres gagnent immédiatement 1 niveau. Si cela permet à un joueur de terminer la partie, il est autorisé à se gausser grassement et sans pitié des autres joueurs.", null));
		cartes.add(new Sort("Malédiction! Poulet sur la tête", "-1 à tous vos jets de dé. toute Malédiction ou Incident Fâcheux qui vous retire votre couvre-chef fera également disparaitre le poulet.", null));
		cartes.add(new Sort("Malédiction! Changement de sexe", "Vous êtes momentanément distrait par le changement pendant votre prochain combat (malus de -5). Après, il n'y a plus aucun malus. Toutefois le changement est permanent.", null));
		cartes.add(new Sort("Malédiction! Changement de race", "Si vous n'avez pas encore de race, cette malédiction est sans effet. Sinon, regardez les cartes de la défausse, en commençant par la dernière posée. La première carte de race que vous trouvez remplace votre (ou vos) race(s) actuelle(s). Si la défausse n'en contient aucune, vous perdez simplement votre race.", null));
		cartes.add(new Sort("Malédiction! Changement de classe", "Si vous n'avez pas encore de classe, cette malédiction est sans effet. Sinon, regardez les cartes de la défausse, en commençant par la dernière posée. La première carte de classe que vous trouvez remplace votre (ou vos) classe(s) actuelle(s). Si la défausse n'en contient aucune, vous perdez simplement votre classe.", null));
		cartes.add(new Sort("Malédiction! Perdez deux cartes", "Le joueur situé à la gauche de la victime prend une carte au hasard dans la main de cette dernière et la conserve. Le joueur situé à la droite de la victime fait ensuite de même", null));
		cartes.add(new Sort("Malédiction! Canard de l'apocalypse", "Les aventuruers malins ne ramassent pas de canard dans les donjons. Perdez 2 niveaux", null));
		cartes.add(new Sort("Malédiction! Miroir perfide", "Vous êtes maudit! Lors de votre prochain combat uniquement, vous n'obtiendrez aucun bonus de vos objets à l'exception de votre armure. Si vous utilisez un Anneau de Souhait avant votre prochain combat, la malédiction est levée.", null));
		cartes.add(new Sort("Pause déjeuner", "A jouer pendant n'importe quel combat. Les monstres de cette pièce font la pause. Le joueur qui affronte le ou les monstres les défausse tous et tire immédiatement 2 cartes trésor", null));
		cartes.add(new Sort("Illusion", "A jouer pendant n'importe quel combat. Défaussez un monstre impliqué dans ce combat, ainsi que toutes les cartes jouées pour le modifier, et remplacez le par une carte par une carte de monstre tirée de votre main.", null));
		cartes.add(new Sort("Tricheur!", "Vous pouvez posséder et utiliser les objets qui vous seraient normalement interdit par les règles. Posez cette carte a côté de l'objet que vous jouez de votre main ou que vous avez déja en jeu. Si vous perdez cette objet, cette carte est défaussée avec.", null));
		cartes.add(new Sort("Tire moi de la!", "Jouez cette carte quand vous êtes en plein combat. Vous pouvez prendre un objet à n'importe quel autre joueur, à condition qu'il vous permette de gagner le combat alors que vous n'aviez aucune chance au moment ou vous jouez la carte. Vous pouvez défausser un de vos propres objets avant de prendre celui ci si vous le désirez", null));
		cartes.add(new Sort("Super Munchkin", "En tant que super grosbill, vous pouvez posséder 2 cartes de Classe, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une classe et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les Prêtres n'auront aucun bonus contre les super Prêtres). Vous perdez cette carte si vous perdez votre ou vos cartes de classe.", null));
		cartes.add(new Sort("Super Munchkin", "En tant que super grosbill, vous pouvez posséder 2 cartes de Classe, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une classe et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les Prêtres n'auront aucun bonus contre les super Prêtres). Vous perdez cette carte si vous perdez votre ou vos cartes de classe.", null));
		cartes.add(new Sort("Sang mêlé", "Vous pouvez avoir deux carte de race, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une race et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les elfes n'auront aucun bonus contre les demi-elfes). Vous perdez cette carte si vous perdez votre ou vos cartes de race.", null));
        cartes.add(new Sort("Sang mêlé", "Vous pouvez avoir deux carte de race, et disposer de tous les avantages et désavantages de chacune. Vous pouvez aussi choisir de n'avoir qu'une race et d'avoir tous ses avantages mais aucun désavantage (par exemple les monstres qui haïssent les elfes n'auront aucun bonus contre les demi-elfes). Vous perdez cette carte si vous perdez votre ou vos cartes de race.", null));
		cartes.add(new Sort("Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", null));
		cartes.add(new Sort("Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", null));
		cartes.add(new Sort("Elfe", "+1 pour déguerpir/ Vous gagnez un niveau pour chaque monstre que vous avez aidé à tuer.", null));
		cartes.add(new Sort("Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", null));
		cartes.add(new Sort("Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", null));
		cartes.add(new Sort("Nain", "Vous pouvez porter autant de Gros objets que vous voulez. Vous pouvez avoir 6 cartes dans votre main", null));
		cartes.add(new Sort("Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", null));
		cartes.add(new Sort("Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", null));
		cartes.add(new Sort("Halfelin", "Vous pouvez vendre un objet par tour au double de son prix (les autres objets sont au prix normal). Si vous ratez votre première tentative pour déguerpir, vous pouvez défausser une carte pour réésayer une fois.", null));
		cartes.add(new Sort("Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", null));
		cartes.add(new Sort("Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", null));
		cartes.add(new Sort("Prêtre", "Résurrection: quand vous devez tirer des cartes face visible, vous pouvez choisir de tirer à la place le même nombre de carte de la défausse appropriée (Trésor ou Donjon). Vous devez ensuite défausser une carte de votre main pour chaque carte que vous avez tirée ainsi. Renvoi: Vous pouvez défausser jusqu'à 3 cartes en combat contre une créature de type Mort-vivant. Chaque carte défaussée vous donne un bonus de +3", null));
		cartes.add(new Sort("Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", null));
		cartes.add(new Sort("Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", null));
		cartes.add(new Sort("Voleur", "Poignarder: Vous pouvez défausser une carte pour poignarder un autre joueur dans le dos (-2 au combat). Vous ne pouvez le faire qu'une fois par victime et par combat, mais si deux joueurs combattent ensemble, vous pouvez les poignarder tous les deux. Vol à la tire: vous pouvez défausser une carte pour essayer de voler un petit objet porté par un autre joueur. Lancez un dé: vous réussissez si vous faites 4 ou plus. Sinon, vous prenez une torgnole et perdez un niveau.", null));
		cartes.add(new Sort("Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", null));
		cartes.add(new Sort("Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", null));
		cartes.add(new Sort("Guerrier", "Rage de Berserker: vous pouvez défausser jusqu'à 3 cartes durant un combat. Chacune vous donne un bonus de +1. En cas d'ex-aequo durant un combat, c'est vous qui l'emportez.", null));
		cartes.add(new Sort("Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", null));
		cartes.add(new Sort("Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", null));
		cartes.add(new Sort("Magicien", "Sort de vol: après avoir jeté le dé pour déguerpir, vous pouvez défausser jusqu'à 3 cartes. Chacune vous confère un bonus de +1. Sort de charme: vous pouvez défausser toute votre main (minimum de trois cartes) pour charmer un monstre, et un seul au lieu de le combattre. Défaussez le monstre et prenez son Trésor, mais ne gagnez pas de niveau. Si d'autres monstres participent au combat, combattez les normalement.", null));
		
		// Monstres
		nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
		cartes.add(new Monstre("Gobelin Estropié", "+1 au jet pour déguerpir", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
		cartes.add(new Sort("Monstre Errant", "A jouer ainsi qu'un monstre de votre main, quand quelqu'un (vous y compris) se bat. Votre monstre rejoint celui qui combat: leurs forces de combat s'additionnent. Si le ou les personnages doivent déguerpir, résolvez séparément les tentatives, dans l'ordre choisi par les victimes", null));
		nouvellesActionsMonstreVaincu(actionTabMonstreVaincu, new PiocherCarte(Constante.TRESOR,1), new ChangerNiveau(1));
		cartes.add(new Monstre("Plante d'ornement", "Les elfes tirent une carte Trésor supplémentaire après l'avoir vaincue.", new Condition(null), new IncidentFacheux(actionTabIncident), new MonstreVaincu(actionTabMonstreVaincu), 1));
		
        cartes.add(new Objet("Objet1", "Le premier objet", null));
        
        cartes.add(new Objet("Objet1", "Le deuxième objet", null));
        
        cartes.add(new Sort("Sort1", "Le premier sort", null));
    }

	private void nouvellesActionsMonstreVaincu(ArrayList<Action> actionTabMonstreVaincu, PiocherCarte piocherCarte,
			ChangerNiveau changerNiveau) 
	{
		actionTabMonstreVaincu.clear();
        actionTabMonstreVaincu.add(piocherCarte);
        actionTabMonstreVaincu.add(changerNiveau);
	}
}
