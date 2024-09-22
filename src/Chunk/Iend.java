package Chunk;

import Utils.ChunkType;

public class Iend extends Chunk {

    public Iend() {
        super(ChunkType.IEND);
        this.length = getLength();
        System.out.println(">>>>" + chunkType);
        System.out.println(">>>>" + chunkData);
        this.crc = getCrc(chunkType, chunkData);
    }

    @Override
    public void printData() {
    }
}
