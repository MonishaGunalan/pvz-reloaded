package pvz;
/**
 * Encapsulate the data for creating the plant
 * @author Tianming Zhuang
 *
 */
public class PlantData {
	/**
	 *  The type of plant it is
	 */
	public final Class<? extends Plant> pClass;
	/**
	 * The cooldown between creating plant
	 */
	public final int seedCooldown;
	/**
	 * The cost of plant
	 */
	public final int seedCost;

	/**
	 * Public Constructor
	 * @param pClass
	 * @param seedCooldown
	 * @param seedCost
	 */
	public PlantData(Class<? extends Plant> pClass, int seedCooldown, int seedCost) {
		this.pClass = pClass;
		this.seedCooldown = seedCooldown;
		this.seedCost = seedCost;
	}
}

