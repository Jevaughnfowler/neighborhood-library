package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Book[] library = getPopulatedBooks();

    public static void main(String[] args) {
        ShowScreenHome();
    }
// prompting the user for an response

    private static void ShowScreenHome() {
        String homeScreenPrompt = "Welcome to the library!\n" +
                "Please select an option from the following:\n" +
                "    1 - Show Available Books\n" +
                "    2 - Show Checked Out Books\n" +
                "    3 - Check Out a Book\n" +
                "    4 - Check In a Book\n" +
                "    0 - Exit App\n" +
                "(1,2,3,4,0): ";

        int option;

        do {
            System.out.print(homeScreenPrompt);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    ShowScreenAvailableBooks();
                    break;
                case 2:
                    ShowScreenCheckedOutBooks();
                    break;
                case 3:
                    checkOutBook();
                    break;
                case 4:
                    checkInBook();
                    break;
                case 0:
                    System.out.println("Exiting the library, have a nice day!");
                    break;
                default:
                    System.out.println("Not a valid option, please try again.");
            }

        } while (option != 0);
    }
// displaying all available books
    private static void ShowScreenAvailableBooks() {
        System.out.println("\nAvailable Books:");
        System.out.println(Book.getFormattedBookTextHeader());
        for (Book book : library) {
            if (!book.isCheckedOut()) {
                System.out.println(book);
            }
        }

        System.out.println("\nWould You Like To Checkout A Book? If Yes Please Select (Y) For Yes");
        System.out.println("X - To Return To The Home Screen");
        String select = scanner.nextLine().trim().toUpperCase();
        if (select.equals("Y")) {
            checkOutBook();
        }
    }
// showing the books that are checkout and propmt the user to check them in if need be.
    private static void ShowScreenCheckedOutBooks() {
        System.out.println("\nChecked Out Books:");
        System.out.println(Book.getFormattedBookTextHeader());
        for (Book book : library) {
            if (book.isCheckedOut()) {
                System.out.println(book);
            }
        }

        System.out.println("\nC - To Check In A Book");
        System.out.println("X - To Return To The Home Screen");
        String select = scanner.nextLine().trim().toUpperCase();
        if (select.equals("C")) {
            checkInBook();
        }
    }
//this is where you prompt if they want to check a book out
    private static boolean checkOutBook() {
        System.out.print("\nEnter the ID of the book you want to check out: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book book : library) {
            if (book.getId() == bookId) {
                found = true;
                if (!book.isCheckedOut()) {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    book.checkOut(name);
                    System.out.println("You have successfully checked out: " + book.getTitle());
                } else {
                    System.out.println("That book is already checked out.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }

        return found;
    }
// where you can check A BOOK IN
    private static void checkInBook() {
        System.out.print("\nEnter the ID of the book you want to check in: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book book : library) {
            if (book.getId() == bookId) {
                found = true;
                if (book.isCheckedOut()) {
                    book.checkIn();
                    System.out.println("You have successfully checked in: " + book.getTitle());
                } else {
                    System.out.println("That book is already available.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private static Book[] getPopulatedBooks()  {
        try{


            FileReader fr = new FileReader("Books.txt");
            BufferedReader reader = new BufferedReader(fr);      Book[] booksTemp = new Book[1000];
            int size = 0;
            String dataString;

            while((dataString = reader.readLine()) != null){

                booksTemp[size] = getBookFromEncodedString(dataString);

                size++;

            }

            Book[] booksFinal = Arrays.copyOf(booksTemp, size);


            return booksFinal;
        } catch (Exception e) {
           // throw new RuntimeException(e);

            System.out.println("there is an error.");
            return null;
        }

    }


    private static Book getBookFromEncodedString(String encodedBook){

        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0].trim());
        String isbn = temp[1].trim();
        String title = temp[2].trim();

        Book result = new Book(id, isbn, title);
        return result;



    }
}