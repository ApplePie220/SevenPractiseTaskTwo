import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {
    public static void main(String[] args) {

        try {
            System.out.print("Введите айпи: ");
            Scanner scanner = new Scanner(System.in);
            String ip = scanner.nextLine();
            System.out.print("Введите порт: ");
            int port = scanner.nextInt();

            Socket receiverSocket = new Socket(ip,port);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(receiverSocket.getInputStream()));

            while (true){
                String receiveCat = bufferedReader.readLine();
                if(receiveCat == null){
                    System.out.println("Никакой информации не поступает. Продолжить?");
                    Scanner scanner1 = new Scanner(System.in);
                    String choose = scanner1.nextLine();
                    if(choose.equals("нет")){
                        bufferedReader.close();
                        break;
                    }
                }
                else{
                    System.out.println(receiveCat);
                    saveFile(receiveCat);
                }
            }
            receiverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            }
    }
    public static void saveFile(String poslanie){
        try (FileWriter writer = new FileWriter("cats.cat", true);){
            writer.write(poslanie+"\n");
            System.out.println("Данные по коту записаны и получены.");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
