package edu.icet.senuka.FXHotelManager.repository;

import java.util.List;

public interface CrudRepository<T, I> extends SuperDao{
    public boolean save(T entity);
    public boolean update(T entity);
    public boolean delete(T entity);
    public List<T> getAll();
}
