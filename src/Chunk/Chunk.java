package Chunk;

import java.util.ArrayList;

import Utils.ChunkType;

public abstract class Chunk {
    protected int chunkType; // IHDR, IDAT, IEND
    protected ArrayList<Number> chunkData;
    protected int length;
    protected int crc;

    public Chunk() {
        this.chunkType = ChunkType.NONE.getValue();
        this.chunkData = new ArrayList<>();
        this.length = 0; // 0 inicialmente.
        this.crc = 0; // Posteriormente generado por algoritmo.
    }

//    public ArrayList<Numbers> getChunkContent() {
//        ArrayList<Number> content = new ArrayList<>();
//        content.add()
//    }
}