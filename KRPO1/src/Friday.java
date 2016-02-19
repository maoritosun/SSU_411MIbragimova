import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Toshiba on 21.01.2016.
 */

public class Friday {

    public static void findYear() {
        Calendar calendar = Calendar.getInstance();
        Scanner sc = new Scanner(System.in);
        String yearString = "";
        Character one_symbol;
        int year = 0, marker = 0, yearInteger = 0;

        do {
            marker = 1;
            System.out.println("enter year: ");
            yearString = sc.next();
            for(int i = 0; i < yearString.length(); i ++) {
                one_symbol = yearString.charAt(i);
                if(!Character.isDigit(one_symbol)) {
                    marker = 0;
                }
            }
            if(marker == 1) {//все цифры
                yearInteger = Integer.parseInt(yearString);
                if (yearString.length() != 4 || yearInteger < 1800 || yearInteger > 2015) {
                    marker = 0;
                }
            }
        } while(marker == 0);

        year = yearInteger;
        String friday = new String("Friday");
        String dayOfWeek;
        for(int i = 0; i < 12; i ++) {
            calendar.clear();
            calendar.set(year, i, 13);
            dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
            if (dayOfWeek.equals(friday)) {
                System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
            }
        }
    }

    public static void findMonth() {
        Calendar calendar = Calendar.getInstance();
        Scanner sc = new Scanner(System.in);
        int month, count = 0, marker = 0;
        String monthString;
        String[] alphabet = {"0","1","2","3","4","5","6","7","8","9","10","11","12"};
        do{
            marker = 0;
            System.out.println("Input month: ");
            monthString = sc.next();
            for(int i = 0; i < alphabet.length; i ++) {
                if(monthString.equals(alphabet[i])) {
                    marker = 1;
                }
            }
        } while(marker == 0);

        month = Integer.valueOf(monthString) - 1;
        String dayOfWeek, friday = "Friday";
        for(int i = 1800; i < 2017; i ++) {
            calendar.clear();
            calendar.set(i, month, 13);
            dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
            if (dayOfWeek.equals(friday)) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println("count: " + count);
    }

    public static void main(String[] args) {
        findYear();
        System.out.println("");
        findMonth();
    }

}
