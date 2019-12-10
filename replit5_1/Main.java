package replit5_1;

import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.util.Scanner;

class BankAccount {
    private int balance;

    public BankAccount(int initial_amount) {
        if (initial_amount >= 0)
            balance = initial_amount;
        else
            balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    //입금
    public boolean deposit(int amount) {
        boolean result = false;
        if (amount < 0)
            System.out.println("invalid deposit amount");
        else {
            balance = balance + amount;
            result = true;
        }
        return result;
    }

    //인출
    public boolean withdraw(int amount) {
        boolean result = false;
        if (amount < 0)
            System.out.println("invalid withdraw amount");
        else if (amount > balance)
            System.out.println("not enough balance");
        else {
            balance = balance - amount;
            result = true;
        }
        return result;
    }
}

class BankReader {
    private String input_line = "";
    private Scanner scan;

    public BankReader() {
        scan = new Scanner(System.in);  // Reading from System.in
    }

    public char readCommand(String message) {
        System.out.print(message);
        input_line = scan.nextLine().toUpperCase();
        return input_line.charAt(0);
    }

    public int readAmount() {
        int answer = 0;
        String s = input_line.substring(1, input_line.length());

        if (s.length() > 0) {
            double dollars_cents = new Double(s).doubleValue();
            answer = (int) (dollars_cents * 100);
        } else
            System.out.println("invalid command - input amount: 0");
        return answer;
    }
}

class BankWriter {
    private BankAccount bank;
    private String last_transaction = "";

    public BankWriter(BankAccount b) {
        bank = b;
    }

    public String unconvert(int i) {
        return new DecimalFormat("0.00").format(i / 100.0);
    }

    public void setTransaction(String message, int amount) {
        last_transaction = message + " " + unconvert(amount);
        System.out.println("");
        System.out.println("transaction: " + last_transaction);
    }

    public void setTransaction(String message) {
        last_transaction = message;
        System.out.println("");
        System.out.println("transaction: " + last_transaction);
    }

}

class AccountController {
    private BankReader reader; // input view
    private BankAccount primary_account, secondary_account, account;
    private BankWriter primary_writer, secondary_writer, writer;

    public AccountController(BankReader r, BankAccount a1, BankWriter w1, BankAccount a2, BankWriter w2) {
        reader = r;
        primary_account = a1;
        primary_writer = w1;
        secondary_account = a2;
        secondary_writer = w2;
        account = primary_account;
        writer = primary_writer;
    }

    public void resetAccount(BankAccount new_account, BankWriter new_writer) {
        account = new_account;
        writer = new_writer;
    }

    public void processTransactions() {
        char command = reader.readCommand("Commands (P/S/D/W/T/I/Q): ");

        switch (command) {
            case 'P':
                resetAccount(primary_account, primary_writer);
                break;
            case 'S':
                System.out.println("");
                resetAccount(secondary_account, secondary_writer);
                break;
            case 'Q':
                System.out.println("Quit");
                return;
            case 'D': {
                int amount = reader.readAmount();
                if (account.deposit(amount))
                    writer.setTransaction("deposit $", amount);
                else
                    writer.setTransaction("deposit error ", amount);

                break;
            }
            case 'W': {
                int amount = reader.readAmount();
                if (account.withdraw(amount))
                    writer.setTransaction("withdraw $", amount);
                else
                    writer.setTransaction("withdraw error ", amount);
                break;
            }
            case 'T':
                // 'T 금액', 활성 계좌에서 비활성 계좌로 금액만큼 이체
            {
                // 코드를 작성하세요.
                int transfer = reader.readAmount();
                if (account.getBalance() >= transfer) {
                    account.withdraw(transfer);
                    if (account == primary_account) {
                        resetAccount(secondary_account, secondary_writer);
                        account.deposit(transfer);
                        resetAccount(primary_account, primary_writer);
                    }
                    else {
                        resetAccount(primary_account, primary_writer);
                        account.deposit(transfer);
                        resetAccount(secondary_account, secondary_writer);
                    }
                    writer.setTransaction("transfer $", transfer);
                }
                else {
                    account.withdraw(transfer);
                    writer.setTransaction("transfer error");
                }
                break;
            }
            case 'I':
                // 'I 이율', 활성 계좌의 금액을 이율만큼 증가
            {
                // 코드를 작성하세요.
                int interest = reader.readAmount();
                int money = (int) (account.getBalance() * interest *0.01);
                if (interest >= 0 && interest <= 100) {
                    account.deposit(money);
                    writer.setTransaction("interest $", money);
                }
                else {
                    writer.setTransaction("interest error");
                }
                break;
            }
            default:
                writer.setTransaction("invalid commpand: " + command);
        }
        // 모든 계좌의 정보를 출력합니다.
        System.out.println("=======================");
        System.out.println("== currently active : " + ((account == primary_account) ? "primary" : "secondary"));
        System.out.println("== primary account : " + primary_writer.unconvert(primary_account.getBalance()));
        System.out.println("== secondary account : " + secondary_writer.unconvert(secondary_account.getBalance()));
        System.out.println("=======================");

        this.processTransactions();
    }
}

/*
수업시간에 다룬 두 계좌를 관리하는 프로그램이다. 코드에는 다음 클래스들의 정의가 이미 되어있다.
BankAccount
BankReader
BankWriter
AccountController2
AccountManger2

이 두 계좌 관리 프로그램에 다음 기능을 추가하라.

"T 금액" 을 명령어로 입력받아서 현재 활성화 된 계좌에서 비활성 계좌로 금액만큼 이체 (transfer)
"I 이율" 을 명령어로 입력받아서, 현재 활성화 된 계좌의 금액을 이율(interest)만큼 증가
이율은 0~1 범위의 실수이다.
이율은 소수점 두자리까지만 인정한다. 예, 0.055 => 0.05
이율만큼 증가한다는 것은 (금액 * 이율) 만큼 증가시킨다는 뜻이다.

다음의 명령문들을 사용하여 성공/실패할 경우 메세지를 출력한다.

이체에 성공할 경우: writer.setTransaction("transfer $", 금액);
이체에 실패할 경우: writer.setTransaction("transfer error");
이율증가에 성공할 경우: writer.setTransaction("interest $", 금액);
이율증가에 실패할 경우: writer.setTransaction("interest error");


참고:
- BankAccount 클래스에 이미 정의되어있는 deposit, withdraw 메소드를 이용하여 문제를 해결하실 수 있습니다.
- 코드를 보시면 main 메소드가 있는 Main 클래스만 public 으로 정의해주고 있고 나머지 클래스는 public 없이 정의하고 있다는 것을 아실 수 있습니다. 이렇게 여러개의 클래스를 파일 하나에 정의하는 것도 가능합니다.
 */

public class Main {
    public static void main(String[] args) {
        BankReader reader = new BankReader();
        BankAccount primary_account = new BankAccount(0);
        BankWriter primary_writer = new BankWriter(primary_account);
        BankAccount secondary_account = new BankAccount(0);
        BankWriter secondary_writer = new BankWriter(secondary_account);
        AccountController controller = new AccountController(reader,
                primary_account, primary_writer, secondary_account,
                secondary_writer);
        controller.processTransactions();
    }
}

