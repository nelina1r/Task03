package entity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer extends Thread {

    private final String name;

    private final Storage storage;

    private int countOfBoughtItems = 0;

    private int countOfPurchases = 0;

    private CyclicBarrier cyclicBarrier;

    public Customer(String name, Storage storage, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.storage = storage;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            buy();
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public void buy() {
        int itemsCounter = (int) (Math.random() * 10 + 1);
        int boughtItemsCounter = storage.buy(itemsCounter);
        if (boughtItemsCounter > 0) {
            countOfBoughtItems += boughtItemsCounter;
            countOfPurchases++;
        }
    }

    public void printResult() {
        System.out.println(name + " bought products: " + countOfBoughtItems +
                           "\nCount of purchases: " + countOfPurchases);
    }
}
