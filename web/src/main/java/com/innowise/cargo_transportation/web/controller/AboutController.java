package com.innowise.cargo_transportation.web.controller;


import com.innowise.cargo_transportation.core.configuration.AboutInfoComponent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about")
@RequiredArgsConstructor
public class AboutController {
    @NonNull
    private AboutInfoComponent aboutInfoComponent;

    @RequestMapping(method = RequestMethod.GET)
    public AboutInfoComponent getAbout() {
        return aboutInfoComponent;
    }
}
