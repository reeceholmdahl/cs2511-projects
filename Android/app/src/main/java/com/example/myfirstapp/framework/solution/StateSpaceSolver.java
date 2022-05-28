package com.example.myfirstapp.framework.solution;

import com.example.myfirstapp.framework.graph.Vertex;
import com.example.myfirstapp.framework.problem.Mover;
import com.example.myfirstapp.framework.problem.Problem;
import com.example.myfirstapp.framework.problem.State;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a state space solver by extending the abstract
 * Solver class.
 * @author Your name and section here
 */
public abstract class StateSpaceSolver extends Solver {
    
    /**
     * Creates a BFS or DFS state space solver.
     * This constructor should set the queue to a double-ended queue 
     * (DEQUE) like the GraphSolver.
     * @param problem the problem being solved
     */
    public StateSpaceSolver(Problem problem) {
        super(problem);
        super.setQueue(new LinkedList<>());
    }
    
    /**
     * This method implements the expand operation required by the 
     * state space search algorithm.
     * @param u the vertex being expanded
     * @return the children of u in the state space search tree
     */
    @Override
    public List<Vertex> expand(Vertex u) { 
        // you must provide
        State curr = (State) u.getData();
        
        List<Vertex> states = new LinkedList<>();
        List<String> moves = super.getProblem().getMover().getMoveNames();
        
        moves.stream().forEach(m -> {
            State next = super.getProblem().getMover().doMove(m, curr);
            if (next != null) {
                next.setMove(m);
                states.add(new Vertex(next));
            }
        });
        
        return states;
    }
}
