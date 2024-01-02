package com.heeju.boardmvc.controller;

import com.heeju.boardmvc.dto.BookDTO;
import com.heeju.boardmvc.dto.MyBookListDTO;
import com.heeju.boardmvc.entity.MyBookList;
import com.heeju.boardmvc.repository.MyBookRepository;
import com.heeju.boardmvc.service.BookService;
import com.heeju.boardmvc.service.MyBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.management.MBeanServer;
import java.util.List;

@Controller
public class MyBookListController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MyBookService myBookService;

    @GetMapping("/my_books")
    public ModelAndView getMyBooks(){
        List<MyBookListDTO> list = myBookService.getAllMyBooks();
        return new ModelAndView("myBooks", "book", list);
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable Long id){
        BookDTO bookDTO = bookService.getBookById(id);
        if(bookDTO==null){
            return "redirect:/my_books";
        }
        MyBookListDTO myBookListDTO = modelMapper.map(bookDTO, MyBookListDTO.class);
        myBookService.saveMyBooks(myBookListDTO);
        return "redirect:/my_books";
    }
    @RequestMapping("/deleteMyBook/{id}")
    public String deleteMyBook(@PathVariable Long id){
        myBookService.deleteById(id);
        return "redirect:/my_books";
    }

}
