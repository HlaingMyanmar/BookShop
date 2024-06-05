package sspd.bookshop.DAO;

import java.util.List;

public interface DataAccessObject<V> {

    List<V> getList();

    void update(V object);
    void create(V object);


}
