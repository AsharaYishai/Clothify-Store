package org.clothifys.controller.order;

public class OrderController {

  private static OrderController instance;

  private OrderController(){}

  public static OrderController getInstance(){
      if (instance==null){
          return instance = new OrderController();
      }
      return  instance;
  }
}
