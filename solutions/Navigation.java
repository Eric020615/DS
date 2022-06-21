package solutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Navigation {
    //main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> place = new HashMap<String, Integer>();
        UnweightedGraph<String> obj = new UnweightedGraph(); // create a graph
        ArrayList<String> destination = new ArrayList<>();
        int index = 0;
        String word;
        int num = Integer.valueOf(scanner.nextLine());
        for(int i=0;i<num;i++){
            int num_connection = Integer.parseInt(scanner.nextLine());
            System.out.print("The number of connection in this case: " + num_connection);
            for(int j=0;j<num_connection;j++){
                String[] stations = scanner.nextLine().split(" => ");
                obj.addVertex(stations[0]);
                obj.addVertex(stations[1]);
                String place_name1 = stations[0];  //source
                String place_name2 = stations[1];  //destination
                if (!place.containsKey(place_name1)) {
                     place.put(place_name1, index++);
                }
                if (!place.containsKey(place_name2)) {
                    place.put(place_name2, index++);
                }
                obj.addEdge(place.get(place_name1), place.get(place_name2));
                    //obj.addEdge(stations[1], stations[0]);
           }
        }
      int queries = Integer.valueOf(scanner.nextLine());
      for (int z = 0; z < queries; z++) {
           String[] query = scanner.nextLine().split(" -> ");
           String from = query[0];
           String to = query[1];
           findShortestPath(obj, place, from, to);
     }
    }
    /** Find the shortest path for the given source and destination */
    public static void findShortestPath(UnweightedGraph<String> graph, HashMap<String, Integer> places, String source, String destination) {
        System.out.print("\nThe source : " + source);
        System.out.print("\nThe destination : " + destination);
        System.out.println();
        System.out.print(source + " -> " + destination);
        try {
            graph.printShortestDistance(places.get(source), places.get(destination), graph.getSize());
        } catch (NullPointerException e) {
            System.out.println("Sorry, the source or destination are not exists. ");
        }
    }
}
