package advisor;

import java.io.IOException;

public class Service {
    boolean isAuthorised = false;
    private Authorisation pedido;
    public static String NUMPAGES = "5";
    public static String api = "https://api.spotify.com";
    String releases = api + "/v1/browse/new-releases?limit=" + NUMPAGES;
    String featured = api + "/v1/browse/featured-playlists?limit=" + NUMPAGES;
    String categories = api + "/v1/browse/categories?limit=" + NUMPAGES;
    String playlists = api + "/v1/browse/categories/";
    String opcion;
    private int estado = 1;
    private int paginacion;

    public void setAuthorization() {
        Authorisation authorisation = new Authorisation();
        authorisation.getAccessCode();
        authorisation.getAccessToken();
        this.isAuthorised = true;
    }

    public void getReleases() throws IOException, InterruptedException {
        if (isAuthorised) {
            opcion = "new";
            pedido = new Authorisation();
            pedido.cliente(releases, "new");
            paginacion = (Authorisation.CANTIDAD / Integer.parseInt(NUMPAGES));
            System.out.printf("---PAGE %d OF %d--- \n", estado, paginacion);
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    public void getFeatured() throws IOException, InterruptedException {
        if (isAuthorised) {
            opcion = "featured";
            pedido = new Authorisation();
            pedido.cliente(featured, "featured");
            paginacion = (Authorisation.CANTIDAD / Integer.parseInt(NUMPAGES));
            System.out.printf("---PAGE %d OF %d--- \n", estado, paginacion);
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    public void getCategories() throws IOException, InterruptedException {
        if (isAuthorised) {
            opcion = "categories";
            pedido = new Authorisation();
            pedido.cliente(categories, "categories");
            paginacion = (Authorisation.CANTIDAD / Integer.parseInt(NUMPAGES));
            System.out.printf("---PAGE %d OF %d--- \n", estado, paginacion);
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    public void getPlaylists(String inf) {
        if (isAuthorised) {
            String link = playlists + inf.toLowerCase() + "/playlists?limit=" + NUMPAGES;
            pedido = new Authorisation();
            try {
                opcion = "playlist";
                pedido.cliente(link, "playlist");
                paginacion = (Authorisation.CANTIDAD / Integer.parseInt(NUMPAGES));
                System.out.printf("---PAGE %d OF %d--- \n", estado, paginacion);
            } catch (IOException e) {
                System.out.println("Specified id doesn't exist");
            } catch (InterruptedException e) {
                System.out.println("Specified id doesn't exist");
            } catch (NullPointerException e) {
                System.out.println("Specified id doesn't exist");
            } catch (IllegalStateException e) {
                System.out.println("Test unpredictable error message");
            }
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    public void next() {
        if (estado == paginacion) {
            System.out.println("No more pages.");
        } else {
            estado++;
            if (opcion.equals("categories")) {
                pedido.datosJsonCategories(Integer.parseInt(NUMPAGES), "next");
            } else if (opcion.equals("featured") || opcion.equals("playlist")) {
                pedido.datosJsonFeatured(Integer.parseInt(NUMPAGES), "next");
            } else if (opcion.equals("new")) {
                pedido.datosJson(Integer.parseInt(NUMPAGES), "next");
            }
            paginacion = (Authorisation.CANTIDAD / Integer.parseInt(NUMPAGES));
            System.out.printf("---PAGE %d OF %d--- \n", estado, paginacion);
        }
    }

    public void prev() {
        estado--;
        if (estado == 0) {
            System.out.println("No more pages.");
            estado++;
        } else {
            if (opcion.equals("categories")) {
                pedido.datosJsonCategories(Integer.parseInt(NUMPAGES), "prev");
            } else if (opcion.equals("featured") || opcion.equals("playlist")) {
                pedido.datosJsonFeatured(Integer.parseInt(NUMPAGES), "prev");
            } else if (opcion.equals("new")) {
                pedido.datosJson(Integer.parseInt(NUMPAGES), "prev");
            }
            paginacion = (Authorisation.CANTIDAD / Integer.parseInt(NUMPAGES));
            System.out.printf("---PAGE %d OF %d--- \n", estado, paginacion);
        }
    }
}
