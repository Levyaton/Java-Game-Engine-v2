package engineFiles.main.models.Sprites.Items;

public class Item {
    private int healthMod = 0;
    private int speedMod = 0;
    private int defMod = 0;
    private int attackMod = 0;
    private int cost = 0;

    private String name;

    public Item(String name){
        this.name = name;
    }

    public Item(String name, int cost){
        this(name);
        this.cost = cost;
    }

    public Item(String name, int healthMod, int speedMod, int defMod, int attackMod){
        this(name);
        this.healthMod = healthMod;
        this.speedMod = speedMod;
        this.defMod = defMod;
        this.attackMod = attackMod;
    }
    public Item(String name, int healthMod, int speedMod, int defMod, int attackMod, int cost){
        this(name,healthMod,speedMod,defMod,attackMod);
        this.cost = cost;
    }

    public int getHealthMod() {
        return healthMod;
    }

    public int getSpeedMod() {
        return speedMod;
    }

    public int getDefMod() {
        return defMod;
    }

    public int getAttackMod() {
        return attackMod;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setHealthMod(int healthMod) {
        this.healthMod = healthMod;
    }

    public void setSpeedMod(int speedMod) {
        this.speedMod = speedMod;
    }

    public void setDefMod(int defMod) {
        this.defMod = defMod;
    }

    public void setAttackMod(int attackMod) {
        this.attackMod = attackMod;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }
}