package advisor;

import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Authorisation {
    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String CLIENT_ID = "XXXXXXXXXXXXXXXXXXXXXXX";
    public static String CLIENT_SECRET = "XXXXXXXXXXXXXXXXXXXXXX";
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_CODE = "";
    public static int CANTIDAD = 0;
    private String datos;
    private DatoCategorie[] datoNuevoAlbums;
    private DatoPlaylist[] datoNuevoFeatured;
    private DatoNuevoAlbum[] datoNuevoNew;
    public static int i = 0;

    /**
     * Getting access_code
     */
    public void getAccessCode() {
        //Creating a line to go to in the browser
        String uri = SERVER_PATH + "/authorize"
                + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code";
        System.out.println("use this link to request the access code:");
        System.out.println(uri);

        //Creating a server and listening to the request.
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            server.start();
            server.createContext("/",
                    new HttpHandler() {
                        public void handle(HttpExchange exchange) throws IOException {
                            String query = exchange.getRequestURI().getQuery();
                            String request;
                            if (query != null && query.contains("code")) {
                                ACCESS_CODE = query.substring(5);
                                System.out.println("code received");
                                System.out.println(ACCESS_CODE);
                                request = "Got the code. Return back to your program.";
                            } else {
                                request = "Authorization code not found. Try again.";
                            }
                            exchange.sendResponseHeaders(200, request.length());
                            exchange.getResponseBody().write(request.getBytes());
                            exchange.getResponseBody().close();
                        }
                    });

            System.out.println("waiting for code...");
            while (ACCESS_CODE.length() == 0) {
                Thread.sleep(100);
            }
            server.stop(5);

        } catch (IOException | InterruptedException e) {
            System.out.println("Server error");
        }
    }

    /**
     * Getting access_token based on access_code
     */
    public void getAccessToken() {

        System.out.println("making http request for access_token...");
        System.out.println("response:");

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(SERVER_PATH + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code"
                                + "&code=" + ACCESS_CODE
                                + "&client_id=" + CLIENT_ID
                                + "&client_secret=" + CLIENT_SECRET
                                + "&redirect_uri=" + REDIRECT_URI))
                .build();

        try {

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            assert response != null;
            getCodigo(response.body());
            System.out.println(response.body());
            System.out.println("Success!");

        } catch (InterruptedException | IOException e) {
            System.out.println("Error response");
        }
    }

    public void getCodigo(String cod) {
        String[] codigo = (cod.replaceAll("[{},:\"]", " ")).trim().split(" ");
        ACCESS_TOKEN = codigo[3];
    }

    public void cliente(String link, String eleccion) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .uri(URI.create(link))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        datos = response.body();
        switch (eleccion) {
            case "new":
                datosJson();
                break;
            case "featured":
                datosJsonFeatured();
                break;
            case "categories":
                datosJsonCategories();
                break;
            case "playlist":
                datosJsonFeatured();
                break;
        }
    }

    public void datosJson() {
        i = 0;
        JsonArray item = jsonObject(datos, "albums").getAsJsonArray("items");
        CANTIDAD = jsonObject(datos, "albums").get("total").getAsInt();
        Gson gson = new Gson();
        datoNuevoNew = gson.fromJson(item, DatoNuevoAlbum[].class);
        for (; i < Integer.parseInt(Service.NUMPAGES); i++) {
            System.out.println(datoNuevoNew[i]);
        }
        i--;
    }
    public void datosJson(int avanza, String movimiento) {
        if (movimiento.equals("next")) {
            for (int j = 0; j < avanza; j++) {
                System.out.println(datoNuevoNew[++i]);
            }
        } else {
            for (int j = 0; j < avanza; j++) {
                System.out.println(datoNuevoNew[--i]);
            }
        }
    }

    public void datosJsonFeatured() {
        i = 0;
        JsonArray item = jsonObject(datos, "playlists").getAsJsonArray("items");
        CANTIDAD = jsonObject(datos, "playlists").get("total").getAsInt();
        Gson gson = new Gson();
        datoNuevoFeatured = gson.fromJson(item, DatoPlaylist[].class);
        for (; i < Integer.parseInt(Service.NUMPAGES); i++) {
            System.out.println(datoNuevoFeatured[i]);
        }
        i--;
    }

    public void datosJsonFeatured(int avanza, String movimiento) {
        if (movimiento.equals("next")) {
            for (int j = 0; j < avanza; j++) {
                System.out.println(datoNuevoFeatured[++i]);
            }
        } else {
            for (int j = 0; j < avanza; j++) {
                System.out.println(datoNuevoFeatured[--i]);
            }
        }
    }

    public void datosJsonCategories() {
        i = 0;
        JsonArray item = jsonObject(datos, "categories").getAsJsonArray("items");
        CANTIDAD = jsonObject(datos, "categories").get("total").getAsInt();
        Gson gson = new Gson();
        datoNuevoAlbums = gson.fromJson(item, DatoCategorie[].class);
        for (; i < Integer.parseInt(Service.NUMPAGES); i++) {
            System.out.println(datoNuevoAlbums[i]);
        }
        i--;
    }

    public void datosJsonCategories(int avanza, String movimiento) {
        if (movimiento.equals("next")) {
            for (int j = 0; j < avanza; j++) {
                System.out.println(datoNuevoAlbums[++i]);
            }
        }else {
            for (int j = 0; j < avanza; j++) {
                System.out.println(datoNuevoAlbums[--i]);
            }
        }
    }

    public JsonObject jsonObject(String datos, String buscar) {
        JsonObject jo = JsonParser.parseString(datos).getAsJsonObject();
        JsonObject bus = jo.getAsJsonObject(buscar);
        return bus;
    }
}
