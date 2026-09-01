import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    String category;
    double amount;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Expense> expenses = new ArrayList<>();

    static double totalIncome = 0;
    static double monthlyBudget = 0;

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("   SMART EXPENSE & BUDGET");
            System.out.println("================================");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Expenses");
            System.out.println("4. Set Monthly Budget");
            System.out.println("5. View Dashboard");
            System.out.println("6. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addIncome();
                    break;

                case 2:
                    addExpense();
                    break;

                case 3:
                    viewExpenses();
                    break;

                case 4:
                    setBudget();
                    break;

                case 5:
                    dashboard();
                    break;

                case 6:
                    System.out.println("Thank you for using Smart Expense Analyzer!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Add Income
    static void addIncome() {

        System.out.print("Enter income amount: ₹");
        double income = sc.nextDouble();

        if (income <= 0) {
            System.out.println("Income must be greater than 0.");
            return;
        }

        totalIncome += income;

        System.out.println("Income added successfully!");
    }

    // Add Expense
    static void addExpense() {

        sc.nextLine();

        System.out.print("Enter expense category: ");
        String category = sc.nextLine();

        System.out.print("Enter expense amount: ₹");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Expense must be greater than 0.");
            return;
        }

        expenses.add(new Expense(category, amount));

        System.out.println("Expense added successfully!");

        // Budget warning
        double totalExpense = calculateTotalExpense();

        if (monthlyBudget > 0 && totalExpense > monthlyBudget) {

            System.out.println("⚠️ WARNING!");
            System.out.println("You have exceeded your monthly budget.");
        }
    }

    // View Expenses
    static void viewExpenses() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses added yet.");
            return;
        }

        System.out.println("\n--------- EXPENSES ---------");

        for (int i = 0; i < expenses.size(); i++) {

            Expense e = expenses.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    e.category +
                    " - ₹" +
                    e.amount
            );
        }

        System.out.println("----------------------------");
        System.out.println(
                "Total Expense: ₹" +
                calculateTotalExpense()
        );
    }

    // Set Budget
    static void setBudget() {

        System.out.print("Enter monthly budget: ₹");
        monthlyBudget = sc.nextDouble();

        System.out.println("Monthly budget set successfully!");
    }

    // Calculate Total Expense
    static double calculateTotalExpense() {

        double total = 0;

        for (Expense e : expenses) {
            total += e.amount;
        }

        return total;
    }

    // Dashboard
    static void dashboard() {

        double totalExpense = calculateTotalExpense();
        double balance = totalIncome - totalExpense;

        System.out.println("\n================================");
        System.out.println("          DASHBOARD");
        System.out.println("================================");

        System.out.println("Total Income   : ₹" + totalIncome);
        System.out.println("Total Expense  : ₹" + totalExpense);
        System.out.println("Balance        : ₹" + balance);

        if (monthlyBudget > 0) {

            double remainingBudget =
                    monthlyBudget - totalExpense;

            System.out.println("Monthly Budget : ₹" + monthlyBudget);
            System.out.println(
                    "Budget Left    : ₹" + remainingBudget
            );

            if (remainingBudget < 0) {

                System.out.println(
                        "Status         : ⚠️ OVER BUDGET"
                );

            } else {

                System.out.println(
                        "Status         : ✅ Within Budget"
                );
            }
        }

        System.out.println("================================");
    }
}
