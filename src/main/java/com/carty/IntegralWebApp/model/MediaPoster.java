package com.carty.IntegralWebApp.model;

import java.util.*;

public class MediaPoster {
    private Long id;
    private String name;
    private List<MediaPoster> followingList;
    private List<MediaPoster> followedByList;
    private PriorityQueue<Post> timeline;
    private PriorityQueue<Post> aggregateFollowingTimeline;

    public MediaPoster(String name) {
        id = System.currentTimeMillis();
        this.name = name;
        followingList = new ArrayList<>();
        followedByList = new ArrayList<>();
        timeline = new PriorityQueue<>();
        aggregateFollowingTimeline = new PriorityQueue<>();
    }

    public String getName() {
        return this.name;
    }

    public PriorityQueue<Post> getTimeline() {
        return this.timeline;
    }

    public PriorityQueue<Post> getAggregateFollowingTimeline() {
        return aggregateFollowingTimeline;
    }

    public void createPost(String msg) {
        Post post = new Post(this, msg);
        this.timeline.add(post);

        for (MediaPoster mediaPoster : followedByList)
            mediaPoster.getAggregateFollowingTimeline().add(post);
    }

    public void follow(MediaPoster mediaPoster) {
        if (mediaPoster != this && !followingList.contains(mediaPoster)) {
            followingList.add(mediaPoster);
            mediaPoster.getFollowedBy().add(this);

//            for (Post post: mediaPoster.getTimeline())  //we can determine whether posts prior to following a mediaPoster become visible to new follower
//                aggregateFollowingTimeline.add(post);
        }
    }

    public List<MediaPoster> getFollowedBy() {
        return followedByList;
    }


    public List<MediaPoster> getFollowingList() {
        return followingList;
    }

    public  PriorityQueue<Post> aggregateFollowingList() {  //not used
        PriorityQueue<Post> aggregatedPosts = new PriorityQueue<>();

        for (MediaPoster mediaPoster : followingList) {
            aggregatedPosts.addAll(mediaPoster.getTimeline());
        }

        return aggregatedPosts;
    }

    public String viewOwnTimeline(){
        StringBuilder timeLineStringBuilder = new StringBuilder(name+"'s view of their timeline:\n----------\n");

        PriorityQueue<Post> copyQueue = new PriorityQueue<>(timeline);
        while (!copyQueue.isEmpty())
            timeLineStringBuilder.append(copyQueue.poll().getMessage()+"\n");
        return timeLineStringBuilder.toString();
    }

    public String viewTimelineOf(MediaPoster mediaPoster) {
        StringBuilder timeLineStringBuilder = new StringBuilder(name+"'s View of "+mediaPoster.getName()+"'s timeline:\n----------\n");

        PriorityQueue<Post> copyQueue = new PriorityQueue<>(mediaPoster.getTimeline());
        while (!copyQueue.isEmpty())
            timeLineStringBuilder.append(copyQueue.poll().toFollowerString());

        return timeLineStringBuilder.toString();
    }

    public String IntegratedTimeline() {
        StringBuilder timeLineStringBuilder = new StringBuilder("Aggregated Timeline for "+name+":\n----------\n");
        PriorityQueue<Post> copyQueue = new PriorityQueue<>(aggregateFollowingTimeline);
        while (!copyQueue.isEmpty())
            timeLineStringBuilder.append(copyQueue.poll().toAggregatedFollowerString());

        return timeLineStringBuilder.toString();
    }
}
