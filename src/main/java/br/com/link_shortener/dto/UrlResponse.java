package br.com.link_shortener.dto;

public class UrlResponse {
    private String urlOriginal;
    private String urlCurta;

    public UrlResponse(String urlOriginal, String urlCurta) {
        this.urlOriginal = urlOriginal;
        this.urlCurta = urlCurta;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public String getUrlCurta() {
        return urlCurta;
    }

    public void setUrlCurta(String urlCurta) {
        this.urlCurta = urlCurta;
    }
}
