import java.util.Scanner;

class Transaction {
    private String date;
    private double amount;
    private String category;
    private String remarks;

    public Transaction(String date, double amount, String category, String remarks) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "日期: " + date + ", 金额: " + amount + ", 类别: " + category + ", 备注: " + remarks;
    }

    public double getAmount() {
        return amount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Transaction income = null;
        Transaction expense = null;

        while (true) {
            System.out.println("请选择操作：1. 记录收入 2. 记录支出 3. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消费换行符

            switch (choice) {
                case 1:
                    System.out.println("输入日期（格式：yyyy-MM-dd）：");
                    String incomeDate = scanner.nextLine();
                    System.out.println("输入收入金额（必须为正数）：");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine(); // 消费换行符
                    if (incomeAmount > 0) {
                        System.out.println("输入收入类别（如工资、奖金等）：");
                        String incomeCategory = scanner.nextLine();
                        System.out.println("输入备注：");
                        String incomeRemarks = scanner.nextLine();
                        income = new Transaction(incomeDate, incomeAmount, incomeCategory, incomeRemarks);
                        System.out.println("收入记录成功：" + income);
                    } else {
                        System.out.println("收入金额必须为正数！");
                    }
                    break;
                case 2:
                    System.out.println("输入日期（格式：yyyy-MM-dd）：");
                    String expenseDate = scanner.nextLine();
                    System.out.println("输入支出金额（必须为正数）：");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine(); // 消费换行符
                    if (expenseAmount > 0) {
                        System.out.println("输入支出类别（如餐饮、交通、购物等）：");
                        String expenseCategory = scanner.nextLine();
                        System.out.println("输入备注：");
                        String expenseRemarks = scanner.nextLine();
                        expense = new Transaction(expenseDate, expenseAmount, expenseCategory, expenseRemarks);
                        System.out.println("支出记录成功：" + expense);
                    } else {
                        System.out.println("支出金额必须为正数！");
                    }
                    break;
                case 3:
                    System.out.println("退出系统。");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选项，请重新选择。");
                    break;
            }
        }
    }
}