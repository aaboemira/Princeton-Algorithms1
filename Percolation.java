import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private final boolean[][] opened;
    private final int size;
    private final int bottom;
    private final WeightedQuickUnionUF qf;
    private int openSites;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        bottom = n * n + 1;
        size = n;
        qf = new WeightedQuickUnionUF(n * n + 2);
        opened = new boolean[n][n];
        openSites = 0;
    }

    public void open(int row, int col) {
        checkException(row, col);
        opened[row - 1][col - 1] = true;
        openSites++;
        if (row == 1) qf.union(getQuickFindIndex(row, col), TOP);
        if (row == size) qf.union(getQuickFindIndex(row, col), bottom);
        if (row > 1 && isOpen(row - 1, col))
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col));
        if (row < size && isOpen(row + 1, col))
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row + 1, col));
        if (col > 1 && isOpen(row, col - 1))
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
        if (col < size && isOpen(row, col + 1))
            qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));

    }

    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return opened[row - 1][col - 1];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean isFull(int row, int col) {
        if ((row > 0 && row <= size) && (col > 0 && col <= size)) {
            return qf.find(TOP) == qf.find(getQuickFindIndex(row, col));
        }
        else throw new IllegalArgumentException();
    }

    private void checkException(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || row > size) throw new IllegalArgumentException();
    }

    public boolean percolates() {
        return qf.find(TOP) == qf.find(bottom);
    }

    private int getQuickFindIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    public static void main(String[] args) {
        Percolation pr = new Percolation(5);
        pr.open(-1, 5);
    }
}
