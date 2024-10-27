package me.brenny.mcclone.Objects;

import javafx.scene.shape.TriangleMesh;

public abstract class Block {
    public abstract String getIdentifier();
    public abstract boolean isSolid();
    public abstract void constructMesh(TriangleMesh mesh, Vec3 coordinate, Vec2 chunkCoordinate);
    public boolean onBreak() {
        return true;
    }
    public boolean onPlace() {
        return true;
    }
}
