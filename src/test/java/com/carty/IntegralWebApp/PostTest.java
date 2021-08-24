package com.carty.IntegralWebApp;

import com.carty.IntegralWebApp.model.MediaPoster;
import com.carty.IntegralWebApp.model.Post;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {


    @BeforeAll
    static void setUp() {

    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    void testCompareTo() throws InterruptedException {
        PriorityQueue<Post> postQueue = new PriorityQueue<>();
        postQueue.add( new Post(new MediaPoster("April"), "Hello there"));
        Thread.sleep(1000);
        postQueue.add( new Post(new MediaPoster("May"), "Hello to you too"));

        assertEquals("May", postQueue.poll().getMediaPoster().getName());
    }

    @Test
    void testCalcTimeString(){
        final long  oneSecond= 1_000;
        Post post =  new Post(new MediaPoster("April"), "Hello there");
        final long currentTime = System.currentTimeMillis();


        assertAll("TimeStringTest",
                ()->assertEquals("(1 second ago)", post.calcTimeString(currentTime+oneSecond)),
                ()->assertEquals("(15 seconds ago)", post.calcTimeString(currentTime+oneSecond*15)),
                ()->assertEquals("(45 seconds ago)", post.calcTimeString(currentTime+oneSecond*15*3)),
                ()->assertEquals("(1 minute ago)", post.calcTimeString(currentTime+oneSecond*60)),
                ()->assertEquals("(15 minutes ago)", post.calcTimeString(currentTime+oneSecond*60*15)),
                ()->assertEquals("(1 hour ago)", post.calcTimeString(currentTime+oneSecond*60*60)),
                ()->assertEquals("(5 hours ago)", post.calcTimeString(currentTime+oneSecond*60*60*5))
                );

    }
}