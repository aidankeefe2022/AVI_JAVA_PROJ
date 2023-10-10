package org.example;

import java.sql.*;
public class DB {
    public Connection connect() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:C:/Users/bnhke/IdeaProjects/AVI PROJECT JAVA IMPORVED/DB/AVI_DATA_SOURCE.db");

    }
}
