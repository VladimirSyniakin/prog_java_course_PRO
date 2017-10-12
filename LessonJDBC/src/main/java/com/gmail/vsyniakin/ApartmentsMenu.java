package com.gmail.vsyniakin;

import com.gmail.vsyniakin.dbconnect.TableApartment;


import java.io.BufferedReader;
import java.sql.Connection;


public class ApartmentsMenu {

    public static void selectAction(Connection connection, BufferedReader bfRd) {
        showMenu();
        String input = Main.inputSystem(bfRd);
        if (input != null) {
            try {
                int actionInt = Integer.parseInt(input);
                if (actionInt >= 0 && actionInt <= 4) {
                    switch (input) {
                        case "0":
                            TableApartment.createTableApartment(connection);
                            selectAction(connection,bfRd);
                            break;
                        case "1":
                            TableApartment.addApartment(connection, addApartmentData(bfRd));
                            selectAction(connection,bfRd);
                            break;
                        case "2":
                            TableApartment.deleteApartment(connection, deleteApartmentData(bfRd));
                            selectAction(connection,bfRd);
                            break;
                        case "3":
                            TableApartment.selectAllApartments(connection);
                            selectAction(connection,bfRd);
                            break;
                        case "4":
                            TableApartment.selectByParameterApartments(connection,showByParameter(bfRd));
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
        System.out.println("0 - create table;");
        System.out.println("1 - add apartment;");
        System.out.println("2 - delete apartment;");
        System.out.println("3 - show all apartments;");
        System.out.println("4 - show apartments by parameter.");
    }

    public static String[] addApartmentData(BufferedReader bfRd) {
        String[] dataApartment = new String[7];
        String[] showInstruction = new String[]{"Enter district:", "Enter street:", "Enter the house number:", "Enter the apartment number:", "Enter area:", "Enter number of rooms:", "Enter price:"};
        for (int i = 0; i < dataApartment.length; i++) {
            System.out.println(showInstruction[i]);
            dataApartment[i] = Main.inputSystem(bfRd);
        }
        if (checkInputData(dataApartment)) {
            return dataApartment;
        } else {
            System.out.println("Incorrect data!");
            return null;
        }
    }

    public static String [] showByParameter (BufferedReader bfRd) {

        System.out.println("0 - area; " + "1 - number of rooms; " + "2 - price");
        String choice = Main.inputSystem(bfRd);
        String [] parameter = new String[3];
        switch (choice) {
            case "0":
                parameter [0] = "apartment.area";
                break;
            case "1":
                parameter [0] = "apartment.rooms";
                break;
            case "2":
                parameter [0] = "apartment.price";
                break;
            default:
                System.out.println("Incorrect choice!");
                return null;
        }
        System.out.println("0 - <; " + "1 - >; " + "2 - =");
        choice = Main.inputSystem(bfRd);
        switch (choice) {
            case "0":
                parameter [1] = "<";
                break;
            case "1":
                parameter [1] = ">";
                break;
            case "2":
                parameter [1] = "=";
                break;
            default:
                System.out.println("Incorrect choice!");
                return null;
        }
        System.out.println(parameter [0] + " " + parameter[1] + " enter number please:");
        choice = Main.inputSystem(bfRd);
        try {
            Integer.parseInt(choice);
            parameter [2] = choice;
            return parameter;
        } catch (NumberFormatException e) {
            System.out.println("Incorrect choice!");
            return null;
        }
    }

    public static  String [] deleteApartmentData (BufferedReader bfRd) {
        System.out.println("0 - delete by ID; 1 - delete by address.");
        String choice = Main.inputSystem(bfRd);

        switch (choice) {
            case "0":
                return deleteApartmentByID(bfRd);
            case "1":
                return deleteApartmentByAddress(bfRd);
            default:
                System.out.println("Incorrect choice!");
                return null;
        }
    }

    public static String [] deleteApartmentByID (BufferedReader bfRd) {
        System.out.println("Enter ID apartment:");
        return new String []{Main.inputSystem(bfRd)};
    }
    public static String [] deleteApartmentByAddress (BufferedReader bfRd) {
        String[] dataApartment = new String[4];
        String[] showInstruction = new String[]{"Enter district:", "Enter street:", "Enter the house number:", "Enter the apartment number:"};
        for (int i = 0; i < dataApartment.length; i++) {
            System.out.println(showInstruction[i]);
            dataApartment[i] = Main.inputSystem(bfRd);
        }
        if (checkInputData(dataApartment)) {
            return dataApartment;
        } else {
            System.out.println("Incorrect data!");
            return null;
        }
    }

    public static boolean checkInputData(String[] dataApartment) {
        for (int i = 0; i < dataApartment.length; i++) {
            try {
                if (i < 4) {
                    if ((dataApartment[i] == null)) {
                        return false;
                    }
                } else {
                    switch (i) {
                        case 4:
                            Double.parseDouble(dataApartment[i]);
                            break;
                        case 5:
                            Integer.parseInt(dataApartment[i]);
                            break;
                        case 6:
                            Integer.parseInt(dataApartment[i]);
                            break;
                    }
                }
            } catch (NullPointerException e) {
                return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }


}
