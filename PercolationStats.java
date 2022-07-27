import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private final int expermientsCount;
    private final double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        expermientsCount = trials;
        fractions = new double[expermientsCount];
        for (int exp = 0; exp < expermientsCount; exp++) {
            Percolation pr = new Percolation(n);
            int openedSites = 0;
            while (!pr.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!pr.isOpen(i, j)) {
                    pr.open(i, j);
                    openedSites++;
                }
            }
            double fraction = (double) openedSites / (n * n);
            fractions[exp] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }


    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE * stddev()) / Math.sqrt(expermientsCount);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE * stddev()) / Math.sqrt(expermientsCount);
    }

    // test client (see below)
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(x, y);
        String confidence = "[" + ps.confidenceLo() + "," + ps.confidenceHi() + "]";
        StdOut.printf("mean                    = %f \n", ps.mean());
        StdOut.printf("stddev                  = %f \n", ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);

    }

}