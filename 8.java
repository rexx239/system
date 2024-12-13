
import java.util.Scanner;

public class bellmanford {
    private static final int MAX_VALUE = 999;

    public static void bellmanFord(int vertices, int[][] adjMatrix, int source) {
        int[] distance = new int[vertices + 1];
        for (int i = 1; i <= vertices; i++) {
            distance[i] = MAX_VALUE;
        }
        distance[source] = 0;

        for (int i = 1; i <= vertices - 1; i++) {
            for (int u = 1; u <= vertices; u++) {
                for (int v = 1; v <= vertices; v++) {
                    if (adjMatrix[u][v] != MAX_VALUE && distance[u] + adjMatrix[u][v] < distance[v]) {
                        distance[v] = distance[u] + adjMatrix[u][v];
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int u = 1; u <= vertices; u++) {
            for (int v = 1; v <= vertices; v++) {
                if (adjMatrix[u][v] != MAX_VALUE && distance[u] + adjMatrix[u][v] < distance[v]) {
                    System.out.println("The graph contains a negative edge cycle.");
                    return;
                }
            }
        }

        for (int i = 1; i <= vertices; i++) {
            System.out.println("Distance from source " + source + " to vertex " + i + " is " + distance[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int vertices = scanner.nextInt();

        int[][] adjMatrix = new int[vertices + 1][vertices + 1];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 1; i <= vertices; i++) {
            for (int j = 1; j <= vertices; j++) {
                adjMatrix[i][j] = scanner.nextInt();
                if (i == j) adjMatrix[i][j] = 0;
                if (adjMatrix[i][j] == 0 && i != j) adjMatrix[i][j] = MAX_VALUE;
            }
        }

        System.out.println("Enter the source vertex:");
        int source = scanner.nextInt();

        bellmanFord(vertices, adjMatrix, source);
        scanner.close();
    }
}
