package Chunk;

import org.joou.UByte;
import org.joou.UInteger;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.zip.CRC32;

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

    protected UInteger calculateCrc32(UInteger chunkType, ArrayList<UNumber> chunkData) {
        CRC32 crc32 = new CRC32();
        crc32.update(chunkType.intValue());
        for (UNumber uN : chunkData) {
            if (uN instanceof UInteger) {
                byte[] bytes = intToByte(uN.intValue());
                crc32.update(bytes);
            } else if (uN instanceof UByte) {
                crc32.update(uN.shortValue());
            }
        }
        return UInteger.valueOf(crc32.getValue());
    }

    // Crea un array de bytes a partir de un Integer (chunkType).
    private static byte[] intToByte(int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    public abstract void printData();

    public UInteger getCrc() {
        return crc;
    }

    public UInteger getLength() {
        return length;
    }

    public ArrayList<UNumber> getData() {
        return this.chunkData;
    }

    public byte[] getDataAsBytes() {
        if (!this.chunkData.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (UNumber uN : chunkData) {
                sb.append(uN.toString());
            }
            String stringBytes = new String(sb);
            return stringBytes.getBytes();
        } else {
            throw new RuntimeException("Chunk Data is empty.");
        }
    }
}