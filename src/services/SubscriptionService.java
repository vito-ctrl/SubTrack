package services;

import model.Subscription;
import model.SubscriptionWithCommitment;
import model.SubscriptionWithoutCommitment;
import dao.SubscriptionDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SubscriptionService {

    private SubscriptionDAO subscriptionDAO;

    public SubscriptionService(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    public Subscription createSubscriptionWithCommitment(String serviceName, double monthlyAmount,
            LocalDate startDate, LocalDate endDate, Subscription.Status status, int commitmentDurationMonths) {
        Subscription subscription = new SubscriptionWithCommitment(
                serviceName, monthlyAmount, startDate, endDate, status, commitmentDurationMonths);
        return subscriptionDAO.create(subscription);
    }

    public Subscription createSubscriptionWithoutCommitment(String serviceName, double monthlyAmount,
            LocalDate startDate, LocalDate endDate, Subscription.Status status) {
        Subscription subscription = new SubscriptionWithoutCommitment(
                serviceName, monthlyAmount, startDate, endDate, status);
        return subscriptionDAO.create(subscription);
    }

    public Optional<Subscription> findById(String id) {
        return subscriptionDAO.findById(id);
    }

    public List<Subscription> findAll() {
        return subscriptionDAO.findAll();
    }

    public void update(Subscription subscription) {
        subscriptionDAO.update(subscription);
    }

    public boolean delete(String id) {
        return subscriptionDAO.delete(id);
    }

    public void terminate(String id) {
        subscriptionDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found: " + id))
                .setStatus(Subscription.Status.Terminated);
    }

    public void generateSchedule(String id){
        SubscriptionDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found: " + id))
                .   
    }

    public List<Payment> generateSchedule(String subscriptionId) {
        Subscription subscription = subscriptionDAO.findById(subscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found: " + subscriptionId));

        List<Payment> createdPayments = new ArrayList<>();
        LocalDate scheduleDate = subscription.getStartDate();

        while (!scheduleDate.isAfter(subscription.getEndDate())) {
            Payment payment = new Payment(subscriptionId, scheduleDate, null, "monthly");
            payment.setStatus(Payment.Status.Unpaid);
            paymentDAO.create(payment);
            createdPayments.add(payment);
            scheduleDate = scheduleDate.plusMonths(1);
        }

        return createdPayments;
    }    
}