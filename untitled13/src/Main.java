import java.util.ArrayList;
import java.util.List;
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

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}

public class Main {
    private static List<Transaction> incomeList = new ArrayList<>();
    private static List<Transaction> expenseList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请选择操作：1. 记录收入 2. 记录支出 3. 展示账单 4. 查询账单 5. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消费换行符

            switch (choice) {
                case 1:
                    recordIncome(scanner);
                    break;
                case 2:
                    recordExpense(scanner);
                    break;
                case 3:
                    displayTransactions();
                    break;
                case 4:
                    queryTransactions(scanner);
                    break;
                case 5:
                    System.out.println("退出系统。");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选项，请重新选择。");
                    break;
            }
        }
    }

    private static void recordIncome(Scanner scanner) {
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
            incomeList.add(new Transaction(incomeDate, incomeAmount, incomeCategory, incomeRemarks));
            System.out.println("收入记录成功！");
        } else {
            System.out.println("收入金额必须为正数！");
        }
    }

    private static void recordExpense(Scanner scanner) {
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
            expenseList.add(new Transaction(expenseDate, expenseAmount, expenseCategory, expenseRemarks));
            System.out.println("支出记录成功！");
        } else {
            System.out.println("支出金额必须为正数！");
        }
    }

    private static void displayTransactions() {
        System.out.println("收入记录：");
        for (Transaction income : incomeList) {
            System.out.println(income);
        }
        System.out.println("支出记录：");
        for (Transaction expense : expenseList) {
            System.out.println(expense);
        }
    }

    private static void queryTransactions(Scanner scanner) {
        System.out.println("选择查询类型（1. 按日期 2. 按日期范围 3. 按类别）：");
        int queryType = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        switch (queryType) {
            case 1:
                System.out.println("输入查询日期（格式：yyyy-MM-dd）：");
                String queryDate = scanner.nextLine();
                System.out.println("收入记录：");
                for (Transaction income : incomeList) {
                    if (income.getDate().equals(queryDate)) {
                        System.out.println(income);
                    }
                }
                System.out.println("支出记录：");
                for (Transaction expense : expenseList) {
                    if (expense.getDate().equals(queryDate)) {
                        System.out.println(expense);
                    }
                }
                break;
            case 2:
                System.out.println("输入开始日期（格式：yyyy-MM-dd）：");
                String startDate = scanner.nextLine();
                System.out.println("输入结束日期（格式：yyyy-MM-dd）：");
                String endDate = scanner.nextLine();
                System.out.println("收入记录：");
                for (Transaction income : incomeList) {
                    if (income.getDate().compareTo(startDate) >= 0 && income.getDate().compareTo(endDate) <= 0) {
                        System.out.println(income);
                    }
                }
                System.out.println("支出记录：");
                for (Transaction expense : expenseList) {
                    if (expense.getDate().compareTo(startDate) >= 0 && expense.getDate().compareTo(endDate) <= 0) {
                        System.out.println(expense);
                    }
                }
                break;
            case 3:
                System.out.println("输入查询类别：");
                String queryCategory = scanner.nextLine();
                System.out.println("收入记录：");
                for (Transaction income : incomeList) {
                    if (income.getCategory().equals(queryCategory)) {
                        System.out.println(income);
                    }
                }
                System.out.println("支出记录：");
                for (Transaction expense : expenseList) {
                    if (expense.getCategory().equals(queryCategory)) {
                        System.out.println(expense);
                    }
                }
                break;
            default:
                System.out.println("无效的查询类型，请重新选择。");
                break;
        }
    }
}