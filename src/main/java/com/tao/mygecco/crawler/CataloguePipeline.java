package com.tao.mygecco.crawler;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.tao.mygecco.entity.BookEntity;
import com.tao.mygecco.entity.ChapterEntity;
import com.tao.mygecco.service.BookService;
import com.tao.mygecco.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@PipelineName("cataloguePipeline")
@Service("cataloguePipeline")
@Slf4j
public class CataloguePipeline implements Pipeline<Catalogue> {

	@Autowired
	BookService bookService;

	@Autowired
	ChapterService chapterService;

	@Autowired
	PipelineFactory springPipelineFactory;


	@Override
	public void process(Catalogue catalogue) {
		BookEntity book = new BookEntity();
		BeanUtils.copyProperties(catalogue, book);
		book.setId(book.getBookId());
		book.setSourceUrl(catalogue.getRequest().getUrl());
		bookService.saveOrUpdate(book);
		for (String url : catalogue.getChapterUrls()) {
			ChapterEntity entity = chapterService.getOne(new LambdaQueryWrapper<ChapterEntity>().eq(ChapterEntity::getChapterUrl, url));
			if (entity == null || entity.getNextChapterId() == null) {
				SchedulerContext.into(catalogue.getRequest().subRequest(url));
			}
		}
	}
}
