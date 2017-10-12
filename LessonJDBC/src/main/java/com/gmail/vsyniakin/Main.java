package com.gmail.vsyniakin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {

    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/MyDB";
    private static final String DB_login = "root";
    private static final String DB_password = "root";


    public static void main (String [] args) {
        try (Connection connMyDB = DriverManager.getConnection(DB_CONNECTION, DB_login, DB_password); BufferedReader bfRd = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Open apartment tables - 0; Open order tables - 1");
            String choice = inputSystem(bfRd);

            switch (choice){
                case "0":
                    ApartmentsMenu.selectAction(connMyDB, bfRd);
                case "1":
                    ProductClientOrderMenu.selectAction(connMyDB, bfRd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String inputSystem(BufferedReader bfRd) {
        try {
            return bfRd.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
