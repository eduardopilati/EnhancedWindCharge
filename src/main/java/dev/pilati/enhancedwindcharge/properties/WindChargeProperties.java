package dev.pilati.enhancedwindcharge.properties;

import org.bukkit.Particle;

public class WindChargeProperties {
    private float strength = 1;

    private float speed = 1;

    private float radius = 1;

    private boolean showParticles = false;

    private Particle particleType = Particle.FIREWORK;

    private int amount = 1;

    private int interval = 1;

    public WindChargeProperties() {
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isShowParticles() {
        return showParticles;
    }

    public void setShowParticles(boolean showParticles) {
        this.showParticles = showParticles;
    }

    public Particle getParticleType() {
        return particleType;
    }

    public void setParticleType(Particle particleType) {
        this.particleType = particleType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
