package dev.training.library.repository;

import dev.training.library.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookModel, Long> {
    Optional<BookModel> findByisbn(String isbn);
}
