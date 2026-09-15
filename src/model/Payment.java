import java.util.UUID;

public class payment {
    private String paymentId;
    private String SubscriptionId;
    private Date dueDate;
    private String paymentType;
    private enum {
        "Paid", "Unpaid", "Overdue"
    };
}