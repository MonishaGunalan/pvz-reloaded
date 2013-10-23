/*
 * @author  Arzaan Irani
 * 100826631
 */
public abstract class PerishableUnit 
	extends Unit{
    protected int maxHP;
    protected int currentHP;

	protected PerishableUnit(int maxHP) {
		super();
		this. maxHP = maxHP;
		this.currentHP = this.maxHP;
	}

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
