package org.clothifys.bo.custom;

import org.clothifys.bo.SuperBo;
import org.clothifys.entity.Customer;

public interface CustomerBo extends SuperBo {

    boolean saveCustomer(Customer dto);

    boolean deleteById(String id);
}
