package com.board.basic.article;

import com.board.basic.user.SiteUser;
import com.board.basic.user.UserSecurityService;
import com.board.basic.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(ArticleForm articleForm) {
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content, Principal principal) {
        SiteUser user = this.userService.findByusername(principal.getName());
        this.articleService.create(title, content, user);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, Principal principal) {
        Article article = this.articleService.getArticle(id);
        if(!principal.getName().equals(article.getAuthor().getUsername())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
        model.addAttribute("article", article);
        return "modify_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(Model model, @Valid ArticleForm articleForm, BindingResult bindingResult, @RequestParam("title") String title, @RequestParam("content") String content, @PathVariable("id") Integer id, Principal principal) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException("제목, 내용을 다시 확인해주세요.");
        }
        Article article = this.articleService.getArticle(id);
        if(!principal.getName().equals(article.getAuthor().getUsername())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
        this.articleService.modifyArticle(article,title,content);
        model.addAttribute("article",article);
        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Principal principal) {
        Article article = this.articleService.getArticle(id);
        if(!principal.getName().equals(article.getAuthor().getUsername())) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        this.articleService.deleteArticle(article);
        return "redirect:/article/list";
    }


}
