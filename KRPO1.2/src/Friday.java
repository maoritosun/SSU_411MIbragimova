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
        int month, n = 0, a = 0;
        String montH;
        Character m;
        do{
            a = 1;
            System.out.println("Input month: ");
            montH = sc.next();
            m = montH.charAt(0);
            if(!Character.isDigit(m)) {
                a = 0;
            }
            if(montH.length() > 1) {
                a = 0;
            }
        } while(a == 0);

        month = Integer.valueOf(montH);
        String n1, n2 = "Friday";
        for(int i = 1800; i < 2017; i ++) {
            calendar.clear();
            calendar.set(i, month, 13);
            n1 = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
            if (n1.equals(n2)) {
                System.out.println(i);
                n++;
            }
        }
        System.out.println("count: " + n);
    }
}
