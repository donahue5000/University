import java.util.Arrays;

public class ArrayPracticeMethods
{
    public static void main(String[] args)
    {
        int[] values = arrayGen(10);
        arrayPrint(values);
        //arrayFirstLastSwap(values);
        //arrayRightShift(values);
        //arrayEvenstoZero(values);
        //arrayBigNeighborAbsorb(values);
        //values = arrayMidChop(values);
        //arrayEvenToFront(values);
        //System.out.println(secondLargest(values));
        //System.out.println(orderChecker(values));
        //int[] values = {1, 2, 3, 4, 5};
        //System.out.println(ascendCheck(values));
        //System.out.println(hasTwin(values));
        //int[] values = {1, 2, 3, 4, 5, 6, 7};
        //System.out.println(hasDuplicate(values));
        
        arrayPrint(values);
    }
    
    static int[] arrayGen(int length)
    {
        int[] values = new int[length];
        for (int i = 0; i < length; i++)
        {
            values[i] = (int) (Math.random() * 10) + 1;
        }
        return values;
    }
    
    static void arrayPrint(int[] temp)
    {
        for (int element : temp)
        {
            System.out.printf("%-4d", element);
        }
        System.out.println();
    }
    
    static void arrayFirstLastSwap(int[] temp)
    {
        int cache = temp[0];
        temp[0] = temp[temp.length - 1];
        temp[temp.length - 1] = cache;
    }
    
    static void arrayRightShift(int[] temp)
    {
        int cache = temp[temp.length - 1];
        for (int i = temp.length - 1; i > 0; i--)
        {
            temp[i] = temp[i - 1];
        }
        temp[0] = cache;
    }
    
    static void arrayEvenstoZero(int[] temp)
    {
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] % 2 == 0)
            {
                temp[i] = 0;
            }
        }
    }
    
    static void arrayBigNeighborAbsorb(int[] original)
    {
        int[] temp = Arrays.copyOf(original, original.length);
        for (int i = 1; i < temp.length - 1; i++)
        {
            int neighbor = temp[i - 1];
            original[i] = Math.max(neighbor, Math.max(temp[i], temp[i + 1]));
        }
    }
    
    static int[] arrayMidChop(int[] temp)
    {
        if (temp.length % 2 == 0)
        {
            for (int i = temp.length / 2 - 1; i < temp.length - 2; i++)
            {
                temp[i] = temp[i + 2];
            }
            return Arrays.copyOf(temp, temp.length - 2);
        }else
        {
            for (int i = temp.length / 2; i < temp.length - 1; i++)
            {
                temp[i] = temp[i + 1];
            }
            return Arrays.copyOf(temp, temp.length - 1);
        }
        
    }
    
    static void arrayEvenToFront(int[] temp)
    {
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] % 2 != 0)
            {
                for (int j = i + 1; j < temp.length; j++)
                {
                    if (temp[j] % 2 == 0)
                    {
                        int cache = temp[j];
                        for (int k = j; k > i; k--)
                        {
                            temp[k] = temp[k - 1];
                        }
                        temp[i] = cache;
                        break;
                    }
                }
            }
        }
    }
    
    static int secondLargest(int[] original)
    {
        int[] temp = Arrays.copyOf(original, original.length);
        int maxIndex = 0;
        for (int i = 1; i < temp.length; i++)
        {
            if (temp[i] > temp[maxIndex]) maxIndex = i;
        }
        temp[maxIndex] = 0;
        int resultIndex = 0;
        for (int i = 1; i < temp.length; i++)
        {
            if (temp[i] > temp[resultIndex]) resultIndex = i;
        }
        
        return temp[resultIndex];
    }
    
    static boolean ascendCheck(int[] temp)
    {
        boolean result = true;
        for (int i = 0; i < temp.length - 1; i++)
        {
            if (temp[i] > temp[i + 1])
            {
                result = false;
                break;
            }
        }
        return result;
    }
    
    static boolean hasTwin(int[] temp)
    {
        boolean result = false;
        for (int i = 0; i < temp.length - 1; i++)
        {
            if (temp[i] == temp[i + 1])
            {
                result = true;
                break;
            }
        }
        return result;
    }
    
    static boolean hasDuplicate(int[] temp)
    {
        boolean result = false;
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = i + 1; j < temp.length; j++)
            {
                if (temp[i] == temp[j])
                {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    
}


















