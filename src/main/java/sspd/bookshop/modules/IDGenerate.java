package sspd.bookshop.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IDGenerate {

    private static String lastDate = "";
    private static int _counter = 0;

    private static String stocklastDate = "";
    private static int stockcounter = 0;


    public static String getID(String prefix,String endID) {

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(formatter);


        if (!currentDate.equals(lastDate)) {
            lastDate = currentDate;
            _counter = 1;
        } else {

            _counter++;

            int count = Integer.parseInt(endID.substring(12));

            if(_counter< count){

                _counter = count;

                _counter++;

            }
        }

        String newNumPart =  String.format("%02d", _counter);


        return prefix +"-"+ currentDate + "-" + newNumPart;

    }

    public static String getStockIDGenerate(String prefix,String endID) {

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(formatter);


        if (!currentDate.equals(stocklastDate)) {
            stocklastDate = currentDate;
            stockcounter = 1;
        } else {

            stockcounter++;

            int count = Integer.parseInt(endID.substring(12));

            if(stockcounter< count){

                stockcounter = count;

                stockcounter++;

            }
        }

        String newNumPart =  String.format("%02d", stockcounter);


        return prefix +"-"+ currentDate + "-" + newNumPart;

    }










}
