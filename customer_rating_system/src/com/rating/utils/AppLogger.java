package com.rating.utils;

import java.util.logging.Logger;

public class AppLogger {
    private static final Logger logger = Logger.getLogger("RatingSystem");
    public static Logger get() { return logger; }
}
