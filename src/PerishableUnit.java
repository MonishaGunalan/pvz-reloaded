
public class PerishableUnit {
    private int maxHP;
    private int currentHP;

	public int getMAXHP(){
		return maxHP;
	}
	
	public int getcurrentHP(){
		return currentHP;
	}
	
	public void reduceHP(int amount){
		if (currentHP != 0 && currentHP > 0){
			currentHP = currentHP - amount;
		}
	}
	
	public void die(){
		if (maxHP<0){
			System.out.println("Dead! GG");
		}
	}
	


}
