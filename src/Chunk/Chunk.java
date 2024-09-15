package Chunk;

import org.joou.UInteger;

import java.util.ArrayList;

import Utils.ChunkType;
import org.joou.UNumber;

public abstract class Chunk {
    protected UInteger chunkType; // IHDR, IDAT, IEND
    protected ArrayList<UNumber> chunkData;
    protected UInteger length;
    protected UInteger crc;

    public Chunk(ChunkType chunkType) {
        switch (chunkType) {
            case IHDR -> this.chunkType = ChunkType.IHDR.getValue();
            case IDAT -> this.chunkType = ChunkType.IDAT.getValue();
            case IEND -> this.chunkType = ChunkType.IEND.getValue();
            case NONE -> this.chunkType = ChunkType.NONE.getValue();
        }
        this.chunkData = new ArrayList<>(0);
        this.length = UInteger.valueOf(0); // 0 inicialmente.
        this.crc = UInteger.valueOf(0); // Posteriormente generado por CRC Algoritmo.
    }

//    public ArrayList<Numbers> getChunkContent() {
//        ArrayList<Number> content = new ArrayList<>();
//        content.add()
//    }
}