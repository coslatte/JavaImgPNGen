package Chunk;

import Utils.ChunkType;

public class Idat extends Chunk {

    public Idat() {
        super(ChunkType.IDAT);
        this.length = getLength();
        this.crc = getCrc(chunkType, chunkData);
    }

    @Override
    public void printData() {
    }
}
