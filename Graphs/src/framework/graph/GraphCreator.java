package framework.graph;

import framework.problem.Problem;
import framework.problem.State;
import framework.graph.Vertex;
import java.util.Stack;
import java.util.List;

/**
 * @author Your name here
 */
public class GraphCreator {
    
    public Graph createGraphFor(Problem problem) {
        graph = new Graph();
        stack = new Stack<>();
        Vertex start = new Vertex(problem.getInitialState());
        List<String> moves = problem.getMover().getMoveNames();
        stack.push(start);
        while (!stack.isEmpty()) {
            Vertex u = stack.pop();
            
            for (String move : moves) {
                State next = problem.getMover().doMove(move, (State) u.getData());
                if (next != null) {
                    Vertex v = new Vertex(next);
//                    stack.push(v);
                    if (graph.getVertices().containsKey(v)) {
                        v = graph.getVertices().get(v);
                    } else {
                        stack.push(v);
                    }
                    graph.addEdge(u, v);
                }
            }
        }
        // You must provide
        return graph;
    }
    
    private Graph graph = null;
    private Stack<Vertex> stack = null;
}