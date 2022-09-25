// --== CS400 File Header Information ==--
// Name: Kaden Almizyed
// Email: kalmizyed@wisc.edu
// Team: DC
// TA: April
// Lecturer: Florian
// Notes to Grader:

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;
    
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to D.
     */
    @Test
    public void testPathAtoD() {
        assertTrue(graph.shortestPath("A", "D").toString().equals(
            "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F").toString().equals(
            "[A, C, F]"
        ));
    }

    /**
     * Checks the distance/total weight cost from the vertex D to B.
     */
    @Test
    public void testPathCostDtoB() {
        assertEquals(12, graph.getPathCost("D", "B"));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * D to B.
     */
    @Test
    public void testPathDtoB() {
        assertEquals("[D, E, A, C, B]", graph.shortestPath("D", "B").toString());
    }

    /**
     * Checks the distance/total weight cost from the vertex E to F.
     */
    @Test
    public void testPathCostEtoF() {
        assertEquals(7, graph.getPathCost("E", "F"));
    }

    /**
     * Checks the predecessor of B along the path from F to B.
     */
    @Test
    public void testPredecessorFtoB() {
        ArrayList<String> path = new ArrayList<String>();
        path.addAll(graph.dijkstrasShortestPath("F", "B").dataSequence);
        assertEquals("C", path.get(path.size() - 2));
    }

    /**
     * Checks that no paths ever take the edge from A to B since it's more efficient to go A-C-B instead.
     */
    @Test
    public void testNoPathsTakeEdgeAtoB() {
        String[] vertices = {"A", "B", "C", "D", "E", "F"};

        for (String start : vertices) {
            for (String end : vertices) {
                assertFalse(graph.shortestPath(start, end).toString().contains("A, B"));
            }
        }
    }

}
