package training.supportbank;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class Transaction {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
    private String owed;
    private String owes;
    private LocalDate date;
    private String reason;
    private BigDecimal amount;

    public Transaction(String date, String owes, String owed, String reason, String amount){

        this.date = LocalDate.parse(date, format);
        this.owes = owes;
        this.owed = owed;
        this.reason = reason;
        this.amount = new BigDecimal(amount);

    }

    public LocalDate getDate() { return this.date; }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getOwed() {
        return owed;
    }

    public String getOwes() {
        return owes;
    }

    public String getReason() {
        return reason;
    }

    public String TransactionAsString() {
        String transactionAsString = this.date + "  | " + this.owes + " owes " + this.owed + "   £" + this.amount + " for: " + this.reason + "\n";
        return transactionAsString;
    }
}
