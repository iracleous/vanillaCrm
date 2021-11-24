package gr.codehub.vanillahr;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp {


   private static Logger logger  = Logger.getLogger( MainApp.class.getName());




    public static void main(String[] args) {

        // log messages using log(Level level, String msg)
        logger.log(Level.INFO, "This is message 1");
        logger.log(Level.WARNING, "This is message 2");



        UseCase useCase = new UseCase();
        useCase.testUseCase();



    }
}
