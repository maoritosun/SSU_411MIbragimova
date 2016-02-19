import javenue.csv.Csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //information
        File dataInformation = new File("information.csv");
        List<List<String>> informationList = new ArrayList<>();

        try {
            Csv.Reader reader = new Csv.Reader(new FileReader(dataInformation)).ignoreComments(true).preserveSpaces(true);

            List<String> b;
            int marker = 0;
            while ((b = reader.readLine()) != null) {
                if(marker != 0) {
                    informationList.add(b);
                }
                marker++;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
/*
        for (List<String> list : informationList) {
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + (j < list.size() - 1 ? ", " : ""));
            }

            System.out.println();
        }
*/
        //result
        File dataResult = new File("result.csv");
        List<List<String>> resultList = new ArrayList<>();
        resultList = informationList;

        //transaction
        File[] fList;
        ArrayList<File> dataTransaction = new ArrayList<>();
        File F = new File("C:\\Users\\Toshiba\\IdeaProjects\\KRPO4");
        fList = F.listFiles();

        System.out.println("Transaction files: ");
        for (int i = 0; i < fList.length; i++) {
            if (fList[i].isFile() && fList[i].getName().startsWith("transaction")) {
                System.out.println(fList[i].getName());
                dataTransaction.add(fList[i]);
            }
        }
        System.out.println("");

        for (int k = 0; k < dataTransaction.size(); k++) {
            List<List<String>> transactionList = new ArrayList<>();
            try {
                Csv.Reader reader = new Csv.Reader(new FileReader(dataTransaction.get(k))).ignoreComments(true).preserveSpaces(true);
                List<String> b;
                int marker = 0;
                while ((b = reader.readLine()) != null) {
                    if(marker != 0) {
                        transactionList.add(b);
                    }
                    marker++;
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < transactionList.size(); i++) {
                int marker1 = 0;
                int marker2 = 0;
                for (int j = 0; j < resultList.size(); j++) {
                    if (transactionList.get(i).get(0).equals(resultList.get(j).get(0)) && transactionList.get(i).get(1).equals(resultList.get(j).get(1))) {
                        marker1 = j;
                    }
                    if (transactionList.get(i).get(2).equals(resultList.get(j).get(0)) && transactionList.get(i).get(3).equals(resultList.get(j).get(1))) {
                        marker2 = j;
                    }
                }
                int sum1 = Integer.parseInt(resultList.get(marker1).get(2));
                int sum2 = Integer.parseInt(resultList.get(marker2).get(2));
                int sum = Integer.parseInt(transactionList.get(i).get(4));
                int result1 = sum1 - sum;
                int result2 = sum2 + sum;
                if (result1 >= 0) {
                    resultList.get(marker1).set(2, String.valueOf(result1));
                    resultList.get(marker2).set(2, String.valueOf(result2));
                } else {
                    System.out.println("Company " + resultList.get(marker1).get(0) + " has not enogh money in the account to transfer");
                }
            }

            /*
            for (List<String> list : transactionList) {
                for (int j = 0; j < list.size(); j++) {
                    System.out.print(list.get(j) + (j < list.size() - 1 ? ", " : ""));
                }
                System.out.println();
            }
            */
        }

        try {
            Csv.Writer writer = new Csv.Writer(new FileWriter(dataResult));

            writer.value("Компания");
            writer.value("Лицевой счет");
            writer.value("Бюджет");
            writer.newLine();
            for (List<String> list : resultList) {
                list.forEach(writer::value); // Эквивалентно for(String s : list) { writer.value(s); }
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}