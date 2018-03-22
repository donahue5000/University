//Consider this example problem: You are given the quiz scores of a student. You are to compute the final quiz score, which 
//is the sum of all scores after dropping the lowest one. For example, if the scores are
//8  7  8.5  9.5  7  4  10
//then the final score is 50.


import java.util.Scanner;

public class Dabble
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of tests: ");
        int numTests = input.nextInt();
        double[] scores = new double[numTests];
        for (int i = 0; i < numTests; i++)
        {
            System.out.print("Score: ");
            scores[i] = input.nextDouble();
        }
        System.out.println(scoreTotal(scores));
    }
    
    static double scoreTotal(double[] scores)
    {
        double result = 0;
        if (scores.length < 1) return result;
        if (scores.length < 2) return scores[0];
        scores = scorePruner(scores);
        for (double element: scores)
        {
            result += element;
        }
        return result;
    }
    
    static double[] scorePruner(double[] scores)
    {
        scores[minIndex(scores)] = 0;
        return scores;
    }
    
    static int minIndex(double[] scores)
    {
        int minIndex = 0;
        double min = scores[0];
        for (int i = 1; i < scores.length; i++)
        {
            if (scores[i] < min) minIndex = i;
        }
        return minIndex;
    }
}
