import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Developing: fix syntax errors, so it compiles<br>
 * Approaching Proficiency: all of the above and make if work as intended. <br>
 *    (by debugging and fixing logical errors, ie should not be rewriting large portions of code)<br>
 * Proficient: all of the above and explain what was wrong, and why the changes needed to be made,
 *    and provide an example set of inputs that would expose the original error.
 *    (you can/may need to provide multiple tests to cover all errors)
 *  <br><br>
 * This program should get a number of scores specified by the user.
 * And then should calculate and print the average of the last 'n' scores excluding
 * the lowest 'x' scores from the last n scores. (see the method javadoc for further details)
 * <br>---<br>
 * for example:
 * if the user enters 4 scores: 100 , 30, 70, 90
 * and enters 3 for n and 1 for x
 * the average should be 80.
 * <br><br>
 * last 3 were 30,70,90. but then 30 is removed and then average of 70 and 90 is 80.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner =  Scanner(System.in);
        List<Integer> scores = new ArrayList<>();

        int numScores = getPositiveInt(scanner, "Enter the number of scores: ")

        // Get scores from the user
        for (int i = 1; i <= numScores; i++) {
             score = getPositiveInt(scanner,"Enter score #" + i + ": ");
            scores.add(score);
        }

        int numScoresToAverage = getPositiveInt(scanner,"Enter the number of scores to average: ");
        int dropCount = GetPositiveInt(scanner,"Enter the number of lowest scores to drop: ");

        double average = calculateAverage(scores, numScoresToAverage, dropCount);

        System.out.println("Average of the last " + numScoresToAverage + " scores after dropping the lowest " + dropCount + " scores: " + average);
    }

    /**
     * calculates the average of the last {@code numScoresToAverage} from the {@code scores} list,
     * removing the lowest {@code dropCount} scores before calculating the average
     * @param scores list of all the scores to include
     * @param numScoresToAverage number of scores to include in average,
     *                           uses all scores if this is larger than the number of scores in the list
     * @param dropCount number of the lowest scores to drop from group of last scores.
     * @return the average or -1 if {@code dropCount} >= {@code numScoresToAverage}
     */
    static double calculateAverage(List<Integer> scores, int numScoresToAverage, int dropCount) {
        //create a new list to hold scores to actually include in average
        List<Integer> scoresToInclude = new ArrayList<>();

        int numScores = Math.min(numScoresToAverage, scores.size()); // Ensure not to go beyond the available scores

        //return -1 if there will be no scores to average
        if(dropCount>=numScoresToAverage){
            return -1;
        }

        //populate the lists to use from the most recent scores
        for (int i = 0; i < numScores; i++) {
           scoresToInclude.add(scores.get(scores.size()-1-i));
        }

        //remove the lowest scores
        Collections.sort(scoresToInclude);
        for(int i = 0; i<dropCount; i++){
            scoresToInclude.remove(scoresToInclude.size()-1); //remove last element
        }

        // Calculate the average of remaining scores
        double sum = 0;
        for (int i = 0;i<scoresToInclude.size(); i++) {
            sum += scores.get(i);
        }

        return sum / scoresToInclude.size();
    }

    /**
     * Asks user for positive whole number and will retry until valid input is entered
     * @param scanner used to get input from the user
     * @param prompt message to display to the user
     * @return the valid integer entered
     */
    static int getPositiveInt(Scanner scanner,String prompt){
        while(true) {
            try {
                System.out.println(prompt);
                int num = Integer.parseInt(scanner.nextLine());
                if(num<=0)
                    System.out.println("number is not positive, try again");
                    continue;
                }
                return num;
            }catch (NumberFormatException ex){
                System.out.println("not an Integer, try again");
                continue;
            }
        }
    }
}