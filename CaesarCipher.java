public class CaesarCipher {

    private final ICharacterShifter shifter;

    public CaesarCipher(ICharacterShifter shifter){
        this.shifter = shifter;
    }
    
    public String cipher(String text, int shift) {
        return shiftText(text, shift);
    }
    
    public String decipher(String text, int shift) {
        return shiftText(text, -shift);
    }

    private String shiftText(String text, int shift){
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            char shiftedCharacter = shifter.shift(character, shift);
            result.append(shiftedCharacter);
        }
        return result.toString();
    }
}