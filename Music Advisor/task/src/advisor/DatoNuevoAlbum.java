package advisor;

import java.util.Arrays;

public class DatoNuevoAlbum {
    private String name;
    private NombreArtista[] artists;
    private Url external_urls;

    public Url getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(Url external_urls) {
        this.external_urls = external_urls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + "\n" + Arrays.toString(artists) + "\n" + external_urls + "\n";
    }
}
