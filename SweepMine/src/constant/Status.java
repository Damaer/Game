package constant;

public enum Status {
    LOADING(1,"Start"),
    GOING(2,"Restart"),
    FAILED(3,"Restart"),
    SUCCESS(4,"Restart");

    private int value;
    private String buttonName;

    Status(int value, String buttonName) {
        this.value = value;
        this.buttonName = buttonName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
}
