package UnsignedNumbers;

import Utils.Comparative;

public class u32 extends uNumber {
    private static final long MIN_VALUE = 0x00000000L;
    private static final long MAX_VALUE = 0xffffffffL;
    private final long value;

    public u32(long u32) throws Exception {
        super();
        if (checkRange(u32)) {
            this.value = u32;
        } else {
            throw new Exception("Unsigned Integer out of range: 0 <= x <= 4,294,967,295");
        }
    }

    public u32 add(u32 n) {
        try {
            return new u32(this.value + n.value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public u32 sub(u32 n) {
        try {
            return new u32((this.value - n.value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public u32 bitAnd(u32 n) {
        try {
            return new u32((this.value & n.value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean compare(u32 n, Comparative operation) {
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
        Long v = (Long) n;
        return v >= MIN_VALUE && v <= MAX_VALUE;
    }

    @Override
    public void printNumber() {
        System.out.println(this.value);
    }
}
