package Chunk;

import UnsignedNumbers.u8;
import UnsignedNumbers.u32;
import Utils.ChunkType;
import Utils.Comparative;

import java.util.ArrayList;
import java.util.Arrays;

public class Ihdr extends Chunk {
    u32 width; // Ancho de la imagen en pixeles.
    u32 height; // Largo de la imagen en pixeles.
    u8 bitDepth; // Numero de bits por sample o por paleta (no por pixel).
    u8 colorType; // Representa el tipo de color de la imagen.
    u8 compressionMethod; // Indica el metodo de compresion usado a la imagen.
    u8 filterMethod; // Indica el metodo de procesado aplicado a la imagen despues de la compresion.
    u8 interlaceMethod; // Indica el orden de transicion de los datos de la imagen: no-interlance || Adam7-interlance.

    public Ihdr(u32 width, u32 height, u8 bitDepth, u8 colorType, u8 compressionMethod, u8 filterMethod, u8 interlaceMethod) throws Exception {
        super();
        this.chunkType = ChunkType.IHDR.getValue();
        this.width = width;
        this.height = height;
        this.bitDepth = bitDepth;
        this.colorType = colorType;
        this.compressionMethod = compressionMethod;
        this.filterMethod = filterMethod;
        this.interlaceMethod = interlaceMethod;

        // Valores admisibles: potencias de 2 hasta el 16.
//        if (!((this.bitDepth & (this.bitDepth - 1)) == 0 && this.bitDepth <= 16)) {
        if (!((this.bitDepth.bitAnd(this.bitDepth.sub(new u8(1))).compare(0, Comparative.EQ)).arithAnd(this.bitDepth.compare(16, Comparative.LEQ)))) {
            throw new Exception("Bit Depth has to be between 1, 2, 4, 8 or 16 values.");
        }

        // Mascara b00000111 para filtrar los bits que no coindicen con estos valores.
        // Si tras operacion AND los valores coinciden quiere decir que el patron de numeros permitidos se cumple.
        /*
         * 0 (binary 00000000): 00000000 & 00000111 = 00000000.
         * 2 (binary 00000010): 00000010 & 00000111 = 00000010.
         * 3 (binary 00000011): 00000011 & 00000111 = 00000011.
         * 4 (binary 00000100): 00000100 & 00000111 = 00000100.
         * 6 (binary 00000110): 00000110 & 00000111 = 00000110.
         */
        if (!((this.colorType & 0b00000111) == this.colorType)) {
            throw new Exception("Color Type has to be between 0, 2, 3, 4 or 6 values.");
        }

        if (!(this.compressionMethod == 0 || this.compressionMethod == 1)) {
            throw new Exception("Compression method has to be between 0 or 1.");
        }

        if (!(this.filterMethod == 0 || this.filterMethod == 1)) {
            throw new Exception("Filter method has to be between 0 or 1.");
        }

        if (!(this.interlaceMethod == 0 || this.interlaceMethod == 1)) {
            throw new Exception("Interlace metho has to be between 0 or 1");
        }

        Number[] data = {
                this.width,
                this.height,
                this.bitDepth,
                this.colorType,
                this.compressionMethod,
                this.filterMethod,
                this.interlaceMethod
        };
        this.chunkData.addAll(Arrays.asList(data));
        this.length = chunkData.size();
//        System.out.println(this.length + "<<<");
//        System.out.println(Arrays.toString(new ArrayList[]{this.chunkData}));
    }

    public ArrayList<Number> getData() {
        return this.chunkData;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Byte getBitDepth() {
        return bitDepth;
    }

    public Byte getColorType() {
        return colorType;
    }

    public Byte getCompressionMethod() {
        return compressionMethod;
    }

    public Byte getFilterMethod() {
        return filterMethod;
    }

    public Byte getInterlaceMethod() {
        return interlaceMethod;
    }
}
