import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Methods methods = new Methods();

        while (true) {
            System.out.println("[1] - Add Order");
            System.out.println("[2] - Display Order");
            System.out.println("[3] - Update Item");
            System.out.println("[4] - Delete Item");
            System.out.println("[5] - Order History");
            System.out.println("[6] - View Stock");
            System.out.println("[7] - Employee Performance");
            System.out.println("[0] - Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 0:
                    System.out.println("~~ EXIT ~~");
                    System.exit(0);
                    break;
                case 1:
                    methods.addOrder();
                    break;
                case 2:
                    System.out.println("~~ DISPLAY ORDER ~~");
                    methods.displayOrders();
                    break;
                case 3:
                    System.out.println("~~ UPDATE ITEM ~~");
                    methods.updateItem();
                    break;
                case 4:
                    System.out.println("~~ DELETE ITEM ~~");
                    methods.deleteItems();
                    break;
                case 5:
                    System.out.println("~~ ORDER HISTORY ~~");
                    System.out.print("Enter customer name: ");
                    String customerName = sc.nextLine();
                    methods.viewOrderHistory(customerName);
                    break;
                case 6:
                    System.out.println("~~ VIEW STOCK ~~");
                    methods.displayStockItemsPublic();
                    break;
                case 7:
                    System.out.println("~~ EMPLOYEE PERFORMANCE ~~");
                        methods.displayEmployeeReviews();

                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
