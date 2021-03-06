package entity;

public class Storage {

    private static int items = 1000;

    public static synchronized int buy(int requestToBuy) {
        if (requestToBuy > items) {
            requestToBuy = items;
            items = 0;
        } else {
            items -= requestToBuy;
        }
        return requestToBuy;
    }

    public static boolean isEmpty() {
        return items == 0;
    }
}
