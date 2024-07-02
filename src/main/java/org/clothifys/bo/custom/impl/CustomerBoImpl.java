package org.clothifys.bo.custom.impl;

import org.clothifys.bo.custom.CustomerBo;
import org.clothifys.entity.Customer;

public class CustomerBoImpl implements CustomerBo {
    @Override
    public boolean saveCustomer(Customer dto) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
