package sspd.bookshop.Test;


import org.junit.Test;
import sspd.bookshop.controllers.NewPurchaseController;
import sspd.bookshop.databases.Bookdb;
import sspd.bookshop.models.Book;
import sspd.bookshop.modules.IDGenerate;

import static org.junit.Assert.assertEquals;


public class Testing {

    @Test
    public void test(){


 //       assertEquals("#P-20240730-01",IDGenerate.getID("#P-",""));
//        assertEquals("#P-20240730-02",IDGenerate.getID("#P-","#P-20240730-01"));
//        assertEquals("#P-20240730-03",IDGenerate.getID("#P-","#P-20240730-02"));
//        assertEquals("#P-20240730-04",IDGenerate.getID("#P-","#P-20240730-03"));
//        assertEquals("#P-20240730-05",IDGenerate.getID("#P-","#P-20240730-04"));

//        assertEquals("#P-20240731-01",IDGenerate.getID("#P-","#P-20240730-05"));
//        assertEquals("#P-20240731-02",IDGenerate.getID("#P-","#P-20240731-01"));
//        assertEquals("#P-20240731-03",IDGenerate.getID("#P-","#P-20240731-02"));
//        assertEquals("#P-20240731-04",IDGenerate.getID("#P-","#P-20240731-03"));
          //assertEquals("#Pu-20240801-01",IDGenerate.getID("#Pu",null));
        assertEquals("#Pu-20240801-02",IDGenerate.getID("#Pu","#Pu-20240801-01"));

      //  assertEquals("#St-20240731-01",IDGenerate.getID("#St",""));

       // assertEquals("#St-20240731-02",IDGenerate.getID("#St-","#St-20240731-01"));


    }





}
