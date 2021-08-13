package com.tao.mygecco.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tao.mygecco.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<BookEntity> {
}
