package com.example.book_club.circles;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Circle {
    
    private int circleId;
    private int ownerUserId;
    private List<Integer> bookList;
    private List<Integer> memberList;

    public Circle() {}

    public Circle(int circleId, int ownerUserId) {
        this.circleId = circleId;
        this.ownerUserId = ownerUserId;
    }

    public Circle(Circle source) {
        this.circleId = source.circleId;
        this.ownerUserId = source.ownerUserId;
        this.bookList = source.bookList;
        this.memberList = source.memberList;
    }
}
