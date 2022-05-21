import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static MongoDB mongoDB;

    private static void init(){
        mongoDB = new MongoDB();
    }

    public static void main(String[] args) {
        init();
        System.out.println("Возможные команды:");
        System.out.println("\tДОБАВИТЬ_МАГАЗИН <магазин>");
        System.out.println("\tДОБАВИТЬ_ТОВАР <товар> <цена>");
        System.out.println("\tВЫСТАВИТЬ_ТОВАР <товар> <магазин>");
        System.out.println("\tСТАТИСТИКА_ТОВАРОВ");
        System.out.println("Exit");
        String command = "";
        Scanner scanner = new Scanner(System.in);
        while (!command.equals("Exit")){
            command = scanner.nextLine();
            choise(command);
        }
    }

    private static void choise(String command){
        String[] parts = command.split(" ");
        switch (parts[0]){
            case "ДОБАВИТЬ_МАГАЗИН":
                if (parts.length == 2){
                    Shop shop = new Shop(parts[1], new ArrayList<>());
                    mongoDB.addShop(parts[1]);
                }
                break;
            case "ДОБАВИТЬ_ТОВАР":
                if (parts.length == 3){
                    Product product = new Product(parts[1], Integer.parseInt(parts[2]));
                    mongoDB.addProduct(parts[1], Integer.parseInt(parts[2]));
                }
                break;
            case "ВЫСТАВИТЬ_ТОВАР":
                if (parts.length == 3){
                    mongoDB.exhibitTheGoods(parts[1], parts[2]);
                }
                break;
            case "СТАТИСТИКА_ТОВАРОВ":
                if (parts.length == 1){
                    mongoDB.getStats();
                }

                mongoDB.getProducts().forEach(System.out::print);
                System.out.println();
                mongoDB.getShops().forEach(System.out::print);
                System.out.println();
                break;
            default:
                break;
        }
    }
}
