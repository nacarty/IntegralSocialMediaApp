package com.carty.IntegralWebApp;

import com.carty.IntegralWebApp.model.MediaPoster;
import com.carty.IntegralWebApp.model.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class MediaPosterTest {

    MediaPoster Jack, Jill, Matt;
    @BeforeEach
    void setUp() throws InterruptedException {

        Jack = new MediaPoster("Jack");
        Jill = new MediaPoster("Jill");

        Matt = new MediaPoster("Matt");
        Matt.follow(Jill);
        Matt.follow(Jack);

        Jill.createPost("I was not well yesterday.");
        Thread.sleep(10);
        Jack.createPost("I felt good yesterday.");
        Thread.sleep(10);
        Jill.createPost("I am not well today too.");
        Thread.sleep(10);
        Jack.createPost("I feel good today too.");
        Thread.sleep(10);
        Jack.createPost("I think I will feel good tomorrow too.");
        Thread.sleep(10);
        Jill.createPost("I think I will be better tomorrow though");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreatePost(){

        assertAll("CreatePostTest",
                ()->assertTrue(Jack.getTimeline().size()==3),
                ()->assertTrue(Jack.getTimeline().peek().getMediaPoster() == Jack),
                ()->assertTrue(Jack.getTimeline().peek().getMessage()=="I think I will feel good tomorrow too.")

        );
    }

    @Test
    void testFollowing() {

        assertAll("FollowingTest",
                ()->assertTrue(Matt.getFollowingList().size()==2),
                ()->assertTrue(Matt.getFollowingList().get(0)==Jill),
                ()->assertTrue(Matt.getFollowingList().get(1)==Jack),
                ()->assertTrue(Jack.getFollowedBy().contains(Matt)),
                ()->assertTrue(Jill.getFollowedBy().contains(Matt))
                );
    }

    @Test
    void testAggregateFollowingLists(){

        PriorityQueue<Post> aggregateFollowingList = Matt.getAggregateFollowingTimeline();

        assertAll("AggregateTest - Posts are in most-recent order",
                ()->assertEquals(aggregateFollowingList.size(), 6),
                ()->assertEquals(aggregateFollowingList.peek(), Jill.getTimeline().peek()),
                ()->assertEquals(aggregateFollowingList.poll().getMessage(), "I think I will be better tomorrow though"),
                ()->assertEquals(aggregateFollowingList.poll().getMessage(), "I think I will feel good tomorrow too."),
                ()->assertEquals(aggregateFollowingList.poll().getMessage(), "I feel good today too."),
                ()->assertEquals(aggregateFollowingList.poll().getMessage(), "I am not well today too."),
                ()->assertEquals(aggregateFollowingList.poll().getMessage(), "I felt good yesterday."),
                ()->assertEquals(aggregateFollowingList.poll().getMessage(), "I was not well yesterday.")
                );

    }

    @Test
    void testViewOwnTimeline(){
        String timeLineString = Jack.viewOwnTimeline();

        assertAll("Check String for Jack's posts on his own timeline",
                ()->assertTrue(timeLineString.contains("I felt good yesterday.")),
                ()->assertTrue(timeLineString.contains("I feel good today too.")),
                ()->assertTrue(timeLineString.contains("I think I will feel good tomorrow too."))
        );

    }

    @Test
    void testViewTimelineOf(){
        String timeLineString = Matt.viewTimelineOf(Jack);

        assertAll("Check String for Jack's posts for Matt's viewing",
                ()->assertTrue(timeLineString.contains("I felt good yesterday.")),
                ()->assertTrue(timeLineString.contains("I feel good today too.")),
                ()->assertTrue(timeLineString.contains("I think I will feel good tomorrow too."))
                );

    }

    @Test
    void testViewIntegratedTimeline(){
        String timeLineString = Matt.IntegratedTimeline();

        assertAll("Check String for Jack's and Matt's posts for ",
                ()->assertTrue(timeLineString.contains("I felt good yesterday.")),
                ()->assertTrue(timeLineString.contains("I feel good today too.")),
                ()->assertTrue(timeLineString.contains("I think I will feel good tomorrow too.")),
                ()->assertTrue(timeLineString.contains("I was not well yesterday.")),
                ()->assertTrue(timeLineString.contains("I am not well today too.")),
                ()->assertTrue(timeLineString.contains("I think I will be better tomorrow though"))
        );
    }

}