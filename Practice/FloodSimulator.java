

class FloodSimulator
{
    public static void main(String[] args)
    {
        int[][] map = new int[10][10];
        buildMap(map);
        printMap(map);
        for (int i = 0; i <= 100; i += 10)
        {
            simulateFlood(map, i);
            System.out.println("----------------------------");
        }
    }
    
    static void buildMap(int[][] temp)
    {
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[0].length; j++)
            {
                temp[i][j] = (int) (Math.random() * 100);
            }
        }
    }
    
    static void printMap(int[][] temp)
    {
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[0].length; j++)
            {
                System.out.printf("%-3d", temp[i][j]);
            }
            System.out.println();
        }
    }
    
    static void simulateFlood(int[][] map, int floodHeight)
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                if (map[i][j] <= floodHeight)
                {
                    System.out.printf("%-3s", "*");
                }else
                {
                    System.out.printf("%-3s", " ");
                }
            }
            System.out.println();
        }
    }
    
}























