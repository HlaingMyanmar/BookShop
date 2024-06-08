package sspd.bookshop.modules;

import javafx.collections.ObservableList;
import sspd.bookshop.DAO.DataAccessObject;
import sspd.bookshop.models.Book;

import javax.swing.text.TableView;
import java.util.List;


public interface GenerateResult {



    ObservableList<String>getDataList(Class<? extends DataAccessObject> dbClass);


    ObservableList<String> getCategoryNameList();

    String getCategoryCode(String item);

    String getCategoryName(String code);

    ObservableList<String> getAuthorNameList();

    String getAuthorCode(String item);

    String getAuthorName(String code);








}
