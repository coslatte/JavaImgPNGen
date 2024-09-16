package Engine;

import Chunk.Idat;
import Chunk.Iend;
import Chunk.Ihdr;
import Utils.Constants;
import Utils.HandyFunctions;
import org.joou.UByte;
import org.joou.UInteger;

import java.util.Iterator;
import java.util.List;

public class PngFile {
    final UByte[] headerBytes;
    Ihdr ihdr;
    Idat idat;
    Iend iend;

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

    public void printData() {
        // Output
        StringBuilder outputSb = new StringBuilder();

        // Header Signature
        StringBuilder headerSb = printHelperBuilder(List.of(headerBytes));
        System.out.printf("Header Signature: %s\n", printHelperBrackets(headerSb, true));

        // IHDR
        StringBuilder ihdrSb = printHelperBuilder(this.ihdr.getData());
        System.out.printf("IHDR: %s\n", printHelperBrackets(ihdrSb, true));

        // IDAT
        StringBuilder idatSb = printHelperBuilder(this.idat.getData());
        System.out.printf("IDAT: %s\n", printHelperBrackets(idatSb, true));

        // IEND
        StringBuilder iendSb = printHelperBuilder(this.iend.getData());
        System.out.printf("IEND: %s\n", printHelperBrackets(iendSb, true));

        outputSb.append(headerSb);
        outputSb.append(ihdrSb);
        outputSb.append(idatSb);
        outputSb.append(iendSb);
        System.out.printf("Final Stream:\n>>> %s\n", printHelperBrackets(outputSb, false));
    }

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
