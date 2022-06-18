import java.util.Scanner;

public class Navigation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
         * Number of the test cases
         */
        int num = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            /*
             * Number of the connection in this graph
             */
            int numberOfConnections = Integer.valueOf(scanner.nextLine());
            Graph graph = new Graph();
            for (int j = 0; j < numberOfConnections; j++) {
                String[] stations = scanner.nextLine().split(" => ");
                graph.addVertex(stations[0]);
                graph.addVertex(stations[1]);
                graph.addEdge(stations[0], stations[1]);
                graph.addEdge(stations[1], stations[0]);
            }
            /*
             * The number of queries from user
             */
            int queries = Integer.valueOf(scanner.nextLine());
            for (int z = 0; z < queries; z++) {
                String[] query = scanner.nextLine().split(" -> ");
                String from = query[0];
                String to = query[1];
                graph.bfs(from, to);
            }
        }
    }
}
