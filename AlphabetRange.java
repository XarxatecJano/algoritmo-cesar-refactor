public final class AlphabetRange {

    private static final int ALPHABET_LENGTH = 26;

    private final char start;
    private final char end;

    public AlphabetRange(char start, char end){
        this.start = start;
        this.end = end;
    }

    public boolean contains(char ch){
        return ch >= start && ch <= end;
    }

    public char rotate (char ch, int shift){
       int offset = ch - start;
       int rotated = (offset + shift) % ALPHABET_LENGTH;

       return (char) (start + rotated);
    }

    public static AlphabetRange uppercase(){
        return new AlphabetRange('A', 'Z');
    }

    public static AlphabetRange lowercase(){
        return new AlphabetRange('a','z');
    }
}