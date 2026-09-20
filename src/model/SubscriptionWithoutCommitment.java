package model;

public class SubscriptionWithoutCommitment extends Subscription {

    public SubscriptionWithoutCommitment(String serviceName, Double monthlyAmount, LocalDate startDate, LocalDate endDate, Status status) {
        super(serviceName, monthlyAmount, startDate, endDate, status);
    }
}