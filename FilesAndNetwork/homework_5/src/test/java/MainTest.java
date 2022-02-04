import junit.framework.TestCase;

import java.util.ArrayList;

public class MainTest extends TestCase {
    private Metro metro;

    @Override
    protected void setUp() {
        metro = new Metro();
        ArrayList<Line> allLine = new ArrayList<>();

        Line line1 = new Line("First", "1");
        Line line2 = new Line("Second", "1");
        Line line3 = new Line("Third", "3");

        line1.addStation(new Station("first_1", line1));
        line1.addStation(new Station("first_2", line1));
        line1.addStation(new Station("first_3", line1));
        line1.addStation(new Station("first_4", line1));

        line2.addStation(new Station("second_1", line2));
        line2.addStation(new Station("second_2", line2));
        line2.addStation(new Station("second_3", line2));
        line2.addStation(new Station("second_4", line2));
        line2.addStation(new Station("second_5", line2));
        line2.addStation(new Station("second_6", line2));
        line2.addStation(new Station("second_7", line2));
        line2.addStation(new Station("second_8", line2));

        line3.addStation(new Station("third_1", line3));
        line3.addStation(new Station("third_2", line3));
        line3.addStation(new Station("third_3", line3));

        allLine.add(line1);
        allLine.add(line2);
        allLine.add(line3);

        metro.setAllLine(allLine);
    }

    public void testGetCountStationsAtLine() {
        String actual = Main.getCountStationsAtLine(metro);

        String expected = """
                First\t4
                Second\t8
                Third\t3
                """;
        assertEquals(expected, actual);
    }
}