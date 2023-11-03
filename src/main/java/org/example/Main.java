package org.example;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Anaylsis anal = new Anaylsis();
        WEB web = new WEB();
        int choice = 1;
        //while(choice >= 1 && choice <= 3) {
            if (args.length == 0) {
                System.out.println("1. get new data \n 2. analysis \n 3. start web server \n 4. end \n");
                choice = input.nextInt();
            }
            switch (choice) {
                case (1):
                    Thread Bridger = new Thread(new BridgerRunnable());
                    Thread CookeCity = new Thread(new CookeCityRunnable());
                    Thread SoGal = new Thread(new SouthernGallatinRunnable());
                    Thread NoGal = new Thread(new NorthernGallatinsRunnable());
                    Thread NoMad = new Thread(new NorthernMadisonsRunnable());
                    Thread SoMad = new Thread(new SouthernMadisonRunnable());
                    Bridger.start();
                    CookeCity.start();
                    SoGal.start();
                    NoGal.start();
                    NoMad.start();
                    SoMad.start();


                    break;
                case (2):
                    System.out.println(anal.windLoaded("CookeCity"));
                    break;
                case (3):
                    web.WebsiteInit();
                    break;
                default:
                    break;


            }
            if(args.length != 0){
                choice = 100;
            }

        }






    }


//}

