package working;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyThread extends Thread{
    String file_name;
    int lines = 0;
    public MyThread(String file_name){
       this.file_name = file_name;
    }
    @Override
    public void run(){
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getlines() {
        return lines;
    }
}
