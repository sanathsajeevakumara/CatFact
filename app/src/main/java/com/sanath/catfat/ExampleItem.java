package com.sanath.catfat;

public class ExampleItem {
    private String text;
    private String type;
    private String upvotes;
    private String firstName;
    private String lastName;


    public ExampleItem(String text, String type, String upvotes, String firstName, String lastName) {
        this.text = text;
        this.type = type;
        this.upvotes = upvotes;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(String upvotes) {
        this.upvotes = upvotes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
