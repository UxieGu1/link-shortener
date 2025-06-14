package br.com.link_shortener.service;

import br.com.link_shortener.model.Link;
import br.com.link_shortener.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String encurtarUrl(String urlOriginal){

        String urlCurta = gerarUrlEncurtada();
        Link link = new Link();
        link.setUrlOriginal(urlOriginal);
        link.setUrlEncurtada(urlCurta);
        link.setDataExpiracao(LocalDateTime.now().plusDays(30));
        linkRepository.save(link);
        return urlCurta;
    }

    public Optional<Link> pegarUrlOriginal(String urlCurta){
        Optional<Link> urlOptional = linkRepository.findByUrlEncurtada(urlCurta);
        if(urlOptional.isPresent()){
            Link link = urlOptional.get();
            if (link.getDataExpiracao().isAfter(LocalDateTime.now())){
                return Optional.of(link);
            }else{
                linkRepository.delete(link);
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
