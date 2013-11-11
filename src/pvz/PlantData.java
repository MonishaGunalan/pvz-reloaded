package pvz;
public class PlantData {
	public final Class<? extends Plant> pClass;
	public final int seedCooldown;
	public final int seedCost;

	public PlantData(Class<? extends Plant> pClass, int seedCooldown, int seedCost) {
		this.pClass = pClass;
		this.seedCooldown = seedCooldown;
		this.seedCost = seedCost;
	}
}

