import java.util.*;

class Main {

  //Incase needed: System.out.println("test");

  //variables
  static Scanner input = new Scanner(System.in);
  static boolean isPlaying = false;
  static boolean validInput = false;
  static int userInt = 0;
  static boolean idk = true;
  static Random numGenerator = new Random();
  public static final String TEXT_PURPLE = "\u001B[35m";
  public static final String TEXT_YELLOW = "\u001B[33m";
  public static final String TEXT_RED = "\u001B[31m";
  public static final String TEXT_RESET = "\u001B[0m";

  public static void welcomeUser(){
    System.out.println("Welcome to our Yahtzee game! We are so excited to play with you, but first you need to answer some questions.");
    questionOne();
    questionTwo();
    System.out.println("Okay! Let's play!");
  }

  public static void invalidInput(){
    System.out.println("That doesn't look right, try again.");
  }

  public static void checkUserInt(){ //invalid input goes into infinite loop???
    try{
        userInt = input.nextInt();
      } catch (Exception e){
        userInt = 0;
      }
  }

  public static void questionOne(){
    while (validInput == false){
      System.out.println("Number 1: Do you want to play? Type " + TEXT_RED + "1 for yes" + TEXT_RESET + " or " + TEXT_RED + "2 for no." + TEXT_RESET);
      checkUserInt();
      if (userInt == 1){
        isPlaying = true;
        validInput = true;
        break;
        } else if (userInt == 2){
          isPlaying = false;
          validInput = true;
          break;
        } else if (userInt != 1 && userInt != 2){
          invalidInput();
        }
    }
  }

  public static void questionTwo(){
    while (validInput = false){
      System.out.println("Number 2: Do you know how to play? Type 1 for yes or 2 for no.");
      try{
        userInt = input.nextInt();
      } catch (NumberFormatException exception){
          invalidInput();
          continue;
      }
      if (userInt == 1){
        validInput = true;
        break;
        } else if (userInt == 2){
          validInput = true;
          //call rules method
          break;
        } else if (userInt != 1 && userInt != 2){
          invalidInput();
        }
    }
  }

  public static boolean rollForFirst (){
    System.out.println("Let's roll for it!" + TEXT_PURPLE + " Me first!");
    int[] cpuRoll = rollDice(5);
    int[] playerRoll = rollDice(5);
    for (int i = 0; i < 5; i++){
      System.out.print(cpuRoll[i] + " ");
    }
    System.out.println("\nThat's what I got..." + TEXT_YELLOW);
    for (int j = 0; j < 5; j++){
      System.out.print(playerRoll[j] + " ");
    }
    System.out.println("\nAnd that's your roll!");
    return whoFirst(cpuRoll, playerRoll);
  }

  public static boolean whoFirst (int[] rollOne, int[] rollTwo){
    int cpuScore = 0;
    for (int i = 0; i < 5; i++){
      cpuScore = cpuScore + rollOne [i];
    }
    int playerScore = 0; 
    for (int j = 0; j < 5; j++){
      playerScore = playerScore + rollTwo [j];
    }
    System.out.println("You got " + playerScore + TEXT_RESET + " and " + TEXT_PURPLE + "I got " + cpuScore + " so...");
    if (cpuScore > playerScore){
      System.out.println(TEXT_PURPLE + "I guess I go first.");
      return true;
    } else if (cpuScore == playerScore){
      System.out.println(TEXT_RESET + "it's a tie! You know what," + TEXT_YELLOW + " you go first.");
      return false;
    } else {
      System.out.println(TEXT_YELLOW + "I guess you go first.");
      return false;
    }
  }

  public static void printRoll (int[] roll){
    for (int i = 0; i < roll.length; i++){
      System.out.print(roll[i] + " ");
    }
  }

  public static void turns(boolean compFirst){
    for (int i = 0; i < 13; i++){
      int[] cpuRoll = rollDice(5);
      int[] playerRoll = rollDice(5);
      if (compFirst == true){
        cpuTurn(cpuRoll);
      }
        playerTurn(playerRoll);
      if (compFirst == true){
        continue;
      }
        cpuTurn(cpuRoll);
    }
  }

  public static void cpuTurn(int[] roll){
    System.out.println( TEXT_PURPLE + "\nOkay here's my roll...");
    printRoll(roll);
  }

  public static void playerTurn(int[] roll){
    System.out.println( TEXT_YELLOW + "\nHere's your roll...");
    printRoll(roll);
  }

  public static int[] rollDice(int dice){
    int[] diceArray = new int [dice];
    for (int i = 0; i < dice; i++){
      diceArray[i] = numGenerator.nextInt(6) + 1; 
    }
    return diceArray;
  }

  public static void main(String[] args) {
    welcomeUser();
    while (isPlaying == true){
      boolean cpuFirst = rollForFirst();
      turns(cpuFirst);
      //call calculating score method?
      //print winner and scores
      //offer another game
      System.out.println( TEXT_RESET + "\nWant to play again? Type " + TEXT_RED + "1 for yes" + TEXT_RESET + " or " + TEXT_RED + "2 for no." + TEXT_RESET);
      int playAgain = input.nextInt();
      if (playAgain == 2){
        isPlaying = false;
      }
    }
    //goobye message    
    System.out.print("\033[H\033[2J");
  }
}