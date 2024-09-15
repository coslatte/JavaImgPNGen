package Utils;

public enum ChunkType {
    IHDR(0x49484452),
    IDAT(0x49444154),
    IEND(0x49454E44),
    NONE(-1);

    private final int value;

    ChunkType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
