package dtos.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortenResponse {

    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
