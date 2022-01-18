import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке");
        String path = scanner.nextLine();

        String answer = "Размер папки " + path + " cоставляет "
                + trueDimensionality(FileUtils.calculateFolderSize(path));
        System.out.println(answer);
    }

    private static String trueDimensionality(double size){
        String answer = "";
        int count = 0;
        while (size > 1024){
            size /= 1024;
            count++;
        }
        answer += size;

        switch (count){
            case 0 -> answer += " б";
            case 1 -> answer += " Кб";
            case 2 -> answer += " Мб";
            case 3 -> answer += " Гб";
        }
        return answer;
    }
}
