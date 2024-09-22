package Engine;

import Chunk.Idat;
import Chunk.Iend;
import Chunk.Ihdr;
import Utils.Constants;
import Utils.HandyFunctions;
import org.joou.UByte;
import org.joou.UInteger;
import org.joou.UNumber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PngFile {
    final UByte[] headerBytes;
    private Ihdr ihdr;
    private Idat idat;
    private Iend iend;

    public PngFile(long width, long height, short bitDepth, short colorType, short compressionMethod, short filterMethod, short interlaceMethod) {
        this.headerBytes = Constants.HEADER_SIGNATURE;
        try {
            this.ihdr = new Ihdr(width, height, bitDepth, colorType, compressionMethod, filterMethod, interlaceMethod);
            this.idat = new Idat();
            this.iend = new Iend();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<UNumber> allAllContent() {
        ArrayList<UNumber> allAllContentLol = new ArrayList<>();
        allAllContentLol.addAll(List.of(headerBytes));
        allAllContentLol.addAll(ihdr.getAllContent());
        allAllContentLol.addAll(idat.getAllContent());
        allAllContentLol.addAll(iend.getAllContent());
        return allAllContentLol;
    }

    public void printData() {
        // Output
        StringBuilder outputSb = new StringBuilder();

        // Header Signature
        StringBuilder headerSb = printHelperBuilder(List.of(headerBytes));
        System.out.printf("Header Signature: %s\n", printHelperBrackets(headerSb, true));

        // IHDR
        StringBuilder ihdrSb = printHelperBuilder(this.ihdr.getAllContent());
        System.out.printf("IHDR: %s\n", printHelperBrackets(ihdrSb, true));

        // IDAT
        StringBuilder idatSb = printHelperBuilder(this.idat.getAllContent());
        System.out.printf("IDAT: %s\n", printHelperBrackets(idatSb, true));

        // IEND
        StringBuilder iendSb = printHelperBuilder(this.iend.getAllContent());
        System.out.printf("IEND: %s\n", printHelperBrackets(iendSb, true));

        outputSb.append(headerSb);
        outputSb.append(ihdrSb);
        outputSb.append(idatSb);
        outputSb.append(iendSb);
        System.out.printf("Final Stream:\n>>> %s\n", printHelperBrackets(outputSb, false));
    }

    /**
     * Imprime en consola un String encerrado entre corchetes.
     *
     * @param string      String a encerrar entre corchetes.
     * @param blankSpaces Elimina los espacios en blanco que tenga el string por parámetros.
     * @return El string pasado por parámetros encerrado entre corchetes.
     */
    private StringBuilder printHelperBrackets(StringBuilder string, boolean blankSpaces) {
        StringBuilder output = new StringBuilder(string);
        if (!blankSpaces) {
            StringBuilder outputNoSpaces = new StringBuilder();
            for (int i = 0; i < output.length(); i++) {
                if (!(string.charAt(i) == ' '))
                    outputNoSpaces.append(string.charAt(i));
            }
            output = new StringBuilder(outputNoSpaces);
        }
        return new StringBuilder("[" + output + "]");
    }

    /**
     * Construye un StringBuilder con los datos que contiene el objeto iterable. Usado para representar
     * en base 16 los bytes que estén contenidos en un array.
     *
     * @param iterable Iterable. Debe ser un array de bytes (UByte) o de enteros (UInteger), pero se puede extender
     *                 para otros tipos de datos numéricos iterables.
     * @return StringBuilder con la representación de un arreglo o lista de bytes o enteros separados por espacios.
     */
    private StringBuilder printHelperBuilder(Iterable<?> iterable) {
        // Super cursed Iterator, pero funciona!
        Iterator<?> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return new StringBuilder("XX");
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            Object o = iterator.next();
            if (o instanceof UByte) {
                sb.append(HandyFunctions.uDecimalToHex((UByte) o));
            } else if (o instanceof UInteger) {
                sb.append(HandyFunctions.uDecimalToHex((UInteger) o));
            }
            if (!iterator.hasNext()) {
                break;
            }
            sb.append(" ");
        }
//        for (Object o : iterable) {
//            if (o instanceof UByte) {
//                sb.append(HandyFunctions.uDecimalToHex((UByte) o));
//            } else if (o instanceof UInteger) {
//                sb.append(HandyFunctions.uDecimalToHex((UInteger) o));
//            }
//            if (iterable.iterator().hasNext()) {
//                sb.append(" ");
//            }
//        }
        return sb;
    }
}
