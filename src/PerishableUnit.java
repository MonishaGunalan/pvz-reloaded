public abstract class PerishableUnit 
	extends Unit{
    protected int maxHP;
    protected int currentHP;

	public int getMAXHP(){
		return maxHP;
	}
	
	public int getcurrentHP(){
		return currentHP;
	}
	
	public void reduceHP(int amount){
		this.currentHP -= amount;
		if (this.currentHP <= 0) {
			this.die();
		}
	}
	
	public void die(){
		this.square.remove(this);
	}
	


}
