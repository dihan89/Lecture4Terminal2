import java.util.Arrays;

public interface TerminalBank  {
    public int getBalance(int[] password);
    public void addToBalance(int[] password, int money);
    public void getFromBalance(int[] password, int money);
}
