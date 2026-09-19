package dao;

import model.Payment;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentDAO {

    private Map<String, Payment> payments = new HashMap<>();

    public Payment create(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
        return payment;
    }

    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(payments.get(id));
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments.values());
    }

    public Payment update(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
        return payment;
    }

    public boolean delete(String id) {
        return payments.remove(id) != null;
    }

    public List<Payment> findByAbonnement(String subscriptionId) {
        return payments.values().stream()
                .filter(p -> p.getSubscriptionId().equals(subscriptionId))
                .collect(Collectors.toList());
    }

    public List<Payment> findUnpaidByAbonnement(String subscriptionId) {
        return payments.values().stream()
                .filter(p -> p.getSubscriptionId().equals(subscriptionId))
                .filter(p -> p.getStatus() != Payment.Status.Paid)
                .collect(Collectors.toList());
    }

    public List<Payment> findLastPayments(int n) {
        return payments.values().stream()
                .sorted(Comparator.comparing(Payment::getDueDate).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}