package training.supportbank;
import java.math.BigDecimal;
import java.util.*;
import java.util.ArrayList;


public class Person {
    private String name;
    private ArrayList<Transaction> transactionsOut = new ArrayList<>();
    private ArrayList<Transaction> transactionsIn = new ArrayList<>();


    public Person(String name){
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public void addTransactionOut(Transaction transaction){
        transactionsOut.add(transaction);
    }

    public void addTransactionIn(Transaction transaction){
        transactionsIn.add(transaction);
    }


    public BigDecimal getBalance() {
        BigDecimal balance = transactionsIn.stream().map(money -> money.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        balance = balance.subtract(transactionsOut.stream().map(money -> money.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));

        return balance;
    }

    public List<Transaction> getAllTransactions(){
        List<Transaction> allTransations = new ArrayList<>();
        allTransations.addAll(transactionsOut);
        allTransations.addAll(transactionsIn);

        return allTransations;
    }

    public List<String> AllTransactionsAsString() {

        List<Transaction> allTransations = getAllTransactions();
        List<String> allTransationsAsString = new ArrayList<>();

        for (int i = 0; i <= allTransations.size() - 1; i++){
            allTransationsAsString.add(allTransations.get(i).TransactionAsString());
        }

        return allTransationsAsString;
    }




}
