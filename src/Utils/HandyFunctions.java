package Utils;

import org.joou.UByte;
import org.joou.UInteger;
import org.joou.UNumber;

public class HandyFunctions {
    private static String intDecimalToHex(int decimal) {
        if (decimal == 0) {
            return "00";
        }
        StringBuilder hex = new StringBuilder();
        while (decimal != 0) {
            int remainder = decimal % 16;
            char hexChar = (remainder < 10) ? (char) (remainder + '0') : (char) (remainder - 10 + 'A');
            hex.insert(0, hexChar);
            decimal = decimal / 16;
        }
        if (hex.length() % 2 != 0) {
            hex.insert(0, '0');
        }
        return hex.toString();
    }

    private static String uIntDecimalToHex(UInteger decimal) {
        return intDecimalToHex(decimal.intValue());
    }

    private static String uByteDecimalToHex(UByte decimal) {
        short d = decimal.shortValue();
        if (d == 0) {
            return "00";
        }
        StringBuilder hex = new StringBuilder();
        while (d != 0) {
            short remainder = (short) (d % 16);
            char hexChar = (remainder < 10) ? (char) (remainder + '0') : (char) (remainder - 10 + 'A');
            hex.insert(0, hexChar);
            d = (short) (d / 16);
        }
        if (hex.length() % 2 != 0) {
            hex.insert(0, '0');
        }
        return hex.toString();
    }

    public static String uDecimalToHex(UNumber decimal) {
        if (decimal instanceof UByte) {
            return uByteDecimalToHex((UByte) decimal);
        } else if (decimal instanceof UInteger) {
            return uIntDecimalToHex((UInteger) decimal);
        } else {
            return "XX";
        }
    }
}
