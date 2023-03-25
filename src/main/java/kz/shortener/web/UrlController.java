package kz.shortener.web;

import kz.shortener.api.client.UrlApiDelegate;
import kz.shortener.api.model.UrlRequest;
import kz.shortener.api.model.UrlResponse;
import kz.shortener.domain.qr.QrService;
import kz.shortener.domain.url.UrlService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;


@RestController
public class UrlController implements UrlApiDelegate {


    private final UrlService urlService;
    private final QrService qrService;

    public UrlController(UrlService urlService, QrService qrService) {
        this.urlService = urlService;
        this.qrService = qrService;
    }

    @Override
    public ResponseEntity<UrlResponse> createShortUrl(UrlRequest urlRequest) {
        return ResponseEntity.ok(urlService.createShortUrl(urlRequest));
    }

    @Override
    public ResponseEntity<byte[]> downloadQr(UrlRequest urlRequest) {
        try {
            return ResponseEntity.ok(qrService.downloadQr(urlRequest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
