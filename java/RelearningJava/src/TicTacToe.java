import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe {
    private char[][] board;
    private static final char EMPTY = ' ';

    public TicTacToe() {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = EMPTY;
            }
        }
    }

    private boolean isWinnerRow(char xo, int row) {
        return board[row][0] == xo && board[row][1] == xo && board[row][2] == xo;
    }

    private boolean isWinnerColumn(char xo, int column) {
        return board[0][column] == xo && board[1][column] == xo && board[2][column] == xo;
    }

    private boolean isWinnerDiagonal(char xo) {
        return (board[0][0] == xo && board[1][1] == xo && board[2][2] == xo)
            || (board[2][0] == xo && board[1][1] == xo && board[0][2] == xo);
    }

    public boolean isWinner(char xo) {
        boolean winner = isWinnerDiagonal(xo);
        for (int i = 0; i < 3 && !winner; i++) {
            winner = isWinnerRow(xo, i);
        }
        for (int i = 0; i < 3 && !winner; i++) {
            winner = isWinnerColumn(xo, i);
        }
        return winner;
    }

    private char getValue(BoardSpot bs) {
        return board[bs.x][bs.y];
    }

    private void setValue(char xo, BoardSpot bs) {
        board[bs.x][bs.y] = xo;
    }

    public void play(char xo, BoardSpot bs) {
        if (getValue(bs) == EMPTY) {
            setValue(xo, bs);
        } else {
            System.out.println(bs + " already occupied");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|" + getValue(BoardSpot.TL) + "|" + getValue(BoardSpot.TC) + "|" + getValue(BoardSpot.TR) + "|");
        sb.append("\n");
        sb.append("|" + getValue(BoardSpot.ML) + "|" + getValue(BoardSpot.MC) + "|" + getValue(BoardSpot.MR) + "|");
        sb.append("\n");
        sb.append("|" + getValue(BoardSpot.BL) + "|" + getValue(BoardSpot.BC) + "|" + getValue(BoardSpot.BR) + "|");
        return sb.toString();
    }

    private static void playGame() {
        TicTacToe ttt = new TicTacToe();
        List<BoardSpot> boardSpots = new ArrayList<>(List.of(BoardSpot.values()));
        char playing = 'X';
        boolean winner = false;
        while (!winner && boardSpots.size() > 0) {
            int index = new Random().nextInt(boardSpots.size());
            BoardSpot bs = boardSpots.remove(index);
            ttt.play(playing, bs);
            winner = ttt.isWinner(playing);
            playing = playing == 'X' ? 'O' : 'X';
        }
        System.out.println(ttt);
        System.out.println(ttt.isWinner('X') || ttt.isWinner('O'));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            playGame();
        }
    }
}

enum BoardSpot {
    TL(0,0),
    TC(1, 0),
    TR(2, 0),
    ML(0, 1),
    MC(1, 1),
    MR(2, 1),
    BL(0, 2),
    BC(1, 2),
    BR(2, 2);

    public int x;
    public int y;
    private BoardSpot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
