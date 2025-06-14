package br.com.link_shortener.service;

import br.com.link_shortener.exception.UrlException;
import br.com.link_shortener.model.Url;
import br.com.link_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String encurtarUrl(String urlOriginal){
        if (urlOriginal == null || urlOriginal.trim().isEmpty()){
            throw new UrlException("URL original não pode ser nula ou vazia");
        }
        String urlCurta = gerarUrlEncurtada();
        Url url = new Url();
        url.setUrlOriginal(urlOriginal);
        url.setUrlEncurtada(urlCurta);
        url.setUrlCriadaEm(LocalDateTime.now());
        url.setDataExpiracao(LocalDateTime.now().plusDays(30));
        urlRepository.save(url);
        return urlCurta;
    }

    public Optional<Url> pegarUrlOriginal(String urlCurta){
        Optional<Url> urlOptional = urlRepository.findByUrlEncurtada(urlCurta);
        if(urlOptional.isPresent()){
            Url url = urlOptional.get();
            if (url.getDataExpiracao().isAfter(LocalDateTime.now())){
                return Optional.of(url);
            }else{
                urlRepository.delete(url);
            }
        }
        return Optional.empty();
    }

    public String gerarUrlEncurtada(){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder urlCurta = new StringBuilder();
        Random random = new Random();
        int tamanho = 5 + random.nextInt(6);
        for (int i = 0; i < tamanho; i++) {
            urlCurta.append(caracteres.charAt(random.nextInt(caracteres.length())));

        }
        return urlCurta.toString();
    }

}
