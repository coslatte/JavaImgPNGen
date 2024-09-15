import Engine.PngFile;

public class Main {
    public static void main(String[] args) {
        try {
            PngFile pngFile = new PngFile(1, 1, 8, 2, 0, 0, 0);
            pngFile.printData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
