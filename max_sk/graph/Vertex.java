package max_sk.graph;

public class Vertex {

    private String label;
    private int edge;

    private boolean visited;

    public Vertex(String label, int edge){
        this.label = label;
        this.edge = edge;
    }

    public String getLabel(){
        return label;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited(){
        return visited;
    }

    public int getEdge() {
        return edge;
    }
}
