package com.example.qzy;

import java.util.UUID;

public class Quiz {
    String quizId;
    String Title;
    String Category;
    String tags;
    String postedDate;
    String Questions;
Quiz()
{

}
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
