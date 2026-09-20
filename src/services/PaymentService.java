package services;

import model.Payment;
import model.Subscription;
import model.SubscriptionWithCommitment;
import dao.PaymentDAO;
import dao.SubscriptionDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentService {

    private PaymentDAO paymentDAO;
    private SubscriptionDAO subscriptionDAO;

    public PaymentService(PaymentDAO paymentDAO, SubscriptionDAO subscriptionDAO) {
        this.paymentDAO = paymentDAO;
        this.subscriptionDAO = subscriptionDAO;
    }

    public Payment recordPayment(String subscriptionId, LocalDate dueDate, LocalDate paymentDate, String paymentType) {
        Payment payment = new Payment(subscriptionId, dueDate, paymentDate, paymentType);
        payment.setStatus(paymentDate != null ? Payment.Status.Paid : Payment.Status.Unpaid);
        return paymentDAO.create(payment);
    }

    public Optional<Payment> findById(String id) {
        return paymentDAO.findById(id);
    }

    public List<Payment> findAll() {
        return paymentDAO.findAll();
    }

    public void update(Payment payment) {
        paymentDAO.update(payment);
    }

    public boolean delete(String id) {
        return paymentDAO.delete(id);
    }

    public List<Payment> findBySubscription(String subscriptionId) {
        return paymentDAO.findByAbonnement(subscriptionId);
    }

    public List<Payment> findLastPayments(int n) {
        return paymentDAO.findLastPayments(n);
    }

    public void detectOverduePayments() {
        LocalDate today = LocalDate.now();
        paymentDAO.findAll().stream()
                .filter(p -> p.getStatus() == Payment.Status.Unpaid)
                .filter(p -> p.getDueDate().isBefore(today))
                .forEach(p -> p.setStatus(Payment.Status.Overdue));
    }

    public double getTotalPaid(String subscriptionId) {
        return paymentDAO.findByAbonnement(subscriptionId).stream()
                .filter(p -> p.getStatus() == Payment.Status.Paid)
                .mapToDouble(p -> subscriptionDAO.findById(subscriptionId)
                        .map(Subscription::getMonthlyAmount)
                        .orElse(0.0))
                .sum();
    }

    public double getTotalUnpaidForCommitment(String subscriptionId) {
        Subscription subscription = subscriptionDAO.findById(subscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found: " + subscriptionId));

        if (!(subscription instanceof SubscriptionWithCommitment)) {
            return 0.0;
        }

        long unpaidCount = paymentDAO.findUnpaidByAbonnement(subscriptionId).size();
        return unpaidCount * subscription.getMonthlyAmount();
    }

    public Map<String, Double> monthlyReport() {
        return paymentDAO.findAll().stream()
                .collect(Collectors.groupingBy(
                        p -> p.getDueDate().getYear() + "-" + p.getDueDate().getMonthValue(),
                        Collectors.summingDouble(p -> subscriptionDAO.findById(p.getSubscriptionId())
                                .map(Subscription::getMonthlyAmount)
                                .orElse(0.0))
                ));
    }

    public Map<Integer, Double> annualReport() {
        return paymentDAO.findAll().stream()
                .collect(Collectors.groupingBy(
                        p -> p.getDueDate().getYear(),
                        Collectors.summingDouble(p -> subscriptionDAO.findById(p.getSubscriptionId())
                                .map(Subscription::getMonthlyAmount)
                                .orElse(0.0))
                ));
    }

    public Map<String, Long> unpaidReport() {
        return paymentDAO.findAll().stream()
                .filter(p -> p.getStatus() != Payment.Status.Paid)
                .collect(Collectors.groupingBy(Payment::getSubscriptionId, Collectors.counting()));
    }
}