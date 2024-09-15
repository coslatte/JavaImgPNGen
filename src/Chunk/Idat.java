package Chunk;

import Utils.ChunkType;

public class Idat extends Chunk {

    public Idat() {
        super();
        this.chunkType = ChunkType.IDAT.getValue();
    }
}
