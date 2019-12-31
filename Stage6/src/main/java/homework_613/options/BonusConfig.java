package homework_613.options;

/**
 * enum BonusConfig.
 * Настройка бонусов сотрудников.
 */
public enum BonusConfig {

    OPERATOR(0),
    MANAGER(0.5),
    TOP_MANAGER(1.5);

    private double bonusSize;

    BonusConfig(double bonusSize) {
        this.bonusSize = bonusSize;
    }

    public double getBonusSize() {
        return bonusSize;
    }
}
