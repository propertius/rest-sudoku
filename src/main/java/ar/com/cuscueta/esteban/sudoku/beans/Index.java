package ar.com.cuscueta.esteban.sudoku.beans;

public class Index {

    private int i;
    private int j;

    public Index(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Index {" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
