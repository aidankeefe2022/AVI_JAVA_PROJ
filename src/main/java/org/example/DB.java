package org.example;

import java.sql.*;
public class DB {
    public Connection connect() throws SQLException{
        Connection conn =  DriverManager.getConnection("jdbc:sqlite:C:/Users/bnhke/IdeaProjects/AVI PROJECT JAVA IMPORVED/DB/AVI_DATA_SOURCE.db");
        try (Statement stmt = conn.createStatement()){
            stmt.execute("PRAGMA foreign_keys = on");}
        return conn;
    }
}
