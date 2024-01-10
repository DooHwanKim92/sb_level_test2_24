package com.board.basic;

import com.board.basic.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicApplicationTests {

	@Autowired
	ArticleService articleService;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
		for (int i = 1; i <= 30; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.articleService.create(subject, content, null);
		}

	}
}
