package Ex2_A;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex2_1 {
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] file_array = new String[n];
        Random rand = new Random(seed);
        for (int i = 1; i <= n; i++) {
            try {
                File file = new File(" file_" + i + ".txt");
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                FileWriter filewriter = new FileWriter(" file_" + i + ".txt");
                int x = rand.nextInt(bound);
                for(int j=0;j<x;j++){
                    filewriter.write("potato potatos \n");
                }
                filewriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            file_array[i-1] = (" file_" + i + ".txt");
        }
        return file_array;
    }
    public static int getNumOfLines(String[] fileNames){
        long start_time = System.nanoTime();
        int total_lines=0;
        for(int i=0;i< fileNames.length;i++){
            int count_line = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]))) {
                while (reader.readLine() != null) count_line++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            total_lines=total_lines+count_line;
        }
        long stop_time = System.nanoTime();
        System.out.println("getNumOfLines time is: "+((stop_time-start_time)/1000000)+"."+
                ((stop_time-start_time)%1000000)+" Millisecond");
        return total_lines;
    }
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        long start_time = System.nanoTime();
        int total_lines=0;
        for(int i=0;i<fileNames.length;i++){
            MyThread thread2 = new MyThread(fileNames[i]);
            thread2.start();
            thread2.join();
            total_lines=total_lines+thread2.getlines();
        }
        long stop_time = System.nanoTime();
        System.out.println("getNumOfLinesThreads time is: "+((stop_time-start_time)/1000000)+"."+
                ((stop_time-start_time)%1000000)+" Millisecond");
        return total_lines;
    }
    public static int getNumOfLinesThreadPool(String[] fileNames){
        long start_time = System.nanoTime();
        int total_lines=0;
        //creat the pool
        ExecutorService service = Executors.newFixedThreadPool(fileNames.length);
        List<Future> allFutures = new ArrayList<>();
        for(int i=0;i<fileNames.length;i++){
            Future<Integer> future = service.submit(new ThreadPool(fileNames[i]));
            allFutures.add(future);
        }
        for(int i=0;i<fileNames.length;i++){
            Future<Integer> future = allFutures.get(i);
            try{
                total_lines=total_lines+future.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long stop_time = System.nanoTime();
        System.out.println("getNumOfLinesThreadPool time is: "+((stop_time-start_time)/1000000)+"."+
                ((stop_time-start_time)%1000000)+" Millisecond");
        return total_lines;
    }
}



