package UnsignedNumbers;

import Utils.Comparative;

public final class u8 extends uNumber {
    private static final short MIN_VALUE = 0x00;
    private static final short MAX_VALUE = 0xff;
    private final short value;

    public u8(short u8) throws Exception {
        super();
        if (checkRange(u8)) {
            this.value = u8;
        } else {
            throw new Exception("Unsigned Byte out of range: 0 <= x <= 255");
        }
    }

    public u8 add(u8 n) {
        try {
            return new u8((short) (this.value + n.value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public u8 sub(u8 n) {
        try {
            return new u8((short) (this.value - n.value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public u8 bitAnd(u8 n) {
        try {
            return new u8((short) (this.value & n.value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean compare(u8 n, Comparative operation) {
        switch (operation) {
            case EQ -> {
                return this.value == n.value;
            }
            case LEQ -> {
                return this.value <= n.value;
            }
            case GEQ -> {
                return this.value >= n.value;
            }
            case GREATER -> {
                return this.value > n.value;
            }
            case LESS -> {
                return this.value < n.value;
            }
        }
        return false;
    }

    public boolean arithAnd(boolean b1, boolean b2) {
        return b1 && b2;
    }

    @Override
    protected boolean checkRange(Object n) {
        Short v = (Short) n;
        return v >= MIN_VALUE && v <= MAX_VALUE;
    }

    @Override
    public void printNumber() {
        System.out.println(this.value);
    }
}