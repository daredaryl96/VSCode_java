import java.util.Random; 

public class PasswordGenerator {

    public static void main (String[] args){
        int length = 10;
        System.out.println("The generated password is " + generatePassword(length)); 
        String password = generatePassword(length);
        System.out.println("The strength of the generated password is " + checkPasswordStrength(password));
    }

    public static String generatePassword(int length) {
        String upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ; 
        String lowerLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789" ;
        String symbols = "!@#$%^&*_=+-/" ; 

        String passwordChars = upperLetters + lowerLetters + numbers + symbols ; 
        Random random = new Random() ; 
        char[] password = new char[length];

        for (int i=0; i < length; i++){
            password[i] = passwordChars.charAt(random.nextInt(passwordChars.length()));
        }

        return new String(password);

    }

    public static String checkPasswordStrength(String password) {
        int flags = 0;
    
        if (password.matches("(?=.*[A-Z]).*")) {
            flags |= 1 << 0;
        }
        if (password.matches("(?=.*[a-z]).*")) {
            flags |= 1 << 1;
        }
        if (password.matches("(?=.*\\d).*")) {
            flags |= 1 << 2;
        }
        if (password.matches("(?=.*[!@#$%^&*()\\[\\]{}\\|;:'\",.<>/?_+=\\-]).*")) {
            flags |= 1 << 3;
        }
    
        int length = password.length();
        int score = Integer.bitCount(flags) + (length >= 8 ? 1 : 0) + (length > 12 ? 1 : 0);
    
        String strength;
    
        if (score < 3) {
            strength = "weak";
        } else if (score < 5) {
            strength = "moderate";
        } else {
            strength = "strong";
        }
    
        return strength;
    }
}