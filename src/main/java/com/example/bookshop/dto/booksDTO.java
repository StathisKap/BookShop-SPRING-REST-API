package com.example.bookshop.dto; 

public class booksDTO {

    private int id;
    private String bookname;
    private String author;
    private int price;

    public int getId() {
		return id;
	}

	public void setBookid(int id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public booksDTO() {
        super();
    }
}