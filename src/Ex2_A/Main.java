package Ex2_A;

import static Ex2_A.Ex2_1.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] arr;
        arr=createTextFiles(5, 5,1000);
        for (int i = 0; i < 5; i++) {
            System.out.println(arr[i]);
        }
        int x = getNumOfLines(arr);
        System.out.println(x);
        int y = getNumOfLinesThreads(arr);
        System.out.println(y);
        int z = getNumOfLinesThreadPool(arr);
        System.out.println(z);
        System.exit(0);
    }
}