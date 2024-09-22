import Engine.PngFile;
import org.joou.UByte;
import org.joou.UInteger;
import org.joou.UNumber;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        long width = 1L;
        long height = 1L;
        short bitDepth = 8;
        short colorType = 2;
        short compressionMethod = 0;
        short filterMethod = 0;
        short interlaceMethod = 0;

        String imgPath = "Test.png";

        try {
            PngFile pngFile = new PngFile(width, height, bitDepth, colorType, compressionMethod, filterMethod, interlaceMethod);
            pngFile.printData();
            try (FileOutputStream img = new FileOutputStream(imgPath)) {
                for (UNumber uN : pngFile.allAllContent()) {
                    if (uN instanceof UByte) {
                        img.write(uN.byteValue());
                    } else if (uN instanceof UInteger) {
                        byte[] intBytes = ByteBuffer.allocate(4).putInt(uN.intValue()).array();
                        img.write(intBytes);
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
