import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // member variables
    private int[][] grid;
    private int n;
    private int countOfOpenSites;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {throw new IllegalArgumentException("N must be nonnegative.");}

        this.grid = new int[n][n];
        this.n = n;
        this.countOfOpenSites = 0;
        this.uf = new WeightedQuickUnionUF(n*n + 2); // add two indices for virtual top and bottom
        this.virtualTop = n*n;
        this.virtualBottom = n*n + 1;
        
        // fill the grid with 1s (blocked)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.grid[i][j] = 1;
            }
        }
    }

    private void checkBounds(int row, int col) {
        if (row > n || row < 1) {throw new IllegalArgumentException(String.format("Row must be within prescribed inclusive range of 1 to {%d}", this.n));}
        if (col > n || col < 1) {throw new IllegalArgumentException(String.format("Column must be within prescribed inclusive range of 0 to {%d}", this.n));}
    }

    private int convert2DCoordinatesTo1D(int row, int col) {
        return row*n + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // check if coordinates are in bounds ; convert to 0-indexed
        this.checkBounds(row, col);
        row--; col--;
        
        if (grid[row][col] == 0) { // already open
            return;
        }
        else { 

            grid[row][col] = 0;
            this.countOfOpenSites++;

            int index = this.convert2DCoordinatesTo1D(row, col);

            // if site is in top row, union to the virtualTop
            if (row == 0) {
                uf.union(this.virtualTop, index);
            }
            
            if (row == n - 1) { 
                uf.union(this.virtualBottom, index);
            }

            // check all 4 directions
            if (row > 0 && grid[row - 1][col] == 0) {uf.union(index, convert2DCoordinatesTo1D(row - 1, col));}
            if (row < n - 1 && grid[row + 1][col] == 0) {uf.union(index, convert2DCoordinatesTo1D(row + 1, col));}
            if (col > 0 && grid[row][col - 1] == 0) {uf.union(index, convert2DCoordinatesTo1D(row, col - 1));}
            if (col < n - 1 && grid[row][col + 1] == 0) {uf.union(index, convert2DCoordinatesTo1D(row, col + 1));}
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.checkBounds(row, col);
        row--;
        col--;

        return this.grid[row][col] == 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        this.checkBounds(row, col);
        row--;
        col--;
        int index = convert2DCoordinatesTo1D(row, col);

        return uf.find(this.virtualTop) == uf.find(index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.countOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(this.virtualBottom) == uf.find(this.virtualTop);
    }

    // test client (optional)
    public static void main(String[] args) {
        
    }
}