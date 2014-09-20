package cityconnect;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CityConnectTest {

    @Test
    public void testExecuteCommand() {

        testOneCommand("simple get before any add", "No route exists from aaa to bbb!", "getdistance aaa bbb");

        testOneCommand("simple add", "Route from aaa to bbb with distance 23km added", "addroute aaa bbb 23");
        testOneCommand("simple get after one add", "Distance from aaa to bbb is 23", "getdistance aaa bbb");
        testOneCommand("simple get , revers order", "Distance from bbb to aaa is 23", "getdistance bbb aaa");

        testOneCommand("simple get , extra spaces", "Distance from aaa to bbb is 23", "  getdistance  	 aaa  bbb ");

		//more tests go here
        //Add until the storage is full
        for (int i = 1; i < 10; i++) {
            testOneCommand("simple add", "Route from a" + i + " to b" + i + " with distance " + i + "km added", "addroute a" + i + " b" + i + " " + i);
        }

        testOneCommand("overwrite, full storage", "Route from a6 to b6 with distance 18km added", "addroute a6 b6 18");
        testOneCommand("add, full storage", "No more space to store locations", "addroute a11 b11 11");

    }

    private void testOneCommand(String description, String expected, String command) {
        assertEquals(description, expected, CityConnect.executeCommand(command));
    }

}
