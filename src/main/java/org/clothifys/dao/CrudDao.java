package org.clothifys.dao;

import org.clothifys.entity.Customer;

public interface CrudDao <T> extends SuperDao{
    boolean save(T dto);

    boolean delete(String id);
}
