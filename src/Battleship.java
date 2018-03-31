import java.util.Random;
import java.util.Scanner;

public class Battleship {

    public static void main(String[] arg){
        String ocean[][] = new String[10][10];

        //Create ocean map
        intro(ocean);
        delpoyPlayerShip(ocean);
        deployComputerShips(ocean);
        battle(ocean);

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
                else if(ocean[r][c] == "3"){
                    System.out.print('!');
                }
                else if(ocean[r][c] == "4"){
                    System.out.print('x');
                }
                else if(ocean[r][c] == "5"){
                    System.out.print('-');
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
        //Deploy computer ships
        Random rand = new Random();
        System.out.println("Computer is deploying ships.");
        int i = 0;
        while(i < 5){
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            boolean res = isValid(ocean, x, y);

            if (res == true){
                ocean[x][y] = "2";
                System.out.println("Ship " + (i+1) + " deployed");
                i++;
            }
            else {
                continue;
            }

        }
        System.out.println("----------------------------------");
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

    public static void battle(String[][] ocean){

        //game is over when either ships reach zero
        boolean gameOn = true;

        while (gameOn){
            System.out.println("Your Turn!");
            printMap(ocean);
            playerGuess(ocean);
            System.out.println("Computer's Turn!");
            computerGuess(ocean);

            int compScore = 0;
            int playerScore = 0;


            for (int i = 0; i < ocean.length; i++){
                for (int j = 0; j < ocean[i].length; j++){
                    if (ocean[i][j] == "3"){
                        playerScore++;
                    }
                    else if(ocean[i][j] == "4"){
                        compScore++;
                    }
                }
            }

            if (compScore == 5){
                System.out.println("You lose!");
                gameOn = false;
            }
            else if (playerScore == 5){
                System.out.println("Hooray! You win!");
                gameOn = false;
            }
        }
    }

    public static void playerGuess(String[][] ocean){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter X coordinate: ");
        int x = sc.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = sc.nextInt();

        //Check if the guess is valid
        boolean res = validGuessPlayer(ocean, x, y);
        if (res == false){
            playerGuess(ocean);
        }
        else {
            //Player correctly guessed coordinates of computer’s ship
            if (ocean[x][y] == "2"){
                System.out.println("Boom! You sunk the ship!");
                ocean[x][y] = "3";
            }

            //Player entered coordinates of his/her own ship
            else if(ocean[x][y] == "1"){
                System.out.println("Oh no, you sunk your own ship :(");
                ocean[x][y] = "4";
            }

            //Player missed
            else {
                System.out.println("Sorry, you missed");
                ocean[x][y] = "5";
            }
        }
    }

    public static void computerGuess(String[][] ocean){
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);

        //Check if the guess is valid
        boolean res = validGuessComputer(ocean, x, y);
        if (res == false){
            computerGuess(ocean);
        }
        else {
            //Computer guessed coordinates of the player’s ship
            if(ocean[x][y] == "1"){
                System.out.println("The Computer sunk one of your ships!");
                ocean[x][y] = "4";
            }

            //Computer guessed coordinates of its own ship
            else if (ocean[x][y] == "2"){
                System.out.println("The Computer sunk one of its own ships");
                ocean[x][y] = "3";
            }

            //Computer missed.
            else {
                System.out.println("Computer missed");
                ocean[x][y] = "6";
            }
        }
    }

    public static boolean validGuessPlayer(String[][] ocean, int x, int y){
        if (x < 0 || x > 10 || y < 0 || y > 10){
            System.out.println("Please choose another coordinates.");
            return false;
        }
        else if(ocean[x][y] == "4"){
            System.out.println("Please choose another coordinates.");
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean validGuessComputer(String[][] ocean, int x, int y){
        if (x < 0 || x > 10 || y < 0 || y > 10){
            return false;
        }
        else if(ocean[x][y] == "6"){
            return false;
        }
        else{
            return true;
        }
    }

    public static void didWin(String[][] ocean){
        int compScore = 5;
        int playerScore = 5;

        for (int i = 0; i < ocean.length; i++){
            for (int j = 0; j < ocean[i].length; j++){
                if (ocean[i][j] == "3"){
                    playerScore++;
                }
                else if(ocean[i][j] == "4"){
                    compScore++;
                }
            }
        }

        if (compScore == 5){
            System.out.println("You lose!");
        }
        else if (playerScore == 5){
            System.out.println("Hooray! You win!");
        }
    }

}
