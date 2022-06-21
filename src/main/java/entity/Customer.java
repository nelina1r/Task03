package entity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer extends Thread {

    private int id;

    private int countOfBoughtItems = 0;

    private int countOfPurchases = 0;

    private CyclicBarrier cyclicBarrier;

    private final int COUNT_OF_MAXIMUM_ITEMS_PER_BUY = 10;


    public Customer(int id, CyclicBarrier cyclicBarrier){
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        while (!Storage.isEmpty()) {
            try {
                cyclicBarrier.await();
                int itemsBought = Storage.buy((int) (Math.random() * COUNT_OF_MAXIMUM_ITEMS_PER_BUY + 1));
                if (itemsBought != 0) {
                    countOfBoughtItems += itemsBought;
                    countOfPurchases++;
                }
                cyclicBarrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                System.out.println("Something went wrong");
            }
        }
        printResult();
    }

    public void printResult() {
        System.out.println("Customer with ID: " + id + " bought products: " + countOfBoughtItems +
                "\nCount of purchases: " + countOfPurchases);
    }
}
