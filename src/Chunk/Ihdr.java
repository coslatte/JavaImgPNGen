package Chunk;

import Utils.ChunkType;
import org.joou.UByte;
import org.joou.UInteger;
import org.joou.UNumber;

import java.util.zip.CRC32;

public class Ihdr extends Chunk {
    UInteger width; // Ancho de la imagen en pixeles.
    UInteger height; // Largo de la imagen en pixeles.
    UByte bitDepth; // Numero de bits por sample o por paleta (no por pixel).
    UByte colorType; // Representa el tipo de color de la imagen.
    UByte compressionMethod; // Indica el method de compresión usado a la imagen.
    UByte filterMethod; // Indica el method de procesado aplicado a la imagen después de la compresión.
    UByte interlaceMethod; // Indica el orden de transición de los datos de la imagen: no-interlace || Adam7-interlace.

    public Ihdr(long width, long height, short bitDepth, short colorType, short compressionMethod, short filterMethod, short interlaceMethod) throws Exception {
        super(ChunkType.IHDR);
        this.width = UInteger.valueOf(width);
        this.height = UInteger.valueOf(height);

        if (!((bitDepth & (bitDepth - 1)) == 0) && bitDepth <= 16) {
            throw new Exception("Bit Depth has to be between 1, 2, 4, 8 or 16 values.");
        } else {
            this.bitDepth = UByte.valueOf(bitDepth);
        }

        if (!((colorType & 0b00000111) == colorType)) {
            throw new Exception("Color Type has to be between 0, 2, 3, 4 or 6 values.");
        } else {
            this.colorType = UByte.valueOf(colorType);
        }

        if (!(compressionMethod == 0 || compressionMethod == 1)) {
            throw new Exception("Compression method has to be between 0 or 1.");
        } else {
            this.compressionMethod = UByte.valueOf(compressionMethod);
        }

        if (!(filterMethod == 0 || filterMethod == 1)) {
            throw new Exception("Filter method has to be between 0 or 1.");
        } else {
            this.filterMethod = UByte.valueOf(filterMethod);
        }

        if (!(interlaceMethod == 0 || interlaceMethod == 1)) {
            throw new Exception("Interlace method has to be between 0 or 1");
        } else {
            this.interlaceMethod = UByte.valueOf(interlaceMethod);
        }

        this.chunkData.add(this.width);
        this.chunkData.add(this.height);
        this.chunkData.add(this.bitDepth);
        this.chunkData.add(this.colorType);
        this.chunkData.add(this.compressionMethod);
        this.chunkData.add(this.filterMethod);
        this.chunkData.add(this.interlaceMethod);
        this.length = getLength();
        this.crc = getCrc(chunkType, chunkData);
    }

    @Override
    public void printData() {
        StringBuffer sb = new StringBuffer();
        for (UNumber uN : this.chunkData) {
            sb.append(uN.toString());
        }
        System.out.println(sb);
    }
}
