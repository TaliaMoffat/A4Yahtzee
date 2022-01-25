/*Yahtzee 
Talia Moffat
Started Jan. 19th 
Handed in Jan 24th
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

  //not fully sure why but this variable needs to be here to work
  static int cpuKeep = -1;

  //sections on score sheet for output (move to a score sheet method?)
  static String[] sections = {"UPPER SECTION", "Aces\t\t", "Twos\t\t", "Threes\t\t", "Fours\t\t", "Fives\t\t", "Sixes\t\t", "TOTAL SCORE", "BONUS\t\t", "TOTAL\t\t", "LOWER SECTION", "3 of a kind", "4 of a kind", "Full House\t", "Small Straight", "Large Straight", "YAHTZEE\t", "Chance\t\t", "YAHTZEE BONUS", "TOTAL - Lower", "TOTAL - Upper", "GRAND TOTAL"};
  //for use in output
  static String[] sectionsSimple = {"aces", "twos", "threes", "fours", "fives", "sixes"};

  //scores of cpu and player for score sheet
  static String[] playerScore = {"", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
  static String[] cpuScore = {"", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};

  //Which sections used for both cpu and player
  static String[] playerUsed = {"", "1", "2", "3", "4", "5", "6", "", "", "", "", "7", "8", "9", "10", "11", "12", "13", "", "", "", ""};
  static String[] cpuUsed = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}; //needed due to the score sheet method

  public static void welcomeUser(){ //welcomes user, calls question methods
    System.out.println("\nWelcome to our Yahtzee game! We are so excited to play with you! \n\nBut first you need to answer some questions.");
    questionOne(); //call question 1
    validInput = false; //reset boolean
    questionTwo(); //call question 2
    System.out.println(TEXT_RESET + "\nOkay! Let's play!\n"); 
    //clear(2000); //call clear console method
    } //closes welcome user method

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
    validInput = false; //sets static variable to false
  } //closes invalid input method

  public static boolean checkUserInt(){ //checks for valid input (int)
    Scanner input = new Scanner(System.in);
    try{ //see if user input can be an int
        userInt = input.nextInt();
        return true;
      } catch (Exception e){ //if not, reset user int to valid number, call invalid input method, return false
        userInt = 0;
        invalidInput();
        return false;
      }
  } //close checkUserInt method

  public static void questionOne(){ //check if playing
    while (validInput == false){
      boolean validInt = false;
      while (validInt == false){
        System.out.println("\nNumber 1: Do you want to play?");
        validInt = yesOrNo();
      }
      if (userInt == 1){ //user indicates yes
        isPlaying = true;
        validInput = true;
        break;
        } else if (userInt == 2){
          goodbye(); //call goodbye method if they say no
        } else {
          invalidInput(); //call invalid input method
        }
    }
  } //closes question one method

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
  } //closes question two method

  public static boolean yesOrNo(){ //tells user how to answer a yes or no question
    System.out.println( TEXT_RESET + "\nType " + TEXT_RED + "1 for yes" + TEXT_RESET + " or " + TEXT_RED + "2 for no.\n" + TEXT_YELLOW);
    return checkUserInt(); //calls check for valid input
  } //closes yes or no method

  public static boolean rollForFirst (){ //find who goes first by usual game rules
    System.out.println("Let's roll for it!" + TEXT_PURPLE + "\n\nI'll roll first!");
    int[] cpuRoll = rollDice(5); //call roll dice with 5 dice for both cpu and player
    int[] playerRoll = rollDice(5);
    printRoll(cpuRoll); //prints roll
    System.out.println("That's what I got...\n" + TEXT_YELLOW);
    printRoll(playerRoll); //prints roll
    System.out.println("And that's your roll!\n\n");
    return whoFirst(cpuRoll, playerRoll); //calls method for scores/values and who goes to return if cpu goes first
  } //closes roll for first method

  public static boolean whoFirst (int[] rollOne, int[] rollTwo){ //called after roll, tally and return true or false based on if cpu goes first
    int cpuScore = 0; //initialize score as zero
    for (int i = 0; i < 5; i++){ //add up dice
      cpuScore = cpuScore + rollOne [i];
    }
    //same as above but for player
    int playerScore = 0; 
    for (int j = 0; j < 5; j++){ 
      playerScore = playerScore + rollTwo [j];
    }
    System.out.println("You got " + playerScore + TEXT_RESET + " and " + TEXT_PURPLE + "I got " + cpuScore + " so...\n"); //prints scores
    if (cpuScore > playerScore){ //prints winner (of who goes first)
      System.out.println(TEXT_PURPLE + "I guess I go first.");
      return true; //returns true so that cpu goes first
    } else if (cpuScore == playerScore){
      System.out.println(TEXT_RESET + "it's a tie! You know what," + TEXT_YELLOW + " you go first."); //to avoid making it more complex and to be polite
      return false; //return false b/c player goes first
    } else {
      System.out.println(TEXT_YELLOW + "I guess you go first.");
      return false; //return false so player goes first
    }
  } //closes who first method

  public static void printRoll (int[] roll){ //prints ascii and int value for each die
    asciiArt(roll); //calls ascii art for the dice
    for (int i = 0; i < roll.length; i++){ //prints dice values
      System.out.print(roll[i] + " "); 
    }
    System.out.println("\n");
  } //closes print roll method

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
  } //closes ascii art method

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

  public static void cpuTurn(int[] roll){ //runs cpu turn
    System.out.println(TEXT_PURPLE + "\nOkay here's my final roll...");
    //yahtzeeCheck(roll);
    cpuReroll(roll); //calls reroll method    
    printRoll(roll); //prints final roll of cpu
    System.out.println("I used it for my " + sectionsSimple[cpuKeep - 1]); //declares which section has been used
  } //closes cpu turn method

  public static void cpuReroll(int[] roll){ //pretty much all of cpu Ai
    int rerollsUsed = 0; //sets rerolls used to zero
    int score = 0; //sets score to zero
    cpuKeep = -1; //sets the value it keeps to impossible value to start
    while (rerollsUsed < 3){ //runs while there are rerolls left
      int[] occurences = new int[6];
      for (int i = 0; i < 5; i++){ //determine occurances of each value
        occurences[roll[i] - 1]++;
      }    
      for (int k = 0; k < 6; k++){
        if (occurences[k] == 5){ //to avoid unessecary rerolls
          rerollsUsed = 3;
          break;
        }
        if (cpuKeep < 0 || occurences[k] > occurences[cpuKeep - 1]){ //set keep to most common value 
          if (cpuScore[k + 1] == "-"){ //but only if score slot is not used
            cpuKeep = k + 1;
          }
        }
      }
      for (int l = 0; l < 5; l++){ //rerolls anything that's not the keep value
        if (roll[l] != cpuKeep) {
          roll[l] = numGenerator.nextInt(6) + 1; 
        }
      }
      rerollsUsed++; //ups rerolls used
    }
  for (int i = 0; i < 5; i++){ //calculates score
    if (roll[i] == cpuKeep){
      score = score + cpuKeep;
    }
  } 
  cpuScore[cpuKeep] = String.valueOf(score);
} //closes cpu rerolls method


  public static void playerTurn(int[] roll){ //runs player turn
    System.out.println(TEXT_YELLOW + "\nHere's your starting roll...");
    printRoll(roll); //prints starting roll
    int usedRerolls = 0;
    //yahtzeeCheck(roll);
    reroll(usedRerolls, roll); //runs player rerolls
    printScoreSheet(playerUsed, playerScore); //prints score sheet
    slotToUse(roll); //lets them choose which slot
  } //closes player turn method

  public static void printScoreSheet(String[] used, String[] score){ //prints score sheet for either cpu or player depending on given values
    for (int i = 0; i < 8; i++) { //sections.length eventually 
      System.out.println(TEXT_RESET + "+---------------------------");
      System.out.println("|" + sections[i] + "\t" + TEXT_RED + used[i] + TEXT_RESET + "\t|\t" + score[i]);
      }
    System.out.println("+---------------------------\n\n");
  }//closes print score method

  public static void totalUpperSection (){ //calculates scores for upper section
    int playerScoreCount = 0;
    int cpuScoreCount = 0;
    for (int i = 1; i <= 6; i++){ //adds up scores
      playerScoreCount += Integer.parseInt(playerScore[i]);
      cpuScoreCount += Integer.parseInt(cpuScore[i]);
    }
    playerScore[7] = String.valueOf(playerScoreCount); //sets total on score sheet
    cpuScore[7] = String.valueOf(cpuScoreCount);
  } //closes total upper section method

  public static void slotToUse(int[] roll){ //allows user to select which slot to use thier roll in
    boolean usedAlready = true;
    while (usedAlready == true) { //does not let user reuse slots
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
     for (int i = 0; i < 5; i++){ //finds score of roll
      if (roll[i] == userInt){
        score = score + userInt;
      }
      playerScore[userInt] = String.valueOf(score);
    }
    System.out.println("\nYou have a score of " + playerScore[userInt] + " for the " + sectionsSimple[userInt - 1] + " section on your score sheet now!");
    playerUsed[userInt] = ""; //informs user where they uses their roll and for how much
  } //closes slot to use method

  public static void reroll(int used, int[] roll){ //runs rerolls for player
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
    printRoll(roll); //prints final roll
  } //closes reroll method

  public static void rerollDice(int[] roll){ //allows user to select individual dice and rerolls them
    System.out.println(TEXT_RESET + "\nWhich di(c)e do you want to reroll? \n\n" + TEXT_RED + "To select a die, type the value on the die and press enter. \n\nEnter a number higher than 6 to stop selecting dice unless you select all 5.\n" + TEXT_YELLOW);
    int[] reroll = new int[5];
    for (int i = 0; i < 5; i++){ //lets user input numbers less than 7 at most 5 times
      checkUserInt();
      if (userInt >= 7){ 
        break;
      }
      reroll[i] = userInt;
    }
    for (int j = 0; j < 5; j++){ //rerolls dice
      for (int k = 0; k < 5; k++){
        if (reroll[j] == roll[k]){
          int l = numGenerator.nextInt(6) + 1; 
          roll[k] = l;
          reroll[j] = 0;
        }
      }
    }
    System.out.print(TEXT_YELLOW);
    printRoll(roll); //prints latest reroll
    System.out.println("Here's your new roll");
  } //closes reroll dice method

  public static int[] rollDice(int dice){ //rolls a given amount of dice
    int[] diceArray = new int [dice];
    for (int i = 0; i < dice; i++){
      diceArray[i] = numGenerator.nextInt(6) + 1; 
    }
    return diceArray; //returns dice in an array
  } //closes roll dice method

  public static void printWinner (){ //print who won 
    int cpu = Integer.parseInt(cpuScore[7]);
    int player = Integer.parseInt(playerScore[7]);
    System.out.println("My total score was " + TEXT_PURPLE + cpu + TEXT_RESET + " and your total score was " + TEXT_YELLOW + player + TEXT_RESET + " so that means...\n\n");
    if (cpu > player){ //prints different statement depending on who had higher score
      System.out.println(TEXT_PURPLE + "I won!");
    } else {
      System.out.println(TEXT_YELLOW + "You won!");
    }
  } //closes print winner method

  public static void anotherGame (){ //asks user if they want to play again?
    System.out.println( TEXT_RESET + "\n\nWant to play again?");
    yesOrNo(); //calls yes or no method
    if (userInt == 2){
      isPlaying = false;
    }
  } //closes another game method

  public static void goodbye (){ //method to say goodbye to user and end program
    System.out.print(TEXT_RESET + "");
    if (hasPlayed == true){ //print different statement depending on if they've played or not
      System.out.println("\nWell it was nice playing with you, I hope you had fun!");
    } else {
      System.out.println("\nMaybe next time then!");
    }
    System.out.println("\nBye!");
    //clear(5000);
    System.exit(0); //ends program //unessecary?
  } //closes goodbye method

  public static void main(String[] args) {
    welcomeUser(); //call welcome user
    while (isPlaying == true){ //only if player has said they want to play
      hasPlayed = true;
      boolean cpuFirst = rollForFirst(); //determine who rolls first
      //clear(3000);
      turns(cpuFirst); //calls turns with a boolean for if the cpu goes first or not
      totalUpperSection(); //totals upper section
      //prints end score sheets for cpu and player
      System.out.println(TEXT_PURPLE + "\nCOMPUTER");
      printScoreSheet (cpuUsed, cpuScore);
      System.out.println(TEXT_YELLOW + "PLAYER");
      printScoreSheet (playerUsed, playerScore);
      printWinner(); //calls print winner method
      anotherGame(); //offers another game in the another game method called
      }
    goodbye(); //calls goodbye method to end program and say goodbye  
    }
}//closes main method