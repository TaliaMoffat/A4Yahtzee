/*Yahtzee 
Talia Moffat
Jan. 19th - Jan. __, 2022
CPU vs. User Yahtzee game.*/

import java.util.*;

class Main {

  //Incase needed: System.out.println("test");

  //variables
  static Scanner input = new Scanner(System.in);
  static Random numGenerator = new Random();
  public static final String TEXT_PURPLE = "\u001B[35m";
  public static final String TEXT_YELLOW = "\u001B[33m";
  public static final String TEXT_RED = "\u001B[31m";
  public static final String TEXT_RESET = "\u001B[0m";

  static boolean isYahtzee = false;
  static boolean isPlaying = false;
  static boolean validInput = false;
  static boolean hasPlayed = false;
  static int userInt = 0;
  static boolean validIn = false;

  static String[] sections = {"UPPER SECTION", "Aces\t\t", "Twos\t\t", "Threes\t\t", "Fours\t\t", "Fives\t\t", "Sixes\t\t", "TOTAL SCORE", "BONUS\t\t", "TOTAL\t\t", "LOWER SECTION", "3 of a kind", "4 of a kind", "Full House\t", "Small Straight", "Large Straight", "YAHTZEE\t", "Chance\t\t", "YAHTZEE BONUS", "TOTAL - Lower", "TOTAL - Upper", "GRAND TOTAL"};

  static String[] playerScore = {"", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
  static String[] cpuScore = {"", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
  static String[] playerUsed = {"", "1", "2", "3", "4", "5", "6", "", "", "", "", "7", "8", "9", "10", "11", "12", "13", "", "", "", ""};
  static String[] cpuUsed = {"", "1", "2", "3", "4", "5", "6", "", "", "", "", "7", "8", "9", "10", "11", "12", "13", "", "", "", ""};

  public static void welcomeUser(){
    System.out.println("\nWelcome to our Yahtzee game! We are so excited to play with you, but first you need to answer some questions.");
    questionOne();
    validInput = false;
    validIn = false;
    questionTwo();
    if (isPlaying == true){
      System.out.println("\nOkay! Let's play!"); //clear?
    }
  }

  public static void invalidInput(){
    System.out.println("That doesn't look right, try again.");
  }

  public static void checkUserInt(){ //invalid input goes into infinite loop???
    try{
        userInt = input.nextInt();
        validIn = true;
      } catch (Exception e){
        userInt = 0;
        validIn = false;
        System.out.println("That doesn't look right, try again.");
      }
      if (userInt == 7){
        validIn =  false;
      }
  }

  public static void questionOne(){
    while (validInput == false){
      while (validIn == false){
        System.out.println("\nNumber 1: Do you want to play?");
        yesOrNo();
      }
      if (userInt == 1){
        isPlaying = true;
        validInput = true;
        break;
        } else if (userInt == 2){
          goodbye();
        } else if (userInt != 1 && userInt != 2){
          invalidInput();
        }
    }
  }

  public static void questionTwo(){
    while (validInput == false){
      while (validIn == false){
        System.out.println("\nNumber 2: Do you know how to play?");
        yesOrNo();
      }
      if (userInt == 1){
        validInput = true;
        break;
        } else if (userInt == 2){
          //add rules
          validInput = true;
          break;
        } else if (userInt != 1 && userInt != 2){
          invalidInput();
        }
    }
  }

  public static void yesOrNo(){
    System.out.println( TEXT_RESET + "\nType " + TEXT_RED + "1 for yes" + TEXT_RESET + " or " + TEXT_RED + "2 for no.\n" + TEXT_RESET);
    checkUserInt();
  }

  public static boolean rollForFirst (){
    System.out.println("Let's roll for it!" + TEXT_PURPLE + "\nMe first!");
    int[] cpuRoll = rollDice(5);
    int[] playerRoll = rollDice(5);
    printRoll(cpuRoll);
    System.out.println("\nThat's what I got..." + TEXT_YELLOW);
    printRoll(playerRoll);
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
      System.out.print("\033[H\033[2J");
      return true;
    } else if (cpuScore == playerScore){
      System.out.println(TEXT_RESET + "it's a tie! You know what," + TEXT_YELLOW + " you go first.");
      System.out.print("\033[H\033[2J");
      return false;
    } else {
      System.out.println(TEXT_YELLOW + "I guess you go first.");
      System.out.print("\033[H\033[2J");
      return false;
    }
  }

  public static void printRoll (int[] roll){
    asciiArt(roll);
    for (int i = 0; i < roll.length; i++){
      System.out.print(roll[i] + " ");
    }
  }

  public static void asciiArt(int[] roll){
    for (int i = 0; i < roll.length; i++){
      System.out.println("\n+---------+");
      switch (roll[i]){
        case 1:
          System.out.println("|         |");
          System.out.println("|    o    |");
          System.out.println("|	      |");
          break;

        case 2:
          System.out.println("| o       |");
          System.out.println("|         |");
          System.out.println("|	    o |");
          break;

        case 3:
          System.out.println("| o       |");
          System.out.println("|    o    |");
          System.out.println("|	    o |");
          break;
        
        case 4:
          System.out.println("| o     o |");
          System.out.println("|         |");
          System.out.println("| o     o |");
          break;

        case 5:
          System.out.println("| o     o |");
          System.out.println("|    o    |");
          System.out.println("| o     o |");
          break;

        case 6:
          System.out.println("| o     o |");
          System.out.println("| o     o |");
          System.out.println("| o     o |");
          break;
      }
      System.out.println("+---------+");
    }
  }

  public static void yahtzeeCheck(int[] roll){
    for (int i = 1; i < roll.length; i++){
      if (roll[i] == roll[i - 1]){
        isYahtzee = true;
      } else {
        isYahtzee = false; 
        break;
      }
    }
    if (isYahtzee == true){
      System.out.println(TEXT_RED + "\n\nYAHTZEE!");
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
    yahtzeeCheck(roll);
  }

  public static void playerTurn(int[] roll){
    System.out.println( TEXT_YELLOW + "\nHere's your roll...");
    printRoll(roll);
    int usedRerolls = 0;
    yahtzeeCheck(roll);
    reroll(usedRerolls, roll);
    playerScoreSheet();
    slotToUse(roll);
  }

  public static void playerScoreSheet(){
    for (int i = 0; i < 10; i++) { //scores.length
      System.out.println("+-----------------");
      System.out.println("|" + sections[i] + "\t" + TEXT_RED + playerUsed[i] + TEXT_RESET + "\t|\t" + playerScore[i]);
    }
    System.out.println("+-----------------");
  }

  public static void slotToUse(int[] roll){
    boolean usedAlready = true;
    while (usedAlready == true) {
    System.out.println("Type the number (red) for the slot you want to use.");
    checkUserInt();
    if (playerScore[userInt] != "-"){
      usedAlready = true;
      System.out.println("You already used this section.");
      continue;
    } else {
      usedAlready = false;
    }
    }
    int score = 0;
     for (int i = 0; i < 5; i++){
      if (roll[i] == userInt){
        score = score + userInt;
      }
      playerScore[userInt] = String.valueOf(score);
    }
    System.out.println("You have a score of " + playerScore[userInt] + " for the " + sections[userInt] + " section on your player sheet now!");
    playerUsed[userInt] = "";
  }

    /*switch (userInt) {
      case 1: aces(roll);
              break;
      case 2: 
      case 3:
      case 4:
      case 5:
      case 6:
    }
  }

  public static void aces(int[] roll){
    int acesScore = 0; //move elsewhere?
    for (int i = 0; i < 5; i++){
      if (roll[i] == 1){
        acesScore++;
      }
      playerScore[1] = String.valueOf(acesScore);
      System.out.println("You have a score of " + acesScore + " for the aces section on your player sheet now!");
    }

  } */

  public static void reroll(int used, int[] roll){
    while (used < 3){
      System.out.println("\n\nYou have " + (3 - used) + " rerolls left.");
      System.out.println("\nDo you want to reroll?");
      yesOrNo();
      if (userInt == 1){
        rerollDice(roll);
        used++;
      } else if (userInt == 2){
        used = 3;
      }
    }
    System.out.print("\033[H\033[2J");
    printRoll(roll);
    System.out.println("\n\nHere's your final roll.\n\n");
  }

  public static void rerollDice(int[] roll){
    System.out.println("\nWhich dice do you want to reroll? \nTo select a die, type the value on the die and press enter. \nWrite 7 to stop selecting dice.\n" + TEXT_YELLOW);
    int i = 0;
    int[] reroll = new int[5];
    validIn = true;
    while (validIn == true){
      checkUserInt();
      if(validIn == false){
        break;
      }
      reroll[i] = userInt;
      i++;
    }
    for (int j = 0; j < 5; j++){
      for (int k = 0; k < 5; k++){
        if (reroll[j] == roll[k]){
          int l = numGenerator.nextInt(6) + 1; 
          roll[k] = l;
          reroll[j] = 0;
        }
      }
    }
    System.out.print(TEXT_YELLOW);
    printRoll(roll);
    System.out.println("\nHere's your new role");
  }

  public static int[] rollDice(int dice){
    int[] diceArray = new int [dice];
    for (int i = 0; i < dice; i++){
      diceArray[i] = numGenerator.nextInt(6) + 1; 
    }
    return diceArray;
  }

  public static void anotherGame (){
    System.out.println( TEXT_RESET + "\nWant to play again?");
    yesOrNo();
    int playAgain = input.nextInt();
    if (playAgain == 2){
      isPlaying = false;
    }
  }

  public static void goodbye (){
    if (hasPlayed == true){
      System.out.println("\nWell it was nice playing with you, I hope you had fun!");
    } else {
      System.out.println("\nMaybe next time then!");
    }
    System.out.println("Bye!");
    try{
    Thread.sleep(5000);
    } catch (InterruptedException ex) {
    Thread.currentThread().interrupt();
    }
    System.out.print("\033[H\033[2J");
    System.exit(0);
  }

  public static void main(String[] args) {
    welcomeUser();
    while (isPlaying == true){
      hasPlayed = true;
      boolean cpuFirst = rollForFirst();
      turns(cpuFirst);
      //call calculating score method?
      //print winner and scores
      anotherGame();
      }
    goodbye();  
    }
}