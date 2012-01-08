package joueur;

public abstract class Race {
	
    public Race(){

    }

    public abstract boolean deguerpir();

    public abstract void modifPersonnage(Personnage personnage);

    @Override
    public String toString(){
        return this.getClass().toString();
    }
}
