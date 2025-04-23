package com.pluralsight;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Book[] library = getPopulatedBooks();

    public static void main(String[] args) {
        ShowScreenHome();

        String input = scanner.nextLine().trim().toUpperCase();
        if (input.equals("C")){
            checkInBook();
        } else if (input.equals("X")) {
            ShowScreenHome();
          }

    }

    //prompt the user for interaction

    private static void ShowScreenHome() {
        String homeScreenPrompt = "\nWelcome to the library!\n" +
                "Please select an option from the following:\n" +
                "    1 - Show Available Books\n" +
                "    2 - Show Checked Out Books\n" +
                "    3 - Check Out a Book\n" +
                "    4 - Check In a Book\n" +
                "    0 - Exit App\n" +
                "(1,2,3,4,0): ";

        //This is where the user take action and response ny choose what they would like to do.
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

    //this is where we are showing the user options they have available

    private static void ShowScreenAvailableBooks() {
        System.out.println("\nAvailable Books:");
        String select = scanner.nextLine().trim().toUpperCase();

        for (Book book : library) {
            if (!book.isCheckedOut()) {
                System.out.println(book);
            }
        }
         if (select.equals("Y")){
            checkOutBook();
        }
        System.out.println("\nWould You Like To Checkout A Book? " +
                            "If Yes Please Select (Y) For Yes");
        System.out.println("\nX - To Return To The Home Screen");
        scanner.nextLine();
    }

    // this is where we show the user what books that have been checked out and by who
    private static void ShowScreenCheckedOutBooks() {
        System.out.println("\nChecked Out Books:");

        for (Book book : library) {
            if (book.isCheckedOut()) {
                System.out.println(book.toCheckedOutString());
            }
        }

        System.out.println("\nC - To Check In A Book");
        System.out.println("\nX - To Return To The Home Screen");
        scanner.nextLine();
    }

    //this is the option the user can choose if they want to, checkOut a book where they would have to enter their names in order to complete the checkout
    private static void checkOutBook() {
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

        System.out.println("\nX - To Return To The Home Screen");
        scanner.nextLine();
    }

// this is where the user can check in the book back into the library

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

        System.out.println("\nX - To Return To The Home Screen");
        scanner.nextLine();
    }


//And these are the list of books the user have to choose from.

    private static Book[] getPopulatedBooks() {
        Book[] library = new Book[20];

        library[0] = new Book(1, "978-1612680194", "Rich Dad Poor Dad");
        library[1] = new Book(2, "978-0062315007", "The Millionaire Next Door");
        library[2] = new Book(3, "978-0399592522", "The Subtle Art of Not Giving a F*ck");
        library[3] = new Book(4, "978-0316334175", "The 7 Habits of Highly Effective People");
        library[4] = new Book(5, "978-1591847786", "Start with Why");
        library[5] = new Book(6, "978-1451668381", "Thinking, Fast and Slow");
        library[6] = new Book(7, "978-0062315007", "Atomic Habits");
        library[7] = new Book(8, "978-1451673515", "How to Win Friends and Influence People");
        library[8] = new Book(9, "978-0446547374", "The Lean Startup");
        library[9] = new Book(10, "978-0547743031", "Grit: The Power of Passion and Perseverance");
        library[10] = new Book(11, "978-0062498533", "The Psychology of Money");
        library[11] = new Book(12, "978-0525533183", "The 4-Hour Workweek");
        library[12] = new Book(13, "978-1591842248", "Tools of Titans");
        library[13] = new Book(14, "978-1250124227", "The Power of Now");
        library[14] = new Book(15, "978-1594480003", "Blink: The Power of Thinking Without Thinking");
        library[15] = new Book(16, "978-0743273565", "The Intelligent Investor");
        library[16] = new Book(17, "978-0385349342", "Shoe Dog");
        library[17] = new Book(18, "978-1781256768", "The Little Book of Common Sense Investing");
        library[18] = new Book(19, "978-0062212579", "Principles: Life and Work");
        library[19] = new Book(20, "978-0062391962", "The Magic of Thinking Big");

        return library;
    }
}