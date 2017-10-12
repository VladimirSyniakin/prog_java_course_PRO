package com.gmail.vsyniakin.dao;

import java.sql.*;

public class TableApartmentDAO {

    public static void createTableApartment(Connection connection) {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Apartment");
            st.execute("DROP TABLE IF EXISTS Address");
            st.execute("CREATE TABLE Address (idAddress INT AUTO_INCREMENT PRIMARY KEY, district VARCHAR (30) NOT NULL, street VARCHAR (40) NOT NULL, house VARCHAR (5) NOT NULL, apartment VARCHAR (5) NOT NULL)");
            st.execute("CREATE TABLE Apartment (idAddress INT PRIMARY KEY, area DOUBLE, rooms INT, price INT, FOREIGN KEY (idAddress) REFERENCES Address (idAddress))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addApartment(Connection connection, String[] dataApartment) {
        if (dataApartment != null) {
            try {
                connection.setAutoCommit(false);
                try (PreparedStatement prStAddress = connection.prepareStatement("INSERT INTO Address VALUES (NULL, ?, ?, ?, ?)"); PreparedStatement prStApartment = connection.prepareStatement("INSERT INTO Apartment VALUES (LAST_INSERT_ID(), ?, ?, ?)")) {
                    for (int i = 1; i <= dataApartment.length; i++) {
                        if (i <= 4) {
                            prStAddress.setString(i, dataApartment[i - 1]);
                        } else {
                            prStApartment.setString((i - 4), dataApartment[i - 1]);
                        }
                    }
                    prStAddress.executeUpdate();
                    prStApartment.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteApartment(Connection connection, String[] dataApartment) {
        if (dataApartment != null) {
            if (dataApartment.length == 1) {
                try (PreparedStatement prStDelete = connection.prepareStatement("DELETE FROM address, apartment USING apartment INNER JOIN address WHERE address.idAddress = ? AND address.idAddress=apartment.idAddress")) {
                    prStDelete.setString(1, dataApartment[0]);
                    prStDelete.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try (PreparedStatement prStSearch = connection.prepareStatement("SELECT idAddress FROM address WHERE district = ? AND street = ? AND house = ? AND apartment = ?")) {
                    connection.setAutoCommit(false);
                    for (int i = 1; i <= dataApartment.length; i++) {
                        prStSearch.setString(i, dataApartment[i - 1]);
                    }
                    connection.commit();
                    ResultSet rs = prStSearch.executeQuery();
                    try {
                        if (rs.next()) {
                            deleteApartment(connection, new String[]{rs.getString("idAddress")});
                        } else {
                            System.out.println("Apartment not found.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        rs.close();
                        connection.setAutoCommit(true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void selectAllApartments (Connection connection) {
        try (PreparedStatement prSt = connection.prepareStatement("SELECT * FROM address NATURAL JOIN apartment")) {
            ResultSet rs = prSt.executeQuery();
            try {
                ResultSetMetaData rsMd = rs.getMetaData();
                for (int i = 1; i <= rsMd.getColumnCount(); i++) {
                    System.out.print(rsMd.getColumnName(i) + "\t" + "|" + "\t");
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= rsMd.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t" + "|" +"\t");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectByParameterApartments (Connection connection, String [] parameter) {
        String temp = "SELECT * FROM address NATURAL JOIN apartment WHERE " + parameter[0] + " " + parameter[1] + " ?" ;

        try (PreparedStatement prSt = connection.prepareStatement(temp)) {
            connection.setAutoCommit(false);

            prSt.setInt(1, Integer.parseInt(parameter[2]));

            connection.commit();
            ResultSet rs = prSt.executeQuery();
            try {
                ResultSetMetaData rsMd = rs.getMetaData();
                for (int i = 1; i <= rsMd.getColumnCount(); i++) {
                    System.out.print(rsMd.getColumnName(i) + "\t" + "|" + "\t");
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= rsMd.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t" + "|" +"\t");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs.close();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
