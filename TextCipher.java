public interface TextCipher {
    String cipher(String text, int shift);
    String decipher(String text, int shift);
}