package matrix;

/**
 * This abstract class partially implements the Matrix API.
 * Only the number of rows and columns are stored. A subclass
 * should create the data structure used to store matrix elements.
 *
 * @author tcolburn
 */
public abstract class AbstractMatrix implements Matrix {

    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     */
    @Override
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     */
    @Override
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Sets the number of rows in this matrix.
     * @param numRows the number of rows in this matrix
     * @throws MatrixException if numRows is not positive
     */
    @Override
    public void setNumRows(int numRows) {
        if (numRows <= 0) {
            throw new MatrixException(String.format("numRows (%s) must be positive", numRows));
        }
        this.numRows = numRows;
    }

    /**
     * Sets the number of columns in this matrix.
     * @param numColumns the number of columns in this matrix
     * @throws MatrixException if numColumns is not positive
     */
    @Override
    public void setNumColumns(int numColumns) {
        if (numColumns <= 0) {
            throw new MatrixException(String.format("numColumns (%s) must be positive", numColumns));
        }
        this.numColumns = numColumns;
    }

    /**
     * Creates a visual representation of this matrix as a string. This method
     * can be used for debugging. This is a template method; it uses a method
     * (get) that must be implemented by a subclass. This method overrides a
     * method in the Object class.
     *
     * @return the string representation of this matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumColumns(); c++) {
                builder.append(String.format("%6s", get(r, c)));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Tests for equality of this matrix with another. Matrices are equal if
     * they have the same dimensions and all elements are equal by ==. This is a
     * template method; it uses a method (get) that must be implemented by a
     * subclass. This method overrides a method in the Object class, so it must
     * type check and cast its argument to the correct type.
     *
     * @param obj the other matrix to be tested for equality with this matrix
     * @return <b>true</b> if the other matrix is equal to this matrix,
     * <b>false</b> otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix)) return false;
        Matrix check = (Matrix) obj;
        int r = this.getNumRows();
        int c = this.getNumColumns();
        if (r != check.getNumRows() || c != check.getNumColumns()) return false;
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (this.get(i, j) != check.get(i, j)) return false;
            }
        }
        return true;
    }

    /**
     * Adds this matrix to another.
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not have
     * the same dimensions
     */
    @Override
    public Matrix add(Matrix other) {
        int r = this.getNumRows();
        int c = this.getNumColumns();
        Matrix add = Matrix.create(r, c);
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                add.set(i, j, this.get(i, j) + other.get(i, j));
            }
        }
        return add;
    }

    /**
     * Multiplies this matrix by another.
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not
     * match the number of rows in the other
     */
    @Override
    public Matrix multiply(Matrix other) {
        int r1 = this.getNumRows();
        int c1 = this.getNumColumns();
        int r2 = other.getNumRows();
        int c2 = other.getNumColumns();
        Matrix pMatrix = Matrix.create(r1, c2);
        if (c1 != r2) throw new MatrixException("The number of columns (" + c1 + ") in this matrix does not equal the number of rows (" + r2 + ") in the other matrix.");
        for (int r = 0; r < r1; ++r) {
            for (int c = 0; c < c2; ++c) {
                int product = 0;
                for (int i = 0; i < c1; ++i) {
                    product += this.get(r, i) * other.get(i, c);
                }
                pMatrix.set(r, c, product);
            }
        }
        return pMatrix;
    }
    
    /**
     * Private instance fields follow
     */

    private int numRows;
    private int numColumns;

}