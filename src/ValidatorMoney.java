import java.util.Scanner;

class NotCorrectMoneyException extends  Exception{
    NotCorrectMoneyException(String message) {
        super(message);
    }
}

public class ValidatorMoney {
    public static int verifyMoney(Scanner scanner) throws NotCorrectMoneyException, NumberFormatException  {
        System.out.print("Enter necessary sum: ");
        int money = Integer.parseInt(scanner.next());
        if (money < 0) {
            throw new NotCorrectMoneyException("Impossible sum: " + money);
        }
        System.out.println(money);
        return money;
    }
}
