package org.clothifys.bo;

import org.clothifys.bo.custom.impl.CustomerBoImpl;
import org.clothifys.util.BoType;


public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}

    public static BoFactory getInstance(){
        return instance!=null?instance:(instance=new BoFactory());
    }

    //Factory Design Pattern
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case CUSTOMER:return (T) new CustomerBoImpl();
        }
        return null;
    }
}
