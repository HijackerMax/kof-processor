package com.hijackermax.kofprocessor.model;

import java.util.Objects;

/**
 * Represents coordinate point
 */
public final class CoordinatePoint {
    private final String name;
    /**
     * In case of WSG84 represent longitude
     */
    private final double x;
    /**
     * In case of WSG84 represent latitude
     */
    private final double y;
    private final double z;

    /**
     * Creates instance of {@link CoordinatePoint} with provided values
     *
     * @param name Point name
     * @param x    x-axis value
     * @param y    y-axis value
     * @param z    z-axis value
     */
    public CoordinatePoint(String name, double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Returns point name
     * @return Point name
     */
    public String getName() {
        return name;
    }

    /**
     * Return x value
     * @return x-axis value, in case of WSG84 represent latitude
     */
    public double getX() {
        return x;
    }

    /**
     * Return y value
     * @return y-axis value, in case of WSG84 represent longitude
     */
    public double getY() {
        return y;
    }

    /**
     * Return z value
     * @return z-axis value
     */
    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoordinatePoint)) {
            return false;
        }
        CoordinatePoint that = (CoordinatePoint) o;
        return Double.compare(that.getX(), getX()) == 0
                && Double.compare(that.getY(), getY()) == 0
                && Double.compare(that.getZ(), getZ()) == 0
                && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getX(), getY(), getZ());
    }
}
