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
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FunctionsUtil {

    static String SELECTOR = "button.sc-beqWaB.cTPvSS";
    static String FILE_PATH = "src/main/resources/filters/filters.txt";
    static String REPLACEMENT_1 = "+";
    static String REPLACEMENT_2 = "%26";
    static String TARGET_1 = " ";
    static String TARGET_2 = "&";


    @SneakyThrows
    @PostConstruct
    static public List<String> getFunctions()  {
        List<String> functions = new ArrayList<>();
        String html = String.join("", Files.readAllLines(Paths.get(FILE_PATH), StandardCharsets.UTF_8));
        Document document = Jsoup.parse(html);
        Elements buttons = document.select(SELECTOR);
        for (Element button : buttons) {
            String buttonText = button.text();
            buttonText = buttonText.replace(TARGET_1, REPLACEMENT_1).replace(TARGET_2, REPLACEMENT_2);
            functions.add(buttonText);
        }
        return functions;
    }
}
