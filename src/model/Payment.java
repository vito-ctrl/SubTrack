package model;

import java.util.UUID;
import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private String subscriptionId;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private String paymentType;
    
    private enum Status {
        Paid, Unpaid, Overdue
    };
    private Status status;

    public Payment(String subscriptionId, LocalDate dueDate, LocalDate paymentDate, String paymentType){
        this.paymentId = UUID.randomUUID().toString();
        this.subscriptionId = subscriptionId;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
    }

    public String getPaymentId() {return paymentId;}

    public String getSubscriptionId() {return subscriptionId;}

    public LocalDate getDueDate() {return dueDate;}

    public String getPaymentType() {return paymentType;}

    public Status getStatus() {return status;}

    public LocalDate getPaymentDate() {return paymentDate;}

    public void setSubscriptionId(String subscriptionId) {this.subscriptionId = subscriptionId;}

    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}

    public void setPaymentDate(LocalDate paymentDate) {this.paymentDate = paymentDate;}

    public void setPaymentType(String paymentType) {this.paymentType = paymentType;}

    public void setStatus(Status status) {this.status = status;}
}