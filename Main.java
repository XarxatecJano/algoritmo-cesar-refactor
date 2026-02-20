public class Main {
    public static void main(String [] args){
        ICharacterShifter shifter = new AlphabetCharacterShifter();
        CaesarCipher cipher = new CaesarCipher(shifter);

        // Test 1
        String result1 = cipher.cipher("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        assert result1.equals(expected1) : 
            String.format("%s === '%s'", result1, expected1);
        
        // Test 2
        String ciphered = cipher.cipher("Hello World", 3);
        String result2 = cipher.decipher(ciphered, 3);
        String expected2 = "Hello World";
        assert result2.equals(expected2) : 
            String.format("%s === '%s'", result2, expected2);

        //Test 3
        String ciphered2 = cipher.cipher("Hola cuanto tiempo sin vernos", 3);
        String result3 = cipher.decipher(ciphered2, 3);
        String expected3 = "Hola cuanto tiempo sin vernos";
        assert result3.equals(expected3) : String.format("%s === '%s'", result3, expected3);

        
        System.out.println("Todos los tests han pasado correctamente");
    }
}
