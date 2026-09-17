package model;

import java.util.UUID;
import java.time.LocalDate;

public abstract class Subscription {
    private String id;
    private String serviceName;
    private Double monthlyAmount;
    private String startDate;
    private String endDate;
    private enum Status {
        Active, Suspended, Terminated 
    };
    private Status status;

    public Subscription(String serviceName, Double monthlyAmount,String startDate, String endDate, Status status){
        this.id = UUID.randomUUID().toString();
        this.serviceName = serviceName;
        this.monthlyAmount = monthlyAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getId() {return id;}

    public String getServiceName() {return serviceName;}

    public Double getMonthlyAmount() {return monthlyAmount;}

    public String getStartDate() {return startDate;}

    public String getEndDate() {return endDate;}
    
    public Status getStatus() {return status;}
    
    public void setServiceName(String serviceName) {this.serviceName = serviceName;}

    public void setMonthlyAmount(Double monthlyAmount) {this.monthlyAmount = monthlyAmount;}

    public void setStartDate(String startDate) {this.startDate = startDate;}

    public void setEndDate(String endDate) {this.endDate = endDate;}

    public void setStatus(Status status) {this.status = status;}
}