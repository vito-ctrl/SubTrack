import java.util.UUID;

public abstract class Subscription {
    private String id;
    private String serviceName;
    private Double monthlyAmount;
    private Date startDate;
    private Date endDate;
    private enum Status {
        "Active", "Suspended", "Terminated" 
    };
    
}