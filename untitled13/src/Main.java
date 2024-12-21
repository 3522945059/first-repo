import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

class BudgetManager {
    private double monthlyBudget;
    private double remainingBudget;
    private List<Transaction> transactions;

    public BudgetManager(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.remainingBudget = monthlyBudget;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        if (transaction.getAmount() < 0) {
            remainingBudget += transaction.getAmount();
        } else {
            remainingBudget -= transaction.getAmount();
        }
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.remainingBudget = monthlyBudget;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }
}

public class Main {
    private static List<Transaction> incomeList = new ArrayList<>();
    private static List<Transaction> expenseList = new ArrayList<>();
    private static BudgetManager budgetManager;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        budgetManager = new BudgetManager(0); // Initialize with no budget

        while (true) {
            System.out.println("=================================\n" +
                    "欢迎使用个人账单管理系统\n" +
                    "=================================\n" +
                    "请选择操作：\n" +
                    "1. 记录收入\n" +
                    "2. 记录支出\n" +
                    "3. 展示账单\n" +
                    "4. 查询账单\n" +
                    "5. 预算管理\n" +
                    "6. 月度统计\n" +
                    "7. 退出\n" +
                    "\n" +
                    "请输入序列号：");
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
                    budgetManagement(scanner);
                    break;
                case 6:
                    monthlyStatistics();
                    break;
                case 7:
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
        System.out.println("=================================\n" +
                "欢迎使用个人账单管理系统\n" +
                "=================================\n" +"输入日期（格式：yyyy-MM-dd）：");
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
        System.out.println("=================================\n" +
                "欢迎使用个人账单管理系统\n" +
                "=================================\n" +"输入日期（格式：yyyy-MM-dd）：");
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
            budgetManager.addTransaction(new Transaction(expenseDate, expenseAmount, expenseCategory, expenseRemarks));
            System.out.println("支出记录成功，当前剩余预算：" + budgetManager.getRemainingBudget());
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

    private static void budgetManagement(Scanner scanner) {
        System.out.println("选择预算管理操作：1. 设置预算 2. 查看预算");
        int budgetChoice = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        switch (budgetChoice) {
            case 1:
                System.out.println("输入每月预算限额：");
                double budget = scanner.nextDouble();
                budgetManager.setMonthlyBudget(budget);
                System.out.println("预算设置成功，当前预算：" + budgetManager.getMonthlyBudget());
                break;
            case 2:
                System.out.println("当前每月预算：" + budgetManager.getMonthlyBudget() + "，剩余预算：" + budgetManager.getRemainingBudget());
                break;
            default:
                System.out.println("无效的预算管理操作，请重新选择。");
                break;
        }
    }

    private static void monthlyStatistics() {
        System.out.println("月度统计：");
        Map<String, Double> monthlyIncome = new HashMap<>();
        Map<String, Double> monthlyExpense = new HashMap<>();

        for (Transaction income : incomeList) {
            String yearMonth = income.getDate().substring(0, 7);
            if (!monthlyIncome.containsKey(yearMonth)) {
                monthlyIncome.put(yearMonth, 0.0);
            }
            monthlyIncome.put(yearMonth, monthlyIncome.get(yearMonth) + income.getAmount());
        }

        for (Transaction expense : expenseList) {
            String yearMonth = expense.getDate().substring(0, 7);
            if (!monthlyExpense.containsKey(yearMonth)) {
                monthlyExpense.put(yearMonth, 0.0);
            }
            monthlyExpense.put(yearMonth, monthlyExpense.get(yearMonth) + expense.getAmount());
        }

        for (Map.Entry<String, Double> entry : monthlyIncome.entrySet()) {
            System.out.println("月份：" + entry.getKey() + ", 总收入：" + entry.getValue());
        }

        for (Map.Entry<String, Double> entry : monthlyExpense.entrySet()) {
            System.out.println("月份：" + entry.getKey() + ", 总支出：" + entry.getValue());
        }
    }
}
