package com.waldron.ptcproject.io;

public enum CsvConstants {
    ROW_SEPARATOR(","),
    NEW_LINE("\n"),
    PROPERTY_FILE_NAME("application.properties"),
    PROPERTY_NAME("file.name"),
    RESOURCE_PATH("/static/");

    public final String label;

    private CsvConstants(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

}
