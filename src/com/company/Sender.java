package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sender {
    public void sent(){
        try {
            ArrayList<Cat> cats = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                cats.add(new Cat());
            }
            ServerSocket serverSocket = new ServerSocket(27015);
            System.out.println("Отправитель поднялся.");
            while (true) {
                Socket inputConnection = serverSocket.accept();
                System.out.println(inputConnection);
                System.out.println("Произошло подключение получателя.");
                while (true){
                    System.out.println("Что выберете?");
                    System.out.println("1. Отправить 1 кота" +
                            "\t2. Отправить всю кошачью семью" +
                            "\t3. Выйти");

                    Scanner scanner = new Scanner(System.in);
                    int choose = scanner.nextInt();
                    if (choose ==1)sendOneCat(inputConnection,cats);
                    else if(choose ==2) sendAllCat(inputConnection,cats);
                    else if(choose ==3) {
                        inputConnection.close();
                        break;
                    }
                }
                break;
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void sendOneCat(Socket inputConnection, ArrayList<Cat> cats){
        try {

            System.out.println("Есть такие коты:");
            for (Cat cat : cats) {
                System.out.print(cat.getName()+", ");
            }
            System.out.println();
            System.out.println("Какого кота отправить? Введите имя");
            Scanner in = new Scanner(System.in);
            String kit = in.nextLine();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(inputConnection.getOutputStream()));
            List<Cat> caty = cats.stream().filter(cat -> cat.getName().equals(kit)).findAny().stream().toList();
            bufferedWriter.write(caty.get(0).toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendAllCat(Socket inputConnection, ArrayList<Cat> cats){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(inputConnection.getOutputStream()));
            for (Cat cat:cats) {
                bufferedWriter.write(cat.toString());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
