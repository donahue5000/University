//Consider this example problem: You are given the quiz scores of a student. You are to compute the final quiz score, which 
//is the sum of all scores after dropping the lowest one. For example, if the scores are
//8  7  8.5  9.5  7  4  10
//then the final score is 50.

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Total quizes: ");
        int totalQuizes = in.nextInt();
        double[] scoresInput = new double[totalQuizes];
        for (int i = 0; i < totalQuizes; i++)
        {
            System.out.print("Enter score: ");
            scoresInput[i] = in.nextDouble();
        }
        System.out.println("Final Score: " + finalScore(scoresInput));
    }
    
    static double finalScore(double[] n)
    {
        if (n.length < 1)
        {
            return 0;
        }
        if (n.length == 1)
        {
            return n[0];
        }
    
        double result = 0;
        int lowIndex = 0;
    
        //find index of lowest score
        for (int i = 1; i < n.length; i++)
        {
            if (n[i] < n[lowIndex])
            {
                lowIndex = i;
            }
        }
        //remove lowest score the hard way
        for (int i = lowIndex + 1; i < n.length; i++)
        {
            n[i - 1] = n[i];
        }
        double[] nTrimmed = Arrays.copyOf(n, n.length - 1);
        //sum scores
        for (double element : nTrimmed)
        {
            result += element;
        }
    
        return result;
    }
}
