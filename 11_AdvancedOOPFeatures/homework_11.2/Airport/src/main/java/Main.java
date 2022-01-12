import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import net.sf.saxon.functions.Abs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        findPlanesLeavingInTheNextTwoHours(airport);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        LocalDateTime now = LocalDateTime.now();
        int nowHour = now.getHour();
        int nowDay = now.getDayOfMonth();

        return airport.getTerminals().stream()
                .map(t -> t.getFlights())
                .flatMap(flight -> flight.stream())
                .filter(f -> {
                    LocalDateTime departureTime  = getLocalDate(f.getDate());
                    boolean suitableType = f.getType() == Flight.Type.DEPARTURE;
                    boolean suitableHour = Math.abs(departureTime.getHour() - nowHour) <= 2;
                    boolean suitableDay = departureTime.getDayOfMonth() == nowDay;

                    if (suitableHour && suitableDay && suitableType) {
                        return true;
                    }else{
                        return false;
                    }
                }).collect(Collectors.toList());
    }

    private static LocalDateTime getLocalDate(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}