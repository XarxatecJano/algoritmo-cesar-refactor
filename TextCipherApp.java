public class TextCipherApp {
    public static void main(String[] args) {
        TextCipher textCipher = new CaesarCipher();
            // Test 1
            String result1 = textCipher.cipher("Hello World", 1);
            String expected1 = "Ifmmp!Xpsme";
            assert result1.equals(expected1) : 
                String.format("%s === '%s'", result1, expected1);
            
            // Test 2
            String ciphered = textCipher.cipher("Hello World", 3);
            String result2 = textCipher.decipher(ciphered, 3);
            String expected2 = "Hello World";
            assert result2.equals(expected2) : 
                String.format("%s === '%s'", result2, expected2);
            
            System.out.println("Todos los tests han pasado correctamente");
    }
}