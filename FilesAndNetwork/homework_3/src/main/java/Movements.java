import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TreeMap;

public class Movements {
    private static String movementFile;
    private static final String DATE_FORMAT = "dd.mm.yy";
    private final ArrayList<Movement> movements;
    private static final TreeMap<String, Double> organizations = new TreeMap<>();

    public Movements(String pathMovementsCsv) {
        movementFile = pathMovementsCsv;
        movements = loadMovementFromFile();
    }

    public double getExpenseSum() {
        return movements.stream().map(Movement::getConsumption).reduce(Double::sum).orElse(0.0);
    }

    public double getIncomeSum() {
        return movements.stream().map(Movement::getComing).reduce(Double::sum).orElse(0.0);
    }

    private static ArrayList<Movement> loadMovementFromFile(){
        ArrayList<Movement> movements = new ArrayList<>();
        try{
            CSVReader reader = new CSVReader(new FileReader(movementFile), ',' , '"');
            String[] fragments;

            while ((fragments = reader.readNext()) != null) {
                if (fragments.length != 8){
                    System.out.println("Wrong line. You have " + fragments.length + " fields");
                    continue;
                }
                if (addMovement(fragments) != null){
                    movements.add(addMovement(fragments));
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return movements;
    }

    private static Movement addMovement(String[] fragments){
        try {
            double coming = toDouble(fragments[6]);
            double consumption = toDouble(fragments[7]);

            String key = fragments[5].substring(20, 65);
            Double value = consumption;
            if (organizations.containsKey(key)){
                value += organizations.get(key);
            }
            organizations.put(key, value);

            return new Movement(
                    fragments[0],
                    fragments[1],
                    fragments[2],
                    (new SimpleDateFormat(DATE_FORMAT).parse(fragments[3])),
                    fragments[4],
                    fragments[5],
                    coming,
                    consumption
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static double toDouble(String str){
        String[] number = str.split(",");

        if (number.length == 2){
            return Double.parseDouble(number[0] + '.' + number[1]);
        }else{
            return Double.parseDouble(str);
        }
    }

    public void getCostsOfOrganizations(){
        System.out.println("Суммы расходов по организациям:");
        organizations.keySet().forEach(e -> System.out.println(e + organizations.get(e) + " руб."));
    }
}
