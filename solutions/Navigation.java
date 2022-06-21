import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class Navigation {
    public static class Edge  {
        public int  u;  //starting vertex of the edge
        public int  v;  //ending vertex of the edge

        /**Construct an edge for(u,v) */
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public static class UnweightedGraph<V> {
        List<V> vertices = new ArrayList<>();
        List<List<Navigation.Edge>> neighbors = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adjList;

        public UnweightedGraph() {
        }

        /** Get the number of vertices */
        public int getSize() {
            return vertices.size();
        }

        /** Return vertices in a list */
        public List<V> getVertices() {
            return vertices;
        }

        /** Get vertex info at a specific index/position */
        public V getVertex(int index) {
            return vertices.get(index);
        }

        /** Get the index of vertices */
        public int getIndex(V v) {
            return vertices.indexOf(v);
        }

        /** Get the degree of vertices */
        public int getDegree(int v) {
            return neighbors.get(v).size();
        }

        /** Return all the neighbours of a vertex to an ArrayList */
        public List<Integer> getNeighbors(int index) {
            List<Integer> result = new ArrayList<>();
            List<String> neighbour = new ArrayList<>();
            for (Navigation.Edge e : neighbors.get(index))
                result.add(e.v);
            return result;
        }

        /** Display all the neighbours of a vertex */
        public String showNeighbours(int index){
            List<Integer> result = new ArrayList<>();
            List<String> neighbour = new ArrayList<>();
            for (Navigation.Edge e : neighbors.get(index))
                result.add(e.v);

            for (Integer num : result) {
                neighbour.add((String) getVertex(num));
            }
            return "The neighbours of "+getVertex(index)+" are : "+ neighbour;
        }

        /** Print edges */
        public void printEdges() {
            for (int u = 0; u < neighbors.size(); u++) {
                System.out.print(getVertex(u) + " (" + u + "): ");
                for (Navigation.Edge e : neighbors.get(u)) {
                    System.out.print("(" + getVertex(e.u) + ", " +
                            getVertex(e.v) + ") ");
                }
                System.out.println();
            }
        }

        /** Clear the graph */
        public void clear() {
            vertices.clear();
            neighbors.clear();
        }

        /** Add a vertex to the graph */
        public boolean addVertex(V vertex) {
            //if the graph does not contain the vertex, add the vertex into graph
            if (!vertices.contains(vertex)) {
                vertices.add(vertex);
                neighbors.add(new ArrayList<Navigation.Edge>()); //add its neighbors to ArrayList
                return true;
            } else {
                return false;
            }
        }

        /** Add an edge to the graph */
        public boolean addEdge(Navigation.Edge e) {
            if (e.u < 0 || e.u > getSize() - 1)
                throw new IllegalArgumentException("No such index: " + e.u);

            if (e.v < 0 || e.v > getSize() - 1)
                throw new IllegalArgumentException("No such index: " + e.v);

            if (!neighbors.get(e.u).contains(e)) {
                neighbors.get(e.u).add(e);
                return true;
            } else {
                return false;
            }
        }

        /** Add an edge to the graph */
        public boolean addEdge(int u, int v) {
            return addEdge(new Navigation.Edge(u, v)) && addEdge(new Navigation.Edge(v, u));
        }

        /** Utility function to print the shortest distance
         between source vertex and destination vertex   */
        public void printShortestDistance(int s, int dest, int v) {
            this.adjList = new ArrayList<ArrayList<Integer>>(getSize());

            for (int i = 0; i < getSize(); i++) {
                adjList.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < getSize(); i++) {
                for (Edge e : neighbors.get(i)) {
                    adjList.get(i).add(e.v);
                }

            }
            // predecessor[i] array stores predecessor of
            // i and distance array stores distance of i
            // from s
            int pred[] = new int[v];
            int dist[] = new int[v];

            if (BFS(adjList, s, dest, v, pred, dist) == false) {
                System.out.println("Given source and destination" +
                        "are not connected");
                return;
            }

            //Display the shortest path from source to destination
            LinkedList<Integer> path = new LinkedList<Integer>();
            int crawl = dest;
            path.add(crawl);
            while (pred[crawl] != -1) {
                path.add(pred[crawl]);
                crawl = pred[crawl];
            }

            System.out.println("\nShortest path length is: " + dist[dest]);

            System.out.print("Path is : ");
            for (int i = path.size() - 1; i >= 0; i--) {
                if(i==0)
                    System.out.print(getVertex(path.get(i)));
                else
                    System.out.print(getVertex(path.get(i))+" -> ");
            }
            System.out.println();
        }

        /** A queue to maintain queue of vertices whose
         * adjacency list is to be scanned as per normal */
        //DFS algorithm
        public boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[]) {
            LinkedList<Integer> queue = new LinkedList<Integer>();

            //Determine whether ith vertex is reached
            //at least once in the Breadth first search
            boolean visited[] = new boolean[v];

            //initially all vertices are unvisited so v[i] for all i is false
            //and as no path is yet constructed dist[i] for all i set to infinity
            for (int i = 0; i < v; i++) {
                visited[i] = false;
                dist[i] = Integer.MAX_VALUE;
                pred[i] = -1;
            }
            //now source is first to be visited and
            //distance from source to itself should be 0
            visited[src] = true;
            dist[src] = 0;
            queue.add(src);

            // bfs Algorithm
            while (!queue.isEmpty()) {
                int u = queue.remove();
                for (int i = 0; i < adj.get(u).size(); i++) {
                    if (visited[adj.get(u).get(i)] == false) {
                        visited[adj.get(u).get(i)] = true;
                        dist[adj.get(u).get(i)] = dist[u] + 1;
                        pred[adj.get(u).get(i)] = u;
                        queue.add(adj.get(u).get(i));

                        if (adj.get(u).get(i) == dest)
                            return true;
                    }
                }
            }
            return false;
        }

        public boolean removeVertex(V v) {
            return true;
        }

        public boolean removeEdge(int u, int v) {
            return true;
        }
    }
    //main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> place = new HashMap<String, Integer>();
        Navigation.UnweightedGraph<String> obj = new Navigation.UnweightedGraph<>(); // create a graph
        ArrayList<String> destination = new ArrayList<>();
        int index = 0;
        String word;
        int num = Integer.valueOf(scanner.nextLine());
        for(int m=0;m<num;m++){
        int num_connection = Integer.valueOf(scanner.nextLine());
        for(int j=0;j<num_connection;j++){
                String input=scanner.nextLine();
                String[]stations = input.split(" => ");
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

        int queries = Integer.valueOf(scanner.nextLine());
        String[]list=new String[queries];
        for (int z = 0; z < queries; z++) {
            String query = scanner.nextLine();
            list[z]=query;
        }
        
        for(int o=0;o<list.length;o++){
            String[]test=list[o].split(" -> ");
            String from = test[0];
            String to = test[1];
            findShortestPath(obj, place, from, to);
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
        System.out.println();
    }
}
