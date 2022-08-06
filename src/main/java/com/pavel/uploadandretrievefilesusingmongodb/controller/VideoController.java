package com.pavel.uploadandretrievefilesusingmongodb.controller;

import com.pavel.uploadandretrievefilesusingmongodb.model.Video;
import com.pavel.uploadandretrievefilesusingmongodb.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/videos/upload")
    public String uploadVideo(Model model) {
        model.addAttribute("message", "hello");
        return "uploadVideo";
    }

    @PostMapping("/videos/add")
    public String addVideo(@RequestParam("title") String title,
                           @RequestParam("file") MultipartFile file, Model model) throws IOException {
        String id = videoService.addVideo(title, file);
        return "redirect:/videos/" + id;
    }

    @GetMapping("/videos/{id}")
    public String getVideo(@PathVariable String id, Model model) throws IOException {
        Video video = videoService.getVideo(id);
        model.addAttribute("title", video.getTitle());
        model.addAttribute("url", "/videos/stream/" + id);
        return "videos";
    }

    @GetMapping("/videos/stream/{id}")
    public void streamVideo(@PathVariable String id, HttpServletResponse response) throws IOException {
        Video video = videoService.getVideo(id);
        FileCopyUtils.copy(video.getStream(), response.getOutputStream());
    }
}
