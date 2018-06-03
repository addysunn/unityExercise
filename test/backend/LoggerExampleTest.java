package backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class LoggerExampleTest {

    /** some test cases might fail based on the differences in line separators
     */

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    File file=new File("log");
    BufferedReader fileReader=null;


    public LoggerExampleTest() throws FileNotFoundException {
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testStandardLogger() {
        Logger debugLogger = new StandardLogger(Logger.LOG_LEVEL_DEBUG, new StdOut());
        debugLogger.Debug("Logger prints Debug statement");
        assertEquals("Logger prints Debug statement\r\n", outContent.toString());

    }

    @Test
    public void testFileLogger() {
        Logger fileLogger = new StandardLogger(Logger.LOG_LEVEL_INFO, new FileOut());
        fileLogger.Info("File Logger prints info statement");
        String line="",fileData="";
        try{
            while((line=fileReader.readLine())!=null)
            {
                fileData=line;
            }

        }catch(IOException ex){}
        assertEquals("File Logger prints info statement", fileData);

    }

    @Test
    public void testSystemErrorLogger() {
        Logger errLogger = new StandardLogger(Logger.LOG_LEVEL_ERROR, new ErrOut());
        errLogger.Error("Logger prints Error statement");
        assertEquals("Logger prints Error statement\r\n", errContent.toString());
    }

    @Test
    public void testLogMisMatchStandardLogger() throws IllegalArgumentException {
        Logger logger = new StandardLogger(Logger.LOG_LEVEL_ERROR, new ErrOut());
        logger.Debug("should print as error messages");
        assertEquals("", outContent.toString());
    }

    @Test
    public void testLogMisMatchSystemErrorLogger() throws IllegalArgumentException {
        Logger logger = new StandardLogger(Logger.LOG_LEVEL_ERROR, new ErrOut());
        logger.Debug("should print as error messages");
        assertEquals("", errContent.toString());
    }

}