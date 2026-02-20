public class AlphabetCharacterShifter implements ICharacterShifter {
    private static final int ALPHABET_LENGTH = 26;
    
    private static final char UPPER_A = 'A';
    private static final char LOWER_A = 'a';
    
    private static int normalizeShift(int shift){
        return shift % ALPHABET_LENGTH;
    }
    
    private static boolean isUpperCase(char character){
        return character >= 'A' && character <= 'Z';
    }

    private static boolean isLowerCase(char character){
        return character >= 'a' && character <= 'z';
    }

    private static char shiftCharacterWhithWrap(char character, int shift, char base){
        int normalizedShift = normalizeShift(shift);
        int alphabetIndex = character - base;
        int shiftedIndex = (alphabetIndex + normalizedShift + ALPHABET_LENGTH) % ALPHABET_LENGTH;

        return (char) (base + shiftedIndex);
    }

    private static char shiftCharacter(char character, int shift){
        if (isUpperCase(character)) {
            return shiftCharacterWhithWrap(character, shift, UPPER_A);
        }

        if (isLowerCase(character)) {
            return shiftCharacterWhithWrap(character, shift, LOWER_A);
        }

        return (char) (character + shift);
    }
    
    @Override
    public char shift(char character, int shift){
        return shiftCharacter(character, shift);
    }

}