import Engine.PngFile;

public class Main {
    public static void main(String[] args) {
        long width = 1L;
        long height = 1L;
        short bitDepth = 8;
        short colorType = 2;
        short compressionMethod = 0;
        short filterMethod = 0;
        short interlaceMethod = 0;

        try {
            PngFile pngFile = new PngFile(width, height, bitDepth, colorType, compressionMethod, filterMethod, interlaceMethod);
            pngFile.printData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
