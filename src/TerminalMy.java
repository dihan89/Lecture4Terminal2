import javax.security.auth.login.AccountLockedException;
import java.util.*;
import java.io.*;
import java.io.InputStreamReader;
import java.util.regex.Pattern;





class ExitException extends  Exception{
    ExitException(String message) {
        super(message);
    }
}


public class TerminalMy {
    private TerminalServer terminalServer;
    private Scanner scanner;
    private int[] enteredPassword;
    private int[] entryPassword() throws ExitException {
        int[] attPassword = new int[TerminalServer.getPasswordLength()];
        Validator.readPassword(scanner, attPassword);
        return attPassword;
    }
    public static void help() {
        System.out.println(" Please enter the following parameters: ");
        System.out.println(" b : get balance");
        System.out.println(" p : put money");
        System.out.println(" w : withdraw money");
        System.out.println(" e : exit" );
    }

    public void mainFunction () throws PinDigitException {

        while (true) {

            help();
            String str = scanner.next().toLowerCase();
            switch (str){
                case "b":
                case "w":
                case "p": break;
                case "e": {
                    return;
                }
                default: {
                    System.out.println("Not correct choice!");
                    return;
                }
            }

            boolean isCorrectPassword = false;
            while(!isCorrectPassword){
                try {
                    enteredPassword = entryPassword();
                    isCorrectPassword = terminalServer.checkPassword(enteredPassword);
                    if (!isCorrectPassword) System.out.println("Password is not corrects. ");
                } catch (ExitException exc){
                    return;
                }
            }
            switch (str) {
                case "b": {
                    System.out.println("Balance: " + terminalServer.getBalance(enteredPassword));
                    break;
                }
                case "p": {
                    try {
                        terminalServer.addToBalance(enteredPassword, ValidatorMoney.verifyMoney(scanner));
                    } catch (NotCorrectMoneyException | NumberFormatException exc){
                        System.out.println("Incorrect sum!");
                    }
                    break;
                }
                case "w": {
                    try {
                        terminalServer.getFromBalance(enteredPassword, ValidatorMoney.verifyMoney(scanner));
                    } catch (NotCorrectMoneyException | NumberFormatException exc){
                        System.out.println("Incorrect sum!");
                    }

                }

            }

        }
        //scanner.close();
    }



    TerminalMy(TerminalServer terminalServer,  Scanner scanner){
        this.scanner = scanner;
        this.terminalServer = terminalServer;
    }
}
