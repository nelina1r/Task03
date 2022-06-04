package entity;

import java.util.ArrayList;
import java.util.List;

public class Storage extends Thread {

    private int itemsOnboardCounter;

    public List<Customer> customerList;

    public Storage(int itemsOnboardCounter) {
        this.itemsOnboardCounter = itemsOnboardCounter;
        this.customerList = new ArrayList();
    }

    public void startBuying() {
        customerList.forEach(x -> new Thread(x).start());
    }

    public synchronized int buy(int requestToBuy) {
        int boughtItems;
        if (itemsOnboardCounter >= requestToBuy) {
            itemsOnboardCounter -= requestToBuy;
            boughtItems = requestToBuy;
        } else {
            boughtItems = itemsOnboardCounter;
            itemsOnboardCounter = 0;
        }
        return boughtItems;
    }

    @Override
    public void run() {
        if (isEmpty()) {
            customerList.forEach(Customer::printResult);
        } else {
            startBuying();
        }
    }

    private boolean isEmpty() {
        return itemsOnboardCounter == 0;
    }
}
