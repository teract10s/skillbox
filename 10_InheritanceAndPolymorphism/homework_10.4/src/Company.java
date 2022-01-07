import java.util.*;

public class Company{
    private static final int INCOME = (int) (100_000 + 24_900_000 * Math.random());

    private TreeSet<Employee> workers = createWorkersList();

    private TreeSet<Employee> createWorkersList(){
        TreeSet<Employee> answer = new TreeSet<>(
        new Comparator<>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Integer.compare(o2.getMonthSalary(), o1.getMonthSalary());
            }
        });
        return answer;
    }

    public TreeSet<Employee> getWorkers() {
        return workers;
    }

    public List<Employee> getTopSalaryStaff(int count){
        List<Employee> answer = new ArrayList<>(workers).subList(0, count);

        for (Employee e : answer){
            System.out.println(e.getMonthSalary() + " руб.");
        }
        return answer;
    }

    public List<Employee> getLowestSalaryStaff(int count){
        List<Employee> answer = new ArrayList<>(workers).subList(workers.size() - count - 1, workers.size() - 1);

        for (Employee e : answer){
            System.out.println(e.getMonthSalary() + " руб.");
        }
        return answer;
    }

    @Override
    public String toString(){
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (Employee e : workers){
            answer.append(++i).append(". ").append(e.getMonthSalary()).append(" руб.  ").append(e.getClass().toString()).append("\n");
        }
        return answer.toString();
    }

    public int getIncome(){
        return INCOME;
    }

//                            ADD/REMOVE worker

    public void hire(TypeOfWorkers type){
        switch (type){
            case Manager -> addManager();
            case TopManager ->  addTopManager();
            case Operator -> addOperator();
        }
    }

    public void hireAll(int count, TypeOfWorkers type){
        switch (type){
            case Manager -> {
                for (int i = 0; i < count; i++) {
                    addManager();
                }
            }
            case TopManager -> {
                for (int i = 0; i < count; i++) {
                    addTopManager();
                }
            }
            case Operator -> {
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

    public void fire(int interest, TypeOfWorkers type){
        Iterator<Employee> iter = workers.iterator();
        int i = 0;
        int indexToRemove = 100 / interest;

        while (iter.hasNext()){
            boolean admissibilityOfTheIndex = i % indexToRemove == 0;
            String clas = iter.next().getClass().toString();
            int first = clas.indexOf(' ');
            int end = clas.length();
            clas = clas.substring(first + 1, end);
            boolean sameType = clas.equals(type.toString());

            if (admissibilityOfTheIndex && sameType && iter.hasNext()){
                iter.remove();
            }
            i++;
        }
    }
}
