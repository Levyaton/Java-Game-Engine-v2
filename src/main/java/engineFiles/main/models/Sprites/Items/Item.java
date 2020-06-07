package engineFiles.main.models.Sprites.Items;

//Class containing the item logic
public class Item {
    private int healthMod = 0;
    private int speedMod = 0;
    private int attackMod = 0;
    private int cost = 0;
    private String description = "default";

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int cost) {
        this(name);
        this.cost = cost;
    }

    public Item(String name, int healthMod, int speedMod, int attackMod) {
        this(name);
        this.healthMod = healthMod;
        this.speedMod = speedMod;
        this.attackMod = attackMod;
    }

    public Item(String name, int healthMod, int speedMod, int attackMod, String description) {
        this(name);
        this.healthMod = healthMod;
        this.speedMod = speedMod;
        this.attackMod = attackMod;
        this.description = description;
    }

    public Item(String name, int healthMod, int speedMod, int attackMod, int cost) {
        this(name, healthMod, speedMod, attackMod);
        this.cost = cost;
    }

    /**
     * @return int
     */
    public int getHealthMod() {
        return healthMod;
    }

    /**
     * @return int
     */
    public int getSpeedMod() {
        return speedMod;
    }

    /**
     * @return int
     */
    public int getAttackMod() {
        return attackMod;
    }

    /**
     * @return int
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param healthMod
     */
    public void setHealthMod(int healthMod) {
        this.healthMod = healthMod;
    }

    /**
     * @param speedMod
     */
    public void setSpeedMod(int speedMod) {
        this.speedMod = speedMod;
    }

    /**
     * @param attackMod
     */
    public void setAttackMod(int attackMod) {
        this.attackMod = attackMod;
    }

    /**
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
