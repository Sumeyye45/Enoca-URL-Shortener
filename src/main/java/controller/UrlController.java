package controller;


import dto.request.ShortenRequest;
import dto.response.ShortenResponse;
import dto.response.UrlDetailResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import service.UrlService;

@RestController
public class UrlController {

    @Resource
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ShortenResponse shorten(@Valid @RequestBody ShortenRequest request) {
        return service.shorten(request);
    }

    @GetMapping("/{code}")
    public RedirectView redirect(@PathVariable String code) {
        String url = service.redirect(code);
        return new RedirectView(url);
    }

    @GetMapping("/urls/{code}")
    public UrlDetailResponse details(@PathVariable String code) {
        return service.detail(code);
    }
}