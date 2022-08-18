import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Conexão HTTP
        String url = "https://imdb-api.com/en/API/Top250Movies/k_icaoary0";
        HttpClient client = HttpClient.newHttpClient();
        URI address = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Extração de dados
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);


        // Exibição e manipulação de dados delimitado a 5
        ImageExporter imageExporter = new ImageExporter();
        for (int i = 0; i < 5; i++) {
            Map<String, String> filme = listaFilmes.get(i);

            String urlImage = filme.get("image")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();


            String Title = title.replace(":", "");
            String fileName ="saida/" + Title + ".png";
            System.out.println("Got post image from: " + title);
            imageExporter.create(inputStream, fileName);
        }
    }
}