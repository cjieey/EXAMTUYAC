 import java.util.ArrayList;
import java.util.Comparator;  // Add this line
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

    public class Methods {
        private List<Order> orders;
        private List<StockItem> stockItems;
     private EmployeeRating employeeRating; // Move to class level
   private List<EmployeeRating> employeeRatings;
        public class StockItem {
            private String itemName;
            private double price;
            private int quantity;
  private int menuNumber;
  
        public StockItem(String itemName, double price, int quantity, int menuNumber) {
            this.itemName = itemName;
            this.price = price;
            this.quantity = quantity;
            this.menuNumber = menuNumber;
        }
        public int getMenuNumber() {
            return menuNumber;
        }
            public String getItemName() {
                return itemName;
            }

            public double getPrice() {
                return price;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int newQuantity) {
                this.quantity = newQuantity;
            }

            public void setPrice(double newPrice) {
                this.price = newPrice;
            }

            @Override
            public String toString() {
                return "StockItem{" +
                        "itemName='" + itemName + '\'' +
                        ", price=" + price +
                        ", quantity=" + quantity +
                        '}';
            }
        }
        public class Order {
            private String customerName;
            private int menuNumber;
            private int quantity;
            private double totalAmount;
            private boolean rateEmployee;
            private int employeeRating;
            

            public Order(String customerName, int menuNumber, int quantity, double totalAmount, boolean rateEmployee) {
                this.customerName = customerName;
                this.menuNumber = menuNumber;
                this.quantity = quantity;
                this.totalAmount = totalAmount;
                this.rateEmployee = rateEmployee;
            }
            

            public String getCustomerName() {
                return customerName;
            }

            public int getMenuNumber() {
                return menuNumber;
            }

            public int getQuantity() {
                return quantity;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public boolean isRateEmployee() {
                return rateEmployee;
            }

            public int getEmployeeRating() {
                return employeeRating;
            }

            public void setEmployeeRating(int employeeRating) {
                this.employeeRating = employeeRating;
            }

            @Override
            public String toString() {
                return "Order{" +
                        "customerName='" + customerName + '\'' +
                        ", menuNumber=" + menuNumber +
                        ", quantity=" + quantity +
                        ", totalAmount=" + totalAmount +
                        ", rateEmployee=" + rateEmployee +
                        ", employeeRating=" + employeeRating +
                        '}';
            }
        }

   public class EmployeeRating {
        private String employeeID;
        private int totalOrdersTaken;
        private double totalSales;
        private int averageRating;

        public EmployeeRating(String employeeID, int totalOrdersTaken, double totalSales, int averageRating) {
            this.employeeID = employeeID;
            this.totalOrdersTaken = totalOrdersTaken;
            this.totalSales = totalSales;
            this.averageRating = averageRating;
        }

        public String getEmployeeID() {
            return employeeID;
        }

        public int getTotalOrdersTaken() {
            return totalOrdersTaken;
        }

        public double getTotalSales() {
            return totalSales;
        }

        public int getAverageRating() {
            return averageRating;
        }

        public void setTotalOrdersTaken(int totalOrdersTaken) {
            this.totalOrdersTaken = totalOrdersTaken;
        }

        public void setTotalSales(double totalSales) {
            this.totalSales = totalSales;
        }

        public void setAverageRating(int averageRating) {
            this.averageRating = averageRating;
        }

        public void updateAverageRating(int newRating) {
            // Update the average rating logic here
        }

        @Override
        public String toString() {
            return "EmployeeRating{" +
                    "employeeID='" + employeeID + '\'' +
                    ", totalOrdersTaken=" + totalOrdersTaken +
                    ", totalSales=" + totalSales +
                    ", averageRating=" + averageRating +
                    '}';
        }
    }
   
    
       public Methods() {
            this.orders = new ArrayList<>();
        this.stockItems = new ArrayList<>();
        this.employeeRatings = new ArrayList<>(); // Add this line to initialize the list
        initializeStock();
       }
         public void displayStockItemsPublic() {
        displayStockItems();
    }

    // Public wrapper method for deleteItems
    public void deleteItemsPublic() {
        deleteItems();
    }

       private void initializeStock() {
    stockItems.add(new StockItem("Adobo", 50, 10, 1)); // Add initial stock items
    stockItems.add(new StockItem("Halang-halang", 30, 15, 2));
    stockItems.add(new StockItem("Menudo", 40, 12, 3));
    stockItems.add(new StockItem("Humba", 60, 8, 4));
}


      public void addOrder() {
    Scanner sc = new Scanner(System.in);

    // Display available menu options
    displayStockItems();

    // Get user details
    System.out.print("Enter your name: ");
    String customerName = sc.nextLine();
    System.out.print("Enter menu number: ");
    int menuNumber = sc.nextInt();
    sc.nextLine(); // Consume newline
    System.out.print("Enter quantity: ");
    int quantity = sc.nextInt();
    sc.nextLine(); // Consume newline

    // Check if the item is in stock
    if (!isItemInStock(menuNumber, quantity)) {
        System.out.println("Sorry, the selected item is out of stock or the quantity is not available.");
        return;
    }

    // Calculate total amount
    double totalAmount = calculateTotalAmount(menuNumber, quantity);

    // Display total amount and ask for rating
    System.out.println("Total amount: " + totalAmount + " pesos");
    System.out.print("Would you like to rate the employee? (yes/no): ");
    String rateEmployeeChoice = sc.nextLine();

    // Add order
    Order newOrder = new Order(customerName, menuNumber, quantity, totalAmount, rateEmployeeChoice.equalsIgnoreCase("yes"));
    orders.add(newOrder);

    // Update stock
    updateStock(menuNumber, quantity);

    // Update employee performance
    updateEmployeePerformance(newOrder);

    if (newOrder.isRateEmployee()) {
        System.out.print("Enter employee rating (1-5): ");
        int employeeRatingValue = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Set the employee rating in the order
        newOrder.setEmployeeRating(employeeRatingValue);
    }

    // Display receipt or amount to pay
    System.out.println("Receipt:");
    System.out.println(newOrder.toString());
}

private boolean isItemInStock(int menuNumber, int quantity) {
        for (StockItem item : stockItems) {
            if (item.getQuantity() >= quantity && item.getMenuNumber() == menuNumber) {
                return true;
            }
        }
        return false;
    }

    private void updateStock(int menuNumber, int quantity) {
        for (StockItem item : stockItems) {
            if (item.getMenuNumber() == menuNumber) {
                item.setQuantity(item.getQuantity() - quantity);
                if (item.getQuantity() == 0) {
                    System.out.println("Warning: " + item.getItemName() + " is now out of stock.");
                }
                return;
            }
        }
    }

        public void updateItem() {
    Scanner sc = new Scanner(System.in);
    displayStockItems();

    System.out.print("Enter the item name to update: ");
    String itemName = sc.nextLine();

    // Find the item in the stockItems list
    for (StockItem item : stockItems) {
        if (item.getItemName().equalsIgnoreCase(itemName)) {
            // Update item details
            System.out.print("Enter new quantity: ");
            int newQuantity = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Enter new price: ");
            double newPrice = sc.nextDouble();
            sc.nextLine(); // Consume newline

            item.setQuantity(newQuantity);
            item.setPrice(newPrice);

            System.out.println("Item updated successfully!");

            // Display updated stock items
            System.out.println("Updated Stock Items:");
            displayStockItems();

            return;
        }
    }

    System.out.println("Item not found in stock.");
}


        public void deleteItems() {
    Scanner sc = new Scanner(System.in);
    displayStockItems();

    System.out.print("Enter the item name to delete: ");
    String itemName = sc.nextLine();

    // Using iterator to remove item from stockItems
    Iterator<StockItem> iterator = stockItems.iterator();
    while (iterator.hasNext()) {
        StockItem item = iterator.next();
        if (item.getItemName().equalsIgnoreCase(itemName)) {
            iterator.remove();
            System.out.println("Item deleted successfully!");

            // Display updated stock items
            System.out.println("Updated Stock Items:");
            displayStockItems();
            
            return;
        }
    }

    System.out.println("Item not found in stock.");
}


      private void displayStockItems() {
    if (stockItems.isEmpty()) {
        System.out.println("No stock available.");
    } else {
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Menu Number", "Item Name", "Price", "Quantity");
        for (StockItem item : stockItems) {
            if (item.getQuantity() > 0) {
                System.out.printf("%-15d %-15s %-15.2f %-15d%n",
                        item.getMenuNumber(), item.getItemName(), item.getPrice(), item.getQuantity());
            }
        }
    }
}
    public void displayOrders() {
    if (orders.isEmpty()) {
        System.out.println("No orders available.");
    } else {
        System.out.printf("%-15s %-15s %-25s %-15s %-15s %-15s%n",
                "Customer", "Menu Number", "Item Name", "Quantity", "Total Amount", "Employee Rating");
        for (Order order : orders) {
            StockItem item = findStockItemByMenuNumber(order.getMenuNumber());

            System.out.printf("%-15s %-15d %-25s %-15d %-15.2f %-15s%n",
                    order.getCustomerName(), order.getMenuNumber(),
                    (item != null) ? item.getItemName() : "", order.getQuantity(),
                    order.getTotalAmount(), (order.isRateEmployee()) ? order.getEmployeeRating() : "N/A");
        }
    }
}

private StockItem findStockItemByMenuNumber(int menuNumber) {
    for (StockItem item : stockItems) {
        if (item.getMenuNumber() == menuNumber) {
            return item;
        }
    }
    return null;
}


        private double calculateTotalAmount(int menuNumber, int quantity) {
        double itemPrice = 0;

        switch (menuNumber) {
            case 1:
                itemPrice = 50;
                break;
            case 2:
                itemPrice = 30;
                break;
            case 3:
                itemPrice = 40;
                break;
            case 4:
                itemPrice = 60;
                break;
            // Add more cases if needed

            default:
                System.out.println("Invalid menu number.");
                return 0;
        }

        return itemPrice * quantity;
    }
  public void viewOrderHistory(String customerName) {
    boolean customerFound = false;

    for (Order order : orders) {
        if (order.getCustomerName().equalsIgnoreCase(customerName)) {
            if (!customerFound) {
                System.out.println("Order History for " + customerName + ":");
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n",
                        "Customer", "Menu Number", "Item Name", "Quantity", "Total Amount", "Employee Rating");
                customerFound = true;
            }

            StockItem item = findStockItemByMenuNumber(order.getMenuNumber());

            System.out.printf("%-15s %-15d %-15s %-15d %-15.2f %-15d%n",
                    order.getCustomerName(), order.getMenuNumber(),
                    (item != null) ? item.getItemName() : "", order.getQuantity(),
                    order.getTotalAmount(), order.getEmployeeRating());
        }
    }

    if (!customerFound) {
        System.out.println("No order history found for " + customerName);
    }
}

        
      public void displayEmployeeReviews() {
    List<EmployeeRating> employeeRatings = new ArrayList<>();

    for (Order order : orders) {
        if (order.isRateEmployee()) {
            EmployeeRating employeeRating = findEmployeeRating(order.getEmployeeRating());
            if (employeeRating != null) {
                employeeRating.setTotalOrdersTaken(employeeRating.getTotalOrdersTaken() + 1);
                employeeRating.setTotalSales(employeeRating.getTotalSales() + order.getTotalAmount());
                employeeRating.updateAverageRating(order.getEmployeeRating());
            } else {
                EmployeeRating newEmployeeRating = new EmployeeRating(
                        String.valueOf(order.getEmployeeRating()),
                        1,
                        order.getTotalAmount(),
                        order.getEmployeeRating()
                );
                employeeRatings.add(newEmployeeRating);
            }
        }
    }

    // Sort the employee ratings based on average rating (descending order)
    employeeRatings.sort(Comparator.comparingInt(EmployeeRating::getAverageRating).reversed());

    // Display the sorted employee ratings in table form
    System.out.printf("%-15s %-20s %-15s %-15s%n",
            "Employee ID", "Total Orders Taken", "Total Sales", "Average Rating");
    for (EmployeeRating rating : employeeRatings) {
        System.out.printf("%-15s %-20d %-15.2f %-15d%n",
                rating.getEmployeeID(), rating.getTotalOrdersTaken(), rating.getTotalSales(), rating.getAverageRating());
    }
}


// Helper method to find an existing EmployeeRating object based on employee ID
private EmployeeRating findEmployeeRating(int employeeRating) {
    String employeeRatingString = Integer.toString(employeeRating);
    for (EmployeeRating rating : employeeRatings) {
        if (rating.getEmployeeID().equals(employeeRatingString)) {
            return rating;
        }
    }
    return null;
}


     public void updateEmployeePerformance(Order order) {
    // Find or create the EmployeeRating object based on employee ID
    employeeRating = findOrCreateEmployeeRating("123");

    // Update totalOrdersTaken, totalSales, and averageRating based on the order
    employeeRating.setTotalOrdersTaken(employeeRating.getTotalOrdersTaken() + 1);
    employeeRating.setTotalSales(employeeRating.getTotalSales() + order.getTotalAmount());

    if (order.isRateEmployee()) {
        employeeRating.updateAverageRating(order.getEmployeeRating());
    }
}

// Helper method to find or create an EmployeeRating object based on employee ID
private EmployeeRating findOrCreateEmployeeRating(String employeeID) {
    for (EmployeeRating rating : employeeRatings) {
        if (rating.getEmployeeID().equals(employeeID)) {
            return rating;
        }
    }

    // If the EmployeeRating does not exist, create a new one
    EmployeeRating newEmployeeRating = new EmployeeRating(employeeID, 0, 0, 0);
    employeeRatings.add(newEmployeeRating);
    return newEmployeeRating;
}
    }