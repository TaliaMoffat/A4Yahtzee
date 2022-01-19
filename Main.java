import java.util.*;

class Main {

  //static variables
  Scanner input = new Scanner(System.in);
  boolean isPlaying = false;
  boolean validInput = false;
  int userInt = 0; 

  public static void welcomeUser(){
    System.out.println("Welcome to our Yahtzee game! We are so excited to play with you, but first you need to answer some questions.");
    questionOne();
    questionTwo();
    System.out.println("Okay! Let's play!");
  }

  public static void invalidInput(){
    System.out.println("That doesn't look right, try again.");
  }

  public void questionOne(){
    while (validInput = false){
      System.out.println("Number 1: Do you want to play? Type 1 for yes or 2 for no.");
      try{
        userInt = input.nextInt();
      } catch (NumberFormatException exception){
          invalidInput();
          continue;
      }
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

  public void questionTwo(){
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

  public static void main(String[] args) {
    welcomeUser();
    while (isPlaying = true){
    //call who goes first method
    //run turns
    //call calculating score method?
    //print winner and scores
    //offer another game
    }
    //good bye message
  }
}