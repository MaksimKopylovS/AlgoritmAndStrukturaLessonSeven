package max_sk.graph;

import java.util.List;

public interface Graph {

    void addVertex(Vertex vertex);

    boolean addEdge(Vertex startVertex, Vertex seconVertex, Vertex... othersVertex);

    int getSize();

    void display();

    void bfs(String startLabel);

    public List<Vertex> fastWay(Vertex statrLabel, Vertex endLabel);
}
