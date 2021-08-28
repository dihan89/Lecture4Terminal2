import java.io.InputStream;
import java.util.*;

public class Lecture4Terminal{

    public static void main(String[] args) throws PinDigitException {
        int [] password = {1, 8, 6, 7};
        Scanner scanner = new Scanner(System.in);
        TerminalServer terminalServ = new TerminalServer(password);
        TerminalMy terminal = new TerminalMy(terminalServ, scanner);
        terminal.mainFunction();
        scanner.close();
    }
}