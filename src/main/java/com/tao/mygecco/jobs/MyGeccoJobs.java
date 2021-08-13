package com.tao.mygecco.jobs;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.tao.mygecco.entity.BookEntity;
import com.tao.mygecco.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyGeccoJobs {

	@Autowired
	PipelineFactory springPipelineFactory;

	@Autowired
	BookService bookService;

	//@Scheduled(cron = "0 0/30 * * * ? ")
	@Scheduled(cron = "0 0/5 * * * ? ")
	private void run() {
		List<HttpRequest> requests = new ArrayList<>();
		List<BookEntity> books = bookService.list();
		books.forEach(e -> {
			requests.add(new HttpGetRequest(e.getSourceUrl()));
		});
		GeccoEngine.create()
				.classpath("com.tao.mygecco")
				.pipelineFactory(springPipelineFactory)
				.interval(1000)
				.thread(5)
				.start(requests)
				.run();
	}
}
