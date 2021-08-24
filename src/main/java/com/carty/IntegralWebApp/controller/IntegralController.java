package com.carty.IntegralWebApp.controller;

import com.carty.IntegralWebApp.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/socialmedia")
@Controller
public class IntegralController {

    @Autowired
    MediaService mediaService;

    @GetMapping("/own")
    String ownTimeLine(Model model, @RequestParam("name") String name){
        String response = mediaService.showOwnTimeline(name).replace("\n", "<br>");

        model.addAttribute("message", response);
        return "socialmedia";
    }


    @GetMapping("/following")
    String followingTimeline(Model model, @RequestParam("follower") String follower, @RequestParam("followee") String followee){
        String response = mediaService.showFollowingTimeline(follower, followee).replace("\n", "<br>");
        model.addAttribute("message", response);
        return "socialmedia";
    }

    @GetMapping("/aggregated")
    String followingTimeline(Model model, @RequestParam("name") String name){
        String response = mediaService.showAggregatedTimeline(name).replace("\n", "<br>");
        model.addAttribute("message", response);
        return "socialmedia";
    }



}
