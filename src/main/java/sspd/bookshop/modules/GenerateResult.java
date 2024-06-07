package sspd.bookshop.modules;

import javafx.collections.ObservableList;

public interface GenerateResult {


    ObservableList<String> getCategoryNameList();

    String getCategoryCode(String item);

    ObservableList<String> getAuthorNameList();

    String getAuthorCode(String item);


}
