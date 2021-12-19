package fall2021.hw6;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Hw6_p5 {

    /**
     * Reads from a file and constructs adjacency list
     * @return
     */
    private static List<Node> constructAdjacencyList(){

        List<Node> nodes = new ArrayList<>();
        Map<String, Node> nodeMap = new HashMap<>();
        List<String> lines = new ArrayList<>();

        try {
            // Read input
            URL url = Hw6_p5.class.getResource("follows_input.txt");
            File file = new File(url.getPath());

            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String lineArray [] = line.split(",");
                Node node = new Node(lineArray[0]); // Get the key for adj list
                nodeMap.put(lineArray[0], node);
                nodes.add(node);
                lines.add(line); // Add lines to draw relationship
            }
        } catch (FileNotFoundException
                | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error reading file " + e.getCause());;
        }

        for(String line: lines){

            String lineArray [] = line.split(",");
            List<Node> edges = new ArrayList<>();
            Node node = nodeMap.get(lineArray[0]);

            // Construct the edges. Had to trim white spaces
            for(int i = 1; i < lineArray.length; i++){
                edges.add(nodeMap.get(lineArray[i].trim()));
            }

            node.setEdges(edges);
        }

        return nodes;
    }

    /**
     * Driver function calling direct and indirect follows
     * @param person
     * @param adjacencyList
     */
    private static void allFollows(Node person, List<Node> adjacencyList){
        directlyFollows(person, adjacencyList);
        inDirectlyFollows(person, adjacencyList);
    }

    /**
     * Prints direct follows
     * @param person
     * @param adjacencyList
     */
    private static void directlyFollows(Node person, List<Node> adjacencyList) {
        for(Node node: adjacencyList){
            if(node.getValue().equals(person.getValue())){
                System.out.print(node.getValue() + " directly follows {");
                for(Node edge: node.getEdges()){
                    System.out.print(edge.getValue() + ", ");
                }
                System.out.println("}");
            }
        }
    }

    /**
     * Indirect follows. Calls recursive function
     * @param person
     * @param adjacencyList
     */
    private static void inDirectlyFollows(Node person, List<Node> adjacencyList) {

        // Find the node and run dfs
        for(Node node: adjacencyList) {
            if (node.getValue().equals(person.getValue())) {
                System.out.print(node.getValue() + " indirectly follows {");
                dfs(node, node.getEdges(), node.getEdges(), new HashSet<>());
                System.out.println("}");
            }
        }
    }

    /**
     * Runs depth first search on unvisited nodes
     * @param node
     * @param adjacencyList
     * @param direct
     * @param visited
     */
    private static void dfs(Node node, List<Node> adjacencyList, List<Node> direct, Set<String> visited) {

        for(Node el: adjacencyList){
            // Print it if is not visited and is not a direct followee
            if(!direct.contains(el) && !visited.contains(el.getValue())){
                System.out.print(el.getValue() + ", ");
                visited.add(el.getValue());
            }
            dfs(el, el.getEdges(), direct, visited); // Call recursively
        }
    }

    public static void main(String [] args){

        // Constructs adjacency list and calls all follows
        List<Node> adjacencyList = constructAdjacencyList();
        allFollows(new Node("D"), adjacencyList);
    }
}