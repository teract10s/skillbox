import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println(findPlanesLeavingInTheNextTwoHours(airport));
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        LocalDate now = LocalDate.now();
        List<Terminal> terminal = airport.getTerminals();
        List<Flight> answer = new ArrayList<>();

        for (Terminal t : terminal){
            List<Flight> intermediateList = t.getFlights().stream()
                    .filter(f -> f.getDate().getHours() - now.atStartOfDay().getHour() <= 2)
                    .collect(Collectors.toList());
            answer.addAll(intermediateList);
        }
        return answer;
    }

}