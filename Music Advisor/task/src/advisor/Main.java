package advisor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")) {
            Authorisation.SERVER_PATH = args[1];
        }
        if (args[2].equals("-resource")) {
            Service.api = args[3];
        }
        if (args[4].equals("-page")) {
            Service.NUMPAGES = args[5];
        }

        Advisor advisor = new Advisor();
        try {
            advisor.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}