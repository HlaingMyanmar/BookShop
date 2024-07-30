package sspd.bookshop.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IDGenerate {

    static  String  number_ =null;

    private static String lastDate = "";
    private static int counter = 0;


    public static String getID(String prefix,String endID) {

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(formatter);


        if (!currentDate.equals(lastDate)) {
            lastDate = currentDate;
            counter = 1;
        } else {

            counter++;




            int count = Integer.parseInt(endID.substring(12));

            if(counter< count){

                counter = count;

                counter++;

            }
        }

        String newNumPart =  String.format("%02d", counter);


        return prefix + currentDate + "-" + newNumPart;

    }










}
