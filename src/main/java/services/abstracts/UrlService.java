package services.abstracts;

import dtos.Request.ShortenRequest;
import dtos.Response.ShortenResponse;
import dtos.Response.UrlDetailResponse;

public interface UrlService {

    ShortenResponse shorten(ShortenRequest request);
    String redirect(String code);
    UrlDetailResponse detail(String code);
}