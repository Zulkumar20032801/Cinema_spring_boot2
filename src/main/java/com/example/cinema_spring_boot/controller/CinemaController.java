package com.example.cinema_spring_boot.controller;


import com.example.cinema_spring_boot.layer.impl.CinemaLayer;
import com.example.cinema_spring_boot.model.Cinema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cinema")
@PreAuthorize("hasAuthority('ADMIN')")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class CinemaController {

     CinemaLayer cinemaLayer;



    @GetMapping("/save")
    public String save(Model model) {
        Cinema cinema = new Cinema();
        model.addAttribute("cinema", cinema);
        return "cinema/save_cinema";
    }

    @PostMapping("/save_cinema")
    public String saveCinema(@ModelAttribute Cinema cinema) {
        cinemaLayer.save(cinema);
        return "redirect:find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_cinemas", cinemaLayer.findAll());
        return "cinema/all_cinemas";
    }


    @GetMapping("/find_id")
    public String findById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("cinema", cinemaLayer.findById(id));
        return "cinema/find_id";
    }



    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Cinema cinema = cinemaLayer.findById(id);
        model.addAttribute("cin", cinema);
        return "cinema/all_cinemas";
    }
    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Cinema cinema, @PathVariable Long id) {
        cinemaLayer.update(id, cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        cinemaLayer.deleteById(id);
        return "redirect:/cinema/find_all";
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping ("/find_by_name")
    public String findByName(Model model, @RequestParam(value = "text") String name){
        Cinema cinema = cinemaLayer.findByName(name);
        model.addAttribute("cinema", cinema);
        return "/cinema/find_id";
    }
}
