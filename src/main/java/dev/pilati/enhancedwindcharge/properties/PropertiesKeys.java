package dev.pilati.enhancedwindcharge.properties;

public enum PropertiesKeys {
    FLAG("flag"),
    STRENGTH("strength"),
    SPEED("speed"),
    RADIUS("radius"),
    SHOW_PARTICLES("showParticles"),
    PARTICLE_TYPE("particleType"),
    PARTICLE_AMOUNT("particleAmount"),
    PARTICLE_INTERVAL("particleInterval");

    private final String key;

    PropertiesKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
