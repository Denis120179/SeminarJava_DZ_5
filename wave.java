import java.util.LinkedList;
import java.util.Queue;

class node {
    int x, y, dist;
    node(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class wave {

    public static void main(String[] args)
    {
        int [][] matrix = getMap();
        System.out.println(rawData(getMap()));                   
        search(matrix, 0, 0, 4, 4);        
    }    
    
    static int M = 5;
    static int N = 5;

    static boolean isValid(int mat[][], boolean visited[][], int row, int col)
    {
        return ((row >= 0) && (row < M)) && ((col >= 0) && (col < N)) && (mat[row][col] == 1) && (!visited[row][col]);
    }
        
    public static void search(int matrix[][], int i, int j, int x, int y) //матрица, координаты старта и финиша 
    {
        int row[] = { -1, 0, 0, 1 }; //перемещения по х
        int col[] = { 0, -1, 1, 0 }; //перемещения по y

        boolean[][] visited = new boolean[M][N];
        Queue<node> q = new LinkedList<node>();
        visited[i][j] = true;
        q.add(new node(i, j, 0));
        int minimum_distance = Integer.MAX_VALUE;
        while (!q.isEmpty())
        {
            node node = q.poll();
            i = node.x;
            j = node.y;
            int dist = node.dist;
            if (i == x && j == y)
            {
                minimum_distance = dist;
                break;
            }

            for (int k = 0; k < 4; k++)
            {
                if (isValid(matrix, visited, i + row[k], j + col[k]))
                {
                    visited[i + row[k]][j + col[k]] = true;
                    node n = new node(i + row[k], j + col[k], dist + 1);
                    q.add(n);
                }
            }
        }

        if (minimum_distance == Integer.MAX_VALUE)
        {
            System.out.print("Расстояние не может быть определено");
        }
        else
        {
            System.out.print("Минимальное расстояние " + minimum_distance);
        }           
    }
    static int[][] getMap(){
        return new int[][] {
            {1, 0, 1, 1, 1 },
            { 1, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 1 },
            { 0, 0, 0, 0, 1 },
            { 1, 1, 1, 0, 1 },
            { 1, 1, 0, 0, 0 }             
        };
    }
    
    static String rawData(int[][] map) {
        StringBuilder sb = new StringBuilder();
    
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
            sb.append(String.format("%5d", map[row][col]));
            }
            sb.append("\n");
        }    
        return sb.toString();
    }  
}