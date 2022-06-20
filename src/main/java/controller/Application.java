package controller;

import entity.Customer;

import java.util.concurrent.CyclicBarrier;

public class Application {

    public static void main(String[] args) {
        try {
            int customersCounter = Integer.parseInt(args[0]);
            CyclicBarrier cyclicBarrier = new CyclicBarrier(customersCounter);
            for (int i = 0; i < customersCounter; i++) {
                Customer customer = new Customer();
                customer.setCyclicBarrier(cyclicBarrier);
                customer.setId(i);
                customer.start();
            }
        } catch (Exception e) {
            System.out.println("Invalid CLI argument: needed digit");
        }
    }
}
