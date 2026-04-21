package service;

import dto.request.ShortenRequest;
import dto.response.ShortenResponse;
import dto.response.UrlDetailResponse;

public interface UrlService {

    ShortenResponse shorten(ShortenRequest request);

    String redirect(String code);

    UrlDetailResponse detail(String code);
}