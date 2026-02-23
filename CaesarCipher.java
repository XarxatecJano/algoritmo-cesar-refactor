public class CaesarCipher implements TextCipher {
    
    private static final int ALPHABET_LENGTH = 26;
    
    private static char shiftCharacter(char character, int shift) {

        if (Character.isLetter(character)){
            int base = Character.isUpperCase(character) ? 'A' : 'a';
            return (char) (base + (character - base + shift + ALPHABET_LENGTH) % ALPHABET_LENGTH);
        }
        return (char) (character + shift);
    }

    private static int normalizeShift(int shift) {
        return shift % ALPHABET_LENGTH;
    }

    private static String processText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        int normalizedShift = normalizeShift(shift);
        for (char character : text.toCharArray()) {
            char newCharToAdd = shiftCharacter(character, normalizedShift);
            result.append(newCharToAdd);
        }
        return result.toString();
    }
    
    public String cipher(String text, int shift) {
        return processText(text, shift);
    }
    
    public String decipher(String text, int shift) {
        return processText(text, -shift);
    }
}