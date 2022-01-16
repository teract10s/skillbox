import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    RouteCalculator calculator;
    Station dispatchStation;
    Station station1;
    Station station2;
    Station station3;

    @Override
    protected void setUp() {
        StationIndex stationIndex = new StationIndex();

        List<Station> stationsLine1 = new ArrayList<>();
        List<Station> stationsLine2 = new ArrayList<>();
        List<Station> stationsLine3 = new ArrayList<>();

        Line line1 = new Line(1, "BLUE");
        Line line2 = new Line(2, "RED");
        Line line3 = new Line(3, "GREEN");

        dispatchStation = new Station("Поштова площа", line1);
        station1 = new Station("Олімпійська", line1);
        stationsLine1.add(dispatchStation);
        stationsLine1.add(new Station("Майдан Незалежності", line1));
        stationsLine1.add(new Station("Площа Льва Толстого", line1));
        stationsLine1.add(station1);

        adding(stationsLine1, line1, stationIndex);

        station2 = new Station("Університет", line2);
        stationsLine2.add(new Station("Вокзальна", line2));
        stationsLine2.add(station2);
        stationsLine2.add(new Station("Театральна", line2));
        stationsLine2.add(new Station("Хрещатик", line2));
        stationsLine2.add(new Station("Арсенальна", line2));

        adding(stationsLine2, line2, stationIndex);

        station3 = new Station("Лук'янівська", line3);
        stationsLine3.add(station3);
        stationsLine3.add(new Station("Золоті ворота", line3));
        stationsLine3.add(new Station("Палац спорту", line3));
        stationsLine3.add(new Station("Кловська", line3));

        adding(stationsLine3, line3, stationIndex);

        List<Station> connection12 = new ArrayList<>();
        connection12.add(new Station("Майдан Незалежності", line1));
        connection12.add(new Station("Хрещатик", line2));
        stationIndex.addConnection(connection12);

        List<Station> connection23 = new ArrayList<>();
        connection23.add(new Station("Театральна", line2));
        connection23.add(new Station("Золоті ворота", line3));
        stationIndex.addConnection(connection23);

        calculator = new RouteCalculator(stationIndex);
    }

    public void testRouteWithoutConnection(){
        List<Station> actual = calculator.getShortestRoute(dispatchStation, station1);
        List<Station> expected = new ArrayList<>();
        expected.add(dispatchStation);
        expected.add(new Station("Майдан Незалежності", new Line(1, "BLUE")));
        expected.add(new Station("Площа Льва Толстого", new Line(1, "BLUE")));
        expected.add(station1);

        assertEquals(expected, actual);
    }

    public void testRouteWithOneConnection(){
        List<Station> actual = calculator.getShortestRoute(dispatchStation, station2);
        List<Station> expected = new ArrayList<>();
        expected.add(dispatchStation);
        expected.add(new Station("Майдан Незалежності", new Line(1, "BLUE")));
        expected.add(new Station("Хрещатик", new Line(2, "RED")));
        expected.add(new Station("Театральна", new Line(2, "RED")));
        expected.add(station2);

        assertEquals(expected, actual);
    }

    public void testRouteWithTwoConnection(){
        List<Station> actual = calculator.getShortestRoute(dispatchStation, station3);
        List<Station> expected = new ArrayList<>();
        expected.add(dispatchStation);
        expected.add(new Station("Майдан Незалежності", new Line(1, "BLUE")));
        expected.add(new Station("Хрещатик", new Line(2, "RED")));
        expected.add(new Station("Театральна", new Line(2, "RED")));
        expected.add(new Station("Золоті ворота", new Line(3, "GREEN")));
        expected.add(station3);

        assertEquals(expected, actual);
    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(calculator.getShortestRoute(dispatchStation, station1));
        double expected = 7.5;

        assertEquals(expected, actual);
    }

    private void adding(List<Station> stations, Line line, StationIndex stationIndex){
        for (Station e : stations) {
            line.addStation(e);
            stationIndex.addStation(e);
        }
        stationIndex.addLine(line);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}