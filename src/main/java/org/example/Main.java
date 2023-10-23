package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args){
        DateGetter dateGetter = new DateGetter();
        dateGetter.insertNewData();
        Anaylsis anal = new Anaylsis();
        System.out.println(anal.windLoaded("CookeCity"));
        WEB web = new WEB();
        web.WebsiteInit();
    }


}

