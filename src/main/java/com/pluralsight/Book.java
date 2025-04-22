package com.pluralsight;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean IsCheckedOut;
    private String checkedOutTo;

    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        IsCheckedOut = false;
        this.checkedOutTo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return IsCheckedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }



    public void checkOut(String name){
        this.IsCheckedOut = true;
        this.checkedOutTo = name;
    }

    public void checkIn(String name){
        this.checkedOutTo = "";
        this.IsCheckedOut = false;
    }
}
