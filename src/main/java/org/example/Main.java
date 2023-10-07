package org.example;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args){
        String[] str = {"https://www.mtavalanche.com/forecast/bridgers",
                "https://www.mtavalanche.com/index.php/forecast/northern-gallatin",
                "https://www.mtavalanche.com/index.php/forecast/northern-madison",
                "https://www.mtavalanche.com/forecast/southern-madison",
                "https://www.mtavalanche.com/forecast/southern-gallatin",
                "https://www.mtavalanche.com/forecast/cooke-city"};
                Document x = makeConnection(str[0]);
                parseData(x);
    }
    private static Document makeConnection(String URL){
        try{
            return Jsoup.connect(URL).get();
        }catch(IOException e){
            throw new RuntimeException();
        }

    }

    private static List<String> parseData(Document doc) {
        // return List object
        List<String> valuesToReturn = new ArrayList<>();
        //prepare sets of data

        Elements classDetails = doc.getElementsByClass("details");
        Elements classHazard = doc.getElementsByClass("hazard-rating-container");

        String data = classDetails.get(0).toString();
        String harzardData = classHazard.get(0).toString();


        Pattern snowAndTempPattern = Pattern.compile("\\d+");
        Pattern windDirectionPattern = Pattern.compile("/NW|NE|SE|SW|E|W|N|S/gm");
        Pattern hazardRatingPattern = Pattern.compile("High|Considerable|Moderate|Low|None|No Rating");



        Matcher snowAndTempMatcher = snowAndTempPattern.matcher(data);
        Matcher windDirectionMatcher = windDirectionPattern.matcher(data);
        Matcher hazardRatingMatcher = hazardRatingPattern.matcher(harzardData);

        while (snowAndTempMatcher.find()) {
            valuesToReturn.add(snowAndTempMatcher.group());

        }
        if(windDirectionMatcher.find()) {
            valuesToReturn.add(windDirectionMatcher.group());
        }
        if(hazardRatingMatcher.find()){
            valuesToReturn.add(hazardRatingMatcher.group());
        }

        System.out.println(valuesToReturn);




        return valuesToReturn;
    }


}

