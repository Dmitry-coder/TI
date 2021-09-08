package sample;

public class Register {
    long state;

    public Register(long state) {
        this.state = state;
    }

    public int shift() {
        String keyByte = "";
        for (int i = 1; i < 9; i++) {
            keyByte += getBit(state, 34);
            state = (state << 1) + (getBit(state, 1) ^ getBit(state, 14) ^ getBit(state, 15) ^ getBit(state, 34));
        }
        return Integer.parseInt(keyByte, 2);
    }

    public long getBit(long num, int n) {
        return ((num >> (n - 1)) & 1);
    }

    public long getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
