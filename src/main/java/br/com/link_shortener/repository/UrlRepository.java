package br.com.link_shortener.repository;

import br.com.link_shortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByUrlEncurtada(String urlEncurtada);
}
