package com.example.cinema_spring_boot.controller;


import com.example.cinema_spring_boot.layer.impl.MovieLayer;
import com.example.cinema_spring_boot.model.Movie2;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
@PreAuthorize("hasAuthority('ADMIN')")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MovieController {

      MovieLayer movieLayer;



    @GetMapping("/save")
    public String save(Model model) {
        Movie2 movie=new Movie2();
        model.addAttribute("movie", movie);
        return "movie/save_movie";
    }

    @PostMapping("/save_movie")
    public String saveMovie(@ModelAttribute Movie2 movie2) {
        movieLayer.save(movie2);
        return "redirect:find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_movies", movieLayer.findAll());
        return "movie/all_movies";
    }

    @GetMapping("/find_id")
    public String findById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("movie", movieLayer.findById(id));
        return "movie/find_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Movie2 movie2 = movieLayer.findById(id);
        model.addAttribute("movie", movie2);
        return "movie/update_movie";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Movie2 movie2, @PathVariable Long id) {
        movieLayer.update(id, movie2);
        return "redirect:/movie/find_all";
    }

    @GetMapping("/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        movieLayer.deleteById(id);
        return "redirect:/movie/find_all";
    }

//    @GetMapping("/find_genre/{genre}")
//    public String findByGenre( @PathVariable("genre") Movie genre,Model model) {
//        model.addAttribute("all_movies", movieLayer.f(genre));
//        return "movie/all_movies";
//    }
@GetMapping("/image/{id}")
public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
    Movie2 movie2 = movieLayer.findById(id);
    if (movie2 != null && movie2.getImage() != null) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(movie2.getImage(),headers, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
