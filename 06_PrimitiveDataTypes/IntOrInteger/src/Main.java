public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        for (char ch = 'А'; ch <= 'я'; ch++) {
            System.out.println(((int) ch) + " - " + ch);
        }

        char ch = 'Ё';
        System.out.println(((int) ch) + " - " + ch);
        ch = 'ё';
        System.out.println(((int) ch) + " - " + ch);
    }
}
