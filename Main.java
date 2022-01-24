/*Yahtzee 
Talia Moffat
Jan. 19th - Jan. __, 2022
CPU vs. User Yahtzee game.
(Only upper section thus far)*/

import java.util.*;

class Main {

  //Incase needed: System.out.println("test");

  static Random numGenerator = new Random(); //Random Number Gen

  //text colours
  public static final String TEXT_PURPLE = "\u001B[35m"; //cpu
  public static final String TEXT_YELLOW = "\u001B[33m"; //player
  public static final String TEXT_RED = "\u001B[31m"; //user input option
  public static final String TEXT_RESET = "\u001B[0m"; //normal

  //static booleans
  static boolean isPlaying = false;
  static boolean validInput = false;
  static boolean hasPlayed = false;

  //static int for user input
  static int userInt = 0;

  //test
  static int cpuKeep = -1;

  //sections on score sheet for output (move to a score sheet method?)
  static String[] sections = {"UPPER SECTION", "Aces\t\t", "Twos\t\t", "Threes\t\t", "Fours\t\t", "Fives\t\t", "Sixes\t\t", "TOTAL SCORE", "BONUS\t\t", "TOTAL\t\t", "LOWER SECTION", "3 of a kind", "4 of a kind", "Full House\t", "Small Straight", "Large Straight", "YAHTZEE\t", "Chance\t\t", "YAHTZEE BONUS", "TOTAL - Lower", "TOTAL - Upper", "GRAND TOTAL"};
  static String[] sectionsSimple = {"aces", "twos", "threes", "fours", "fives", "sixes"};

  //scores of cpu and player
  static String[] playerScore = {"", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
  static String[] cpuScore = {"", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};

  //Which sections used for both cpu and player
  static String[] playerUsed = {"", "1", "2", "3", "4", "5", "6", "", "", "", "", "7", "8", "9", "10", "11", "12", "13", "", "", "", ""};
  static String[] cpuUsed = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

  public static void welcomeUser(){ //welcomes user, calls question methods
    System.out.println("\nWelcome to our Yahtzee game! We are so excited to play with you! \n\nBut first you need to answer some questions.");
    questionOne(); //call question 1
    validInput = false; //reset boolean
    questionTwo(); //call question 2
    System.out.println(TEXT_RESET + "\nOkay! Let's play!\n"); 
    //clear(2000); //call clear console method
    }

  /*public static void clear(int wait){ //clears console (keep it tidy)
    try{
    Thread.sleep(wait); //wait given number of milliseconds
    } catch (InterruptedException ex) {
    Thread.currentThread().interrupt();
    }
    System.out.print("\033[H\033[2J"); //clear
  }*/

  public static void invalidInput(){ //tells user they have invalid input
    System.out.println("\nThat doesn't look right, try again.");
    validInput = false;
  }

  public static boolean checkUserInt(){ //checks for valid input (int)
    Scanner input = new Scanner(System.in);
    try{
        userInt = input.nextInt();
        return true;
      } catch (Exception e){
        userInt = 0;
        invalidInput();
        return false;
      }
  }

  public static void questionOne(){ //check if playing
    while (validInput == false){
      boolean validInt = false;
      while (validInt == false){
        System.out.println("\nNumber 1: Do you want to play?");
        validInt = yesOrNo();
      }
      if (userInt == 1){ 
        isPlaying = true;
        validInput = true;
        break;
        } else if (userInt == 2){
          goodbye(); //call goodbye method if they say no
        } else {
          invalidInput();
        }
    }
  }

  public static void questionTwo(){ //check if know how to play
    while (validInput == false){
      boolean validInt = false;
      while (validInt == false){
        System.out.println(TEXT_RESET + "\nNumber 2: Do you know how to play?");
        validInt = yesOrNo();
      }
      if (userInt == 1){ 
        validInput = true;
        break;
        } else if (userInt == 2){
          //make and call a method for explaining rules
          validInput = true;
          break;
        } else {
          invalidInput();
        }
    }
  }

  public static boolean yesOrNo(){ //tells user how to answer a yes or no question
    System.out.println( TEXT_RESET + "\nType " + TEXT_RED + "1 for yes" + TEXT_RESET + " or " + TEXT_RED + "2 for no.\n" + TEXT_YELLOW);
    return checkUserInt(); //calls check for valid input
  }

  public static boolean rollForFirst (){ //find who goes first by usual game rules
    System.out.println("Let's roll for it!" + TEXT_PURPLE + "\n\nI'll roll first!");
    int[] cpuRoll = rollDice(5); //call roll dice with 5 dice for both cpu and player
    int[] playerRoll = rollDice(5);
    printRoll(cpuRoll); //prints rolls
    System.out.println("That's what I got...\n" + TEXT_YELLOW);
    printRoll(playerRoll);
    System.out.println("And that's your roll!\n\n");
    return whoFirst(cpuRoll, playerRoll); //calls method for scores/values and who goes
  }

  public static boolean whoFirst (int[] rollOne, int[] rollTwo){ //called after roll, tally and return true or false based on if cpu goes first
    int cpuScore = 0;
    for (int i = 0; i < 5; i++){ //add up dice
      cpuScore = cpuScore + rollOne [i];
    }
    int playerScore = 0; 
    for (int j = 0; j < 5; j++){ //add up dice
      playerScore = playerScore + rollTwo [j];
    }
    System.out.println("You got " + playerScore + TEXT_RESET + " and " + TEXT_PURPLE + "I got " + cpuScore + " so...\n"); //prints scores
    if (cpuScore > playerScore){ //prints winner (of who goes first)
      System.out.println(TEXT_PURPLE + "I guess I go first.");
      return true;
    } else if (cpuScore == playerScore){
      System.out.println(TEXT_RESET + "it's a tie! You know what," + TEXT_YELLOW + " you go first."); //to avoid making it more complex and to be polite
      return false;
    } else {
      System.out.println(TEXT_YELLOW + "I guess you go first.");
      return false;
    }
  }

  public static void printRoll (int[] roll){ //prints ascii and int value for each die
    asciiArt(roll); //calls ascii art for the dice
    for (int i = 0; i < roll.length; i++){ //prints dice values
      System.out.print(roll[i] + " "); 
    }
    System.out.println("\n");
  }

  public static void asciiArt(int[] roll){ //prints the ascii art for any die
    for (int i = 0; i < roll.length; i++){
      System.out.println("+---------+"); //top of die
      switch (roll[i]){ 
        case 1: //prints a die showing 1
          System.out.println("|         |");
          System.out.println("|    o    |");
          System.out.println("|         |");
          break;

        case 2: //prints a die showing 2
          System.out.println("| o       |");
          System.out.println("|         |");
          System.out.println("|       o |");
          break;

        case 3: //prints a die showing 3
          System.out.println("| o       |");
          System.out.println("|    o    |");
          System.out.println("|       o |");
          break;
        
        case 4: //prints a die showing 4
          System.out.println("| o     o |");
          System.out.println("|         |");
          System.out.println("| o     o |");
          break;

        case 5: //prints a die showing 5
          System.out.println("| o     o |");
          System.out.println("|    o    |");
          System.out.println("| o     o |");
          break;

        case 6: //prints a die showing 6
          System.out.println("| o     o |");
          System.out.println("| o     o |");
          System.out.println("| o     o |");
          break;
      }
      System.out.println("+---------+\n"); //bottom of die
    }
  }

  /*public static void yahtzeeCheck(int[] roll){
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
  }*/

  public static void turns(boolean compFirst){ //keeps track of and calls turns
    for (int i = 0; i < 6; i++){ //6 turns for upper section 13 for all
      int[] cpuRoll = rollDice(5); //roll 5 dice for cpu
      int[] playerRoll = rollDice(5); //roll 5 dice for player
      if (compFirst == true){
        cpuTurn(cpuRoll); //calls cpu turn method
      }
        playerTurn(playerRoll); //calls player turn method
      if (compFirst == true){
        continue;
      }
        cpuTurn(cpuRoll); //calls cpu turn method
    }
  }

  public static void cpuTurn(int[] roll){
    System.out.println(TEXT_PURPLE + "\nOkay here's my final roll...");
    //yahtzeeCheck(roll);
    cpuReroll(roll);    
    printRoll(roll);
    System.out.println("I used it for my " + sectionsSimple[cpuKeep - 1]);
  }

  public static void cpuReroll(int[] roll){
    int rerollsUsed = 0;
    int score = 0;
    cpuKeep = -1;
    while (rerollsUsed < 3){
      int[] occurences = new int[6];
      for (int i = 0; i < 5; i++){
        occurences[roll[i] - 1]++;
      }    
      for (int k = 0; k < 6; k++){
        if (occurences[k] == 5){
          rerollsUsed = 3;
          break;
        }
        if (cpuKeep < 0 || occurences[k] > occurences[cpuKeep - 1]){
          if (cpuScore[k + 1] == "-"){
            cpuKeep = k + 1;
          }
        }
      }
      for (int l = 0; l < 5; l++){
        if (roll[l] != cpuKeep) {
          roll[l] = numGenerator.nextInt(6) + 1; 
        }
      }
      rerollsUsed++;
    }
  for (int i = 0; i < 5; i++){
    if (roll[i] == cpuKeep){
      score = score + cpuKeep;
    }
  } 
  cpuScore[cpuKeep] = String.valueOf(score);
}


  public static void playerTurn(int[] roll){
    System.out.println(TEXT_YELLOW + "\nHere's your starting roll...");
    printRoll(roll);
    int usedRerolls = 0;
    //yahtzeeCheck(roll);
    reroll(usedRerolls, roll);
    printScoreSheet(playerUsed, playerScore);
    slotToUse(roll);
  }

  public static void printScoreSheet(String[] used, String[] score){
    for (int i = 0; i < 8; i++) { //sections.length eventually 
      System.out.println(TEXT_RESET + "+---------------------------");
      System.out.println("|" + sections[i] + "\t" + TEXT_RED + used[i] + TEXT_RESET + "\t|\t" + score[i]);
      }
    System.out.println("+---------------------------\n\n");
  }

  public static void totalUpperSection (){
    int playerScoreCount = 0;
    int cpuScoreCount = 0;
    for (int i = 1; i <= 6; i++){
      playerScoreCount += Integer.parseInt(playerScore[i]);
      cpuScoreCount += Integer.parseInt(cpuScore[i]);
    }
    playerScore[7] = String.valueOf(playerScoreCount);
    cpuScore[7] = String.valueOf(cpuScoreCount);
  }

  public static void slotToUse(int[] roll){
    boolean usedAlready = true;
    while (usedAlready == true) {
    System.out.println("Type the number " + TEXT_RED + "(red)"+ TEXT_RESET+ " for the slot you want to use.\n" + TEXT_YELLOW);
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
    System.out.println("\nYou have a score of " + playerScore[userInt] + " for the " + sectionsSimple[userInt - 1] + " section on your score sheet now!");
    playerUsed[userInt] = "";
  }

  public static void reroll(int used, int[] roll){
    while (used < 3){
      System.out.println(TEXT_YELLOW + "\nYou have " + (3 - used) + " reroll(s) left.");
      System.out.println(TEXT_RESET + "\nDo you want to reroll?");
      yesOrNo();
      if (userInt == 1){
        rerollDice(roll);
        used++;
      } else if (userInt == 2){
        used = 3;
      }
    }
    //clear(2000);
    System.out.println(TEXT_YELLOW + "\nHere's your final roll.\n");
    printRoll(roll);
  }

  public static void rerollDice(int[] roll){
    System.out.println(TEXT_RESET + "\nWhich di(c)e do you want to reroll? \n\n" + TEXT_RED + "To select a die, type the value on the die and press enter. \n\nEnter a number higher than 6 to stop selecting dice unless you select all 5.\n" + TEXT_YELLOW);
    int[] reroll = new int[5];
    for (int i = 0; i < 5; i++){
      checkUserInt();
      if (userInt >= 7){ 
        break;
      }
      reroll[i] = userInt;
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
    System.out.println("Here's your new roll");
  }

  public static int[] rollDice(int dice){
    int[] diceArray = new int [dice];
    for (int i = 0; i < dice; i++){
      diceArray[i] = numGenerator.nextInt(6) + 1; 
    }
    return diceArray;
  }

  public static void printWinner (){
    int cpu = Integer.parseInt(cpuScore[7]);
    int player = Integer.parseInt(playerScore[7]);
    System.out.println("My total score was " + TEXT_PURPLE + cpu + TEXT_RESET + " and your total score was " + TEXT_YELLOW + player + TEXT_RESET + " so that means...\n\n");
    if (cpu > player){
      System.out.println(TEXT_PURPLE + "I won!");
    } else {
      System.out.println(TEXT_YELLOW + "You won!");
    }
  }

  public static void anotherGame (){
    System.out.println( TEXT_RESET + "\n\nWant to play again?");
    yesOrNo();
    if (userInt == 2){
      isPlaying = false;
    }
  }

  public static void goodbye (){
    System.out.print(TEXT_RESET + "");
    if (hasPlayed == true){
      System.out.println("\nWell it was nice playing with you, I hope you had fun!");
    } else {
      System.out.println("\nMaybe next time then!");
    }
    System.out.println("\nBye!");
    //clear(5000);
    System.exit(0);
  }

  public static void main(String[] args) {
    welcomeUser();
    while (isPlaying == true){
      hasPlayed = true;
      boolean cpuFirst = rollForFirst();
      //clear(3000);
      turns(cpuFirst);
      totalUpperSection();
      System.out.println(TEXT_PURPLE + "\nCOMPUTER");
      printScoreSheet (cpuUsed, cpuScore);
      System.out.println(TEXT_YELLOW + "PLAYER");
      printScoreSheet (playerUsed, playerScore);
      printWinner();
      anotherGame();
      }
    goodbye();  
    }
}