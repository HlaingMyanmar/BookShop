package sspd.bookshop.modules;

import javafx.collections.ObservableList;
import sspd.bookshop.DAO.DataAccessObject;



public interface GenerateResult {



    ObservableList<String>getDataList(Class<? extends DataAccessObject> dbClass);


    ObservableList<String> getCategoryNameList();

    String getCategoryCode(String item);

    ObservableList<String> getAuthorNameList();

    String getAuthorCode(String item);




}
