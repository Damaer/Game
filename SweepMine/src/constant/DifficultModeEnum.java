package constant;

public enum DifficultModeEnum {
    SIMPLE(0,"简单模式",10,20),
    MIDDLE(1,"中等模式",30,40),
    HARD(2,"困难模式",40,50);

    private int value;
    private String name;
    private int min;
    private int max;

    DifficultModeEnum(int value, String name, int min, int max) {
        this.value = value;
        this.name = name;
        this.min =min;
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public static DifficultModeEnum getByValue(int index){
        switch (index){
            case 1:
                return DifficultModeEnum.MIDDLE;
            case 2:
                return DifficultModeEnum.HARD;
            default:
                return DifficultModeEnum.SIMPLE;
        }
    }
}
