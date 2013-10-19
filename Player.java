import java.util.Scanner;


public class Player {

	private int money;
	private int sun;
	//private Field field;
	private PlantFactory plantFactory;

	public Player(/*Field field*/){
		//this.field = field;
		plantFactory = new PlantFactory();
		sun = 0;
		money = 0;
	}

	public void play(){
		Scanner c = new Scanner(System.in);

		while (true){
			String command = c.next();

			//before do anything reduce cooldowns
			plantFactory.triggerCooldowns();
			PlantFactory.PlantType p = null;
			try{
				p = PlantFactory.PlantType.valueOf(command.toUpperCase());
			}catch(IllegalArgumentException e){
				System.out.println("No such plant!");
				continue;
			}
			if (p != null){
				grow(0,0,p);

			}
		}
	}


	public boolean grow(int row, int col, PlantFactory.PlantType plantType){
		//		Square square = field.getSquare(row,col);
		//		if(square.getPlant() != null && Type.getCost() <= sun){	
		Plant plant = plantFactory.makePlant(plantType);
		if (plant != null){
			System.out.println("Plant Created");
			System.out.println(plant.getClass().getName());
		} else {
			System.out.println("Plant Still On cooldown!");
		}



		if (plant != null){
			//			field.getSquare(row, col).setPlant()

			sun-= plantType.getCost();
			return true;
		}

		//		}

		return false;
	}




}
