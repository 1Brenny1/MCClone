package me.brenny.mcclone;

import javafx.scene.shape.TriangleMesh;
import me.brenny.mcclone.Objects.*;

public class MeshGenerator {

    public static TriangleMesh mesh;
    public static TriangleMesh generateMesh(Chunk chunk) {
        mesh = new TriangleMesh();

        // Setup textures
        TextureManager.loadTextureData(mesh);

        // Create Mesh Object
        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.blocks[x][y][z];
                    block.constructMesh(mesh, new Vec3(x,y,z), chunk.coordinates);
                }
            }
        }

        return mesh;
    }
}
