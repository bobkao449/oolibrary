package net.enjoy.springboot.registrationlogin.repository;
import net.enjoy.springboot.registrationlogin.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
}

