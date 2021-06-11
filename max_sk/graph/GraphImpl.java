package max_sk.graph;

import java.util.*;

public class GraphImpl implements Graph {



    private List<Vertex> vertexList;
    private final boolean[][] vertexMass;
    private static int count = 0;
    private int countMin = Integer.MAX_VALUE;
    private List<Vertex> fastWay;
    private List<Vertex> countList;

    public GraphImpl(int maxVertexCountI, int maxVertexCountJ) {
        this.vertexList = new ArrayList<>();
        this.vertexMass = new boolean[maxVertexCountI][maxVertexCountJ];
        fastWay = new ArrayList<>();
        countList = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    @Override
    public boolean addEdge(Vertex startVertex, Vertex secondVertex, Vertex... othersVertex) {
        boolean result = addEdge(startVertex.getLabel(), secondVertex.getLabel());
        for (Vertex label : othersVertex) {
            result &= addEdge(startVertex.getLabel(), label.getLabel());
        }
        return result;
    }

    private boolean addEdge(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        vertexMass[startIndex][endIndex] = true;
        vertexMass[endIndex][startIndex] = true;
        return true;
    }

    private int indexOf(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            Vertex vertex = vertexList.get(i);
            if (vertex.getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {

        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(vertexList.get(i).getLabel());
            for (int j = 0; j < vertexList.size(); j++) {
                if (vertexMass[i][j]) {

                    System.out.print("->" + vertexList.get(j).getLabel());
                }
            }
            System.out.println();

        }
    }


    @Override
    public List<Vertex> fastWay(Vertex startLabel, Vertex endLabel) {
        List<Vertex> list = new LinkedList<>(fastWayRec(startLabel.getLabel(),endLabel.getLabel()));

        if (list.get(0).equals(startLabel)){
            list.add(list.size(), endLabel);
            return list;
        }else {
            list.add(list.size(), endLabel);
            list.add(0,startLabel);
        }

        return list;
    }

    private List<Vertex> fastWayRec(String startLabel, String endLabel){
        int start = indexOf(startLabel);
        int end = indexOf(endLabel);
        Vertex startVertex = vertexList.get(start);
        Queue<Vertex> queue;

        if(findFastWay(startLabel,endLabel)){
            return countList;
        }
        count = count + startVertex.getEdge();
        vertexList.get(start).setVisited(true);
        queue = getFrends(startVertex);
        fastWay.add(startVertex);
        inVisit(queue, endLabel);

        return countList;
    }

    private void inVisit(Queue<Vertex> queue, String endLabel){
        for (Vertex v : queue) {
            if (!v.isVisited()) {
                fastWayRec(v.getLabel(), endLabel);
            }
        }
    }

    private boolean findFastWay(String startLabel, String endLabel){
        if (startLabel.equals(endLabel)) {
            if (count < countMin) {
                countMin = count;
                countList = fastWay;
                count = 0;
                fastWay = new ArrayList<>();
                return true;
            }else {
                count = 0;
                fastWay = new ArrayList<>();
            }
        }
        vertexList.get(indexOf(endLabel)).setVisited(false);
        return false;
    }

    private Queue<Vertex> getFrends(Vertex vertex) {
        int startIndex = indexOf(vertex.getLabel());
        Queue<Vertex> queueVertex = new LinkedList<>();
        queueVertex.add(vertex);
        for (int j = 0; j < vertexList.size(); j++) {
            if (vertexMass[startIndex][j]) {
                if (!vertexList.get(j).isVisited()) {
                    queueVertex.add(vertexList.get(j));
                }
            }
        }
        return queueVertex;
    }

    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label: " + startLabel);
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
            } else {
                queue.remove();
            }
        }
        resetVertexState();
    }

    private Vertex getNearUnvisitedVertex(Vertex current) {
        int currentIndex = vertexList.indexOf(current);
        for (int i = 0; i < getSize(); i++) {
            if (vertexMass[currentIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void showQueue(Queue<Vertex> queue, String endLabel) {
        for (Vertex v : queue) {
            System.out.println(v.getLabel());
            if (v.getLabel().equals(endLabel)) {

                System.out.println("Нашли");
            }
        }
    }



    private int getAdjUnivisitedVertex(int ver) {
        for (int i = 0; i < getSize(); i++) {
            if (vertexMass[ver][i] && !vertexList.get(i).isVisited()) {
                return i;
            }
        }
        return -1;
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        System.out.println(vertex.getLabel());
        queue.add(vertex);
        vertex.setVisited(true);
    }


    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }


}
