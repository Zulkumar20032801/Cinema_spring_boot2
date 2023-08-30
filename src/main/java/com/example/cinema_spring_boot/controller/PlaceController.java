package com.example.cinema_spring_boot.controller;

import com.example.cinema_spring_boot.layer.impl.CinemaLayer;
import com.example.cinema_spring_boot.layer.impl.PlaceLayer;

import com.example.cinema_spring_boot.layer.impl.RoomLayer;
import com.example.cinema_spring_boot.model.Place;
import com.example.cinema_spring_boot.model.Room;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
@PreAuthorize("hasAuthority('ADMIN')")
public class PlaceController {
     PlaceLayer placeLayer;

     RoomLayer roomLayer;
    CinemaLayer cinemaLayer;






    @ModelAttribute("roomList")
    public List<Room> roomList() {
        return roomLayer.findAll();
    }


    @GetMapping("/save")
    public String save(org.springframework.ui.Model model) {
        Place place=new Place();
        model.addAttribute("place", place);
        return "place/save_place";
    }


    @PostMapping("/save_place")
    public String saveCinema(@ModelAttribute Place place) {
        placeLayer.save(place);
        return "redirect:find_all";
    }

    @GetMapping("/find_all")
    public String findAll(org.springframework.ui.Model model) {
        model.addAttribute("all_places", placeLayer.findAll());
        return "/place/all_places";
    }

    @GetMapping("/find_id")
    public String findById(@RequestParam("id") Long id, org.springframework.ui.Model model) {
        Place place=placeLayer.findById(id);
        model.addAttribute("place", place);
        return "place/find_id";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, org.springframework.ui.Model model) {
        Place place=placeLayer.findById(id);
        model.addAttribute("place" ,place );
        return "place/update_place";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Place place, @PathVariable Long id){
        placeLayer.update(id, place);
        return "redirect:/place/find_all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        placeLayer.deleteById(id);
        return "redirect:/place/find_all";
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        Place place = placeLayer.findById(id);
        if (place != null && place.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(place.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//    @GetMapping("/buy")
//    public String buy(){
//        return "/plac/buy";
//    }
}