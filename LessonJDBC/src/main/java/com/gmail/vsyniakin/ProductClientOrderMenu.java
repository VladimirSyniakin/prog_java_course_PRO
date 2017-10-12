package com.gmail.vsyniakin;

import com.gmail.vsyniakin.dao.TablesProductClientOrderDAO;

import java.io.BufferedReader;
import java.sql.Connection;

public class ProductClientOrderMenu {

    public static void selectAction(Connection connection, BufferedReader bfRd) {
        showMenu();
        String input = Main.inputSystem(bfRd);
        if (input != null) {
            try {
                int actionInt = Integer.parseInt(input);
                if (actionInt >= 0 && actionInt <= 4) {
                    switch (input) {
                        case "0":
                            TablesProductClientOrderDAO.createTableApartment(connection);
                            selectAction(connection,bfRd);
                            break;
                        case "1":
                            TablesProductClientOrderDAO.addProduct(connection, addProductData(bfRd));
                            selectAction(connection,bfRd);
                            break;
                        case "2":
                            TablesProductClientOrderDAO.addClient(connection, addClientData(bfRd));
                            selectAction(connection,bfRd);
                            break;
                        case "3":
                            TablesProductClientOrderDAO.addOrder(connection, addOrderData(connection,bfRd));
                            selectAction(connection,bfRd);
                            break;
                        case "4":
                            TablesProductClientOrderDAO.selectOrders(connection);
                            selectAction(connection,bfRd);
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect choice!");
                selectAction(connection, bfRd);
            }
        }
    }

    public static void showMenu() {
        System.out.println("0 - create tables;");
        System.out.println("1 - add product;");
        System.out.println("2 - add client;");
        System.out.println("3 - add order;");
        System.out.println("4 - show all orders.");
    }

    public static String [] addProductData (BufferedReader bfRd) {
        String[] dataProduct = new String[2];
        String[] showInstruction = new String[]{"Enter name:", "Enter price:"};
        for (int i = 0; i < dataProduct.length; i++) {
            System.out.println(showInstruction[i]);
            dataProduct[i] = Main.inputSystem(bfRd);
        }
        try {
            if ((dataProduct[0] == null) && ((Integer.parseInt(dataProduct[1]) < 0))) {
                System.out.println("Incorrect data!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect price!");
            return null;
        }
        return dataProduct;
    }

    public static String [] addClientData (BufferedReader bfRd) {
        String[] dataClient = new String[3];
        String[] showInstruction = new String[]{"Enter name:", "Enter phone:", "Enter email:"};
        for (int i = 0; i < dataClient.length; i++) {
            System.out.println(showInstruction[i]);
            dataClient[i] = Main.inputSystem(bfRd);
            if ((i < (dataClient.length - 1)&&(dataClient[i] == null))) {
                System.out.println("Incorrect data!");
                return null;
            }
        }
        return dataClient;
    }

    public static int [] addOrderData (Connection connection, BufferedReader bfRd) {
        final String CLIENT = "client";
        final String PRODUCT = "product";
        final String PRICE = "price";

        System.out.println("Enter name client:");
        String client = Main.inputSystem(bfRd);
        int idClient = TablesProductClientOrderDAO.selectId(connection, client, CLIENT);

        System.out.println("Enter name product:");
        String product = Main.inputSystem(bfRd);
        int idProduct = TablesProductClientOrderDAO.selectId(connection, product, PRODUCT);
        int price = TablesProductClientOrderDAO.selectId(connection, product, PRICE);

        System.out.println("Enter quantity of product:");
        int quantity = -1;
        try {
            quantity  = Integer.parseInt(Main.inputSystem(bfRd));
        } catch (NumberFormatException e) {
            System.out.println("Incorrect data!");
        }

        int [] dataOrder = {quantity, (price * quantity), idProduct, idClient};

        for (int i = 0; i < dataOrder.length; i++) {
            if (dataOrder [i] < 0) {
                return null;
            }
        }
        return dataOrder;
    }
}
