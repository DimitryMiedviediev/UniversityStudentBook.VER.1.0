package model;

/**
 * Created by Dimitry on 18.02.17.
 */
public class test {
    public static void main(String[] args) {
        Beans beans = new Beans();

        String ddmmyyyy = "01/01/1995";

        String dateNum = beans.dateFormatNum(ddmmyyyy);
        System.out.println(dateNum);

        String lnText = beans.dateFormatLongText(ddmmyyyy);
        System.out.println(lnText);
    }
}
