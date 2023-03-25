package kz.shortener.domain.qr;

import kz.shortener.api.model.UrlRequest;
import org.springframework.stereotype.Service;

@Service
public class QrService {

    private final QrGenerator qrGenerator;

    public QrService(QrGenerator qrGenerator) {
        this.qrGenerator = qrGenerator;
    }

    public byte[] downloadQr(UrlRequest urlRequest) throws Exception {
        return qrGenerator.generateQRCodeImage(urlRequest.getBaseUrl());
    }
}
