public class Percolation {

    // member variables
    private int[][] grid;
    private int n;
    private int countOfOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n < 0) {throw new IllegalArgumentException("N must be nonnegative.");}

        this.grid = new int[n][n];
        this.n = n;
        this.countOfOpenSites = 0;
        
        // fill the grid with 1s (blocked)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.grid[i][j] = 1;
            }
        }
    }

    private void check_bounds(int row, int col) {
        if (row >= n || row < 0) {throw new IllegalArgumentException(String.format("Row must be within prescribed inclusive range of 0 to {%d} - 1", this.n));}
        if (col >= n || col < 0) {throw new IllegalArgumentException(String.format("Column must be within prescribed inclusive range of 0 to {%d} - 2", this.n));}
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        this.check_bounds(row, col);

        if (this.grid[row][col] == 1) {
            this.grid[row][col] = 0;
            this.countOfOpenSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.check_bounds(row, col);
        return this.grid[row][col] == 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.countOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}