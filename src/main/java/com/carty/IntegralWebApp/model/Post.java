package com.carty.IntegralWebApp.model;

import java.util.Objects;

public class Post  implements Comparable<Post>{
    private Long id;
    private MediaPoster poster;
    private String message;


    public Post(MediaPoster poster, String message){
        id = System.currentTimeMillis();
        this.poster = poster;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public MediaPoster getPoster() {
        return poster;
    }


    public String getMessage() {
        return message;
    }


    String toFollowerString(){
        return message+" "+calcTimeString(System.currentTimeMillis())+"\n";
    }

    String toAggregatedFollowerString(){
        return poster.getName()+"-"+message+" "+calcTimeString(System.currentTimeMillis())+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Post post){
        long ans = id - post.getId();

        if (ans == 0)
            return 0;
        if (ans > 0)
            return -1;
        return 1;
    }

    public MediaPoster getMediaPoster() {
        return this.poster;
    }

    public String calcTimeString(long currentTime) {
        long oneSecond = 1_000;
        long timeDifference = (currentTime - id)/oneSecond;

        if (timeDifference < 2){
            return "("+timeDifference+" second ago)";
        }
        if (timeDifference < 60){
            return "("+timeDifference+" seconds ago)";
        }

        timeDifference/=60;
        if (timeDifference < 2){
            return "("+timeDifference+" minute ago)";
        }
        if (timeDifference < 60){
            return "("+timeDifference+" minutes ago)";
        }

        timeDifference/=60;
        if (timeDifference < 2){
            return "("+timeDifference+" hour ago)";
        }
        if (timeDifference < 60){
            return "("+timeDifference+" hours ago)";
        }

        timeDifference/=24;
        if (timeDifference < 2){
            return "("+timeDifference+" day ago)";
        }
        if (timeDifference < 7){
            return "("+timeDifference+" days ago)";
        }

        timeDifference/=7;
        if (timeDifference < 2){
            return "("+timeDifference+" week ago)";
        }
        if (timeDifference < 4){
            return "("+timeDifference+" weeks ago)";
        }

        timeDifference/=4;
        if (timeDifference < 2){
            return "("+timeDifference+" month ago)";
        }
        if (timeDifference <= 12){
            return "("+timeDifference+" months ago)";
        }

        return "(more than a year ago)";
    }

}
