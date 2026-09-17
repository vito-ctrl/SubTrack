package ui;

import util.ValidateInput;
import java.util.Scanner;
import java.services.SubscriptionService;
public class Menu {

    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void start() {

        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    createSubscription();
                    break;

                case 2:
                    modifySubscription();
                    break;

                case 3:
                    deleteSubscription();
                    break;

                case 4:
                    listSubscriptions();
                    break;

                case 5:
                    showSubscriptionPayments();
                    break;

                case 6:
                    recordPayment();
                    break;

                case 7:
                    modifyPayment();
                    break;

                case 8:
                    deletePayment();
                    break;

                case 9:
                    showUnpaidPayments();
                    break;

                case 10:
                    showTotalPaid();
                    break;

                case 11:
                    showLastFivePayments();
                    break;

                case 12:
                    financialReports();
                    break;

                case 0:
                    System.out.println("\nGoodbye!");
                    break;

                default:
                    System.out.println("\nInvalid choice!");
            }

        } while (choice != 0);
    }


    private void displayMenu() {

        System.out.println("\n======================================");
        System.out.println("       SUBSCRIPTION MANAGEMENT");
        System.out.println("======================================");

        System.out.println("\n--- SUBSCRIPTIONS ---");
        System.out.println("1. Create subscription");
        System.out.println("2. Modify subscription");
        System.out.println("3. Delete subscription");
        System.out.println("4. List subscriptions");

        System.out.println("\n--- PAYMENTS ---");
        System.out.println("5. Show subscription payments");
        System.out.println("6. Record payment");
        System.out.println("7. Modify payment");
        System.out.println("8. Delete payment");
        System.out.println("9. Show unpaid payments");
        System.out.println("10. Show total paid");

        System.out.println("\n--- REPORTS ---");
        System.out.println("11. Show last 5 payments");
        System.out.println("12. Financial reports");

        System.out.println("\n0. Exit");

        System.out.print("\nChoose an option: ");
    }


    private void createSubscription() {
        System.out.println("\n--- Create Subscription ---");

        System.out,print("service name : ");
        String serviceName;
        serviceName = nextLine();

        System.out,print("monthly amount : ");
        double monthlyAmount = nextDouble();

        System.out,print("start date (dd/MM/yyyy): ");
        String startDate = nextLine();

        System.out,print("end date (dd/MM/yyyy): ");
        String endDate = nextLine();

        System.out.print("subscription status : ");
        String status = nextLine();
    
        System.out.println("------- commited --------: ");
        System.out.println("1. commitment required : ");
        System.out.println("2. no commitment required : ");
        System.out.print("=>");
        int cm = scanner.nextInt();

        SubscriptionService.createSubscription();
    }


    private void modifySubscription() {
        System.out.println("\n--- Modify Subscription ---");

        // TODO:
        // Get subscription ID
        // Call SubscriptionService.update()
    }


    private void deleteSubscription() {
        System.out.println("\n--- Delete Subscription ---");

        // TODO:
        // Get subscription ID
        // Call SubscriptionService.delete()
    }


    private void listSubscriptions() {
        System.out.println("\n--- All Subscriptions ---");

        // TODO:
        // Call SubscriptionService.findAll()
    }


    private void showSubscriptionPayments() {
        System.out.println("\n--- Subscription Payments ---");

        // TODO:
        // Ask for subscription ID
        // Call PaymentService
    }


    private void recordPayment() {
        System.out.println("\n--- Record Payment ---");

        // TODO:
        // Ask for payment information
        // Call PaymentService
    }


    private void modifyPayment() {
        System.out.println("\n--- Modify Payment ---");

        // TODO:
        // Get payment ID
        // Call PaymentService.update()
    }


    private void deletePayment() {
        System.out.println("\n--- Delete Payment ---");

        // TODO:
        // Get payment ID
        // Call PaymentService.delete()
    }


    private void showUnpaidPayments() {
        System.out.println("\n--- Unpaid Payments ---");

        // TODO:
        // Ask for subscription ID
        // Show unpaid payments
        // Show total unpaid amount
    }


    private void showTotalPaid() {
        System.out.println("\n--- Total Paid ---");

        // TODO:
        // Ask for subscription ID
        // Calculate total paid
    }


    private void showLastFivePayments() {
        System.out.println("\n--- Last 5 Payments ---");

        // TODO:
        // Call PaymentService
    }


    private void financialReports() {

        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println("          FINANCIAL REPORTS");
            System.out.println("======================================");

            System.out.println("1. Monthly report");
            System.out.println("2. Annual report");
            System.out.println("3. Unpaid payments report");
            System.out.println("0. Back");

            System.out.print("\nChoose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Monthly Report ---");
                    // TODO
                    break;

                case 2:
                    System.out.println("\n--- Annual Report ---");
                    // TODO
                    break;

                case 3:
                    System.out.println("\n--- Unpaid Payments Report ---");
                    // TODO
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}