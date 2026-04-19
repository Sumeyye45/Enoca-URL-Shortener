package dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class ShortenRequest {

    @NotBlank(message = "URL boş olamaz")
    @Pattern(
            regexp = "^(https?://).+",
            message = "Geçerli bir URL giriniz (http/https olmalı)"
    )
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
