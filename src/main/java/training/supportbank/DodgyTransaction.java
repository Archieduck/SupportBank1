package training.supportbank;

public class DodgyTransaction {

    private String date;
    private String owed;
    private String owes;
    private String reason;
    private String amount;

    public DodgyTransaction(String date, String owed, String owes, String reason, String amount) {

        this.amount = amount;
        this.date = date;
        this.owed = owed;
        this.owes = owes;
        this.reason = reason;

    }
}


