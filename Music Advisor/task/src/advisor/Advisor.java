package advisor;

import java.io.IOException;
import java.util.Scanner;

public class Advisor {
    public void start() throws IOException, InterruptedException {

        Service service = new Service();
        Scanner scanner = new Scanner(System.in);
        String[] query = scanner.nextLine().split(" ");
        while (!query[0].equals("exit")) {
            switch (query[0]) {
                case ("auth"):
                    service.setAuthorization();
                    break;
                case ("new"):
                    service.getReleases();
                    break;
                case ("featured"):
                    service.getFeatured();
                    break;
                case ("categories"):
                    service.getCategories();
                    break;
                case ("playlists"):
                    service.getPlaylists(query[1]);
                    break;
                case ("next"):
                    service.next();
                    break;
                case ("prev"):
                    service.prev();
                    break;
            }
            query = scanner.nextLine().split(" ");
        }
        System.out.println("---GOODBYE!---");
    }
}