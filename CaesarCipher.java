public class CaesarCipher {

    private static final int ALPHABET_LENGTH = 26;

    private static class Letters {
        static final int A = 65;
        static final int Z = 90;
        static final int a = 97;
        static final int z = 122;
    }

    public static String cipher(String text, int shift) {
        return process(text, shift);
    }

    public static String decipher(String text, int shift) {
        return process(text, -shift);
    }

    private static String process(String text, int shift) {
        if (text == null) return null;

        char[] newCharToProcess = text.toCharArray();
        int shiftOfPlacesToProcess = shift % ALPHABET_LENGTH;

        for (int i = 0; i < newCharToProcess.length; i++) {
            newCharToProcess[i] = applyShift(newCharToProcess[i], shiftOfPlacesToProcess);
        }

        return new String(newCharToProcess);
    }

    private static char applyShift(char currentChar, int shift) {
        int charCode = (int) currentChar;

        if (isOutOfAlphabet(charCode, shift)) {
            return (char) (charCode + calculateWrapShift(shift));
        }

        return (char) (charCode + shift);
    }

    private static boolean isOutOfAlphabet(int charCode, int shift) {
        boolean isUpperOut = (charCode >= Letters.A && charCode <= Letters.Z) &&
                (charCode + shift > Letters.Z || charCode + shift < Letters.A);

        boolean isLowerOut = (charCode >= Letters.a && charCode <= Letters.z) &&
                (charCode + shift > Letters.z || charCode + shift < Letters.a);

        return isUpperOut || isLowerOut;
    }

    private static int calculateWrapShift(int shift) {
        return (shift > 0) ? shift - ALPHABET_LENGTH : shift + ALPHABET_LENGTH;
    }

    public static void main(String[] args) {
        // Test 1
        String result1 = cipher("Hello World", 1);
        assert result1.equals("Ifmmp!Xpsme");

        // Test 2
        String encrypted = cipher("Hello World", 3);
        assert decipher(encrypted, 3).equals("Hello World");

        System.out.println("Todos los tests han pasado correctamente");
    }
}