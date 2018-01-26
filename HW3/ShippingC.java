// Jillian Fliedner
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

public class ShippingC {
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

    Schedule fin = getBestSchedule(changeA, changeB, changeC, a, b, c, 0, 0, 0, a.size() - 1);
    ArrayList<Character> schedule = fin.sch;
    for (int i = 0; i < schedule.size(); i++) {
      System.out.println(schedule.get(i));
    }
  }

  // ANY = 0, A = 1, B = 2, C =3
  // generates the best schedule with the correct start and end company,
  // using the range of indices given
  public static Schedule getBestSchedule(int changeA, int changeB, int changeC,
      ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> c, 
      int startCompany, int endCompany, int startIndex, int endIndex) {

    // pairings  
    int bestA = 0;
    int bestAA = 0;
    int bestAB = 0;
    int bestAC = 0;
    int bestBA = 0;
    int bestBB = 0;
    int bestBC = 0;
    int bestCA = 0;
    int bestCB = 0;
    int bestCC = 0;
    int bestB = 0;
    int bestC = 0;
    int min = 0;
 

    ArrayList<Character> schedule = new ArrayList<Character>();
    Schedule toReturn = new Schedule(schedule, 0);

    // base case of one day  
    if (startIndex == endIndex) {
      if (endCompany == 1) {
        bestA = a.get(startIndex);
        schedule.add('A');
        toReturn = new Schedule(schedule, bestA);
      }
      else if (endCompany == 2) {
        bestB = b.get(startIndex);
        schedule.add('B');
        toReturn = new Schedule(schedule, bestB);
      }
      else if (endCompany == 3) {
        bestC = c.get(startIndex);
        schedule.add('C');
        toReturn = new Schedule(schedule, bestC);
      }
      else {
        if (startCompany == 1) {
          bestA = a.get(startIndex);
          schedule.add('A');
          toReturn = new Schedule(schedule, bestA);
        }
        else if (startCompany == 2) {
          bestB = b.get(startIndex);
          schedule.add('B');
          toReturn = new Schedule(schedule, bestB);
        }
        else {
          bestC = c.get(startIndex);
          schedule.add('C');
          toReturn = new Schedule(schedule, bestC);
        }
      }
    }




    // comparing pairs, looking at the start and end company passed in, finding the min  
    else if (endIndex - startIndex == 1) {
      if (startCompany == 0 && endCompany == 0) {
        bestAA = a.get(startIndex) + a.get(endIndex);
        bestAB = a.get(startIndex) + b.get(endIndex) + changeB;
        bestAC = a.get(startIndex) + c.get(endIndex) + changeC;

        bestBA = b.get(startIndex) + a.get(endIndex) + changeA;
        bestBB = b.get(startIndex) + b.get(endIndex);
        bestBC = b.get(startIndex) + c.get(endIndex) + changeB;

        bestCA = c.get(startIndex) + a.get(endIndex) + changeA;
        bestCB = c.get(startIndex) + b.get(endIndex) + changeB;
        bestCC = c.get(startIndex) + c.get(endIndex);

        bestA = Math.min(Math.min(bestAA, bestAB), bestAC);
        bestB = Math.min(Math.min(bestBA, bestBB), bestBC);
        bestC = Math.min(Math.min(bestCA, bestCB), bestCC);

        min = Math.min(Math.min(bestA, bestB), bestC);

        if (min == bestA) {
          if (bestA == bestAA) {
            schedule.add('A');
            schedule.add('A');
            toReturn = new Schedule(schedule, bestAA);
          }
          else if (bestA == bestAB) {
            schedule.add('A');
            schedule.add('B');
            toReturn = new Schedule(schedule, bestAB);
          }
          else {
            schedule.add('A');
            schedule.add('C');
            toReturn = new Schedule(schedule, bestAC);
          }
        }
        else if (min == bestB) {
          if (bestB == bestBA) {
            schedule.add('B');
            schedule.add('A');
            toReturn = new Schedule(schedule, bestBA);
          }
          else if (bestB == bestBB) {
            schedule.add('B');
            schedule.add('B');
            toReturn = new Schedule(schedule, bestBB);
          }
          else if (bestB == bestBC) {
            schedule.add('B');
            schedule.add('C');
            toReturn = new Schedule(schedule, bestBC);
          }

        }
        else {
          if (bestC == bestCA) {
            schedule.add('C');
            schedule.add('A');
            toReturn = new Schedule(schedule, bestCA);
          }
          else if (bestC == bestCB) {
            schedule.add('C');
            schedule.add('B');
            toReturn = new Schedule(schedule, bestCB);
          }
          else {
            schedule.add('C');
            schedule.add('C');
            toReturn = new Schedule(schedule, bestCC);
          }
        }
      }
      else if (startCompany == 1 && endCompany == 0) {
        bestAA = a.get(startIndex) + a.get(endIndex);
        bestAB = a.get(startIndex) + b.get(endIndex) + changeB;
        bestAC = a.get(startIndex) + c.get(endIndex) + changeC;

        bestA = Math.min(Math.min(bestAA, bestAB), bestAC);
        if (bestA == bestAA) {
          schedule.add('A');
          schedule.add('A');
          toReturn = new Schedule(schedule, bestAA);
        }
        else if (bestA == bestAB) {
          schedule.add('A');
          schedule.add('B');
          toReturn = new Schedule(schedule, bestAB);
        }
        else {
          schedule.add('A');
          schedule.add('C');
          toReturn = new Schedule(schedule, bestAC);
        }
      }
      else if (startCompany == 1 && endCompany == 1) {
        schedule.add('A');
        schedule.add('A');
        bestAA = a.get(startIndex) + a.get(endIndex);
        toReturn = new Schedule(schedule, bestAA);
      }
      else if (startCompany == 1 && endCompany == 2) {
        schedule.add('A');
        schedule.add('B');
        bestAB = a.get(startIndex) + b.get(endIndex) + changeB;
        toReturn = new Schedule(schedule, bestAB);
      }
      else if (startCompany == 1 && endCompany == 3) {
        schedule.add('A');
        schedule.add('C');
        bestAC = a.get(startIndex) + c.get(endIndex) + changeC;
        toReturn = new Schedule(schedule, bestAC);
      }
      else if (startCompany == 2 && endCompany == 0) {
        bestBA = b.get(startIndex) + a.get(endIndex) + changeA;
        bestBB = b.get(startIndex) + b.get(endIndex);
        bestBC = b.get(startIndex) + c.get(endIndex) + changeC;

        bestB = Math.min(Math.min(bestBA, bestBB), bestBC); 
        if (bestB == bestBA) {
          schedule.add('B');
          schedule.add('A');
          toReturn = new Schedule(schedule, bestBA);
        }
        else if (bestB == bestBB) {
          schedule.add('B');
          schedule.add('B');
          toReturn = new Schedule(schedule, bestBB);
        }
        else {
          schedule.add('B');
          schedule.add('C');
          toReturn = new Schedule(schedule, bestBC);
        }
      }

      else if (startCompany == 2 && endCompany == 1) {
        schedule.add('B');
        schedule.add('A');
        bestBA = b.get(startIndex) + a.get(endIndex) + changeA;
        toReturn = new Schedule(schedule, bestBA);
      }
      else if (startCompany == 2 && endCompany == 2) {
        schedule.add('B');
        schedule.add('B');
        bestBB = b.get(startIndex) + b.get(endIndex);
        toReturn = new Schedule(schedule, bestBB);
      }
      else if (startCompany == 2 && endCompany == 3) {
        schedule.add('B');
        schedule.add('C');
        bestBC = b.get(startIndex) + c.get(endIndex) + changeC;
      }
      else if (startCompany == 3 && endCompany == 0) {
        bestCA = c.get(startIndex) + a.get(endIndex) + changeA;
        bestCB = c.get(startIndex) + b.get(endIndex) + changeB;
        bestCC = c.get(startIndex) + c.get(endIndex);

        bestC = Math.min(Math.min(bestCA, bestCB), bestCC);
        if (bestC == bestCA) {
          schedule.add('C');
          schedule.add('A');
          toReturn = new Schedule(schedule, bestCA);
        }
        else if (bestC == bestCB) {
          schedule.add('C');
          schedule.add('B');
          toReturn = new Schedule(schedule, bestCB);
        }
        else {
          schedule.add('C');
          schedule.add('C');
          toReturn = new Schedule(schedule, bestCC);
        }

      }
      else if (startCompany == 3 && endCompany == 1) {
        schedule.add('C');
        schedule.add('A');
        bestCA = c.get(startIndex) + a.get(endIndex) + changeA;
        toReturn = new Schedule(schedule, bestCA);
      }
      else if (startCompany == 3 && endCompany == 2) {
        schedule.add('C');
        schedule.add('B');
        bestCB = c.get(startIndex) + b.get(endIndex) + changeB;
        toReturn = new Schedule(schedule, bestCB);
      }
      else if (startCompany == 3 && endCompany == 3) {
        schedule.add('C');
        schedule.add('C');
        bestCC = c.get(startIndex) + c.get(endIndex);
        toReturn = new Schedule(schedule, bestCC);

      }
      else if (startCompany == 0 && endCompany == 1) {
        bestAA = a.get(startIndex) + a.get(endIndex);
        bestBA = b.get(startIndex) + a.get(endIndex) + changeA;
        bestCA = c.get(startIndex) + a.get(endIndex) + changeA;

        min = Math.min(Math.min(bestAA, bestBA), bestCA);
        if (min == bestAA) {
          schedule.add('A');
          schedule.add('A');
          toReturn = new Schedule(schedule, bestAA);
        }
        else if (min == bestBA) {
          schedule.add('B');
          schedule.add('A');
          toReturn = new Schedule(schedule, bestBA);
        }
        else {
          schedule.add('C');
          schedule.add('A');
          toReturn = new Schedule(schedule, bestCA);
        }


      }
      else if (startCompany == 0 && endCompany == 2) {
        bestAB = a.get(startIndex) + b.get(endIndex) + changeB;
        bestBB = b.get(startIndex) + b.get(endIndex);
        bestCB = c.get(startIndex) + b.get(endIndex) + changeB;

        min = Math.min(Math.min(bestAB, bestBB), bestCB);
        if (min == bestAB) {
          schedule.add('A');
          schedule.add('B');
          toReturn = new Schedule(schedule, bestAB);
        }
        else if (min == bestBB) {
          schedule.add('B');
          schedule.add('B');
          toReturn = new Schedule(schedule, bestBB);
        }
        else {
          schedule.add('C');
          schedule.add('B');
          toReturn = new Schedule(schedule, bestCB);
        }
      }
      else if (startCompany == 0 && endCompany == 3) {
        bestAC = a.get(startIndex) + c.get(endIndex) + changeC;
        bestBC = b.get(startIndex) + c.get(endIndex) + changeC;
        bestCC = c.get(startIndex) + c.get(endIndex);

        min = Math.min(Math.min(bestAC, bestBC), bestCC);

        if (min == bestAC) {
          schedule.add('A');
          schedule.add('C');
          toReturn = new Schedule(schedule, bestAC);
        }
        else if (min == bestBC) {
          schedule.add('B');
          schedule.add('C');
          toReturn = new Schedule(schedule, bestBC);
        }
        else {
          schedule.add('C');
          schedule.add('C');
          toReturn = new Schedule(schedule, bestCC);
        }
      }
    } 

    // recursive case
    else {
      int split = ((endIndex - startIndex) / 2) + startIndex;
      // first half of the schedule  
      Schedule firA = getBestSchedule(changeA, changeB, changeC, a, b, c, 0, 1, startIndex, split);
      Schedule firB = getBestSchedule(changeA, changeB, changeC, a, b, c, 0, 2, startIndex, split);
      Schedule firC = getBestSchedule(changeA, changeB, changeC, a, b, c, 0, 3, startIndex, split);

      // second half of the schedule  
      Schedule endA = getBestSchedule(changeA, changeB, changeC, a, b, c, 1, 0, split + 1, endIndex);
      Schedule endB = getBestSchedule(changeA, changeB, changeC, a, b, c, 2, 0, split + 1, endIndex);
      Schedule endC = getBestSchedule(changeA, changeB, changeC, a, b, c, 3, 0, split + 1, endIndex);

      // finding the best pair
      int costAA = firA.cost + endA.cost;
      int costBA = firB.cost + endA.cost + changeA;
      int costCA = firC.cost + endA.cost + changeA;
      int costAB = firA.cost + endB.cost + changeB;
      int costBB = firB.cost + endB.cost;
      int costCB = firC.cost + endB.cost + changeB;
      int costAC = firA.cost + endC.cost + changeC;
      int costBC = firB.cost + endC.cost + changeC;
      int costCC = firC.cost + endC.cost;


      bestA = Math.min(Math.min(costAA, costBA), costCA);
      bestB = Math.min(Math.min(costAB, costBB), costCB);
      bestC = Math.min(Math.min(costAC, costBC), costCC);

      min = Math.min(Math.min(bestA, bestB), bestC);

      // creating schedule to return based on min  
      if (min == bestA) {
        if (bestA == costAA) {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firA.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endA.sch.get(i));
          }

          toReturn = new Schedule(schedule, firA.cost + endA.cost);
        }
        else if (bestA == costBA) {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firB.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endA.sch.get(i));
          }

          toReturn = new Schedule(schedule, firB.cost + endA.cost);
        }
        else {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firC.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endA.sch.get(i));
          }

          toReturn = new Schedule(schedule, firC.cost + endA.cost);
        }
      }
      else if (min == bestB) {
        if (bestB == costAB) {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firA.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endB.sch.get(i));
          }

          toReturn = new Schedule(schedule, firA.cost + endB.cost);
        }
        else if (bestB == costBB) {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firB.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endB.sch.get(i));
          }

          toReturn = new Schedule(schedule, firB.cost + endB.cost);
        }
        else {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firC.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endB.sch.get(i));
          }

          toReturn = new Schedule(schedule, firC.cost + endB.cost);
        }
      }
      else {
        if (bestC == costAC) {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firA.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endC.sch.get(i));
          }

          toReturn = new Schedule(schedule, firA.cost + endC.cost);
        }
        else if (bestC == costBC) {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firB.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endC.sch.get(i));
          }

          toReturn = new Schedule(schedule, firB.cost + endC.cost);
        }
        else {
          for (int j = 0; j < firA.sch.size(); j++) {
            schedule.add(firC.sch.get(j));
          }
          for (int i = 0; i < endA.sch.size(); i++) {
            schedule.add(endC.sch.get(i));
          }

          toReturn = new Schedule(schedule, firC.cost + endC.cost);
        }
      }
    }

    return toReturn;
  }

}

// to represent a Schedule with a list of characters and a total cost
class Schedule {
  ArrayList<Character> sch = new ArrayList<Character>();
  int cost = 0;

  Schedule(ArrayList<Character> sch, int cost) {
    this.sch = sch;
    this.cost = cost;
  }

}





