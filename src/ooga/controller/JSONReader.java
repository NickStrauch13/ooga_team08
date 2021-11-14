package ooga.controller;

import java.util.List;

public class JSONReader {

    private List<List<Integer>> myInfo;
    private int myNumOfRows;
    private int myNumOfCols;

    public JSONReader(int rows, int cols, List<List<Integer>> boardInfo) {
        myNumOfRows = rows;
        myNumOfCols = cols;
        myInfo = boardInfo;
    }

    public List<List<Integer>> getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(List<List<Integer>> myInfo) {
        this.myInfo = myInfo;
    }

    public int getMyNumOfRows() {
        return myNumOfRows;
    }

    public void setMyNumOfRows(int myNumOfRows) {
        this.myNumOfRows = myNumOfRows;
    }

    public int getMyNumOfCols() {
        return myNumOfCols;
    }

    public void setMyNumOfCols(int myNumOfCols) {
        this.myNumOfCols = myNumOfCols;
    }
}
