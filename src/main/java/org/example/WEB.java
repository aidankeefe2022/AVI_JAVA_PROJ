package org.example;
import spark.Spark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WEB {

   public void WebsiteInit(){
       Spark.port(80);
       Spark.get("/Bridger", (req, res) -> {
           res.header("Content-Type", "Text-Text");
           DB db = new DB();
           StringBuilder sb = new StringBuilder();

           try(Connection conn = db.connect(); PreparedStatement BridgerStatement = conn.prepareStatement("SELECT * from Bridger")){
               ResultSet set = BridgerStatement.executeQuery();
               formater(sb,set,"Bridger");
           }
           return sb.toString();


       });
       Spark.get("/CookeCity", (req, res) -> {
           res.header("Content-Type", "Text-Text");
           DB db = new DB();
           StringBuilder sb = new StringBuilder();
           try(Connection conn = db.connect();
               PreparedStatement BridgerStatement = conn.prepareStatement("SELECT * from CookeCity")){
               ResultSet set = BridgerStatement.executeQuery();
               formater(sb,set,"CookeCity");

           }
           return sb.toString();
       });
       Spark.get("/NorthernGallatins", (req, res) -> {
           res.header("Content-Type", "Text-Text");
           DB db = new DB();
           StringBuilder sb = new StringBuilder();
           try(Connection conn = db.connect(); PreparedStatement BridgerStatement = conn.prepareStatement("SELECT * from NorthernGallatins")){
               ResultSet set = BridgerStatement.executeQuery();
               formater(sb,set,"NorthernGallatins");
           }
           return sb.toString();


       });
       Spark.get("/SouthernGallatins", (req, res) -> {
           res.header("Content-Type", "Text-Text");
           DB db = new DB();
           StringBuilder sb = new StringBuilder();

           try(Connection conn = db.connect(); PreparedStatement BridgerStatement = conn.prepareStatement("SELECT * from SouthernGallatins")){
               ResultSet set = BridgerStatement.executeQuery();
               formater(sb,set,"SouthernGallatins");
           }

           return sb.toString();


       });
       Spark.get("/NorthernMadisons", (req, res) -> {
           res.header("Content-Type", "Text-Text");
           DB db = new DB();
           StringBuilder sb = new StringBuilder();
           try(Connection conn = db.connect();
               PreparedStatement BridgerStatement = conn.prepareStatement("SELECT * from NorthernMadisons")){
               ResultSet set = BridgerStatement.executeQuery();
               formater(sb,set,"NorthernMadisons");
           }
           return sb.toString();


       });
       Spark.get("/SouthernMadisons", (req, res) -> {
           res.header("Content-Type", "Text-Text");
           DB db = new DB();
           StringBuilder sb = new StringBuilder();
           try(Connection conn = db.connect();
               PreparedStatement BridgerStatement = conn.prepareStatement("SELECT * from SouthernMadisons")){
               ResultSet set = BridgerStatement.executeQuery();
               formater(sb,set,"SouthernMadisons");
           }
           return sb.toString();


       });

       Spark.get("/", ((request, response) ->
               "<p><a href='/NorthernGallatins'>Northern Gallatins</a></p>" +
                       "<p><a href='/SouthernGallatins'>Southern Gallatins</a></p>" +
                       "<p><a href='/NorthernMadisons'>Northern Madisons</a></p>" +
                       "<p><a href='/SouthernMadisons'>Southern Madisons</a></p>" +
                       "<p><a href='/Bridger'>Bridger</a></p>" +
                       "<p><a href='/CookeCity'>Cooke City</a></p>"));

   }
    private void formater(StringBuilder sb, ResultSet set, String tableName){
        try {
            sb.append("DATE\tNEW_SNOW\tHIGH_WIND\tLOW_WIND\tWIND_DIRECTION\tHAZRAD_RATING\tTEMP\n\n");
            while (set.next()) {
                sb.append(set.getString("DATE"))
                        .append("\t")
                        .append(set.getString("NEW_SNOW"))
                        .append("\t")
                        .append(set.getString("HIGH_WIND"))
                        .append("\t\t")
                        .append(set.getString("LOW_WIND"))
                        .append("\t\t")
                        .append(set.getString("WIND_DIRECTION"))
                        .append("\t\t")
                        .append(set.getString("HAZARD_RATING"))
                        .append("\t")
                        .append(set.getString("TEMP"))
                        .append("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Anaylsis anal = new Anaylsis();
        sb.append(anal.windLoaded(tableName));

    }
}
