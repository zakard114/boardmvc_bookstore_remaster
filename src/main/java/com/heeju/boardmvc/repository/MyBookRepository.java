package com.heeju.boardmvc.repository;

import com.heeju.boardmvc.entity.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookRepository extends JpaRepository<MyBookList, Long> {

}
