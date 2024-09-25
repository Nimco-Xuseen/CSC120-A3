import java.util.Scanner;
import java.util.Random; 

public class Conversation {

  public static void main(String[] args) {
    // You will start the conversation here.
    Scanner input = new Scanner(System.in);

    // Asking user for the number of conversation rounds
    System.out.print("How many rounds?");
    int rounds = input.nextInt();
    input.nextLine();

    //print initial greeting
    System.out.println("Hi there! Mind sharing what is on your mind?");

    //Storung the conversation transcript
    StringBuilder transcript = new StringBuilder();
    transcript.append("Hi there! Mind sharing what is on your mind?\n");

    //responses
    String[] cannedResponses = {
      "Interesting, tell me more.",
      "I'm sorry, I didn't understand that",
      "It will be alright!",
      "Thank you for sharing!",
      "Goodbye!"
    };

    // number of rounds
    for (int i = 0; i< rounds; i++ ) {
      // Get user unput
      String userInput = input.nextLine();
      transcript.append(userInput).append("\n");

      //Mirror response logic
      String response = generateResponse(userInput, cannedResponses);
      System.out.println(response);
      transcript.append(response).append("\n");
    }

    //Goodbye message 
    System.out.println("Okay, Have a good day!");
    transcript.append("Okay, Have a good day!\n");

    // Print transcript
    System.out.println("\nTRANSCRIPT:");
    System.out.println(transcript.toString());

    input.close();

  }

  // Function to generate the chatbot's response
  public static String generateResponse(String userInput, String[] cannedResponses) {
    // Words to be mirrored
    String[][] mirrorWords = {
        {"I am", "You are"},
        {"me", "You"},
        {"am", "are"},
        {"You", "I"},
        {"my", "your"},
        {"your", "my"}
    };

    //split user input into words
    String[] words = userInput.split("\\s+");

    //Checking if we need to mirror any words
    boolean mirrored = false; 
    for (int i=0; i < words.length; i++){
      for (String[] pair : mirrorWords) {
        if (words[i].equalsIgnoreCase(pair[0])) {
          words[i] = pair[1];
          mirrored = true;
        } else if (words[i].equalsIgnoreCase(pair[1])) {
            words[i] = pair[0];
            mirrored = true;

        }
      }
    }

    
    // If mirrored, return the mirrored sentence
    if (mirrored) {
      return String.join(" ", words) + "?";
  } else {
      // Otherwise, return a random canned response
      Random rand = new Random();
      return cannedResponses[rand.nextInt(cannedResponses.length)];
  }
}
}

