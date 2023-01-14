package Ex2_A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ThreadPool implements Callable<Integer> {
    int lines = 0;
    String file_name;
    public ThreadPool(String file_name){
        this.file_name=file_name;
    }

    @Override
    public Integer call() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
