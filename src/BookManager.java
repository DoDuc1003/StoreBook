import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class BookManager {
    private String pathFile = "books.txt";
    private ArrayList<Book> books;
    public BookManager() {
        books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void sortDescByPrice() {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getPrice() == o2.getPrice()) {
                    return 0;
                }
                return o1.getPrice() > o2.getPrice() ? -1 : 1;
            }
        });
    }

    public boolean add(Book book) {
        // TODO: your code here
        for (Book bookCheck : books) {
            if (Integer.compare(bookCheck.getId(), book.getId()) == 0) {
                return false;
            }
        }
        books.add(book);
        return true;
    }

    public void loadFromFile() {
        System.out.println("Loading books...");

        Path path = Path.of(pathFile);
        try {
            List<String> list = Files.readAllLines(path);
            list.forEach(str -> {
                String[] data = str.split("<>");
                System.out.println("ID: " + data[0] + " " + data[1] + " " + data[2]);
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                Book newBook = new Book(id, name, price);
                books.add(newBook);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.printf("%-5s %-45s %-10s\n", "ID", "Name", "Price");
            books.forEach(book -> {
                System.out.printf("%5d %-45s %10.2f\n", book.getId(), book.getName(), book.getPrice());
            });
        }
    }

    public void remove(Book book) {
        if (books.size() == 0) {
            System.out.println("don't have book");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            if (book.equals(books.get(i))) {
                books.remove(i);
                return;
            }
        }
    }

    public Book getBookById(int id) {
        for (Book book : books) {
            if (Integer.compare(id, book.getId()) == 0) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();

        for (Book book : books) {
            if (book.getName().contains(keyword)) {
                matches.add(book);
            }
        }

        return matches;
    }

    public void saveToFile() {
        // TODO: your code here
        try {
            pathFile = "output.txt";
            FileWriter writer = new FileWriter(pathFile);
            for (Book book : books) {
                writer.write(book.getId() + "<>" + book.getName() + "<>" + book.getPrice() + "\n");
                System.out.println("done");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void screenShots() {
        String s =
                "-----------------------------------\n" +
                "1. list all books\n" +
                "2. add a new book\n" +
                "3. edit book\n" +
                "4. delete a book\n" +
                "5. search books by name \n" +
                "6. sort books descending by price\n" +
                "0. save & exit\n" +
                "-----------------------------------\n";
        System.out.println(s);
    }
}
