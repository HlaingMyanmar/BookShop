package sspd.bookshop.DAO;

import java.util.List;

public interface DataAccessObject<V> {

    List<V> getList();

    int update(V v);
    int create(V v);
    int delete(V v);


}
