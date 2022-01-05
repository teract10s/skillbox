import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Company{
    private static final int income = (int) (100_000 + 24_900_000 * Math.random());

    TreeSet<Employee> workers = new TreeSet<>(new Comparator<>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o2.getMonthSalary(), o1.getMonthSalary());
        }
    });

    public ArrayList<Employee> getTopSalaryStaff(int count){
        ArrayList<Employee> answer = new ArrayList<>(workers);

        for (int i = workers.size() - 1; i > workers.size() - count - 1; i--){
            System.out.println(answer.get(i).getMonthSalary() + " руб.");
        }
        return answer;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count){
        ArrayList<Employee> answer = new ArrayList<>(workers);

        for (int i = 0; i < count; i++){
            System.out.println(answer.get(i).getMonthSalary() + " руб.");
        }
        return answer;
    }

    @Override
    public String toString(){
        String answer = "";
        int i = 0;
        for (Employee e : workers){
            answer += ++i + ". " + e.getMonthSalary() + " руб.  " + e.getClass().toString() + "\n";
        }
        return answer;
    }

    public int getIncome(){
        return income;
    }

//                            ADD/REMOVE worker

    public void hire(String position){
        switch (position){
            case "Manager" -> addManager();
            case "TopManager" ->  addTopManager();
            case "Operator" -> addOperator();
        }
    }

    public void hireAll(int count, String position){
        switch (position){
            case "Manager" -> {
                for (int i = 0; i < count; i++) {
                    addManager();
                }
            }
            case "TopManager" -> {
                for (int i = 0; i < count; i++) {
                    addTopManager();
                }
            }
            case "Operator" -> {
                for (int i = 0; i < count; i++) {
                    addOperator();
                }
            }
        }
    }

    private void addManager(){
        Manager manager = new Manager();
        workers.add(manager);
    }

    private void addTopManager(){
        TopManager topManager = new TopManager();
        workers.add(topManager);
    }

    private void addOperator(){
        Operator operator = new Operator();
        workers.add(operator);
    }

    public void fire(int index){
        ArrayList<Employee> newWorkersList = new ArrayList<>(workers);
        newWorkersList.remove(index);
        workers.removeAll(workers);
        workers.addAll(newWorkersList);
    }
}
