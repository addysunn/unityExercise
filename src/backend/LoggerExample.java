package backend;

import java.io.*;

public class LoggerExample {
    public static void main(String[] args) {
        Logger debugLogger = new StandardLogger(Logger.LOG_LEVEL_DEBUG, new StdOut());
        debugLogger.Debug("Logger prints Debug statement \n");

        Logger errLogger = new StandardLogger(Logger.LOG_LEVEL_ERROR, new ErrOut());
        errLogger.Error("Logger prints Error statement \n");

        Logger fileLogger = new StandardLogger(Logger.LOG_LEVEL_INFO, new FileOut());
        fileLogger.Info("File Logger prints info statement \n");
    }
}

class StandardLogger implements Logger {
    private final Output output;
    private final int logLevel;
    public StandardLogger(int logLevel, Output output) {
        this.logLevel = logLevel;
        this.output = output;
    }
    public void Debug(String msg) {
        log(Logger.LOG_LEVEL_DEBUG, msg);
    }
    public void Error(String msg) {
        log(Logger.LOG_LEVEL_ERROR, msg);
    }
    public void Info(String msg) {
        log(Logger.LOG_LEVEL_INFO, msg);
    }
    void log(int requestedLevel, String msg) {
        if ((requestedLevel & this.logLevel) > 0) {
            this.output.write(msg);
        }
    }
}

interface Logger {
    public final static int LOG_LEVEL_INFO = 0x0002 ;
    public final static int LOG_LEVEL_ERROR = 0x0004 ;
    public final static int LOG_LEVEL_DEBUG = 0x0008 ;
    public void Debug (String msg);
    public void Error (String msg);
    public void Info (String msg);
}

interface Output {
    public void write (String msg);
}

class StdOut implements Output {
    @Override
    public void write(String msg) {
        System.out.println(msg);
    }
}

class ErrOut implements Output{
    @Override
    public void write(String msg) {
        System.err.println(msg);
    }
}

class FileOut implements Output {
    @Override
    public void write(String msg) {
        System.out.println("FileOut.write()");

//        try {
//            Writer writer = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(fileWithDirectoryAssurance("/tmp", "log")), "utf-8"));
//            writer.write(msg);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("log"), "utf-8"))) {
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File fileWithDirectoryAssurance(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return new File(directory + "/" + filename);
    }
}