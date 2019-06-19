package com.example.servicewordladder.Controller;

public class Node {
    public String word;
    public int depth;
    public Node prev;

    public Node(String word, int depth, Node prev){
        this.word=word;
        this.depth=depth;
        this.prev=prev;
    }
}
