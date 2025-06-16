package br.com.link_shortener.dto;

import jakarta.validation.constraints.NotBlank;

public class UrlRequest {

    @NotBlank(message = "A URL original é obrigatória")
    private String urlOriginal;

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }
}
