// Jillian Fliedner
import java.util.Scanner;
import java.util.ArrayList;


public class ShippingA {

  public static void main(String[] args) {
    Scanner myScanner = new Scanner(System.in);
    ArrayList<Integer> a = new ArrayList<Integer>();
    ArrayList<Integer> b = new ArrayList<Integer>();
    ArrayList<Integer> c = new ArrayList<Integer>();
    
    int changeA = 0;
    int changeB = 0;
    int changeC = 0;
    
    String firstLine = myScanner.nextLine();
    String[] inputParts = firstLine.split(",");
    changeA = Integer.parseInt(inputParts[0]);
    changeB = Integer.parseInt(inputParts[1]);
    changeC = Integer.parseInt(inputParts[2]);
    
    String costsForA = myScanner.nextLine().substring(2);
    String[] inputA = costsForA.split(",");
    for (String s : inputA) {
      a.add(Integer.parseInt(s));
    
    }
    
    String costsForB = myScanner.nextLine().substring(2);
    String[] inputB = costsForB.split(",");
    for (String s : inputB) {
      b.add(Integer.parseInt(s));
    }
    
    String costsForC = myScanner.nextLine().substring(2);
    String[] inputC = costsForC.split(",");
    for (String s : inputC) {
      c.add(Integer.parseInt(s));
    }
    
    scheduleDays(a, b, c, changeA, changeB, changeC, a.size());
    
  }
  
  public static void scheduleDays(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> c,
      int changeA, int changeB, int changeC, int days) {
    
    char currentComp = ' ';
    int possibleChangeFee = 0;
    Integer costA = 0;
    Integer costB = 0;
    Integer costC = 0;
    ArrayList<Character> schedule = new ArrayList<Character>();
    for (int i = 0; i < days; i++) {
      if (schedule.isEmpty()) {
        costA = a.get(i);
        costB = b.get(i);
        costC = c.get(i);
        
      }
      else {
        costA = a.get(i);
        costB = b.get(i);
        costC = c.get(i);
        if (currentComp != 'a') {
          costA = costA + possibleChangeFee;
          if (currentComp != 'b') {
            costB = costB + possibleChangeFee;
          }
          else {
            costC = costC + possibleChangeFee;
          }
        }
        else {
          if (currentComp != 'b') {
            costB = costB + possibleChangeFee;
          }
          else {
            costC = costC + possibleChangeFee;
          }
        }
      }
        if (costA <= costB && costA <= costC) {
          currentComp = 'a';
          possibleChangeFee = changeA;
          schedule.add('A');
        }
        else if (costB <= costA && costB <= costC) {
          currentComp = 'b';
          possibleChangeFee = changeB;
          schedule.add('B');
        }
        else if (costC <= costA && costC <= costB) {
          currentComp = 'c';
          possibleChangeFee = changeC;
          schedule.add('C');
        }
      }
    for (int i = 0; i < schedule.size(); i++) {
      System.out.println(schedule.get(i));
    }
  
    
    
  }
}
