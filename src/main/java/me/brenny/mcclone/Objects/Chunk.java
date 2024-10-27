package me.brenny.mcclone.Objects;

import javafx.scene.shape.MeshView;

public class Chunk {
    public MeshView meshView;
    public Block[][][] blocks = new Block[16][64][16];
    public Vec2 coordinates;
}
