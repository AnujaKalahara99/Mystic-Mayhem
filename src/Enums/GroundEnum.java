package Enums;

public enum GroundEnum {
    HILLCREST("Hillcrest"),
    MARSHLAND("Marshland"),
    DESERT("Desert"),
    ARCANE("Arcane");

    private final String label;

    GroundEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
