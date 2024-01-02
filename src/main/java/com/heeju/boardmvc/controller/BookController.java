package com.heeju.boardmvc.controller;

import com.heeju.boardmvc.dto.BookDTO;
import com.heeju.boardmvc.dto.MyBookListDTO;
import com.heeju.boardmvc.entity.Book;
import com.heeju.boardmvc.entity.MyBookList;
import com.heeju.boardmvc.repository.MyBookRepository;
import com.heeju.boardmvc.service.BookService;
import com.heeju.boardmvc.service.MyBookService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Log4j2
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookService myBookService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister(){

        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllbook(){
        List<BookDTO> list = bookService.getAllBook();
        return new ModelAndView("bookList", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute BookDTO bookDTO){
        Long regBookId = bookService.register(bookDTO);
        MyBookList myBookList = myBookService.getMyBookById(regBookId);
        if(myBookList!=null){
            MyBookListDTO myBookListDTO = modelMapper.map(bookDTO, MyBookListDTO.class);
            myBookService.saveMyBooks(myBookListDTO);
        }
        return "redirect:/available_books";
    }

    @RequestMapping("/update/{id}")
    public ModelAndView modifyBook(@PathVariable Long id){
        BookDTO book = bookService.getBookById(id);
        return new ModelAndView("bookUpdate", "book", book);
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        MyBookList myBookList = myBookService.getMyBookById(id);
        if(myBookList!=null){
            myBookService.deleteById(id);
        }
        bookService.deleteBookById(id);
        return "redirect:/available_books";
    }

}
