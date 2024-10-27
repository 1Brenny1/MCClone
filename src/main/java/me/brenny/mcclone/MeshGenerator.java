package me.brenny.mcclone;

import javafx.scene.shape.TriangleMesh;
import me.brenny.mcclone.Objects.Block;
import me.brenny.mcclone.Objects.Chunk;
import me.brenny.mcclone.Objects.Vec2;
import me.brenny.mcclone.Objects.Vec3;

public class MeshGenerator {

    public static TriangleMesh mesh;
    public static TriangleMesh generateMesh(Chunk chunk) {
        mesh = new TriangleMesh();

        // Setup textures
        mesh.getTexCoords().addAll(0,0);
        mesh.getTexCoords().addAll(1,0);
        mesh.getTexCoords().addAll(1,1);

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
