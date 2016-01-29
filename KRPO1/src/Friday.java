import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Toshiba on 21.01.2016.
 */
public class Friday {
    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        Scanner sc = new Scanner(System.in);
        String year = "";
        Character s1;
        int yeaR = 0, a = 0,l = 0;

        do {
            a = 1;
            System.out.println("enter year: ");
            year = sc.next();
            for(int i = 0; i < year.length(); i ++) {
                s1 = year.charAt(i);
                if(!Character.isDigit(s1)) {
                      a = 0;
                }
            }
            if(a == 1) {//все цифры
                l = Integer.parseInt(year);
                if (year.length() != 4 || l < 1800 || l > 2015) {
                    a = 0;
                }
            }
        } while(a == 0);

        yeaR = l;
        String n2 = new String("Friday");
        String n1;
        for(int i = 0; i < 12; i ++) {
            calendar.clear();
            calendar.set(yeaR, i, 13);
            n1 = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
            if (n1.equals(n2)) {
                System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
            }
        }
    }
}
