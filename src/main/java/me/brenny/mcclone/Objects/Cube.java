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
        float halfScale = MCClone.BLOCK_SIZE / 2.0f;

        float x = (float) (coordinate.x)*MCClone.BLOCK_SIZE;
        float y = (float) -coordinate.y*MCClone.BLOCK_SIZE; // Negative to fix issue with JavaFX
        float z = (float) (coordinate.z)*MCClone.BLOCK_SIZE;

        int modifier = mesh.getPoints().size()/3;

        TextureData texData = this.getTextureData();
        texData.process();

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
            mesh.getFaces().addAll(// Top Right                 TOP LEFT                   Bottom Right
                    modifier, texData.front+3, 1+modifier,texData.front+2, 2+modifier,texData.front+1,
                    3+modifier, texData.front, 2+modifier, texData.front+1, 1+modifier, texData.front+2
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
                    6+modifier, texData.back+1, 5+modifier, texData.back+2, 4+modifier, texData.back+3,
                    5+modifier, texData.back+2, 6+modifier, texData.back+1, 7+modifier, texData.back
            );
        }
        boolean renderTop = false;
        if (coordinate.y == 63) renderTop = true;
        else if (!chunk.blocks[(int)coordinate.x][(int)coordinate.y+1][(int)coordinate.z].isSolid()) renderTop = true;
        if (renderTop) { // Top
            mesh.getFaces().addAll(
                    5+modifier, texData.top, 1+modifier, texData.top+1, modifier, texData.top+3,
                    modifier, texData.top+3, 4+modifier, texData.top+2, 5+modifier, texData.top
            );
        }
        boolean renderBottom = false;
        if (coordinate.y == 0) renderBottom = true;
        else if (!chunk.blocks[(int)coordinate.x][(int)coordinate.y-1][(int)coordinate.z].isSolid()) renderBottom = true;
        if (renderBottom) { // bottom
            mesh.getFaces().addAll(
                    2+modifier, texData.bottom, 3+modifier, texData.bottom+1, 7+modifier, texData.bottom+3,
                    7+modifier, texData.bottom+3, 6+modifier, texData.bottom+2, 2+modifier, texData.bottom
            );
        }
        boolean renderLeft = false;
        if (coordinate.x == 0) {
            Chunk _chunk = ChunkManager.loadedChunks.get(new Vec2(chunkCoordinate.x-1, chunkCoordinate.y));
            if (_chunk != null) if (!_chunk.blocks[15][(int)coordinate.y][(int)coordinate.z].isSolid()) renderLeft = true;
        } else if (!chunk.blocks[(int)coordinate.x-1][(int)coordinate.y][(int)coordinate.z].isSolid()) renderLeft = true;
        if (renderLeft) { // Left
            mesh.getFaces().addAll(
                    6+modifier, texData.left+1, 4+modifier, texData.left+3, modifier, texData.left+2,
                    modifier, texData.left+2, 2+modifier,texData.left, 6+modifier,texData.left+1
            );
        }
        boolean renderRight = false;
        if (coordinate.x == 15) {
            Chunk _chunk = ChunkManager.loadedChunks.get(new Vec2(chunkCoordinate.x+1, chunkCoordinate.y));
            if (_chunk != null) if (!_chunk.blocks[0][(int)coordinate.y][(int)coordinate.z].isSolid()) renderRight = true;
        } else if (!chunk.blocks[(int)coordinate.x+1][(int)coordinate.y][(int)coordinate.z].isSolid()) renderRight = true;
        if (renderRight) { // Right
            mesh.getFaces().addAll(
                    3+modifier, texData.right+1, 1+modifier, texData.right+3, 5+modifier, texData.right+2,
                    5+modifier, texData.right+2, 7+modifier, texData.right, 3+modifier, texData.right+1
            );
        }
    }
}
