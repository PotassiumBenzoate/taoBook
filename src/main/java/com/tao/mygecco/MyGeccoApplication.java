package com.tao.mygecco;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@MapperScan(value = {"com.tao.mygecco.dao"})
@EnableScheduling
@SpringBootApplication
public class MyGeccoApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MyGeccoApplication.class, args);

      /* GeccoEngine.create()
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
                .run();*/

		/*在配置中实例化*/
       /* PipelineFactory springPipelineFactory = (PipelineFactory)context.getBean("springPipelineFactory");
        List<HttpRequest> requests= new ArrayList<>();
        requests.add(new HttpGetRequest("http://www.b5200.net/52_52542/"));
        requests.add(new HttpGetRequest("http://www.b5200.net/0_195/"));
        GeccoEngine.create()
                .classpath("com.tao.mygecco")
                .pipelineFactory(springPipelineFactory)
                .interval(1000)
                .thread(2)
                .start(requests)
                .run();*/
	}

}
