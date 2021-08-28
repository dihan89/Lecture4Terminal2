import java.util.Arrays;
import java.util.Scanner;

class PinDigitException extends Exception{
    PinDigitException(String message) {
        super(message);
    }
}

public class Validator {
    final static int passwordLength = 4;

    public static int getDigit(String str) throws PinDigitException {
        if (str.matches("[0-9]")) {
            return Integer.parseInt(str);
        }
        throw new PinDigitException("Incorrect data is entered! Try again.");
    }

    public static void readPassword(Scanner scanner, int [] password) throws ExitException {
        System.out.println(" Enter the password (four digits).");
        int counter = 0;
        while (counter < passwordLength) {
            System.out.print(" Enter digit[" + Integer.toString(counter) + "] : or 'e' for exit:  ");
            String str = scanner.next();
            if (str.length() == 1 && str.toLowerCase().charAt(0) == 'e') {
                throw new ExitException("User decide to exit!");
            }
            try {
                int digit = getDigit(str);
                password[counter++] = digit;
            } catch (PinDigitException exc) {
                counter = 0;
                System.out.println(exc.getMessage());
            }
        }
        System.out.println(" Read password = " + Arrays.toString(password));
    }
}
