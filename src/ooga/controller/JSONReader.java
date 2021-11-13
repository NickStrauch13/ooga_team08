package ooga.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class JSONReader {
    public JSONReader(BufferedReader reader) {
    }

    /**
     * reads data from CSV file to CSV container
     *
     * @param file is the file containing a configuration
     * @return properly initialized CSV file
     */
//    public JSONReader readAllDataCSV(File file) {
//        try {
//
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            JSONReader csvReader = new JSONReader(reader)
//                    .build();
//            List<String[]> allData = csvReader.readAll();
//
//            int numOfRows = Integer.parseInt(allData.get(0)[1].trim());
//            int numOfCols = Integer.parseInt(allData.get(0)[0].trim());
//
//            int[][] statusGrid = new int[numOfRows][numOfCols];
//            for (int r = 1; r < allData.size(); r++) {
//                for (int c = 0; c < allData.get(1).length; c++) {
//                    statusGrid[r - 1][c] = Integer.parseInt(allData.get(r)[c].trim());
//                }
//            }
//            System.out.println("Number of rows: " + numOfRows);
//            System.out.println("Number of cols: " + numOfCols);
//            for (int r = 0; r < statusGrid.length; r++) {
//                for (int c = 0; c < statusGrid[0].length; c++) {
//                    System.out.print(statusGrid[r][c] + "\t"); // means insert a tab
//                }
//                System.out.println();
//            }
//            CSVContainer CSVInformation = new CSVContainer(numOfRows, numOfCols, statusGrid);
//            return CSVInformation;
//        } catch (Exception e) {
//            errorHandler.handleError(ERROR_CSV);
//            return null;
//        }
//    }
}
