package services.concretes;

import dtos.Request.ShortenRequest;
import dtos.Response.ShortenResponse;
import dtos.Response.UrlDetailResponse;
import entites.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Service;
import repositories.UrlRepository;
import services.abstracts.UrlService;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private final UrlRepository repository;
    @Autowired
    private final ExitCodeGenerator generator;

    private String port;

    public UrlRepository getRepository() {
        return repository;
    }

    public ExitCodeGenerator getGenerator() {
        return generator;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public UrlServiceImpl(UrlRepository repository, ExitCodeGenerator generator, String port) {
        this.repository = repository;
        this.generator = generator;
        this.port = port;
    }

    @Override
    public ShortenResponse shorten(ShortenRequest request) {
        int code = generator.getExitCode();

        Url url = new Url();
        url.setOriginalUrl(request.getUrl());
        url.setShortCode(code);
        url.setClickCount(0);
        url.setCreatedAt(LocalDateTime.now());

        repository.save(url);

        String shortUrl = "http://localhost:" + port + "/" + code;

        ShortenResponse response = new ShortenResponse();
        response.setShortUrl(shortUrl);

        return response;
    }

    @Override
    public String redirect(String code) {

        Url url = repository.findByShortCode(code).orElse(null);

        if (url == null) {
            throw new RuntimeException("Not found");
        }

        long currentClickCount = url.getClickCount();
        url.setClickCount(currentClickCount + 1);

        repository.save(url);

        return url.getOriginalUrl();
    }

    @Override
    public UrlDetailResponse detail(String code) {

        Url url = repository.findByShortCode(code).orElse(null);

        if (url == null) {
            throw new RuntimeException("Not found");
        }

        UrlDetailResponse response = new UrlDetailResponse();
        response.setOriginalUrl(url.getOriginalUrl());
        response.setShortCode(url.getShortCode());
        response.setClickCount(url.getClickCount());
        response.setCreatedAt(url.getCreatedAt());

        return response;
    }
}