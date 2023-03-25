package kz.shortener.domain.url;

import kz.shortener.api.model.UrlRequest;
import kz.shortener.api.model.UrlResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    private static final String DOMAIN = "http://localhost:8080/";

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    public UrlResponse createShortUrl(UrlRequest urlRequest) {
        Optional<Url> optionalUrl = urlRepository.findByBase(urlRequest.getBaseUrl());

        if (optionalUrl.isPresent()) {
            return new UrlResponse().shortUrl(optionalUrl.get().getShortened());
        }

        Url url = new Url();
        url.setBase(urlRequest.getBaseUrl());
        url.setShortened(generateUniqueUrlId());
        url = urlRepository.save(url);

        return new UrlResponse().shortUrl(url.getShortened());
    }

    private String generateUniqueUrlId() {
        int shortUrlLength = 7;
        int leftLimit = 33; // ascii code for symbol '!'
        int rightLimit = 126; // ascii code for symbol '~'

        SecureRandom random = new SecureRandom();

        StringBuilder buffer = new StringBuilder(shortUrlLength);
        for (int i = 0; i < shortUrlLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedShortUrl = DOMAIN + buffer;

        return !urlRepository.existsByShortened(generatedShortUrl) ? generatedShortUrl : generateUniqueUrlId();
    }
}
