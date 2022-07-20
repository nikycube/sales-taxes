package org.lastminute.salestaxes.util;


import java.io.InputStream;

public final class FileUtils {
    //utility class
    private FileUtils() {
    }

    /**
     * get InputStream for a file name. If the file doesn't exist throw an exception.
     * @param fileName given fileName
     * @return {@ref InputStream} of a file
     */
    public static InputStream getFileAsIOStream(final String fileName) {
        InputStream ioStream = FileUtils.class
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }
}
