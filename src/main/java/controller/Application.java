package controller;

import entity.Customer;
import entity.Storage;

import java.util.concurrent.CyclicBarrier;

public class Application {

    public static void main(String[] args) {
        Storage storage = new Storage(1000);

        try {
            int customersCounter = Integer.parseInt(args[0]);
            CyclicBarrier cyclicBarrier = new CyclicBarrier(customersCounter, storage);
            for (int i = 0; i < customersCounter; i++) {
                Customer customer = new Customer("Customer " + i, storage, cyclicBarrier);
                storage.customerList.add(customer);
            }
            storage.startBuying();
        } catch (Exception e) {
            System.out.println("Invalid CLI argument: needed digit");
        }
    }
}
