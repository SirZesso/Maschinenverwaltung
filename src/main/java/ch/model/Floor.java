package ch.model;

public enum Floor {
    UNTERGESCHOSS_2(-2),
    UNTERGESCHOSS_1(-1),
    ERDGESCHOSS(0),
    OBERGESCHOSS_1(1),
    OBERGESCHOSS_2(2),
    OBERGESCHOSS_3(3),
    OBERGESCHOSS_4(4),
    OBERGESCHOSS_5(5);

    private Integer level;

    private Floor(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
