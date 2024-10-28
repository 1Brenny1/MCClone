package me.brenny.mcclone.Objects;

import javafx.scene.shape.TriangleMesh;
import me.brenny.mcclone.ChunkManager;
import me.brenny.mcclone.MCClone;

public abstract class Cube extends Block {

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void constructMesh(TriangleMesh mesh, Vec3 coordinate, Vec2 chunkCoordinate) {
        float halfScale = MCClone.BLOCK_SIZE / 2f;

        float x = (float) (coordinate.x/* + chunkCoordinate.x*16 */)*MCClone.BLOCK_SIZE;
        float y = (float) -coordinate.y*MCClone.BLOCK_SIZE; // Negative to fix issue with JavaFX
        float z = (float) (coordinate.z/* + chunkCoordinate.y*16 */)*MCClone.BLOCK_SIZE;

        int modifier = mesh.getPoints().size()/3;

        Chunk chunk = ChunkManager.loadedChunks.get(chunkCoordinate);

        mesh.getPoints().addAll(
                -halfScale+x, -halfScale+y, halfScale+z, // 0: FBL
                halfScale+x, -halfScale+y, halfScale+z, // 1: FBR
                -halfScale+x, halfScale+y, halfScale+z, // 2: FTL
                halfScale+x, halfScale+y, halfScale+z, // 3: FTR

                -halfScale+x, -halfScale+y, -halfScale+z, // 4: BBL
                halfScale+x, -halfScale+y, -halfScale+z, // 5: BBR
                -halfScale+x, halfScale+y, -halfScale+z, // 6: BTL
                halfScale+x, halfScale+y, -halfScale+z // 7: BTR
        );
        boolean renderFront = false;
        if (coordinate.z == 15) {
            Chunk _chunk = ChunkManager.loadedChunks.get(new Vec2(chunkCoordinate.x, chunkCoordinate.y+1));
            if (_chunk != null) if (!_chunk.blocks[(int)coordinate.x][(int)coordinate.y][0].isSolid()) renderFront = true;
        } else if (!chunk.blocks[(int)coordinate.x][(int)coordinate.y][(int)coordinate.z+1].isSolid()) renderFront = true;
        if (renderFront) { // Front
            mesh.getFaces().addAll(
                    0+modifier,0, 1+modifier,0, 2+modifier,0,
                    3+modifier,0, 2+modifier,0, 1+modifier,0
            );
        }
        boolean renderBack = false;
        if (coordinate.z == 0) {
            Chunk _chunk = ChunkManager.loadedChunks.get(new Vec2(chunkCoordinate.x, chunkCoordinate.y-1));
            if (_chunk != null) if (!_chunk.blocks[(int)coordinate.x][(int)coordinate.y][15].isSolid()) renderBack = true;
        }
        else if (!chunk.blocks[(int)coordinate.x][(int)coordinate.y][(int)coordinate.z-1].isSolid()) renderBack = true;
        if (renderBack) { // Back
            mesh.getFaces().addAll(
                    6+modifier,0, 5+modifier,0, 4+modifier,0,
                    5+modifier,0, 6+modifier,0, 7+modifier,0
            );
        }
        boolean renderTop = false;
        if (coordinate.y == 63) renderTop = true;
        else if (!chunk.blocks[(int)coordinate.x][(int)coordinate.y+1][(int)coordinate.z].isSolid()) renderTop = true;
        if (renderTop) { // Top
            mesh.getFaces().addAll(
                    5+modifier,0, 1+modifier,1, 0+modifier,2,
                    0+modifier,2, 4+modifier,1, 5+modifier,0
            );
        }
        boolean renderBottom = false;
        if (coordinate.y == 0) renderBottom = true;
        else if (!chunk.blocks[(int)coordinate.x][(int)coordinate.y-1][(int)coordinate.z].isSolid()) renderBottom = true;
        if (renderBottom) { // bottom
            mesh.getFaces().addAll(
                    2+modifier,0, 3+modifier,0, 7+modifier,0,
                    7+modifier,0, 6+modifier,0, 2+modifier,0
            );
        }
        boolean renderLeft = false;
        if (coordinate.x == 0) {
            Chunk _chunk = ChunkManager.loadedChunks.get(new Vec2(chunkCoordinate.x-1, chunkCoordinate.y));
            if (_chunk != null) if (!_chunk.blocks[15][(int)coordinate.y][(int)coordinate.z].isSolid()) renderLeft = true;
        } else if (!chunk.blocks[(int)coordinate.x-1][(int)coordinate.y][(int)coordinate.z].isSolid()) renderLeft = true;
        if (renderLeft) { // Left
            mesh.getFaces().addAll(
                    6+modifier,0, 4+modifier,0, 0+modifier,0,
                    0+modifier,0, 2+modifier,0, 6+modifier,0
            );
        }
        boolean renderRight = false;
        if (coordinate.x == 15) {
            Chunk _chunk = ChunkManager.loadedChunks.get(new Vec2(chunkCoordinate.x+1, chunkCoordinate.y));
            if (_chunk != null) if (!_chunk.blocks[0][(int)coordinate.y][(int)coordinate.z].isSolid()) renderRight = true;
        } else if (!chunk.blocks[(int)coordinate.x+1][(int)coordinate.y][(int)coordinate.z].isSolid()) renderRight = true;
        if (renderRight) { // Right
            mesh.getFaces().addAll(
                    3+modifier,0, 1+modifier,0, 5+modifier,0,
                    5+modifier,0, 7+modifier,0, 3+modifier,0
            );
        }
    }
}
