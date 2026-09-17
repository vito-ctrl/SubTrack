package model;

public class SubscriptionWithoutCommitment extends Subscription {

    public SubscriptionWithoutCommitment(String serviceName, Double monthlyAmount, String startDate,
                                          String endDate, Status status) {
        super(serviceName, monthlyAmount, startDate, endDate, status);
    }
}