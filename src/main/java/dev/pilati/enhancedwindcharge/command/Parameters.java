package dev.pilati.enhancedwindcharge.command;

public enum Parameters {

    TARGET(0, "target"),
    AMOUNT(1, "amount"),
    STRENGTH(2, "strength"),
    SPEED(3, "speed"),
    RADIUS(4, "radius"),
    SHOW_PARTICLES(5, "showParticles"),
    PARTICLE_TYPE(6, "particleType"),
    PARTICLE_AMOUNT(7, "particleAmount"),
    PARTICLE_INTERVAL(8, "particleInterval");

    private final int index;

    private final String key;

    Parameters(int index, String key) {
        this.index = index;
        this.key = key;
    }

    public int getIndex() {
        return index;
    }

    public String getKey() {
        return key;
    }
}
