package com.tao.mygecco.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.mygecco.dao.BookDao;
import com.tao.mygecco.entity.BookEntity;
import com.tao.mygecco.service.BookService;
import org.springframework.stereotype.Service;


@Service("bookService")
public class BookServiceImpl extends ServiceImpl<BookDao, BookEntity> implements BookService {
}