package me.brenny.mcclone.Objects;

import java.util.Objects;

public class Vec2 {
    public double x;
    public double y;

    public Vec2() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vec2 vec = (Vec2) obj;
        return x == vec.x && y == vec.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
