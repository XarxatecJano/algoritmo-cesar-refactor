import Foundation

class CaesarCipher {
    
    private static let alphabetLength = 26
    
    private struct Letters {
        static let A = Int(Character("A").asciiValue!)
        static let Z = Int(Character("Z").asciiValue!)
        static let a = Int(Character("a").asciiValue!)
        static let z = Int(Character("z").asciiValue!)
    }
    
    private static func willUppercaseLetterExceedAlphabetRange(_ charCode: Int, shift: Int) -> Bool {
        return charCode >= Letters.A &&
               charCode <= Letters.Z &&
               (charCode + shift > Letters.Z || charCode - shift < Letters.A)
    }
    
    private static func willLowercaseLetterExceedAlphabetRange(_ charCode: Int, shift: Int) -> Bool {
        return charCode >= Letters.a &&
               charCode <= Letters.z &&
               (charCode + shift > Letters.z || charCode - shift < Letters.a)
    }
    
    private static func willShiftExceedAlphabetRange(_ charCode: Int, shift: Int) -> Bool {
        return isUpperCaseLetterOutOfRange(charCode, shift: shift) ||
               isLowerCaseOutOfRange(charCode, shift: shift)
    }
    
    static func cipher(_ text: String, shift: Int) -> String {
        var result = ""
        var shift = shift % alphabetLength
        
        for character in text {
            let currentChar = Int(character.unicodeScalars.first!.value)
            
            let shiftToApply = isOutOfAlphabet(currentChar, shift: shift)
                ? shift - alphabetLength
                : shift
            
            let newCharCode = currentChar + shiftToApply
            
            if let scalar = UnicodeScalar(newCharCode) {
                result.append(Character(scalar))
            }
        }
        
        return result
    }
    
    static func decipher(_ text: String, shift: Int) -> String {
        var result = ""
        var shift = -shift % alphabetLength
        
        for character in text {
            let currentChar = Int(character.unicodeScalars.first!.value)
            
            let shiftToApply = isOutOfAlphabet(currentChar, shift: shift)
                ? shift + alphabetLength
                : shift
            
            let newCharCode = currentChar + shiftToApply
            
            if let scalar = UnicodeScalar(newCharCode) {
                result.append(Character(scalar))
            }
        }
        
        return result
    }
}

func runTests() {
    // Test 1
    let result1 = CaesarCipher.cipher("Hello World", shift: 1)
    let expected1 = "Ifmmp!Xpsme"
    assert(result1 == expected1, "\(result1) === '\(expected1)'")
    
    // Test 2
    let ciphered = CaesarCipher.cipher("Hello World", shift: 3)
    let result2 = CaesarCipher.decipher(ciphered, shift: 3)
    let expected2 = "Hello World"
    assert(result2 == expected2, "\(result2) === '\(expected2)'")
    
    print("Todos los tests han pasado correctamente")
}

runTests()
