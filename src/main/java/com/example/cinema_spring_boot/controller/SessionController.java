package com.example.cinema_spring_boot.controller;


//        import com.example.cinema_spring_boot.layer.impl.MovieLayer;
        import com.example.cinema_spring_boot.layer.impl.MovieLayer;
        import com.example.cinema_spring_boot.layer.impl.SessionLayer;
//        import com.example.cinema_spring_boot.model.Movie;
        import com.example.cinema_spring_boot.model.Session;
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
@RequestMapping("/session")
@PreAuthorize("hasAuthority('ADMIN')")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SessionController {
     SessionLayer sessionLayer;

      MovieLayer movieLayer;


//    @Autowired
//    @ModelAttribute("movieList")
//    public List<Movie> movieList() {
//        return movieLayer.findAll();
//    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("sess", new Session());
        return "session/save_session";
    }

    @PostMapping("/save_session")
    public String saveSession(@ModelAttribute Session session) {
        sessionLayer.save(session);
        return "redirect:find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_sessions", sessionLayer.findAll());
        return "session/all_sessions";
    }

//    @GetMapping("/findAllId/{id}")
//    public String findAllId(@PathVariable("id") int id, Model model) {
//        model.addAttribute("all_session_id", sessionLayer.findById(id));
//        return "/session/all_session_id";
//    }


    @GetMapping("/find_id")
    public String findById(@RequestParam("id") long id, Model model) {
        model.addAttribute("sess", sessionLayer.findById(id));
        return "session/find_id";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        Session session= sessionLayer.findById(id);
        model.addAttribute("sess",session);
        return "session/update_session";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Session session, @PathVariable long id){
        sessionLayer.update(id, session);
        return "redirect:/session/find_all";
    }
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        sessionLayer.deleteById(id);
        return "redirect:/session/find_all";
    }

}


