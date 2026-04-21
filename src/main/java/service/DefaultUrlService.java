package service;

import com.sumeyye.demo.exception.ResourceNotFoundException;
import dto.request.ShortenRequest;
import dto.response.ShortenResponse;
import dto.response.UrlDetailResponse;
import entity.Url;
import lombok.Value;
import org.springframework.stereotype.Service;
import repository.UrlRepository;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DefaultUrlService implements UrlService {

    private final UrlRepository repository;

    @Value("${server.port}")
    private String port;

    public DefaultUrlService(UrlRepository repository, String port) {
        this.repository = repository;
        this.port = port;
    }

    @Override
    public ShortenResponse shorten(ShortenRequest request) {

        String code = generateShortCode();

        Url url = new Url();
        url.setOriginalUrl(request.getUrl());
        url.setShortCode(code);
        url.setClickCount(0);
        url.setCreatedAt(LocalDateTime.now());

        repository.save(url);

        String shortUrl = "http://localhost:" + port + "/" + code;

        return new ShortenResponse(shortUrl);
    }

    @Override
    public String redirect(String code) {

        Url url = repository.findByShortCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Url not found"));

        url.setClickCount(url.getClickCount() + 1);
        repository.save(url);

        return url.getOriginalUrl();
    }

    @Override
    public UrlDetailResponse detail(String code) {

        Url url = repository.findByShortCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Url not found"));

        return new UrlDetailResponse(
                url.getOriginalUrl(),
                url.getShortCode(),
                url.getClickCount(),
                url.getCreatedAt()
        );
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }


}