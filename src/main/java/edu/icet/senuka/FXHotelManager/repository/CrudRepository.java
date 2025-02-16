package edu.icet.senuka.FXHotelManager.repository;

import java.util.List;

public interface CrudRepository<T, I> extends SuperDao{
    public boolean save(T entity);
    public boolean update(I id, T entity);
    public boolean delete(I id);
    public List<T> getAll();
}
