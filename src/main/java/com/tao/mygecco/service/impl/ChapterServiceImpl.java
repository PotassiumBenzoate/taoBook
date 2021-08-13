package com.tao.mygecco.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.mygecco.dao.ChapterDao;
import com.tao.mygecco.entity.ChapterEntity;
import com.tao.mygecco.service.ChapterService;
import org.springframework.stereotype.Service;


@Service("chapterService")
public class ChapterServiceImpl extends ServiceImpl<ChapterDao, ChapterEntity> implements ChapterService {

}