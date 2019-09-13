//TODO --- Checker not importing for some stupid reason...
/**
 * Interface for different checker types
 */
public class CheckerChecker{
    CheckerChecker() {}

    public boolean validate() {
        return false;
    }

    public static void main(String[] args) {
        boolean bugged = false;
        ColorChecker colorChecker = new ColorChecker();
        PostionChecker posChecker = new PostionChecker();
        MoveChecker moveChecker = new MoveChecker();

        bugged = colorChecker.validate() && posChecker.validate() && moveChecker.validate();

        if (bugged) {
            System.out.println("BUG");
        } else {
            System.out.println("CORRECT");
        }

    }

}


/**
 * Checks that the checker is the color it was initialized as
 */
class ColorChecker extends CheckerChecker {
    private Checker redChecker;
    private Checker blackChecker;

    public boolean validate() {
        redChecker = new Checker(true);
        blackChecker = new Checker(false);
        return isCorrectColor();
    }
    private boolean isCorrectColor() {
        return redChecker.isRed() && !blackChecker.isRed();
    }
}


/**
 * Checks that the (x,y) coordinate of the checker is OK.
 * Possible problems :
 *      x and y are swapped
 *      off the board
 *      square is invalid
 */
class PostionChecker extends CheckerChecker {
    private Checker checker;

    public boolean validate() {
        return checkSwap() && checkOff() && checkInvalid();
    }
    private boolean checkSwap() {
        int row =1, col = 2;
        checker = new Checker(true, row, col);
        return checker.getColumn() == col && checker.getRow() == row;
    }
    private boolean checkOff() {
        // off the bottom
        checker = new Checker(true, 1, 3);
        checker.move(-1,-1);
        if (checker.getRow() == 0) {
            return false;
        }
        // off the top
        checker = new Checker(false, 8, 2);
        checker.move(1,-1);
        if (checker.getRow() == 9) {
            return false;
        }
        // off rignt
        checker = new Checker(true, 2, 8);
        checker.move(-1,1);
        if (checker.getColumn() == 9) {
            return false;
        }
        // off left
        checker = new Checker(true, 3, 1);
        checker.move(-1,-1);
        if (checker.getColumn() == 0) {
            return false;
        }
        return true;
    }
    private boolean checkInvalid() {
        checker = new Checker(false, 1,2);
        checker.move(1,1);
        return checker.getColumn() != 3 || checker.getRow() != 2;
    }
}

/**
 * Checks move is allowed
 *      Cant move backwards
 *      Cant move more than one square
 *      Move could be interpreted wrong
 */
class MoveChecker extends CheckerChecker {

    public boolean validate() {
        return checkBack() && checkInterp() && checkCheat();
    }
    private boolean checkBack() {
        Checker redChecker;
        Checker whiteChecker;
        redChecker = new Checker(true, 4,4);
        redChecker.move(1,1);

        if (redChecker.getRow()!=5 || redChecker.getColumn() != 5) {
            return false;
        }

        whiteChecker = new Checker(false, 4,4);
        whiteChecker.move(-1,-1);
        return whiteChecker.getRow() == 3 && whiteChecker.getColumn() == 3;
    }
    private boolean checkInterp() {
        Checker checker = new Checker(true, 4, 4);
        checker.move(-1,1);
        return checker.getColumn() == 5 && checker.getRow() == 3;
    }
    private boolean checkCheat() {
        Checker checker = new Checker(true, 4, 4);
        checker.move(-1, -3);
        return checker.getRow() == 4 && checker.getColumn() == 4;
    }


}


