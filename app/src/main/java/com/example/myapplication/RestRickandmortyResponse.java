package com.example.myapplication;

        import java.util.List;

public class RestRickandmortyResponse {
    private int count;
    private int page;
    private String next;
    private List<Character> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Character> getResults() {
        return results;
    }
}
