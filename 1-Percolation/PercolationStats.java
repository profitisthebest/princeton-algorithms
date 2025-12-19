import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // member variables
    private int n;
    private int trials;
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0) throw new IllegalArgumentException("N must be greater than 0.");
        if (trials <= 0) throw new IllegalArgumentException("The number of trials must be greater than 0.");

        this.n = n;
        this.trials = trials;
        this.thresholds = new double[trials];

        // fill the thresholds array
        int total_sites = n*n;
        for (int i = 0; i < trials; i++) {
            Percolation my_perc = new Percolation(n);           
            while (!my_perc.percolates()) { // while not percolating, keep opening sites
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                my_perc.open(row, col);
            }

            // calculate threshold
            int open_sites = my_perc.numberOfOpenSites();
            thresholds[i] = (double) open_sites / total_sites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / Math.sqrt(this.trials));
    }

   // test client (see below)
   public static void main(String[] args) {

    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);

    PercolationStats stats = new PercolationStats(n, trials);
    
    System.out.println("mean                    = " + stats.mean());
    System.out.println("stddev                  = " + stats.stddev());
    System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
   }
}
