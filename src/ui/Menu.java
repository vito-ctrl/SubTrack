package ui;

import model.Subscription;
import model.Payment;
import services.SubscriptionService;
import services.PaymentService;
import dao.SubscriptionDAO;
import dao.PaymentDAO;
import util.ValidateInput;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private SubscriptionService subscriptionService;
    private PaymentService paymentService;

    public Menu() {
        scanner = new Scanner(System.in);
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        subscriptionService = new SubscriptionService(subscriptionDAO, paymentDAO);
        paymentService = new PaymentService(paymentDAO, subscriptionDAO);
    }

    public void start() {
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1: createSubscription(); break;
                    case 2: modifySubscription(); break;
                    case 3: deleteSubscription(); break;
                    case 4: listSubscriptions(); break;
                    case 5: showSubscriptionPayments(); break;
                    case 6: recordPayment(); break;
                    case 7: modifyPayment(); break;
                    case 8: deletePayment(); break;
                    case 9: showUnpaidPayments(); break;
                    case 10: showTotalPaid(); break;
                    case 11: showLastFivePayments(); break;
                    case 12: financialReports(); break;
                    case 0: System.out.println("\nGoodbye!"); break;
                    default: System.out.println("\nInvalid choice!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
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

        System.out.print("service name : ");
        String serviceName = scanner.nextLine();

        double monthlyAmount = ValidateInput.readDouble(scanner, "monthly amount : ");
        LocalDate startDate = ValidateInput.readDate(scanner, "start date (dd/MM/yyyy): ");
        LocalDate endDate = ValidateInput.readDate(scanner, "end date (dd/MM/yyyy): ");
        Subscription.Status status = ValidateInput.readEnum(scanner, "status (Active, Suspended, Terminated): ", Subscription.Status.class);

        System.out.println("1. commitment required");
        System.out.println("2. no commitment required");
        int cm = ValidateInput.readInt(scanner, "=> ");

        Subscription created;
        if (cm == 1) {
            int commitmentDurationMonths = ValidateInput.readInt(scanner, "commitment duration (months) : ");
            created = subscriptionService.createSubscriptionWithCommitment(
                    serviceName, monthlyAmount, startDate, endDate, status, commitmentDurationMonths);
        } else {
            created = subscriptionService.createSubscriptionWithoutCommitment(
                    serviceName, monthlyAmount, startDate, endDate, status);
        }

        System.out.println("Created subscription with id: " + created.getId());

        System.out.print("Generate payment schedule now? (y/n) : ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            List<Payment> schedule = subscriptionService.generateSchedule(created.getId());
            System.out.println("Generated " + schedule.size() + " payments.");
        }
    }

    private void modifySubscription() {
        System.out.println("\n--- Modify Subscription ---");
        System.out.print("subscription id : ");
        String id = scanner.nextLine();

        Optional<Subscription> found = subscriptionService.findById(id);
        if (!found.isPresent()) {
            System.out.println("Subscription not found.");
            return;
        }

        Subscription subscription = found.get();
        System.out.print("new service name (" + subscription.getServiceName() + ") : ");
        String newName = scanner.nextLine();
        if (!newName.isBlank()) {
            subscription.setServiceName(newName);
        }

        double newAmount = ValidateInput.readDouble(scanner, "new monthly amount : ");
        subscription.setMonthlyAmount(newAmount);

        subscriptionService.update(subscription);
        System.out.println("Subscription updated.");
    }

    private void deleteSubscription() {
        System.out.println("\n--- Delete Subscription ---");
        System.out.print("subscription id : ");
        String id = scanner.nextLine();
        boolean deleted = subscriptionService.delete(id);
        System.out.println(deleted ? "Deleted." : "Subscription not found.");
    }

    private void listSubscriptions() {
        System.out.println("\n--- All Subscriptions ---");
        List<Subscription> all = subscriptionService.findAll();
        if (all.isEmpty()) {
            System.out.println("No subscriptions yet.");
        }
        for (Subscription s : all) {
            System.out.println(s.getId() + " | " + s.getServiceName() + " | " + s.getMonthlyAmount()
                    + " | " + s.getStatus());
        }
    }

    private void showSubscriptionPayments() {
        System.out.println("\n--- Subscription Payments ---");
        System.out.print("subscription id : ");
        String id = scanner.nextLine();
        List<Payment> payments = paymentService.findBySubscription(id);
        if (payments.isEmpty()) {
            System.out.println("No payments for this subscription.");
        }
        for (Payment p : payments) {
            System.out.println(p.getPaymentId() + " | due: " + p.getDueDate() + " | status: " + p.getStatus());
        }
    }

    private void recordPayment() {
        System.out.println("\n--- Record Payment ---");
        System.out.print("subscription id : ");
        String subscriptionId = scanner.nextLine();
        LocalDate dueDate = ValidateInput.readDate(scanner, "due date (dd/MM/yyyy) : ");

        System.out.print("mark as paid now? (y/n) : ");
        boolean paidNow = scanner.nextLine().equalsIgnoreCase("y");
        LocalDate paymentDate = paidNow ? LocalDate.now() : null;

        System.out.print("payment type : ");
        String paymentType = scanner.nextLine();

        Payment payment = paymentService.recordPayment(subscriptionId, dueDate, paymentDate, paymentType);
        System.out.println("Recorded payment with id: " + payment.getPaymentId());
    }

    private void modifyPayment() {
        System.out.println("\n--- Modify Payment ---");
        System.out.print("payment id : ");
        String id = scanner.nextLine();

        Optional<Payment> found = paymentService.findById(id);
        if (!found.isPresent()) {
            System.out.println("Payment not found.");
            return;
        }

        Payment payment = found.get();
        System.out.print("mark as paid now? (y/n) : ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            payment.setPaymentDate(LocalDate.now());
            payment.setStatus(Payment.Status.Paid);
        }

        paymentService.update(payment);
        System.out.println("Payment updated.");
    }

    private void deletePayment() {
        System.out.println("\n--- Delete Payment ---");
        System.out.print("payment id : ");
        String id = scanner.nextLine();
        boolean deleted = paymentService.delete(id);
        System.out.println(deleted ? "Deleted." : "Payment not found.");
    }

    private void showUnpaidPayments() {
        System.out.println("\n--- Unpaid Payments ---");
        System.out.print("subscription id : ");
        String id = scanner.nextLine();

        paymentService.detectOverduePayments();
        List<Payment> unpaid = paymentService.findBySubscription(id).stream()
                .filter(p -> p.getStatus() != Payment.Status.Paid)
                .toList();

        for (Payment p : unpaid) {
            System.out.println(p.getPaymentId() + " | due: " + p.getDueDate() + " | status: " + p.getStatus());
        }

        double totalUnpaid = paymentService.getTotalUnpaidForCommitment(id);
        System.out.println("Total unpaid (commitment): " + totalUnpaid);
    }

    private void showTotalPaid() {
        System.out.println("\n--- Total Paid ---");
        System.out.print("subscription id : ");
        String id = scanner.nextLine();
        System.out.println("Total paid: " + paymentService.getTotalPaid(id));
    }

    private void showLastFivePayments() {
        System.out.println("\n--- Last 5 Payments ---");
        List<Payment> last5 = paymentService.findLastPayments(5);
        for (Payment p : last5) {
            System.out.println(p.getPaymentId() + " | due: " + p.getDueDate() + " | status: " + p.getStatus());
        }
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
                    Map<String, Double> monthly = paymentService.monthlyReport();
                    monthly.forEach((month, total) -> System.out.println(month + " : " + total));
                    break;
                case 2:
                    Map<Integer, Double> annual = paymentService.annualReport();
                    annual.forEach((year, total) -> System.out.println(year + " : " + total));
                    break;
                case 3:
                    Map<String, Long> unpaid = paymentService.unpaidReport();
                    unpaid.forEach((subId, count) -> System.out.println(subId + " : " + count + " unpaid"));
                    break;
                case 0: break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}