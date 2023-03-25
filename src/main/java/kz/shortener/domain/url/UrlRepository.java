package kz.shortener.domain.url;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {

    Optional<Url> findByBase(String base);

    Boolean existsByShortened(String shortened);
}
