package com.carty.IntegralWebApp.service;

import com.carty.IntegralWebApp.model.MediaPoster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MediaService {


    List<MediaPoster> mediaPosters = new ArrayList<>();

    MediaService(){

    }
    @PostConstruct
    void CreateMediaService() throws InterruptedException{
        MediaPoster Jack = new MediaPoster("Jack");
        MediaPoster Jill = new MediaPoster("Jill");
        MediaPoster Matt = new MediaPoster("Matt");

        mediaPosters = new ArrayList<>();

        mediaPosters.add(Jack);
        mediaPosters.add(Jill);
        mediaPosters.add(Matt);

        Matt.follow(Jill);
        Matt.follow(Jack);

        Matt.createPost("India was a really nice vacation last year");
        Thread.currentThread().sleep(2*1000);
        Jill.createPost("I was not well yesterday.");
        Thread.currentThread().sleep(1*1000);
        Jack.createPost("I felt good yesterday.");
        Thread.currentThread().sleep(4*1000);
        Jill.createPost("I am not well today too.");
        Thread.currentThread().sleep(1*1000);
        Jack.createPost("I feel good today too.");
        Thread.currentThread().sleep(2*1000);
        Jack.createPost("I think I will feel good tomorrow too.");
        Thread.currentThread().sleep(1*1000);
        Jill.createPost("I think I will be better tomorrow though");
    }


   public String showOwnTimeline(String name){
        Optional<MediaPoster> mediaPoster = mediaPosters.stream().filter(m->m.getName().equalsIgnoreCase(name)).findFirst();

        if (mediaPoster.isPresent())
            return mediaPoster.get().viewOwnTimeline()+"\n";
        else
            return "\nNo media poster with name '"+name+"' found...";
    }

    public String showFollowingTimeline(String followerName, String followeeName){
        Optional<MediaPoster> follower = mediaPosters.stream().filter(m->m.getName().equalsIgnoreCase(followerName)).findFirst();

        if (follower.isPresent()){
            Optional<MediaPoster> followee = follower.get().getFollowingList().stream().filter(m->m.getName().equalsIgnoreCase(followeeName)).findFirst();

            if (followee.isPresent())
                return follower.get().viewTimelineOf(followee.get());

            else
                return "\nNo media poster with name '"+followeeName+"' found...";
        }
        else
            return "\nNo media poster with name '"+followerName+"' found...";
    }

    public String showAggregatedTimeline(String name){
        Optional<MediaPoster> mediaPoster = mediaPosters.stream().filter(m->m.getName().equalsIgnoreCase(name)).findFirst();

        if (mediaPoster.isPresent())
          return mediaPoster.get().IntegratedTimeline();

        else
            return "\nNo media poster with name '"+name+"' found...";
    }

}
