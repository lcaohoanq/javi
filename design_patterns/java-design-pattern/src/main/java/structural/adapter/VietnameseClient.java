package structural.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VietnameseClient {

    public static void main(String[] args) {
        VietnameseTarget client = new TranslatorAdapter(new JapaneseAdaptee());
        client.send("Xin chào");

        // BufferedReader
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your name: ");
            String s = br.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
