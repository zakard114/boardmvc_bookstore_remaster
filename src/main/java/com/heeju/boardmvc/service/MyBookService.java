package com.heeju.boardmvc.service;

import com.heeju.boardmvc.dto.MyBookListDTO;
import com.heeju.boardmvc.entity.MyBookList;
import com.heeju.boardmvc.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface MyBookService {

    public void saveMyBooks(MyBookListDTO myBookListDTO);
    List<MyBookListDTO> getAllMyBooks();

    public MyBookList getMyBookById(Long id);

    public void deleteById(Long id);

}
