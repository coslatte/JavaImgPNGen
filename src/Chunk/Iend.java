package Chunk;

import Utils.ChunkType;

public class Iend extends Chunk {

    public Iend() {
        super();
        this.chunkType = ChunkType.IEND.getValue();
    }
}
