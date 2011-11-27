package comportement;

public class ChangerNiveau extends Action {
	int niveau;
	
	public ChangerNiveau(int niveau)
	{
		this.niveau = niveau;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.print("Vous ");
		if(niveau < 0)
		{
			System.out.print("perdez ");
		}
		else if(niveau > 0)
		{
			System.out.print("gagnez ");
		}
		System.out.println(niveau+" niveau !!");
	}

}
