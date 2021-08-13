package com.tao.mygecco.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_chapter")
public class ChapterEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	private String chapterId;

	private String chapterName;

	private String bookId;

	private String lastChapterId;

	private String nextChapterId;

	private String content;

	private Date lastUpdateTime;

	private String chapterUrl;

}
