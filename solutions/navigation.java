import java.util.*;
import java.io.*;

public class navigation {
    public class Edge  {
    public int  u;
    public int  v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
    }
    
    import java.util.*;

public class UnweightedGraph<V> {
    List<V> vertices = new ArrayList<>();
    List<List<Edge>> neighbors = new ArrayList<>();
    ArrayList<ArrayList<Integer>> adjList;

    public UnweightedGraph() {
    }

    public int getSize() {
        return vertices.size();
    }

    public List<V> getVertices() {
        return vertices;
    }

    public V getVertex(int index) {
        return vertices.get(index);
    }

    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        List<String> neighbour = new ArrayList<>();
        for (Edge e : neighbors.get(index))
            result.add(e.v);
        return result;
    }

    public String showNeighbours(int index){
        List<Integer> result = new ArrayList<>();
        List<String> neighbour = new ArrayList<>();
        for (Edge e : neighbors.get(index))
            result.add(e.v);

        for (Integer num : result) {
            neighbour.add((String) getVertex(num));
        }
        return "The neighbours of "+getVertex(index)+" are : "+ neighbour;
    }

    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e : neighbors.get(u)) {
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
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        } else {
            return false;
        }
    }

    /** Add an edge to the graph */
    public boolean addEdge(Edge e) {
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

    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v)) && addEdge(new Edge(v, u));
    }


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

        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(adjList, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                    "are not connected");
            return;
        }

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
    }

    public boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[]) {
        LinkedList<Integer> queue = new LinkedList<Integer>();

        boolean visited[] = new boolean[v];

        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

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

    
    public static void main(String[] args) throws IOException {
        HashMap<String,Integer> place = new HashMap<String , Integer>();
        UnweightedGraph<String> obj = new UnweightedGraph();
        ArrayList<String> destination = new ArrayList<>();
        File dir = new File("C:\\Users\\USER\\IdeaProjects\\DS\\solutions\\cases_navigation");
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
