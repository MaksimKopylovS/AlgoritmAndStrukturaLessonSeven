package max_sk;

import max_sk.graph.Graph;
import max_sk.graph.GraphImpl;
import max_sk.graph.Vertex;

import java.util.List;

public class HomeWorkApplication {
    public static void main(String[] args) {
        Graph graph = new GraphImpl(10,10);

        Vertex moskvaVertex = new Vertex("Москва", 0);
        Vertex tulaVertex = new Vertex("Тула", 10);
        Vertex lipeckVertex = new Vertex("Липецк", 3);
        Vertex voronozhVertex= new Vertex("Воронеж", 0);
        Vertex ryzanVertex= new Vertex("Рязань",3);
        Vertex tambovVertex= new Vertex("Тамбов", 2);
        Vertex saratobVertex= new Vertex("Саратов",3);
        Vertex kalugaVertex= new Vertex("Калуга",2);
        Vertex orelVertex= new Vertex("Орел",1);
        Vertex kurskVertex= new Vertex("Курск",26);

        graph.addVertex(moskvaVertex);
        graph.addVertex(tulaVertex);
        graph.addVertex(lipeckVertex);
        graph.addVertex(voronozhVertex);
        graph.addVertex(ryzanVertex);
        graph.addVertex(tambovVertex);
        graph.addVertex(saratobVertex);
        graph.addVertex(kalugaVertex);
        graph.addVertex(orelVertex);
        graph.addVertex(kurskVertex);

        graph.addEdge(moskvaVertex,tulaVertex, ryzanVertex, kalugaVertex);
        graph.addEdge(tulaVertex,lipeckVertex);
        graph.addEdge(lipeckVertex,voronozhVertex);
        graph.addEdge(ryzanVertex,tambovVertex);
        graph.addEdge(tambovVertex,saratobVertex);
        graph.addEdge(saratobVertex,voronozhVertex);
        graph.addEdge(kalugaVertex,orelVertex);
        graph.addEdge(orelVertex,kurskVertex);
        graph.addEdge(kurskVertex,voronozhVertex);


        List<Vertex> list = graph.fastWay(moskvaVertex, voronozhVertex);
        System.out.print("Которкий путь: ");
        for (Vertex v : list){
            System.out.print(v.getLabel() + " ");
        }

    }
}
