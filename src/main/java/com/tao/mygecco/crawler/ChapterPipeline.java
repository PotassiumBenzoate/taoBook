package com.tao.mygecco.crawler;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.tao.mygecco.entity.ChapterEntity;
import com.tao.mygecco.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@PipelineName("chapterPipeline")
@Service("chapterPipeline")
public class ChapterPipeline implements Pipeline<Chapter> {


	@Autowired
	private ChapterService chapterService;


	@Override
	public void process(Chapter chapter) {
		try {
			ChapterEntity entity = chapterService.getOne(new LambdaQueryWrapper<ChapterEntity>()
					.eq(ChapterEntity::getBookId, chapter.getBookId())
					.eq(ChapterEntity::getChapterId, chapter.getChapterId())
			);
			if (entity == null) {
				entity = new ChapterEntity();
				entity.setLastUpdateTime(new Date());
			}
			BeanUtils.copyProperties(chapter, entity);
			entity.setContent(chapter.getContent());
			entity.setChapterUrl(chapter.getRequest().getUrl());
			//todo 待优化
			entity.setLastChapterId(chapter.getLastPageUrl().replace(chapter.getRequest().getUrl().replace(chapter.getChapterId() + ".html", ""), "").replace(".html", ""));
			entity.setNextChapterId(chapter.getNextPageUrl().replace(chapter.getRequest().getUrl().replace(chapter.getChapterId() + ".html", ""), "").replace(".html", ""));

			chapterService.saveOrUpdate(entity);
			/*//构造下一页
			if (StringUtils.isNotEmpty(chapter.getNextPageUrl())) {
				SchedulerContext.into(chapter.getRequest().subRequest(chapter.getNextPageUrl()));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
