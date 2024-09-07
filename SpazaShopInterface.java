
package za.tut.ac.bl;

import java.util.List;

public interface SpazaShopInterface <T>
{
    T get(Integer id);
    boolean update(T t, double price);
    boolean update(T t, Integer quantity);
    boolean delete(T t);
    boolean add(T t);
    List<T> getAllItems();
}
