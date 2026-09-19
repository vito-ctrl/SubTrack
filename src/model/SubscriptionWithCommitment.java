package model;

public class SubscriptionWithCommitment extends Subscription {

    private int commitmentDurationMonths;

    public SubscriptionWithCommitment(String serviceName, double monthlyAmount, String startDate,String endDate, Status status, int commitmentDurationMonths) {
        super(serviceName, monthlyAmount, startDate, endDate, status);
        this.commitmentDurationMonths = commitmentDurationMonths;
    }

    public int getCommitmentDurationMonths() {
        return commitmentDurationMonths;
    }

    public void setCommitmentDurationMonths(int commitmentDurationMonths) {
        this.commitmentDurationMonths = commitmentDurationMonths;
    }
}