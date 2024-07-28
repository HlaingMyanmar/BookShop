package sspd.bookshop.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IDGenerate {

    static  String  number_ =null;



    public static String getID(String startID) {


        int len = startID.length();
        int i = len - 1;


        while (i >= 0 && Character.isDigit(startID.charAt(i))) {
            i--;
        }

        String prefix = startID.substring(0, i + 1);
        String numPart = startID.substring(i + 1);

        int num = Integer.parseInt(numPart);
        num++;
        String newNumPart = String.format("%0" + numPart.length() + "d", num);


        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = now.format(formatter);

        number_ = newNumPart;

        return prefix + dateStr +"-"+ newNumPart;
    }








}
