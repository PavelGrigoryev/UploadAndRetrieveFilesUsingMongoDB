package com.pavel.uploadandretrievefilesusingmongodb.controller;

import com.pavel.uploadandretrievefilesusingmongodb.model.Photo;
import com.pavel.uploadandretrievefilesusingmongodb.service.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photos/upload")
    public String uploadPhoto(Model model) {
        model.addAttribute("message", "hello");
        return "uploadPhoto";
    }

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image, Model model) throws IOException {
        String id = photoService.addPhoto(title, image);
        return "redirect:/photos/" + id;
    }

    @GetMapping("/photos/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "photos";
    }
}
