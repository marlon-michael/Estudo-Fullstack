package classes;

public enum PlayerOption {
    A(5, "nada");

    PlayerOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    private int value;
    private String description;
}
