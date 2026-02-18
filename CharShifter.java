public class CharShifter {

    private final AlphabetRange uppercase;
    private final AlphabetRange lowercase;
    private final NonLetterPolicy nonLetterPolicy;

    public CharShifter(AlphabetRange uppercase, AlphabetRange lowercase, NonLetterPolicy nonLetterPolicy) {
        this.uppercase = uppercase;
        this.lowercase = lowercase;
        this.nonLetterPolicy = nonLetterPolicy;
    }

    public char shift(char ch, int shift){
        if (uppercase.contains(ch))
            return uppercase.rotate(ch, shift);
        if (lowercase.contains(ch))
            return lowercase.rotate(ch, shift);
        return nonLetterPolicy.apply(ch, shift);
    }
}