// Jillian Fliedner

import java.util.ArrayList;
import java.util.Scanner;

class ShippingF {
    // parsing and calling schedule method
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

  public static void scheduleDays(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> c, int changeA,
      int changeB, int changeC, int days) {

    char currentComp = ' ';
    int possibleChangeFee = 0;
     // min of each
    int bestA = 0;
    int bestB = 0;
    int bestC = 0;
    int currentCost = 0;
   
    int min = 0;
      // possible combinations to find minimum
    int bestAA = 0;
    int bestAB = 0;
    int bestAC = 0;
    int bestBA = 0;
    int bestBB = 0;
    int bestBC = 0;
    int bestCA = 0;
    int bestCB = 0;
    int bestCC = 0;
  
    Character[] finalSchedule = new Character[a.size()];
  
      // Nx3 table of optimal schedules
    ArrayList<ArrayList<Integer>> optimal = new ArrayList<ArrayList<Integer>>();
    optimal.add(new ArrayList<Integer>());
    optimal.add(new ArrayList<Integer>());
    optimal.add(new ArrayList<Integer>());
    
      // Nx3 table of companies used in optimal
    ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
    current.add(new ArrayList<Integer>());
    current.add(new ArrayList<Integer>());
    current.add(new ArrayList<Integer>());
    
      // first day
    bestA = a.get(0);
    bestB = b.get(0);
    bestC = c.get(0);
    current.get(0).add(0);
    current.get(1).add(1);
    current.get(2).add(2);
    
      // creating the Nx3 table
    for (int i = 1; i < days; i++) {
      bestAA = a.get(i - 1) + a.get(i);
      bestAB = b.get(i - 1) + changeA + a.get(i);
      bestAC = c.get(i - 1) + changeA + a.get(i); // min of A
      
      bestA = Math.min(Math.min(bestAA, bestAB), bestAC);
      
      bestBA = a.get(i - 1) + changeB + b.get(i); // min of B
      bestBB = b.get(i - 1) + b.get(i); 
      bestBC = c.get(i - 1) + changeB + b.get(i);
      
      bestB = Math.min(Math.min(bestBA, bestBB), bestBC);
      
      bestCA = a.get(i - 1) + changeC + c.get(i);
      bestCB = b.get(i - 1) + changeC + c.get(i);
      bestCC = c.get(i - 1) + c.get(i); // min of C
      
      bestC = Math.min(Math.min(bestCA, bestCB), bestCC);
      
      optimal.get(0).add(bestA); // add to the table
      optimal.get(1).add(bestB);
      optimal.get(2).add(bestC);
      
      if (bestA == bestAA) { // updating current based on what's added to optimal
        current.get(0).add(0);
        if (bestB == bestBA) {
          current.get(1).add(0);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
        else if (bestB == bestBB) {
          current.get(1).add(1);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
        else {
          current.get(1).add(2);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
      }
      else if (bestA == bestAB) {
        current.get(0).add(1);
        if (bestB == bestBA) {
          current.get(1).add(0);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);            
          }
          else {
            current.get(2).add(2);
          }
        }
        else if (bestB == bestBB) {
          current.get(1).add(1);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
        else {
          current.get(1).add(2);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
      }
      else {
        current.get(0).add(2);
        if (bestB == bestBA) {
          current.get(1).add(0);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
        else if (bestB == bestBB) {
          current.get(1).add(1);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
        else {
          current.get(1).add(2);
          if (bestC == bestCA) {
            current.get(2).add(0);
          }
          else if (bestC == bestCB) {
            current.get(2).add(1);
          }
          else {
            current.get(2).add(2);
          }
        }
      }
      
      
    }
    
      bestA = optimal.get(0).get(days - 2);
      bestB = optimal.get(1).get(days - 2);
      bestC = optimal.get(2).get(days - 2); // finding least cost on last day
      
      min = Math.min(Math.min(bestA, bestB), bestC);
      // adding to the final schedule based on pattern from current
      if (min == bestA) { 
        finalSchedule[days - 1] = 'A';
        for (int j = days - 1; j >= 1; j--) {
          if (current.get(0).get(j) == 0) {
            finalSchedule[j - 1] = 'A';
            }
          else if (current.get(0).get(j) == 1) {
            finalSchedule[j - 1] = 'B';
          }
          else {
            finalSchedule[j - 1] = 'C';
          }
        }
      }
      else if (min == bestB) {
        finalSchedule[days - 1] = 'B';
        for (int j = days - 1; j >= 1; j--) {
          if (current.get(1).get(j) == 0) {
            finalSchedule[j - 1] = 'A';
            }
          else if (current.get(1).get(j) == 1) {
            finalSchedule[j - 1] = 'B';
          }
          else {
            finalSchedule[j - 1] = 'C';
          }
        }
      }
      else {
        finalSchedule[days - 1] = 'C';
        for (int j = days - 1; j >= 1; j--) {
          if (current.get(2).get(j) == 0) {
            finalSchedule[j - 1] = 'A';
            }
          else if (current.get(1).get(j) == 1) {
            finalSchedule[j - 1] = 'B';
          }
          else {
            finalSchedule[j - 1] = 'C';
          }
        }
      }
   for (int k = 0; k < finalSchedule.length; k++) {
     System.out.println(finalSchedule[k]);
   }
  }
}
    
    

   


       













