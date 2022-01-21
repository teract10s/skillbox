import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args){
        try {
            Document doc = Jsoup.connect("https://lenta.ru/").get();

            Elements elements = doc.select("img");
            AtomicInteger i = new AtomicInteger();

            elements.forEach(element -> {
                String absSrc = element.attr("abs:src");
                String fileName = "src/main/resources/photo/" + absSrc.substring(61);

                System.out.println(fileName);
                i.getAndIncrement();
                downloadPhoto(absSrc, fileName);
            });

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void downloadPhoto(String strURL, String fileName){
        try{
            URL url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

            File f1 = new File(fileName);
            FileOutputStream fw = new FileOutputStream(f1);

            byte[] b = new byte[1024];
            int count;

            while ((count=bis.read(b)) != -1) {
                fw.write(b, 0, count);
            }

            fw.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
