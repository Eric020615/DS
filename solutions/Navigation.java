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
            }
            int queries = Integer.valueOf(scanner.nextLine());
            for (int z = 0; z < queries; z++) {
                String[]query = scanner.nextLine().split(" -> ");;
                String from = query[0];
                String to = query[1];
                findShortestPath(obj, place, from, to);
            }
        }
        
    }
    /** Find the shortest path for t389 1683 1266 255 293 1779 1286 1058 1461 252 407 768 845 1434 1421 589 268 1004 1710 449 247 316 1570 387 1290 742 1365 1574 1671 1522 1274 119 1330 1574 221 1130 404 430 529 1134 156 1027 1077 58 1667 40 655 1784 252 11 1244 278 1506 487 1029 316 1173 1137 224 1739 251 912 294 324 47 80 1503 1618 840 457 1855 162 457 1255 1671 255 173 276 14 446 1762 32 948 301 260 86 966 1405 197 315 940 461 1443 1254 443 1082 1460 60 1279 1882 804 443 576 1167 1351 1076 227 1391 1287 439 1159 1143 1215 273 1308 1293 1555 923 238 323 1085 1091 1411 20 181 371 1210 1676 1357 1228 1294 211 186 1762 1686 252 359 22 1526 1074 32 1075 247 431 175 1200 96 1289 1102 987 941 74 406 1182 786 517 567 1543 269 1398 1386 1891 387 61 289 1115 859 10 1328 1778 240 175 138 1348 167 1334 1272 5 1314 368 120 1744 566 777 72 1210 1825 464 428 414 1271 1152 443 881 1612 451 1020 100 256 1267 397 286 1278 397 370 899 80 745 1342 407 1795 434 1556 1210 39 976 1200 129 1599 302 1384 197 1691 1016 329 1715 270 899 1119 1260 222 809 371 541 117 1080 1535 642 1159 362 1181 1119 476 392 1718 656 1586 81 1077 1770 188 977 1147 9 1632 1195 209 1386 1039 189 1014 199 1419 613 119 1493 1233 434 1133 465 443 273 31 22 692 141 527 1327 301 401 356 1256 1063 1735 1191 154 1519 289 1702 316 1898 1837 191 221 243 1369 1659 1840 1027 1440 367 1166 1397 420 1094 1181 914 1596 227 1819 1736 1767 1245 257 1829 1204 1236 134 128 1401 1178 230 115 1545 1511 73 809 1766 347 89 612 1496 1182 328 1893 858 1242 464 1118 1782 1202 3 664 1507 1448 1178 1694 217 1349 288 1060 1842 1277 1560 1682 418 48 513 1539 1286 813 244 309 281 1144 1762 355 25 1863 22 128 93 1107 1424 414 893 1326 1177 1569 164 888 1683 1536 267 1117 97 1274 111 786 138 1332 1179 253 126 993 1251 1767 672 1008 1772 43 977 610 1726 304 320 187 908 42 1773 457 1583 179 1149 334 1307 1440 1149 230 332 1062 640 1063 15 1764 617 1310 1891 165 51 961 1655 349 1075 1174 225 1136 1014 538 1155 1096 1639 389 1351 1382 198 1788 1755 1642 1281 1178 1723 967 446 1316 411 1609 165 1657 1024 165 1177 1190 258 446 259 1758 1410 1393 490 1406 8 1270 687 1068 1756 33 1674 333 1504 887 197 183 1624 1157 397 1292 1033 399 1109 1273 122 1563 1635 1110 746 430 466 161 1562 233 256 333 430 1552 98 1164 1167 31 272 362 977 375 917 819 119 1275 327 1575 981 1532 1726 328 1238 970 1445 1870 78 377 1335 950 1225 1377 1765 1781 1016 1727 1154 424 1839 1035 348 1378 1390 282 1137 848 436 96 431 1059 1390 1583 43 523 1767 959 1114 1092 1009 312 305 1210 287 1430 467 973 1869 1904 1103 694 1108 995 1166 1134 458 16 1221 1303 1628 137 1760 1094 275 848 1168 301 1003 1574 1022 895 982 32 724 168 1362 144 1895 1686 57 292 327 30 744 426 1404 362 1794 772 1264 1245 469 73 910 436 1128 299 1551 1301 1740 44 14 218 334 187 1353 553 1652 134 317 1475 965 1635 1209 100 396 1300 997 1596 1657 91 1208 1221 1317 345 474 209 842 412 562 1422 172 1388 1321 1513 1293 1275 828 1397 908 1335 1292 357 566 1511 1602 1333 369 1467 1655 7 956 525 467 1741 27 1161 1627 1348 1210 53 853 1185 1663 369 874 43 1504 146 221 374 249 980 1678 1140 430 102 202 439 1856 463 967 1784 1314 59 1195 424 474 1444 1403 965 1147 928 444 36 389 1280 633 470 1785 128 1276 975 1233 1105 1083 1253 306 1626 1019 660 984 1302 741 1812 457 248 94 1121 1659 614 1341 301 1361 1038 1087 1838 1246 438 477 1816 403 1319 1318 771 139 216 1530 375 1357 1790 1532 249 110 809 1246 314 1109 467 1642 1511 172 1071 1549 1137 348 1354 1163 66 20 114 1526 255 477 419 1868 133 461 4 1179 1186 1590 201 1707 1191 273 1125 1372 1076 1271 1572 215 181 1583 1159 1267 1230 631 1066 43 406 281 77 265 1306 468 1810 1643 1901 1099 1758 1065 183 646 360 453 116 63 954 1007 1183 369 1258 769 1139 1005 207 1757 469 785 1391 952 55 1499 1720 1217 1783 1168 1601 131 283 1722 1235 831 900 324 335 1094 574 817 380 1266 838 218 9 1332 1811 1123 930 929 1519 1257 974 513 319 192 1392 1784 1611 1260 975 341 254 488 1656 75 1378 452 302 843 1533 1606 825 132 269 924 254 1197 431 108 289 322 1367 1273 1811 1209 1828 527 1272 1066 1324 655 306 238 146 1789 29 467 185 1699 235 360 228 1079 1729 264 1452 238 454 1289 283 1360 1745 1688 1061 1367 1320 474 1259 1750 1298 1201 1821 1195 149 1470 1446 374 1196 306 1065 134 1857 1327 1368 120 392 1599 591 1285 1882 1068 1089 315 1675 1186 1629 411 898 94 165 1253 288 1053 1101 54 131 1438 1336 294 316 354 1176 1890 1278 1158 1790 665 1672 246 1021 1376 3 963 1803 1138 993 1123 1600 1838 261 256 701 1130 1433 273 1151 1774 1444 1653 737 811 1222 1599 871 332 474 966 7 40 197 73 1387 388 1232 1068 222 258 308 1603 987 1842 1054 1351 305 1896 1593 1287 1336 1519 1744 261 1220 1342 395 1412 417 1764 1001 678 998 220 841 734 284 108 46 875 285 1789 1361 1753 1572 1732 1421 1086 1152 996 293 1384 476 1248 1365 836 1193 264 901 1388 1676 448 942 1554 229 91 274 1402 1034 374 317 1000 373 1348 968 469 1054 715 1513 1906 380 12 1120 5 1004 295 603 956 1251 185 116 331 209 1813 1761 437 279 569 179 1252 1163 237 267 465 1212 716 1114 571 1677 1444 231 990 1696 979 988 8 367 168 1228 296 1794 1019 1667 1140 266 295 179 1694 970 569 129 107 184 618 1096 1550 21 1359 1170 495 1462 1373 212 1059 82 261 440 1616 1367 1476 57 94 206 389 328 238 446 1891 138 1306 1150 150 416 1151 2 1175 149 1750 396 277 1745 1331 1611 1229 1872 66 1654 275 1761 276 374 1699 1876 1306 1059 572 1664 1009 1389 1551 1505 106 328 1308 981 280 488 1740 1408 435 260 1393 634 1749 1398 1136 923 1102 299 468 1129 1720 726 1036 211 1833 1693 596 200 1870 697 1424 300 2 144 360 1104 286 1543 233 305 188 1588 171 324 1271 1603 1112 1816 1114 1353 1570 1684 1438 932 1587 1500 1744 1105 216 245 744 1208 1450 1839 1285 1169 1599 159 1273 1616 1351 433 1213 1074 1895 1567 383 392 1860 1512 1790 211 1744 1116 1385 1465 1229 894 1286 1359 1091 34 1641 899 581 397 1219 1311 706 1797 1871 333 47 151 388 299 1145 1360 793 892 1439 1264 1079 648 730 306 1083 541 1786 1057 1068 1201 1515 1196 357 1871 1468 1630 472 1271 121 1759 773 1505 830 1612 525 1121 1852 1130 349 132 1028 883 1625 1190 1383 1287 1401 1019 48 1753 1231 1335 1245 1189 1099 126 1269 23 134 1591 214 254 1548 1184 1323 530 925 163 420 836 913 1124 1672 1152 477 54 1353 211 84 107 1339 406 64 33 1756 1203 1099 1673 838 465 1429 1857 166 328 303 1626 122 1608 253 116 1162 479 1791 966 1498 341 144
     he given source and destination */
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
