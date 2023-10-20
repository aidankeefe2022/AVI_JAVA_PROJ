package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Anaylsis {

    public StringBuilder windLoaded(String tableName) {
        DB db = new DB();
        StringBuilder retString = new StringBuilder();
        int numberOfResults = 0;
        List<Integer> snow = new ArrayList<>();
        List<String> windDirection = new ArrayList<>();
        List<Integer> highWind = new ArrayList<>();
        List<String> dateArrayList = new ArrayList<>();
        int newSnowSum =0;
        try {
            try (Connection conn = db.connect();
                 PreparedStatement ps = conn.prepareStatement("SELECT NEW_SNOW, WIND_DIRECTION, HIGH_WIND, DATE FROM " +tableName+" WHERE DATE > date('now','-7 day')");
                 PreparedStatement ps2 = conn.prepareStatement("SELECT SUM(NEW_SNOW) AS NSS FROM "+tableName+" WHERE DATE > date('now','-7 day')")) {
                ResultSet rs = ps.executeQuery();
                ResultSet rs1 = ps2.executeQuery();
                while (rs.next()) {
                    snow.add(rs.getInt("NEW_SNOW"));
                    windDirection.add(rs.getString("WIND_DIRECTION"));
                    highWind.add(rs.getInt("HIGH_WIND"));
                    dateArrayList.add(rs.getString("DATE"));
                    numberOfResults++;
                }
                newSnowSum = rs1.getInt("NSS");
            }
        } catch (SQLException e) {
            return new StringBuilder();
        }
        int i = 0;
        while(i <= snow.size()-1 ){
            if(snow.get(i) >= 2 && (highWind.get(i) >= 15 && highWind.get(i) <= 40)){
                retString.append( "there are signs of wind slab formation on: " + dateArrayList.get(i) + " with new snow that day measuring " + snow.get(i) + " inches with wind slab most likley on " + windSwitcher(windDirection.get(i)) + "\n");
            }
            i++;
        }
         if (retString.length() == 0){
              retString.append("there are no signs of windslab in the last week \n");
         }

         retString.append("snow totals this last " + numberOfResults + " days were " + newSnowSum);

        return retString;


    }

    private String windSwitcher(String windDir){
        switch (windDir) {
            case "N":
                return "S";
            case "S":
                return "N";
            case "E":
                return "W";
            case "W":
                return "E";
            case "NE":
                return "SW";
            case "SE":
                return "NW";
            case "NW":
                return "SE";
            case "SW":
                return "NE";
            default:
                return null;

        }
    }

}
