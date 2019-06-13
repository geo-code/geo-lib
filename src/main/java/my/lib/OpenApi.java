package my.lib;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OpenApi {
    public BookInfo get(String id) {
        return parse(getList("all", id).get(0));
    }

    public List<BookInfo> search(String query) {
        Stream<BookInfo> stream = getList("all", query).stream().filter(this::checkIsbn).map(this::parse);
        return stream.collect(Collectors.toList());
    }

    private boolean checkIsbn(Map m) {
        return StringUtils.isNotBlank((String) m.get("isbn"));
    }

    private List<Map> getList(String target, String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 0af5f5d3d06bd0c9b9d1909022f7206b");
        HttpEntity entity = new HttpEntity(headers);
        String url = String.format("https://dapi.kakao.com/v3/search/book?target=%s&size=20&query=%s", target, query);
        Map response = new RestTemplate().exchange(url, HttpMethod.GET, entity, Map.class).getBody();
        return (List<Map>) response.get("documents");
    }

    @SneakyThrows
    private BookInfo parse(Map<String, Object> map) {
        BookInfo book = new BookInfo();
        book.setIsbn(((String) map.get("isbn")).split(" ")[1]);
        book.setTitle((String) map.get("title"));
        try {
            book.setAuthor(((List<String>) map.get("authors")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        book.setPublisher((String) map.get("publisher"));
        book.setPrice((Integer) map.get("sale_price"));
        book.setCoverUrl((String) map.get("thumbnail"));
        book.setPublishedAt(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse((String) map.get("datetime")));
        return book;
    }

    @Data
    public static class BookInfo {
        private String isbn;
        private String title;
        private String author;
        private String publisher;
        private Date publishedAt;
        private Integer price;
        private String coverUrl;

        @SneakyThrows
        @JsonIgnore
        public byte[] getCoverBytes() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(ImageIO.read(new URL(coverUrl)), "jpg", baos);
            return baos.toByteArray();
        }
    }
}
