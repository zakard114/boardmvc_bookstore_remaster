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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


public interface BookService {

    Long register(BookDTO bookDTO);
    public List<BookDTO> getAllBook();
    public BookDTO getBookById(Long id);

    void deleteBookById(Long id);
}
