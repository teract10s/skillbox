public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader("src//main//resources//mongo.csv");
        MongoDB mongoDB = new MongoDB(reader.getStudents());

        System.out.println("Size: " + mongoDB.getCount());
        System.out.println("Number of students over the age of 40: " + mongoDB.getCountWhereAge());
        System.out.println("The youngest student: " + mongoDB.getNameYoungStudent());
        System.out.println("Courses of the oldest student: " + mongoDB.getCoursesOldestStudent());

    }
}
