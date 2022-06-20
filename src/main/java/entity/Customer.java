package entity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer extends Thread {

    private int id;

    private int countOfBoughtItems = 0;

    private int countOfPurchases = 0;

    private CyclicBarrier cyclicBarrier;

    private final int RANDOM_COEFFICIENT = 10;

    @Override
    public void run() {
        while (!Storage.isEmpty()) {
            try {
                cyclicBarrier.await();
                int itemsBought = new Storage().buy((int) (Math.random() * RANDOM_COEFFICIENT + 1));
                if (itemsBought != 0) {
                    countOfBoughtItems += itemsBought;
                    countOfPurchases++;
                }
                cyclicBarrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                System.out.println("Something went wrong");
                ;
            }
        }
        printResult();
    }

    public void printResult() {
        System.out.println("Customer with ID: " + id + " bought products: " + countOfBoughtItems +
                "\nCount of purchases: " + countOfPurchases);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }
}
