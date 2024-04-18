public class Teacher {

    private String name;
    private int health;
    private int attack;
    private int defeatReward;

    // CONSTRUCTOR

    Teacher(String name, int health, int attack, int defeatReward) {

        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defeatReward = defeatReward;
    }

    public String getName() {
        return name;

    }

    public int getAttack() {
        return attack;
    }

    public int getDefeatReward() {
        return defeatReward;
    }

    public int getHealth() {
        return health;
    }

}
