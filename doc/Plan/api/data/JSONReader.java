package ooga.controller;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface JSONReader {
    /**
     * Read data from JSON file into a JSONReader object
     *
     * @return the returned JSONReader object with info from the JSON game configuration file
     * @throws IOException
     * @throws ParseException
     */
    //TODO: add deprecated methods
    JSONContainer readJSONConfig();

    String getMostRecentPath();
}
