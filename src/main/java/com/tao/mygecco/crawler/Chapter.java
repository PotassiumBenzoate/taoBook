package com.tao.mygecco.crawler;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.List;

@Gecco(matchUrl = "http://www.b5200.net/{bookId}/{chapterId}.html", pipelines = "chapterPipeline")
@Data
public class Chapter implements HtmlBean {

	private static final long serialVersionUID = 1L;


	@Request
	private HttpRequest request;


	/**
	 * 书Id
	 */
	@RequestParameter
	private String bookId;


	/**
	 * 章节
	 */
	@RequestParameter
	private String chapterId;

	/**
	 * 章节标题
	 */
	@Text
	@HtmlField(cssPath = ".bookname h1")
	private String chapterName;


	/**
	 * 章节正文
	 */
	@Html
	@HtmlField(cssPath = "#content p")
	private List<String> contentList;


	private String content;


	/**
	 * 上一章节的url
	 */
	@Href
	@HtmlField(cssPath = ".bottem2 :nth-child(3)")
	private String lastPageUrl;

	/**
	 * 下一章节的url
	 */
	@Href
	@HtmlField(cssPath = ".bottem2 > :nth-child(5)")
	private String nextPageUrl;

	public static void main(String[] args) {
		GeccoEngine.create()
				//工程的包路径
				.classpath("com.tao.mygecco")
				//开始抓取的页面地址
				.start("http://www.b5200.net/52_52542/20380548.html")
				//开启几个爬虫线程
				.thread(1)
				//单个爬虫每次抓取完一个请求后的间隔时间
				.interval(2000)
				//循环抓取
				.loop(false)
				//使用pc端userAgent
				.mobile(false)
				//开始运行
				.run();
	}

	public String getContent() {
		StringBuilder text = new StringBuilder();
		if (contentList != null && !contentList.isEmpty()) {
			contentList.forEach(e -> {
				text.append(e + "\r\n");
			});
		}
		return text.toString();
	}
}