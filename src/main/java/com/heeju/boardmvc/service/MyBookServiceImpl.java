package com.heeju.boardmvc.service;

import com.heeju.boardmvc.dto.MyBookListDTO;
import com.heeju.boardmvc.entity.MyBookList;
import com.heeju.boardmvc.repository.MyBookRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class MyBookServiceImpl implements MyBookService {

    @Autowired
    private MyBookRepository myBookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveMyBooks(MyBookListDTO myBookListDTO) {
        MyBookList myBookList = modelMapper.map(myBookListDTO, MyBookList.class);
        myBookRepository.save(myBookList);
    }

    @Override
    public List<MyBookListDTO> getAllMyBooks() {
        List<MyBookList> myBookLists = myBookRepository.findAll();
        List<MyBookListDTO> myBookListDTOS = new ArrayList<>();
        for (MyBookList myBookList :myBookLists) {
            myBookListDTOS.add(modelMapper.map(myBookList, MyBookListDTO.class));
        }
        return myBookListDTOS;
    }

    @Override
    public MyBookList getMyBookById(Long id) {
        MyBookList myBookList = myBookRepository.findById(id).orElse(null);
        return myBookList;
    }

    @Override
    public void deleteById(Long id) {
        MyBookList target = myBookRepository.findById(id).orElse(null);
        myBookRepository.deleteById(id);
    }
}
