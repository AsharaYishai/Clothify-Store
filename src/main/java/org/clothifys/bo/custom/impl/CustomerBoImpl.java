package org.clothifys.bo.custom.impl;

import org.clothifys.bo.custom.CustomerBo;
import org.clothifys.dao.DaoFactory;
import org.clothifys.dao.custom.CustomerDao;
import org.clothifys.entity.Customer;
import org.clothifys.util.DaoType;
import org.modelmapper.ModelMapper;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean saveCustomer(Customer dto) {
        return customerDao.save(new ModelMapper().map(dto,Customer.class));
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
