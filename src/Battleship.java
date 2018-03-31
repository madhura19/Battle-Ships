import java.util.Scanner;

public class Battleship {

    public static void main(String[] arg){
        String ocean[][] = new String[10][10];

        //Create ocean map
        intro(ocean);
        delpoyPlayerShip(ocean);


    }

    public static void intro(String[][] ocean){
        System.out.println("**** Welcome to the Battle Ships game ****");
        System.out.println("Right now, the sea is empty.");
        printMap(ocean);
    }

    public static void createMap(){}

    public static void printMap(String[][] ocean){
        //Displaying the map
        System.out.println("\n  0123456789 ");
        for(int r = 0; r < ocean.length; r++){
            System.out.print(r + "|");
            for (int c = 0; c < ocean[r].length; c++){
                if (ocean[r][c] == null){
                    System.out.print(" ");
                }
                else if (ocean[r][c] == "1"){
                    System.out.print('@');
                }
                else {
                    System.out.print(ocean[r][c]);
                }
            }
            System.out.println("|" + r);
        }
        System.out.println("  0123456789 ");
    }

    public static void delpoyPlayerShip(String[][] ocean){
        Scanner sc = new Scanner(System.in);

        int i = 0;
        while (i < 5){
            System.out.print("Enter X coordinate for your ship: ");
            int x = sc.nextInt();
            System.out.print("Enter Y coordinate for your ship: ");
            int y = sc.nextInt();
            boolean res = isValid(ocean, x, y);

            if(res == true){
                ocean[x][y] = "1";
                i++;
            }
            else {
                continue;
            }

            /*if (x < 0 || x > 10 || y < 0 || y > 10){
                System.out.println("Please choose legal coordinates.");
                continue;
            }

            else if (ocean[x][y] != null){
                System.out.println("Please choose another coordinate.");
                continue;
            }

            else {
                ocean[x][y] = "1";
                i++;
            }*/
        }
        printMap(ocean);
    }

    public static void deployComputerShips(String[][] ocean){

    }

    public static boolean isValid(String[][] ocean, int x, int y){
        if (x < 0 || x > 10 || y < 0 || y > 10){
            System.out.println("Please choose legal coordinates.");
            return false;
        }

        else if (ocean[x][y] != null){
            System.out.println("Please choose another coordinate.");
            return false;
        }

        else {
            return true;
        }
    }

    public static void battle(){}


}
