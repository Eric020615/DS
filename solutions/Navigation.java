package solutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Navigation {
    //main method
    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> place = new HashMap<String, Integer>();
        UnweightedGraph<String> obj = new UnweightedGraph(); // create a graph
        ArrayList<String> destination = new ArrayList<>();
        Scanner scr = new Scanner(System.in);
        Scanner sc = null;
        String word;
        //prompt user to input the source and destination and read from file to find them
        try {
            sc = new Scanner(new FileInputStream("AllConnection.txt"));
            int index = 0;
            System.out.print("Please enter source : ");
            String source = scr.nextLine();
            System.out.print("Please enter destination : ");
            String destination1 = scr.nextLine();
            // the number of line represent the number of connection in the case
            int num = Integer.parseInt(sc.nextLine());
            System.out.println();
            System.out.print("The number of connection in this case: " + num);
            while (sc.hasNextLine()) {
                word = sc.nextLine();
                //split every element with => to separate source and destination
                String[] elements = word.split(" => ");
                obj.addVertex(elements[0]); //add source to vertex list
                obj.addVertex(elements[1]); //add destination to vertex list
                String place_name1 = elements[0];  //source
                String place_name2 = elements[1];  //destination
                if (!place.containsKey(place_name1)) {
                    place.put(place_name1, index++);
                }
                if (!place.containsKey(place_name2)) {
                    place.put(place_name2, index++);
                }
                // add edge between source and destination
                obj.addEdge(place.get(place_name1), place.get(place_name2));

            }
            //print the shortest path for the given source and destination
            findShortestPath(obj, place, source, destination1);

        } catch (IOException e) {
            System.out.println("File got some problems.");
        } finally {
            if (sc != null) {
                sc.close();
            }
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
