import java.util.*;

public class Graph<T> {

    private LinkedList<Node<T>> nodes = new LinkedList<>();
    private LinkedList<Node<T>> copyOfNodes;

    private static class Node<T>{

        private T t;
        private LinkedList<Node<T>> nodes = new LinkedList<>();
        private boolean isChecked = false;

        public Node(T t) {
            this.t = t;
        }

        private void addRel(Node<T> n){
            if (nodes.contains(n))
                return;
            nodes.add(n);
            n.addRel(this);
        }

        private void check(){
            isChecked = !isChecked;
        }

        private boolean isChecked(){
            return isChecked;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(t);
            stringBuilder.append(": [ ");
            for (Node<T> node : nodes)
                stringBuilder.append(node.t).append(" ");
            stringBuilder.append("] ");
            return stringBuilder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(t, node.t);
        }

        @Override
        public int hashCode() {
            return Objects.hash(t);
        }
    }

    public void addNode(T t){
        Node<T> node = new Node<T>(t);
        if (nodes.contains(node))
            return;
        nodes.add(node);
    }

    public void addNodeRel(T t, T n){

        Node<T> mainNode = new Node<T>(t);
        Node<T> relNode = new Node<T>(n);

        if (!nodes.contains(mainNode))
            nodes.add(mainNode);
        if (!nodes.contains(relNode))
            nodes.add(relNode);

        for (Node<T> node : nodes){
            if (mainNode.equals(node))
                mainNode = node;
            if (relNode.equals(node))
                relNode = node;
        }

        mainNode.addRel(relNode);

    }

    public void depthFirstSearch(){
        copyOfNodes = (LinkedList<Node<T>>) nodes.clone();
        depthFirstSearchPrivate(copyOfNodes.poll());
        if (!copyOfNodes.isEmpty())
            depthFirstSearchPrivate(copyOfNodes.poll());
        System.out.println("\n");
        for (Node node : nodes)
            node.check();
    }

    private void depthFirstSearchPrivate(Node<T> node){
        if (node.isChecked)
            return;
        node.check();
        copyOfNodes.remove(node);
        System.out.print(node.t + " ");
        for (Node<T> relNode : node.nodes)
            depthFirstSearchPrivate(relNode);
    }

    public void breadthFirstSearch(){
        copyOfNodes = (LinkedList<Node<T>>) nodes.clone();
        breadthFirstSearchPrivate(copyOfNodes.poll());
        if (!copyOfNodes.isEmpty())
            breadthFirstSearchPrivate(copyOfNodes.poll());
        System.out.println("\n");
        for (Node node : nodes)
            node.check();
    }

    private void breadthFirstSearchPrivate(Node<T> node) {
        if (node.isChecked())
            return;
        System.out.print(node.t + " ");
        for (Node<T> relNode : node.nodes){
            if (!relNode.isChecked()) {
                relNode.check();
                System.out.print(relNode.t + " ");
                copyOfNodes.remove(relNode);
            }
        }
        for (Node<T> relNode : node.nodes){
            breadthFirstSearchPrivate(relNode);
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }
}
