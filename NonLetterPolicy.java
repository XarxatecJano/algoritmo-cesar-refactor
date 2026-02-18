public interface NonLetterPolicy {

    char apply(char ch, int shift);

    static NonLetterPolicy shifNonLetterPolicy(){
        return (ch, shift) -> (char) (ch + shift);
    }

    static NonLetterPolicy keepNonLetters(){
        return(ch, shift) -> ch;
    }
    
}