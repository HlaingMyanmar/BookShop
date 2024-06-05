package sspd.bookshop.DAO;

import java.util.List;

public interface DataAccessObject<V> {

    List<V> getList();

    void update(V v);
    void create(V v);
    void delete(V v);


}
