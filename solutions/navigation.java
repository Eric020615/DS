import java.util.*;
import java.io.*;
public class navigation {
    public static void main(String[] args) throws IOException {
        HashMap<String,Integer> place = new HashMap<String , Integer>();
        UnweightedGraph<String> obj = new UnweightedGraph();
        ArrayList<String> destination = new ArrayList<>();
        File dir = new File("C:\\Users\\USER\\IdeaProjects\\Batman-Team\\solutions\\navigation\\cases_navigation");
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Scanner sc = null;
                String word;
                try {
                    sc = new Scanner(new FileInputStream(file));
                    int index = 0;
                    while (sc.hasNextLine()) {
                        int num = Integer.parseInt(sc.nextLine());
                        System.out.print("The number of connection in this case: "+num);
                        for (int i = 0; i < num; i++) {
                            word = sc.nextLine();
                            String[] elements = word.split(" => ");
                            obj.addVertex(elements[0]);
                            obj.addVertex(elements[1]);
                            String place_name1 = elements[0];
                            String place_name2 = elements[1];
                            if (!place.containsKey(place_name1)) {
                                place.put(place_name1, index++);
                            }
                            if (!place.containsKey(place_name2)) {
                                place.put(place_name2, index++);
                            }
                            obj.addEdge(place.get(place_name1), place.get(place_name2));
                        }

                        word = sc.nextLine();
                        word = sc.nextLine();

                        while (sc.hasNextLine()) {
                            word = sc.nextLine();
                            System.out.print("\n\n"+word);

                            String[] a = word.split(" -> ");
                            findShortestPath(obj,place,a[0],a[1]);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("File got some problems.");
                } finally {
                    if (sc != null) {
                        sc.close();
                    }
                }

            }
        }

    }

    public static void findShortestPath(UnweightedGraph<String> graph, HashMap<String, Integer> places, String source, String destination) {
        System.out.print("\nThe source : " + source);
        System.out.print("\nThe destination : " + destination);
        try {
            graph.printShortestDistance(places.get(source), places.get(destination), graph.getSize());
        }
        catch (NullPointerException e) {
            System.out.println("Sorry, the source or destination are not exists. ");
        }
    }
}