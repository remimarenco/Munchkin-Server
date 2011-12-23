package action;

import java.util.ArrayList;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

public class ModifDeguerpir extends Action{
	private Integer bonusDeguerpir=0;
	private Integer niveauMax;
	private Integer sexe;
	private ArrayList<Race> tabRace;
	private ArrayList<Classe> tabClasse;
	
	
	
	
	public ModifDeguerpir(Integer bonusDeguerpir, Integer niveauMax, Integer sexe, ArrayList<Race> tabRace, ArrayList<Classe> tabClasse) {
		super();
		this.bonusDeguerpir = bonusDeguerpir;
		this.niveauMax=niveauMax;
		this.sexe=sexe;
		this.tabRace=tabRace;
		this.tabClasse=tabClasse;
	}



	@Override
	public String action(Joueur joueurImpacte) {
                String out = "";
                out += "On passe dans une action de modification déguerpir :\n";
                out += "Le bonus déguerpir est : " + bonusDeguerpir + ", le niveau max pour ce bonus est : "
                        + niveauMax + ", le sexe sur lequel il s'applique est : "+ sexe;

                if(tabRace != null)
                {
                    out += ", les races impliquées sont : ";
                    for(Race race : tabRace)
                    {
                        out += race.toString();
                    }

                }
                else
                {
                    out += ", aucune race impliquée";
                }

                if(tabClasse != null)
                {
                    out += ", les classes impliquées sont : ";
                    for(Classe classe : tabClasse)
                    {
                        out += classe.toString();
                    }
                    
                }
                else
                {
                    out += ", aucune classe impliquée";
                }
                out += "\n";
                
		boolean accept=true;
		boolean raceTrouve=false;
		boolean classeTrouve=false;
		
		
		if(niveauMax!=null)
			if(joueurImpacte.getPersonnage().getNiveau()>niveauMax)
				accept=false;
		
		if(sexe!=null)
			if(joueurImpacte.getPersonnage().getSexe()!=sexe)
				accept=false;
		
		if(tabRace!=null)
		{
			for(Race race: tabRace)
			{
				if(joueurImpacte.getPersonnage().getRace().equals(race))
					raceTrouve=true;
			}
			if(!raceTrouve)
				accept=false;
		}
		
		if(tabClasse!=null)
		{
			for(Classe classe: tabClasse)
			{
				if(joueurImpacte.getPersonnage().getClasse().equals(classe))
					raceTrouve=true;
			}
			if(!classeTrouve)
				accept=false;
		}
		
		if(accept==true)
			joueurImpacte.getPersonnage().setBonusCapaciteFuite(joueurImpacte.getPersonnage().getBonusCapaciteFuite()+bonusDeguerpir);
                return out;
        }

}
