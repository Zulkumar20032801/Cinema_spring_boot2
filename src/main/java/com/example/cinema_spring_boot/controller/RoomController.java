package com.example.cinema_spring_boot.controller;

import com.example.cinema_spring_boot.layer.impl.CinemaLayer;
import com.example.cinema_spring_boot.layer.impl.RoomLayer;
import com.example.cinema_spring_boot.model.Cinema;
import com.example.cinema_spring_boot.model.Room;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
@PreAuthorize("hasAuthority('ADMIN')")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomController {
     RoomLayer roomLayer;
   CinemaLayer cinemaLayer;



    @ModelAttribute("cinemaList")
    public List<Cinema> cinemaList() {
        return cinemaLayer.findAll();
    }

    @GetMapping("/save")

    public String save(Model model) {
        model.addAttribute("room", new Room());
        return "room/save_room";
    }

    @PostMapping("/save_room")
    public String saveRoom(@ModelAttribute Room room) {
        roomLayer.save(room);
        return "redirect:find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_rooms", roomLayer.findAll());
        return "room/all_rooms";
    }

    @GetMapping("/find_id")
    public String findById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("room", roomLayer.findById(id));
        return "/room/find_id";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomLayer.findById(id));
        return "room/update_room";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Room room, @PathVariable Long id){
        roomLayer.update(id, room);
        return "redirect:/room/find_all";
    }
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        roomLayer.deleteById(id);
        return "redirect:/room/find_all";
    }
}
