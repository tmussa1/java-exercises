package fall2021.hw6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private String value;

    private List<Node> edges;

    public Node(String value) {
        this.value = value;
        this.edges = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public List<Node> getEdges() {
        return edges;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setEdges(List<Node> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
