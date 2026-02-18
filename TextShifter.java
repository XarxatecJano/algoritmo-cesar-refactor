public final class TextShifter {
    
    private final ShiftNormalizer normalizer;
    private final CharShifter charShifter;


    public TextShifter(ShiftNormalizer normalizer, CharShifter charShifter) {
        this.normalizer = normalizer;
        this.charShifter = charShifter;
    }

    public String shift(String text, int shift) {

        int normalizedShift = normalizer.normalize(shift);

        StringBuilder out = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            out.append(charShifter.shift(text.charAt(i), normalizedShift));
        }

        return out.toString();
    }
}
