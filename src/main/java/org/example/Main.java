package org.example;

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
import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args){
        DB db = new DB();
        String[] str = {"https://www.mtavalanche.com/forecast/bridgers",
                "https://www.mtavalanche.com/forecast/northern-gallatin",
                "https://www.mtavalanche.com/forecast/northern-madison",
                "https://www.mtavalanche.com/forecast/southern-madison",
                "https://www.mtavalanche.com/forecast/southern-gallatin",
                "https://www.mtavalanche.com/forecast/cooke-city"};
        String[] tableNames = {"Bridger", "CookeCity", "NorthernMadisons", "NorthernGallitins", "SouthernMasisons", "SouthernGallatins"};
        int i = 0;
                for(String URL : str) {
                    List<String> aviData = parseData(makeConnection(URL));
                    System.out.println(aviData);
                    if (aviData.size() == 6) {
                        try {
                            try (Connection conn = db.connect()) {
                                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + tableNames[i++] + " (NEW_SNOW,TEMP,HIGH_WIND,LOW_WIND,WIND_DIRECTION,HAZARD_RATING,DATE) values (?,?,?,?,?,?,current_date)");
                                preparedStatement.setString(1, aviData.get(0));
                                preparedStatement.setString(2, aviData.get(3));
                                preparedStatement.setString(3, aviData.get(2));
                                preparedStatement.setString(4, aviData.get(1));
                                preparedStatement.setString(5, aviData.get(4));
                                preparedStatement.setString(6, aviData.get(5));
                                preparedStatement.executeUpdate();

                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }






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
        String tempData = classDetails.get(3).toString();
        String hazardData = classHazard.get(0).toString();

        Pattern snowAndWindPattern = Pattern.compile("\\d+");
        Pattern tempPattern = Pattern.compile("\\d+");
        Pattern windDirectionPattern = Pattern.compile("NW|NE|SE|SW|E|W|N|S");
        Pattern hazardRatingPattern = Pattern.compile("High|Considerable|Moderate|Low|None|No Rating");

        Matcher snowAndTempMatcher = snowAndWindPattern.matcher(data);
        Matcher tempMatcher = tempPattern.matcher(tempData);
        Matcher windDirectionMatcher = windDirectionPattern.matcher(data);
        Matcher hazardRatingMatcher = hazardRatingPattern.matcher(hazardData);

        finder(snowAndTempMatcher,valuesToReturn);
        finder(tempMatcher,valuesToReturn);
        finder(windDirectionMatcher,valuesToReturn);
        finder(hazardRatingMatcher,valuesToReturn);
        System.out.println(valuesToReturn);




        return valuesToReturn;
    }


    private static void finder(Matcher m, List<String> list){
        while (m.find()) {
            list.add(m.group());
        }

    }

}

