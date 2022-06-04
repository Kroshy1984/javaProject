package com.zotkin.blog.controllers;

import com.zotkin.blog.models.Post;
import com.zotkin.blog.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/")
    public String allPosts(Model model){
        Iterable<Post> posts = postRepo.findAllByOrderByIdDesc();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/post/create")
    public String createPostView(Model model){
        return "post-create";
    }

    @PostMapping("/post/create")
    public String createPost(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text){
        Post post = new Post(title, anons, full_text);
        postRepo.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String postDetails(@PathVariable(value = "id") long id, Model model){
        if(!postRepo.existsById(id)){
            return "redirect:/";
        }
        Post post = postRepo.findById(id).get();
        post.setViews(post.getViews() + 1);
        postRepo.save(post);
        model.addAttribute("post", post);
        return "post-details";
    }



    @GetMapping("/post/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepo.existsById(id)){
            return "redirect:/";
        }
        Post post = postRepo.findById(id).get();
        model.addAttribute("post", post);
        return "post-edit";
    }


    @PostMapping("/post/{id}/edit")
    public String postSaveEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text){
        Post post = postRepo.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepo.save(post);
        return "redirect:/post/" + id;
    }



    @PostMapping("/post/{id}/delete")
    public String postDelete(@PathVariable(value = "id") long id){
        Post post = postRepo.findById(id).orElseThrow();
        postRepo.delete(post);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "data") String data, Model model){
        Iterable<Post> posts = postRepo.findAllByTitleAndAnonsLike(data);
        model.addAttribute("posts", posts);
        return "home";
    }
}
