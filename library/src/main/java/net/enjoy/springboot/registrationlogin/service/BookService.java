package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.entity.Book;
import net.enjoy.springboot.registrationlogin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// BookService.java

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean borrowBook(Long id, String note) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.isAvailable()) {
                book.setAvailable(false);
                book.setNote(note);
                bookRepository.save(book);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (!book.isAvailable()) {
                book.setAvailable(true);
                book.setNote("");
                bookRepository.save(book);
                return true;
            }
        }
        return false;
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public boolean deleteBook(Long id) { // 实现删除书本的方法
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.delete(optionalBook.get());
            return true;
        }
        return false;
    }
}