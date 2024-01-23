package oksana.dvornitska.interview.utils;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FunctionsUtil {

    static String selector = "button.sc-beqWaB.cTPvSS";
    final static String FILE_PATH = "src/main/resources/filters/filters.txt";

    @SneakyThrows
    @PostConstruct
    static public List<String> getFunctions()  {
        List<String> functions = new ArrayList<>();
        String html = String.join("", Files.readAllLines(Paths.get(FILE_PATH), StandardCharsets.UTF_8));
        Document document = Jsoup.parse(html);
        Elements buttons = document.select(selector);
        for (Element button : buttons) {
            String buttonText = button.text();
            buttonText = buttonText.replace(" ", "+").replace("&", "%26");
            functions.add(buttonText);
        }
        return functions;
    }
}
