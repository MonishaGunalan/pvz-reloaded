package pvz.level;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

public class BuilderModel extends Observable {

	public static final int ROWSIZE = 5;
	public static final int NUM_LEVELS = 5;
	private String[] terrainList, levelSelection, defaultLevels;
	private int[] numZombies;
	private String[][] row1, row2, row3, row4, row5;

	public BuilderModel() {
		this.terrainList = new String[ROWSIZE];
		this.numZombies = new int[ROWSIZE];
		loadDefaultValuesForLevel();
	}

	public void loadDefaultValuesForLevel() {
		defaultLevels = new String[NUM_LEVELS];
		defaultLevels[0] = "level1\nMUD 0\nMUD 0\nGRASS 6\n10 normal\n20 normal\n30 normal\n40 normal\n50 normal\n60 normal\nMUD 0\nMUD 0";
		defaultLevels[1] = "level2\nMUD 0\nMUD 0\nGRASS 12\n10 normal\n19 normal\n27 normal\n36 normal\n45 normal\n54 normal\n63 normal\n72 flag\n76 normal\n80 normal\n84 normal\n87 normal\nMUD 0\nMUD 0";
		defaultLevels[2] = "level3\nMUD 0\nGRASS 5\n10 normal\n34 normal\n84 normal\n104 flag\n118 normal\nGRASS 6\n18 normal\n50 normal\n92 cone\n102 normal\n108 normal\n130 cone\nGRASS 6\n26 normal\n42 normal\n114 normal\n123 normal\n126 normal\n133 normal\nMUD 0";
		defaultLevels[3] = "level4\nGRASS 4\n37 normal\n66 normal\n94 cone\n114 normal\nGRASS 5\n42 normal\n59 cone\n108 normal\n117 normal\n133 normal\nGRASS 5\n10 normal\n46 cone\n73 normal\n110 cone\n121 normal\nGRASS 4\n17 normal\n87 normal\n106 flag\n125 normal\nGRASS 5\n24 normal\n52 normal\n80 normal\n101 cone\n129 normal";
		defaultLevels[4] = "level5\nGRASS 5\n22 normal\n46 normal\n76 cone\n94 normal\n115 normal\nGRASS 5\n28 normal\n52 cone\n100 normal\n108 normal\n118 normal\nGRASS 5\n34 normal\n70 normal\n82 normal\n121 cone\n129 normal\nGRASS 5\n16 normal\n58 normal\n88 normal\n112 normal\n123 normal\nGRASS 5\n10 normal\n40 normal\n64 cone\n105 flag\n126 normal";
	}

	public void setLevelsToEdit(String[] levels) {
		levelSelection = levels.clone();
		this.setChanged();
		this.notifyObservers(levelSelection[0]);
	}

	public void setTerrainList(String[] terrain) {
		this.terrainList = terrain;
		this.setChanged();
		this.notifyObservers("Terrain List");
	}

	public String[] getTerrianList() {
		return terrainList;
	}

	public void setNumZombies(int[] num) {
		numZombies = num.clone();
		System.out.println("print numZombies");
		for (int i = 0; i < num.length; i++) {
			System.out.println(numZombies[i]);
		}
		this.setChanged();
		this.notifyObservers("Num Zombies");
	}

	public int[] getNumZombies() {
		return numZombies;
	}

	public void setZombieType(String[][] zType, int rowNum, int size) {
		System.out.println("rowNum = " + rowNum);
		if (rowNum == 1) {
			row1 = new String[size][2];
			for (int i = 0; i < size; i++) {
				System.arraycopy(zType[i], 0, row1[i], 0, zType[i].length);
			}
		} else if (rowNum == 2) {
			row2 = new String[size][2];
			for (int i = 0; i < size; i++) {
				System.arraycopy(zType[i], 0, row2[i], 0, zType[i].length);
			}
		} else if (rowNum == 3) {
			row3 = new String[size][2];
			for (int i = 0; i < size; i++) {
				System.arraycopy(zType[i], 0, row3[i], 0, zType[i].length);
			}
		} else if (rowNum == 4) {
			row4 = new String[size][2];
			for (int i = 0; i < size; i++) {
				System.arraycopy(zType[i], 0, row4[i], 0, zType[i].length);
			}
		} else if (rowNum == 5) {
			row5 = new String[size][2];
			for (int i = 0; i < size; i++) {
				System.arraycopy(zType[i], 0, row5[i], 0, zType[i].length);
			}
		}

		this.setChanged();
		this.notifyObservers("row" + rowNum);
	}

	public void writeToFile(String level) {
		BufferedWriter out;
		int position = 0;
		try {
			out = new BufferedWriter(new FileWriter(level + ".txt"));
			out.write(this.toString(level));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < levelSelection.length; i++) {
			if (levelSelection[i].equals(level)) {
				position = i;
				System.out.println("current position: " + i);
				System.out.println("next position: " + (i + 1));
				if (position == levelSelection.length - 1) {
					System.out.println("No More Levels to Edit");
					this.setChanged();
					this.notifyObservers("Done Editing");
					return;
				} else {
					this.setChanged();
					this.notifyObservers(levelSelection[position + 1]);
					return;
				}
			}
		}

	}

	public String toString(String level) {

		System.out.println("print numZombies");
		for (int i = 0; i < 5; i++) {
			System.out.println(numZombies[i]);
		}

		String s = "";
		s += level + "\n";
		for (int i = 0; i < terrainList.length; i++) {
			s += terrainList[i] + " " + numZombies[i] + "\n";
			if (terrainList[i].equals("GRASS") && numZombies[i]!=0 ) {
				switch (i + 1) {
				case 1:
					s += writeZombies(numZombies[0], i + 1, row1);
					break;
				case 2:
					s += writeZombies(numZombies[1], i + 1, row2);
					break;
				case 3:
					s += writeZombies(numZombies[2], i + 1, row3);
					break;
				case 4:
					s += writeZombies(numZombies[3], i + 1, row4);
					break;
				case 5:
					s += writeZombies(numZombies[4], i + 1, row5);
					break;
				default:
					System.out.println("did not work");
				}
			}
		}
		return s;
	}

	public String writeZombies(int zombieCount, int rowSelection, String[][] row) {
		System.out.println("write Zombies " + rowSelection + ", row size = "
				+ row.length + ", Zombie Count = " + zombieCount);
		String z = "";
		for (int i = 0; i < zombieCount; i++) {
			for (int j = 1; j >= 0; j--) {
				z += row[i][j] + " ";
				System.out.println(row[i][j]);
			}
			z += "\n";
		}
		return z;
	}

	public void reset() {
		System.out.println("model.reset");
		for (int i = 0; i < NUM_LEVELS; i++) {
			writeDefaultToFile("level"+(i+1),defaultLevels[i]);
		}
		this.setChanged();
		this.notifyObservers("Reset");
	}
	
	public void writeDefaultToFile(String level, String contents){
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(level + ".txt"));
			out.write(contents);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
