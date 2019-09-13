//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Checker {
    private boolean isRed;
    private int row;
    private int column;
    private static final int firstRow = 1;
    private static final int lastRow = 8;
    private static final int firstColumn = 1;
    private static final int lastColumn = 8;
    private static final int NO_BUG = 0;
    private static final int ALWAYS_RED = 1;
    private static final int TRANSPOSE_POS = 2;
    private static final int TRANSPOSE_MOVE = 3;
    private static final int ALLOW_BACKWARDS = 4;
    private static final int ALLOW_TOO_FAR = 5;
    private static final int ALLOW_OFF_LEFT = 6;
    private static final int ALLOW_OFF_RIGHT = 7;
    private static final int ALLOW_OFF_TOP = 8;
    private static final int ALLOW_OFF_BOTTOM = 9;
    private static final int ALLOW_INVALID_SQUARE = 10;
    private static int curr_bug =2;

    public static void setBug(int bug) {
        curr_bug = bug;
    }

    private static boolean bugIs(int bug) {
        return bug == curr_bug;
    }

    public Checker(boolean isRed) {
        this(bugIs(1) || isRed, 1, 1);
    }

    public Checker(boolean isRed, int row, int column) {
        this.isRed = isRed;
        if (!this.validSquare(row, column)) {
            row = 1;
            column = 1;
        }

        this.row = bugIs(2) ? column : row;
        this.column = bugIs(2) ? row : column;
    }

    public boolean isRed() {
        return this.isRed;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void move(int rows, int columns) {
        if (bugIs(3)) {
            this.moveIfValid(columns, rows, 1);
        } else {
            this.moveIfValid(rows, columns, 1);
        }

    }

    private void moveIfValid(int rows, int columns, int validSize) {
        if (this.validStep(rows, columns, validSize)) {
            this.row += rows;
            this.column += columns;
        }

    }

    private boolean validStep(int rows, int columns, int validSize) {
        return (bugIs(5) || Math.abs(rows) == validSize) && (bugIs(5) || Math.abs(columns) == validSize) && (bugIs(4) || this.isRed == rows < 0) && this.validSquare(this.row + rows, this.column + columns);
    }

    private boolean validSquare(int row, int column) {
        return (bugIs(9) || row >= 1) && (bugIs(8) || row <= 8) && (bugIs(6) || column >= 1) && (bugIs(7) || column <= 8) && (bugIs(10) || column % 2 == row % 2);
    }
}
