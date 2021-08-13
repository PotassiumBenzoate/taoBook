package com.tao.mygecco.crawler;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Gecco(matchUrl = "http://www.b5200.net/{bookId}/", pipelines = "cataloguePipeline")
@Data
public class Catalogue implements HtmlBean {

	private static final long serialVersionUID = 1L;

	@Request
	private HttpRequest request;

	/**
	 * 书Id
	 */
	@RequestParameter
	private String bookId;

	/**
	 * 书名
	 */
	@Text
	@HtmlField(cssPath = "#info h1")
	private String bookName;

	/**
	 * author
	 */
	@Text
	@HtmlField(cssPath = "#info p:nth-child(2)")
	private String author;


	/**
	 * 章节目录url
	 */
	@Href
	@HtmlField(cssPath = "#list dl dd a")
	private List<String> chapterUrls;

	public static void main(String[] args) {
		GeccoEngine.create()
				//工程的包路径
				.classpath("com.tao.mygecco")
				//开始抓取的页面地址
				.start("http://www.b5200.net/52_52542/")
				//开启几个爬虫线程
				.thread(5)
				//单个爬虫每次抓取完一个请求后的间隔时间
				.interval(100)
				//循环抓取
				.loop(false)
				//使用pc端userAgent
				.mobile(false)
				//开始运行
				.run();
	}

	public String getAuthor() {
		if (StringUtils.isNotEmpty(author)) {
			author = author.replace("作&nbsp;&nbsp;&nbsp;&nbsp;者：", "");
			author = author.replace("作者：", "");
		}
		return author;
	}

	public List<String> getChapterUrls() {
		if (chapterUrls != null && !chapterUrls.isEmpty()) {
			return chapterUrls.stream().distinct().collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

}
