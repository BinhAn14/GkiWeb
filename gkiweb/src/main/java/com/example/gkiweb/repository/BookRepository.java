package com.example.gkiweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gkiweb.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}