public final class ShiftNormalizer {

    private final int modulus;

    public ShiftNormalizer(int modulus){
        this.modulus = modulus;
    }

    public int normalize(int shift) {
        int r = shift % modulus;
        return (r < 0) ? r + modulus : r;
    }
    
}
