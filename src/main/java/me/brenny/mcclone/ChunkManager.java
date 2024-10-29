package me.brenny.mcclone;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import me.brenny.mcclone.Blocks.Air;
import me.brenny.mcclone.Blocks.Grass;
import me.brenny.mcclone.Blocks.Stone;
import me.brenny.mcclone.Objects.Block;
import me.brenny.mcclone.Objects.Chunk;
import me.brenny.mcclone.Objects.Vec2;

import java.util.HashMap;
import java.util.Map;

public class ChunkManager {
    public static Map<Vec2, Chunk> loadedChunks = new HashMap<>();
    public static NoiseGenerator noise = new NoiseGenerator();

    public static void init() {
        for (int x = -5; x <= 5; x++) {
            for (int y = -5; y <= 5; y++){
                Chunk chunk = generateChunk(new Vec2(x,y));
                spawnChunk(Window.root, chunk);
            }
        }
    }

    public static void spawnChunk(StackPane root, Chunk chunk) {
        chunk.meshView = new MeshView();
        chunk.meshView.setMaterial(TextureManager.MATERIAL);

        Mesh mesh = MeshGenerator.generateMesh(chunk);
        chunk.meshView.setMesh(mesh);

        chunk.meshView.setTranslateX(chunk.coordinates.x*MCClone.BLOCK_SIZE*16);
        chunk.meshView.setTranslateZ(chunk.coordinates.y*MCClone.BLOCK_SIZE*16);

        root.getChildren().add(chunk.meshView);
    }

    public static Chunk generateChunk(Vec2 coordinates) {
        Chunk chunk = new Chunk();


        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    if (noise.noise(x+coordinates.x*16,y,z+coordinates.y*16)*5+32 < y) {
                        chunk.blocks[x][y][z] = new Air();
                        continue;
                    }
                    chunk.blocks[x][y][z] = new Grass();
                }
            }
        }

        chunk.coordinates = coordinates;
        loadedChunks.put(coordinates, chunk);
        return chunk;
    }
}
