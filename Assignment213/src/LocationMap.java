import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    /**
     * TODO
     * create a static locations HashMap
     */
    private static final HashMap<Integer, Location> locations = new HashMap<>();

    static {
        /** TODO
         * create a FileLogger object
         */
        FileLogger fl = new FileLogger();

        /** TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger cl = new ConsoleLogger();

        /** TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put the location and a new Location object in the locations HashMap, using temporary empty hashmaps for exits
         */
        int byteDataOfCommas = 44;
        int byteDtaOfNewLine = 10;
        try {
            FileReader reader = new FileReader(LOCATIONS_FILE_NAME);
            fl.log("Available locations:\n");
            cl.log("Available locations:\n");
            int byteData = reader.read();
            String charToBeLog;
            boolean changeCommas = false;
            StringBuilder locationId = new StringBuilder();
            StringBuilder description = new StringBuilder();
            boolean startAppendId = true;
            Location location;
            int locationIdInInt;
            while (byteData != -1) {
                charToBeLog = String.valueOf((char) byteData);
                if (byteData == byteDataOfCommas && changeCommas == true) {
                    fl.log(": ");
                    cl.log(": ");
                    charToBeLog = "";
                    startAppendId = false;
                } else {
                    fl.log(charToBeLog);
                    cl.log(charToBeLog);
                }

                if (startAppendId)
                    locationId.append(charToBeLog);
                else
                    description.append(charToBeLog);
                locationIdInInt = Integer.parseInt(locationId.toString());
                if (locationIdInInt == 141) {
                    String descriptions = "CONGRATULATIONS, YOU FOUND THE POT OF GOLD!\n";
                    location = new Location(locationIdInInt, descriptions, null);
                    locations.put(141, location);
                }

                if (byteData == byteDtaOfNewLine) {
                    startAppendId = true;
                    locationIdInInt = Integer.parseInt(locationId.toString());
                    location = new Location(locationIdInInt, description.toString(), new HashMap<String, Integer>());
                    locations.put(locationIdInInt, location);
                    locationId = new StringBuilder();
                    description = new StringBuilder();
                }

                try {
                    Integer.parseInt(charToBeLog);
                    changeCommas = true;
                } catch (NumberFormatException e) {
                    changeCommas = false;
                }

                byteData = reader.read();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * add the exits for each location
         */

        try {
            FileReader reader = new FileReader(DIRECTIONS_FILE_NAME);
            fl.log("\nAvailable directions:\n");
            cl.log("\nAvailable directions:\n");
            int byteData = reader.read();
            String charToBeLog;
            int commasCount = 0;
            Location location;
            String directionInString;
            int exitIdInInt;
            StringBuilder locationId = new StringBuilder();
            StringBuilder direction = new StringBuilder();
            StringBuilder exitId = new StringBuilder();
            int locationIdInInt;
            while (byteData != -1) {
                charToBeLog = String.valueOf((char) byteData);
                if (byteData == byteDataOfCommas) {
                    fl.log(": ");
                    cl.log(": ");
                    charToBeLog = "";
                    commasCount++;

                } else if (byteData == byteDtaOfNewLine) {
                    commasCount = 0;
                    locationIdInInt = Integer.parseInt(locationId.toString().trim());
                    location = locations.get(locationIdInInt);
                    directionInString = direction.toString().trim();
                    exitIdInInt = Integer.parseInt(exitId.toString().trim());
                    location.addExit(directionInString, exitIdInInt);
                    locationId = new StringBuilder();
                    direction = new StringBuilder();
                    exitId = new StringBuilder();
                } else {
                    fl.log(charToBeLog);
                    cl.log(charToBeLog);
                }
                if (commasCount == 0) {
                    locationId.append(charToBeLog);
                } else if (commasCount == 1)
                    direction.append(charToBeLog);
                else
                    exitId.append(charToBeLog);


                byteData = reader.read();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * TODO
     * implement all methods for Map
     *
     * @return
     */
    @Override
    public int size() {
        //TODO
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO
        return locations.containsKey(value);
    }

    @Override
    public Location get(Object key) {
        //TODO
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //TODO
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
        locations.putAll(m);
    }

    @Override
    public void clear() {
        //TODO
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        //TODO
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO
        return locations.entrySet();
    }
}

