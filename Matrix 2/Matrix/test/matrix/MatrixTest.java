package matrix;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Matrix API.
 * @author tcolburn
 */
public class MatrixTest {
    
    public MatrixTest() {
        m3x2 = Matrix.create(3, 2);
        m2x3 = Matrix.create(2, 3);
        m3x3 = Matrix.create(3, 3);
    }

    @Test
    public void testDimensions() {
        assertTrue(m3x2.getNumRows() == 3);
        assertTrue(m3x2.getNumColumns() == 2);
    }

    @Test
    public void testBounds() {
        // these checks should succeed
        for (int r = 0; r < m3x2.getNumRows(); r++) {
            for (int c = 0; c < m3x2.getNumColumns(); c++) {
                m3x2.checkBounds(r, c);
            }
        }
        // Test for exceptions
        tryBadIndex(-1, 2); // bad row
        tryBadIndex(2, -1); // bad column
        tryBadIndex(3, 1);  // bad row
        tryBadIndex(2, 2);  // bad column
    }
    
    private void tryBadIndex(int row, int col) { // row or column should be
        try {                                    // out of bounds
            m3x2.checkBounds(row, col);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) { }
    }
    
    @Test
    public void testGet() {  // m should have all zeroes
        for (int r = 0; r <  m3x2.getNumRows(); r++) 
            for (int c = 0; c < m3x2.getNumColumns(); c++) 
                assertTrue(m3x2.get(r, c) == 0);
//        System.out.println(m3x2);
    }
    
    @Test
    public void testSetAndGet() {
        m3x2.set(0, 0, 1);   m3x2.set(0, 1, 4);  // 1 4
        m3x2.set(1, 0, 2);   m3x2.set(1, 1, 5);  // 2 5
        m3x2.set(2, 0, 3);   m3x2.set(2, 1, 6);  // 3 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 4);
        assertTrue(m3x2.get(1, 0) == 2);  assertTrue(m3x2.get(1, 1) == 5);
        assertTrue(m3x2.get(2, 0) == 3);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testClear() {
        m3x2.set(0, 0, 1);   m3x2.set(0, 1, 4);  // 1 4
        m3x2.set(1, 0, 2);   m3x2.set(1, 1, 5);  // 2 5
        m3x2.set(2, 0, 3);   m3x2.set(2, 1, 6);  // 3 6
        
        m3x2.clear();
        
        assertTrue(m3x2.get(0, 0) == 0);  assertTrue(m3x2.get(0, 1) == 0);
        assertTrue(m3x2.get(1, 0) == 0);  assertTrue(m3x2.get(1, 1) == 0);
        assertTrue(m3x2.get(2, 0) == 0);  assertTrue(m3x2.get(2, 1) == 0);
    }
    
    @Test
    public void testFillRowWise() {
        m3x2.fillRowWise();           // 1 2
                                      // 3 4
                                      // 5 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 2);
        assertTrue(m3x2.get(1, 0) == 3);  assertTrue(m3x2.get(1, 1) == 4);
        assertTrue(m3x2.get(2, 0) == 5);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testFillColumnWise() {
        m3x2.fillColumnWise();        // 1 4
                                      // 2 5
                                      // 3 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 4);
        assertTrue(m3x2.get(1, 0) == 2);  assertTrue(m3x2.get(1, 1) == 5);
        assertTrue(m3x2.get(2, 0) == 3);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testTranspose() {
        m3x2.fillColumnWise();          // 1 4
                                        // 2 5
                                        // 3 6
                                        
        m2x3 = m3x2.transpose();        // 1 2 3
                                        // 4 5 6
        assertTrue(m2x3.get(0, 0) == 1 && m2x3.get(0, 1) == 2 && m2x3.get(0, 2) == 3);
        assertTrue(m2x3.get(1, 0) == 4 && m2x3.get(1, 1) == 5 && m2x3.get(1, 2) == 6);
    }
    
    @Test
    public void testMakeIdentity() {
        try {                                    
            m3x2.makeIdentity();
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) { }
        
        m3x3.makeIdentity();    // 1 0 0
                                // 0 1 0
                                // 0 0 1
        assertTrue(m3x3.get(0, 0) == 1 && m3x3.get(0, 1) == 0 && m3x3.get(0, 2) == 0);
        assertTrue(m3x3.get(1, 0) == 0 && m3x3.get(1, 1) == 1 && m3x3.get(1, 2) == 0);
        assertTrue(m3x3.get(2, 0) == 0 && m3x3.get(2, 1) == 0 && m3x3.get(2, 2) == 1);
    }
    
    /**
     * This test should test the equality of:
     *   1. A matrix with itself (should be true)
     *   2. Several pairs of matrices of differing dimensions 
     *      (should be false)
     *   3. Two empty matrices of the same dimensions (should be true)
     *   4. Two nonempty matrices of the same dimensions with the same 
     *      values for elements (should be true)
     *   5. Two nonempty matrices of the same dimensions with the same 
     *      values except for one element (should be false)
     *   6. A nonempty matrix with the transpose of the transpose of
     *      itself (should be true)
     */
    @Test
    public void testEquals() {
	// You must provide
        // 1.
        m3x2.fillRowWise();
        m3x3.fillRowWise();
        m2x3.fillRowWise();
        assertTrue(m3x2.equals(m3x2) == true);
        // 2.
        assertTrue(m3x2.equals(m2x3) == false);
        assertTrue(m3x2.equals(m3x3) == false);
        assertTrue(m3x3.equals(m2x3) == false);
        // 3.
        m3x3 = Matrix.create(3, 3);
        Matrix m3x3p2 = Matrix.create(3, 3);
        assertTrue(m3x3.equals(m3x3p2) == true);
        
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                m3x3.set(i, j, 3 * i + 4 * j);
                m3x3p2.set(i, j, 3 * i + 4 * j);
            }
        }
        // 4.
        assertTrue(m3x3.equals(m3x3p2) == true);
        // 5.
        m3x3p2.set(2, 2, 100);
        assertTrue(m3x3.equals(m3x3p2) == false);
        // 6.
        assertTrue(m3x3.equals(m3x3.transpose().transpose()) == true);
    }
    
    /**
     * This test should:
     *   1. Try to add two matrices of different dimensions and catch the
     *      thrown exception
     *   2. Add two empty matrices of the same dimensions and confirm
     *      the result with assertions
     *   3. Add two nonempty matrices of the same dimensions and confirm
     *      the result with assertions
     */
    @Test
    public void testAdd() {
//	 You must provide
        // 1.
        try {
            m3x3.add(m3x2);
        } catch (MatrixException e) { }
        
        // 2.
        Matrix m1 = Matrix.create(2, 2);
        Matrix m2 = Matrix.create(2, 2);
        Matrix m3 = m1.add(m2);
        Matrix test = Matrix.create(2, 2);
        System.out.println(test);
        assertTrue(m3.equals(test));
        
        // 3.
        m1.fillRowWise();
        m2.fillColumnWise();
        m3 = m1.add(m2);
        test = Matrix.create(2, 2);
        test.set(0, 0, 2);
        test.set(0, 1, 5);
        test.set(1, 0, 5);
        test.set(1, 1, 8);
        assertTrue(m3.equals(test) == true);
    }
    
    /**
     * This test should:
     *   1. Try to multiply several pairs of incompatible matrices and catch the
     *      thrown exceptions
     *   2. Multiply two nonempty compatible matrices and confirm
     *      the result with assertions
     *   3. Multiply a nonempty square matrix by the identity matrix of the same
     *      dimensions and confirm that the result is the original matrix
     */
    @Test
    public void testMultiply() {
	// You must provide
        // 1.
        Matrix m1 = Matrix.create(1, 2);
        Matrix m2 = Matrix.create(3, 4);
        Matrix m3 = Matrix.create(5, 2);
        
        try {
            m1.multiply(m2);
        } catch (MatrixException e) { }
        
        try {
            m1.multiply(m3);
        } catch (MatrixException e) { }
        
        try {
            m2.multiply(m3);
        } catch (MatrixException e) { }
        
        // 2.
        m1 = Matrix.create(3, 3);
        m2 = Matrix.create(3, 3);
        m3 = Matrix.create(3, 3);
        m1.fillRowWise();
        m2.fillColumnWise();
        m3.set(0, 0, 14);
        m3.set(1, 0, 32);
        m3.set(0, 1, 32);
        m3.set(0, 2, 50);
        m3.set(2, 0, 50);
        m3.set(1, 1, 77);
        m3.set(1, 2, 122);
        m3.set(2, 1, 122);
        m3.set(2, 2, 194);
        Matrix product = m1.multiply(m2);
        
        assertTrue(product.equals(m3) == true);
        
        // 3.
        m2.makeIdentity();
        assertTrue(m1.multiply(m2).equals(m1) == true);
    }
    
    private final Matrix m3x2;
    private Matrix m2x3;
    private Matrix m3x3;
}