package Engine;

import Chunk.Idat;
import Chunk.Iend;
import Chunk.Ihdr;
import Utils.Constants;

public class PngFile {
    final int[] header;
    Ihdr ihdr;
    Idat idat;
    Iend iend;

    public PngFile(int width, int height, int bitDepth, int colorType, int compressionMethod, int filterMethod, int interlaceMethod) {
        this.header = Constants.HEADER_SIGNATURE;
        try {
            this.ihdr = new Ihdr(width, height, bitDepth, colorType, compressionMethod, filterMethod, interlaceMethod);
            this.idat = new Idat();
            this.iend = new Iend();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printData() {
        StringBuffer sb = new StringBuffer();
        // Header
        for (int b : header) {
            sb.append(b);
        }
        // Data
        for (Number n : ihdr.getData()) {
            sb.append(n.byteValue());
        }
        // Print Format Looking
        for (int i = sb.length(); i > 0; i -= 2) {
            if (i % 2 == 0) {
                sb.insert(i, " ");
            }
            if (i % 32 == 0) {
                sb.insert(i, "\n");
            }
        }
        System.out.println(sb);
    }
}
