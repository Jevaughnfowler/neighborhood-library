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
        String homeScreenPrompt = "\nWelcome to the library!\n" +
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
            FileReader fr = new FileReader("books.txt");
            BufferedReader reader = new BufferedReader(fr);

            Book[] booksTemp = new Book[1000];
            int size = 0;
            String dataString;

            while((dataString = reader.readLine()) != null){

                booksTemp[size] = getBookFromEncodedString(dataString);

                size++;

            }

            Book[] booksFinal = Arrays.copyOf(booksTemp, size);


            return booksFinal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static Book getBookFromEncodedString(String encodedBook){

        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[2];

        Book result = new Book(id, isbn, title);
        return result;


        //    private static Book[] getPopulatedBooks() {
//        Book[] library = new Book[20];
//
//        library[0] = new Book(1, "978-1612680194", "Rich Dad Poor Dad");
//        library[1] = new Book(2, "978-0062315007", "The Millionaire Next Door");
//        library[2] = new Book(3, "978-0399592522", "The Subtle Art of Not Giving a F*ck");
//        library[3] = new Book(4, "978-0316334175", "The 7 Habits of Highly Effective People");
//        library[4] = new Book(5, "978-1591847786", "Start with Why");
//        library[5] = new Book(6, "978-1451668381", "Thinking, Fast and Slow");
//        library[6] = new Book(7, "978-0062315007", "Atomic Habits");
//        library[7] = new Book(8, "978-1451673515", "How to Win Friends and Influence People");
//        library[8] = new Book(9, "978-0446547374", "The Lean Startup");
//        library[9] = new Book(10, "978-0547743031", "Grit: The Power of Passion and Perseverance");
//        library[10] = new Book(11, "978-0062498533", "The Psychology of Money");
//        library[11] = new Book(12, "978-0525533183", "The 4-Hour Workweek");
//        library[12] = new Book(13, "978-1591842248", "Tools of Titans");
//        library[13] = new Book(14, "978-1250124227", "The Power of Now");
//        library[14] = new Book(15, "978-1594480003", "Blink: The Power of Thinking Without Thinking");
//        library[15] = new Book(16, "978-0743273565", "The Intelligent Investor");
//        library[16] = new Book(17, "978-0385349342", "Shoe Dog");
//        library[17] = new Book(18, "978-1781256768", "The Little Book of Common Sense Investing");
//        library[18] = new Book(19, "978-0062212579", "Principles: Life and Work");
//        library[19] = new Book(20, "978-0062391962", "The Magic of Thinking Big");
//
//        return library;
    }
}