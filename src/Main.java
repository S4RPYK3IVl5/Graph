public class Main {
    public static void main(String[] args) {

        Graph<Integer> graph = new Graph<>();

        graph.addNode(1);
        graph.addNode(4);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNodeRel(1,4);
        graph.addNodeRel(1,2);
        graph.addNodeRel(1,3);
        graph.addNodeRel(3,2);
        graph.addNodeRel(3,4);
        graph.addNodeRel(2,4);

        graph.depthFirstSearch();
        graph.breadthFirstSearch();

        System.out.println(graph);

    }
}
