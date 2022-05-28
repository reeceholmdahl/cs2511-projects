package domains.puzzle;

import framework.problem.Mover;
import framework.problem.State;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Your name here
 */
public class PuzzleMoverTest {
        
    /**
     * Declare private instance fields here. For example:
     */
    
    private final State start;
    private final Mover mover;
    private final List<String> moveNames;
    private final String slide1, slide2, slide3, slide4,  // move names
	                 slide5, slide6, slide7, slide8;
    
    public PuzzleMoverTest() {
        
        start = new PuzzleState(new int[][]{{2, 4, 6}, {1, 5, 3}, {8, 7, 0}});
        
        /**
         * Initialize instance fields here
         */
        mover = new PuzzleMover();
        moveNames = mover.getMoveNames();
        slide1 = moveNames.get(0);
        slide2 = moveNames.get(1);
        slide3 = moveNames.get(2);
        slide4 = moveNames.get(3);
        slide5 = moveNames.get(4);
        slide6 = moveNames.get(5);
        slide7 = moveNames.get(6);
        slide8 = moveNames.get(7);
    }
    
    /**
     * Test all moves in the methods below by replacing the calls to fail.
     */

    @Test
    public void testSlide1() {
        /**
         * For example, if mover, start, and slide1 have been
         * initialized, call:
         *
         *     mover.doMove(slide1, start)
         *
         * and test the result with assertions.
         */
        assertTrue(mover.doMove(slide1, start) == null);
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 4, 6}, {1, 5, 3}, {0, 7, 8}});
        
        assertTrue(mover.doMove(slide1, check).equals(new PuzzleState(new int[][]{{2, 4, 6}, {0, 5, 3}, {1, 7, 8}})));
    }

    @Test
    public void testSlide2() {
        assertTrue(mover.doMove(slide2, start) == null);
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 4, 6}, {0, 5, 3}, {1, 7, 8}});
        
        assertTrue(mover.doMove(slide2, check).equals(new PuzzleState(new int[][]{{0, 4, 6}, {2, 5, 3}, {1, 7, 8}})));    
    }

    @Test
    public void testSlide3() {
        assertTrue(mover.doMove(slide3, start).equals(new PuzzleState(new int[][]{{2, 4, 6}, {1, 5, 0}, {8, 7, 3}})));
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 4, 6}, {0, 5, 3}, {1, 7, 8}});
        
        assertTrue(mover.doMove(slide3, check) == null);         
    }

    @Test
    public void testSlide4() {
        assertTrue(mover.doMove(slide4, start) == null);
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 4, 6}, {1, 0, 3}, {8, 7, 5}});
        
        assertTrue(mover.doMove(slide4, check).equals(new PuzzleState(new int[][]{{2, 0, 6}, {1, 4, 3}, {8, 7, 5}})));
    }

    @Test
    public void testSlide5() {
        assertTrue(mover.doMove(slide5, start) == null);
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 4, 6}, {1, 0, 3}, {8, 5, 7}});
        
        assertTrue(mover.doMove(slide5, check).equals(new PuzzleState(new int[][]{{2, 4, 6}, {1, 5, 3}, {8, 0, 7}})));     
    }

    @Test
    public void testSlide6() {
        assertTrue(mover.doMove(slide6, start) == null);
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 0, 6}, {1, 4, 3}, {8, 5, 7}});
        
        assertTrue(mover.doMove(slide6, check).equals(new PuzzleState(new int[][]{{2, 6, 0}, {1, 4, 3}, {8, 5, 7}})));         
    }

    @Test
    public void testSlide7() {
        assertTrue(mover.doMove(slide7, start).equals(new PuzzleState(new int[][]{{2, 4, 6}, {1, 5, 3}, {8, 0, 7}})));
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 0, 6}, {1, 4, 3}, {8, 5, 7}});
        
        assertTrue(mover.doMove(slide7, check) == null);            
    }

    @Test
    public void testSlide8() {
        assertTrue(mover.doMove(slide8, start) == null);
        
        PuzzleState check = new PuzzleState(new int[][]{{2, 1, 6}, {8, 4, 3}, {0, 5, 7}});
        
        assertTrue(mover.doMove(slide8, check).equals(new PuzzleState(new int[][]{{2, 1, 6}, {0, 4, 3}, {8, 5, 7}})));        
    }
    
}
