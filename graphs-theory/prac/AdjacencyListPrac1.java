import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class AdjacencyListPrac1 {
    
    public HashMap<String, ArrayList<String>> buildAdjacencyList(
        String[][] srcDestArrayStrings
    ){
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        for(String[] srcDestArrayString : srcDestArrayStrings){
            String src = srcDestArrayString[0];
            String dest = srcDestArrayString[1];
            if(!adjList.containsKey(src)){
                adjList.put(src, new ArrayList<>());
            }
            if(!adjList.containsKey(dest)){
                adjList.put(dest, new ArrayList<>());
            }
            ArrayList<String> res = adjList.get(src);
            res.add(dest);
        }
        return adjList;
    }   

    public int dfs(
        HashMap<String, ArrayList<String>> adjList,
        HashSet<String> visit,
        String src,
        String target
    ) {
        if(src == target){
            return 1;
        }
        if(visit.contains(src)){
            return 0;
        }
        int count = 0;
        visit.add(src);
        for(String node : adjList.get(src)){
            count += dfs(adjList, visit, node, target);
        }
        visit.remove(src);
        return count;
    }

    public int bfs(
        HashMap<String, ArrayList<String>> adjList,
        String src,
        String target
    ){
        HashSet<String> visit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(src);
        visit.add(src);
        int length = 0;
        while(!queue.isEmpty()){
            int queueLength = queue.size();
            for(int i = 0; i < queueLength; i++){
                String element = queue.peek();
                if(element == target) return length;
                queue.poll();
                for(String node: adjList.get(element)){
                    if(!visit.contains(node)){
                        queue.offer(node);
                        visit.add(node);
                    }
                }
            }
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        AdjacencyListPrac1 adjacencyListPrac = new AdjacencyListPrac1();
        String[][] edges = {{"A", "B"}, {"B", "C"}, {"B", "E"}, {"C", "E"}, {"E", "D"}};
        HashMap<String, ArrayList<String>> al = adjacencyListPrac.buildAdjacencyList(edges);
        System.out.println(al);
        HashSet<String> visit = new HashSet<>();
        System.out.println("Number of paths " + adjacencyListPrac.dfs(al, visit, "A", "E"));
        System.out.println("Shortest Path " + adjacencyListPrac.bfs(al, "A", "D"));
    }
}