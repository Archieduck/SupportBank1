package training.supportbank;
import java.math.BigDecimal;
import java.util.*;
import java.io.*;
import java.time.*;
import java.util.stream.Collectors;

import com.google.gson.*;
import com.google.gson.JsonParser;
import com.opencsv.*;

public class Main {

    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int pos = 0;
        CSVReader reader = new CSVReaderBuilder(new FileReader("/Users/aduck1/SupportBank/SupportBank-data/Transactions2014.csv")).build();
        String [] nextLine;
        ArrayList<Person> people = new ArrayList<>();

        reader.readNext();
        while ((nextLine = reader.readNext()) != null) {
            reader.readNext();

            List<String> listOfNames = people.stream()
                    .map(names -> names.getName())
                    .collect(Collectors.toList());

            Boolean nameExists = listOfNames.contains(nextLine[1]);

            if (nameExists) {
                pos = listOfNames.indexOf(nextLine[1]);
                Person myPerson = people.get(pos);
                myPerson.addTransactionOut(new Transaction(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4]));

            }else{
                people.add(new Person(nextLine[1]));
                pos = people.size() - 1;
                Person myPerson = people.get(pos);
                myPerson.addTransactionOut(new Transaction(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4]));
            }
        }


        reader = new CSVReaderBuilder(new FileReader("/Users/aduck1/SupportBank/SupportBank-data/Transactions2014.csv")).build();
        reader.readNext();
        while ((nextLine = reader.readNext()) != null) {

            reader.readNext();

            List<String> listOfNames = people.stream()
                    .map(names -> names.getName())
                    .collect(Collectors.toList());

            pos = listOfNames.indexOf(nextLine[2]);
            Person myPerson = people.get(pos);
            myPerson.addTransactionIn(new Transaction(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4]));


        }

//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
//                // Convert jsonElement to a LocalDate here...
//        );
//        Gson gson = gsonBuilder.create();
        File jFile = new File("/Users/aduck1/SupportBank/SupportBank-data/Transactions2013.json");
        //try
//        JsonElement fileElement = JSON.parseReader(new FileReader(jFile));
//        JsonArray jsonArray =

        //input to ask for what to do
        do {
            System.out.println("What function would you like to run?");
            String option = scanner.nextLine();
            String name = "";
            if (option.contains("List All")) {
                listAll(people);
            } else if (option.contains("exit")){
                break;
            } else if (option.contains("List ")){
                String[] opt = option.split(" ");
                if (opt.length == 3) {
                    name = opt[1] + " " + opt[2];
                }else{
                    name = opt[1];
                }
                listPerson(people, name);
            }
        } while (false);
    }

    public static void listAll(List<Person> people) {
        List<BigDecimal> listOfBalance = people.stream()
                .map(balance -> balance.getBalance())
                .collect(Collectors.toList());

        List<String> listOfNames = people.stream()
                .map(names -> names.getName())
                .collect(Collectors.toList());

        for (int i = 0; i <= listOfBalance.size() - 1; i++){
            System.out.println(listOfNames.get(i) + " : " + listOfBalance.get(i));
        }
    }

    public static void listPerson(List<Person> people, String name){


                System.out.println(name);
                people.stream()
                        .filter(all -> Objects.equals(all.getName(), name))
                        .map(allTrans -> allTrans.AllTransactionsAsString())
                        //.map(allTrans -> allTrans.replaceAll("^[|]|,", ""))
                        .forEach(System.out::println);

            }






}
