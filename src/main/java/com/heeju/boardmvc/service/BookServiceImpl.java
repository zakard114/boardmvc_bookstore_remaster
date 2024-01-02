package com.heeju.boardmvc.service;

import com.heeju.boardmvc.dto.BookDTO;
import com.heeju.boardmvc.entity.Book;
import com.heeju.boardmvc.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Long regBookId = bookRepository.save(book).getId();
        return regBookId;
    }

    @Override
    public List<BookDTO> getAllBook() {
//        List<BookDTO> l = bookRepository.findAll().stream()
//                .map(book-> BookDTO.class).collect(Collectors.toList());
        List<BookDTO> list = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            BookDTO mapped = modelMapper.map(book, BookDTO.class);
            list.add(mapped);
        }
        return list;
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book==null){
            return null;
        }
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
