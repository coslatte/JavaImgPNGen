package Utils;

import org.joou.UInteger;

public enum ChunkType {
//    IHDR(0x49484452),
//    IDAT(0x49444154),
//    IEND(0x49454E44),
//    NONE(0);

    IHDR(UInteger.valueOf(0x49484452)),
    IDAT(UInteger.valueOf(0x49444154)),
    IEND(UInteger.valueOf(0x49454E44)),
    NONE(UInteger.MIN);

    private final UInteger value;

    ChunkType(UInteger value) {
        this.value = value;
    }

    public UInteger getValue() {
        return value;
    }
}
