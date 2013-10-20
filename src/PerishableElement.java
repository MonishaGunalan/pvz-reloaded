
public class PerishableElement {
    private Field field;
    private int row;
    private int HP;
    private int position;


	public int getHP(){
		return HP;
	}

	public void attack(){
		//how to attack?
	}
	
	public void die(){
		if (HP<0){
			System.out.println("Dead! GG");
		}
	}
	
	public int getRow(){
		return row;
	}
	
	public int getPosition(){
		return position;
	}
	

}
