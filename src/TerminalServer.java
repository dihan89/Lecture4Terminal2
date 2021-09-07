import javax.security.auth.login.AccountLockedException;
import java.util.*;
import java.io.*;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
class DangerAccessException extends RuntimeException{
    DangerAccessException(String str){
        super("Danger attempt to access. " + str);
    }
}

public class TerminalServer implements TerminalBank{
    final static int lockedTimeMs = 10_000;
    final static int passwordLength = 4;
    final static int nAttempts = 3;
    private int[] password;
    private int attemptNo;
    private boolean blocked;
    long  startTimePause;
    private int balance;

    public static int getPasswordLength() {
        return passwordLength;
    }

    public int getBalance(int[] password) {
        if (Arrays.equals(this.password, password))
            return balance;
        else {
            throw new DangerAccessException("get Balance");
        }
    }

    public void addToBalance(int[] password, int money) {
        if (Arrays.equals(this.password, password))
            balance += money;
        else {
            throw new DangerAccessException("addToBalance");
        }
    }

    public void getFromBalance(int[] password, int money) {
        if (money % 100 != 0) {
            System.out.println("Wrong summa! It must be divided by 100!");
            return;
        }
        if (money > balance) {
            System.out.println("Not enough money!");
            return;
        }
        if (Arrays.equals(this.password, password))
            balance -= money;
        else {
            throw new DangerAccessException("getFromBalance");
        }
    }

   public boolean checkPassword(int[] password) throws  ExitException {
        if (blocked){
            try {
                if (System.currentTimeMillis() - startTimePause < lockedTimeMs)
                    throw new AccountLockedException();
                else {
                    blocked = false;
                    attemptNo = 0;
                }
            } catch(AccountLockedException exc) {
                System.out.println("Account is locked! Wait: " +
                        Math.max(1, Math.round((lockedTimeMs - (System.currentTimeMillis() - startTimePause)) * 0.001))
                        + "  seconds");
            }
        }

        if (Arrays.equals(this.password, password)) {
            attemptNo = 0;
            return true;
        }
        else {
            attemptNo += 1;
            if (attemptNo == nAttempts){
                startTimePause = System.currentTimeMillis();
                blocked = true;
                System.out.println(" Is Locked! Wait 10 seconds! ");
            }
            return false;
        }
    }



    TerminalServer(int [] password){
        this.password = password.clone();
       // System.out.println("Original password = " + Arrays.toString(this.password));
        balance = 0;
        attemptNo = 0;
        blocked = false;
    }
}