import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean running = true;
        BookManager manager = new BookManager();
        manager.loadFromFile();
        Scanner sc = new Scanner(System.in);
        System.out.println("Loading books...\n");
        while (running) {

            manager.screenShots();
            System.out.print("Your option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("VALID option");
                    System.out.println();
                    manager.printBooks(manager.getBooks());
                    break;
                case 2:
                    System.out.println("VALID option");
                    System.out.print("Enter book id: ");
                    int id = sc.nextInt();
                    Scanner textscan =new Scanner(System.in);
                    System.out.print("Enter book name: ");
                    String name = textscan.nextLine();
                    System.out.print("Enter book price: ");
                    double price = textscan.nextDouble();
                    if (manager.add(new Book(id, name, price))) {
                        System.out.println("Added successfully.");
                    }
                    System.out.println();
                    break;
                case 3:
                    /**
                     * edit
                     */
                    System.out.print("Enter keyword: ");
                    int idEdit = sc.nextInt();
                    System.out.print("Enter book name: ");
                    Scanner inputEdit = new Scanner(System.in);
                    String nameEdit = inputEdit.nextLine();
                    System.out.print("Enter book price: ");
                    double peiceEdit = sc.nextDouble();
                    Book book = manager.getBookById(idEdit);
                    book.setName(nameEdit);
                    book.setPrice(peiceEdit);

                    System.out.println("Updated successfully.");
                    break;
                case 4:
                    System.out.println("VALID option");
                    System.out.print("Enter book id: ");
                    int idDelete = sc.nextInt();
                    manager.remove(manager.getBookById(idDelete));
                    manager.printBooks(manager.getBooks());
                    break;
                case 5:
                    System.out.print("Enter keyword: ");
                    String key = sc.nextLine();
                    manager.printBooks(manager.searchByName(key));
                    break;
                case 6:
                    manager.sortDescByPrice();
                    manager.printBooks(manager.getBooks());
                    break;
                case 0:
                    running = false;
                    manager.saveToFile();
                    System.out.println("Saving to file...\n" +
                            "Bye!");
                    break;
                default:
                    System.out.println("INVALID");
            }
        }
    }
}
