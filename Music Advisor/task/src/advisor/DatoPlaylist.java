package advisor;

public class DatoPlaylist {
    private String name;
    private Url external_urls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Url getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(Url external_urls) {
        this.external_urls = external_urls;
    }

    @Override
    public String toString() {
        return name + '\n' + external_urls + '\n';
    }
}
