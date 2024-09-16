package Utils;

import org.joou.UByte;
import org.joou.UInteger;

public class Constants {
    // 89 50 4E 47 0D 0A 1A 0A
//    public static final int[] HEADER_SIGNATURE = {0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
    public static final UByte[] HEADER_SIGNATURE = {
            UByte.valueOf(Short.parseShort("89", 16)),
            UByte.valueOf(Short.parseShort("50", 16)),
            UByte.valueOf(Short.parseShort("4E", 16)),
            UByte.valueOf(Short.parseShort("47", 16)),
            UByte.valueOf(Short.parseShort("0D", 16)),
            UByte.valueOf(Short.parseShort("0A", 16)),
            UByte.valueOf(Short.parseShort("1A", 16)),
            UByte.valueOf(Short.parseShort("0A", 16)),
    };
}