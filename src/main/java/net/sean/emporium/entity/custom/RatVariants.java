package net.sean.emporium.entity.custom;

public enum RatVariants {
    GRAY(0, "gray"),
    BROWN(1, "brown"),
    BLACK(2, "black"),
    GOLDEN(3, "golden"),
    NAKED(4, "naked"),
    WHITE(5, "white");

    private final int id;
    private final String color;

    RatVariants(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public static RatVariants byId(int id) {
        // Mod operator makes sure if id > length, it will still return a value within range
        return values()[id % values().length];
    }
}
