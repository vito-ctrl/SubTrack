import java.util.UUID;
// import java.util.Date;

public class Payment {
    private String paymentId;
    private String SubscriptionId;
    // private Date dueDate;
    private String paymentType;
    private enum Status {
        Paid, Unpaid, Overdue
    };
}