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


        assertEquals("#Pu-20240801-2",IDGenerate.getID("#Pu","#Pu-20240801-1"));


    }





}
