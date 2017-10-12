package com.gmail.vsyniakin.dbconnect;

import java.sql.*;

public class TablesProductClientOrder {

    public static void createTableApartment(Connection connection) {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS orders");
            st.execute("DROP TABLE IF EXISTS products");
            st.execute("DROP TABLE IF EXISTS clients");
            st.execute("CREATE TABLE products (id_product INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR (30) NOT NULL, price INT)");
            st.execute("CREATE TABLE clients (id_client INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR (20) NOT NULL, phone VARCHAR (15) NOT NULL, email VARCHAR (30))");
            st.execute("CREATE TABLE orders (id_order INT AUTO_INCREMENT PRIMARY KEY, date_order DATE, quantity INT, amount INT, id_product INT NOT NULL, id_client INT NOT NULL, FOREIGN KEY (id_product) REFERENCES products (id_product), FOREIGN KEY (id_client) REFERENCES clients (id_client))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(Connection connection, String[] dataProduct) {
        if (dataProduct != null) {
            try (PreparedStatement prSt = connection.prepareStatement("INSERT INTO products VALUES (NULL, ?, ?)")) {
                prSt.setString(1, dataProduct[0]);
                prSt.setInt(2, Integer.parseInt(dataProduct[1]));
                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addClient(Connection connection, String[] dataClient) {
        if (dataClient != null) {
            try (PreparedStatement prSt = connection.prepareStatement("INSERT INTO clients VALUES (NULL, ?, ?, ?)")) {
                for (int i = 1; i <= dataClient.length; i++) {
                    prSt.setString(i, dataClient[i - 1]);
                }
                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addOrder(Connection connection, int[] dataOrder) {
        if (dataOrder != null) {
            try (PreparedStatement prSt = connection.prepareStatement("INSERT INTO orders VALUES (NULL, NOW(), ?, ?, ?, ?)")) {
                for (int i = 1; i <= dataOrder.length; i++) {
                    prSt.setInt(i, dataOrder[i - 1]);
                }
                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int selectId(Connection connection, String name, String TABLE) {
        int result = -1;
        String request = null;
        if (TABLE.equals("product")) {
            request = "SELECT id_product FROM products WHERE products.name = ?";
        } else if (TABLE.equals("client")) {
            request = "SELECT id_client FROM clients WHERE clients.name = ?";
        } else if (TABLE.equals("price")) {
            request = "SELECT price FROM products WHERE products.name = ?";
        }
        if ((name != null) && (request != null)) {
            try (PreparedStatement prSt = connection.prepareStatement(request)) {
                prSt.setString(1, name);
                ResultSet rs = prSt.executeQuery();
                try {
                    if (rs.next()) {
                        result = rs.getInt(1);
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
        return result;
    }

    public static void selectOrders (Connection connection) {
        try (PreparedStatement prSt = connection.prepareStatement("SELECT c.name, o.date_order, p.name, p.price, o.quantity, o.amount FROM orders o LEFT JOIN clients c ON o.id_client=c.id_client LEFT JOIN products p ON o.id_product = p.id_product")) {
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
}
