import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    /**
     * TODO
     * declare private final locationId, description, exits
     */
    private final int LOCATION_ID;
    private final String DESCRIPTION;
    private final LinkedHashMap<String, Integer> EXITS;


    public Location(int locationId, String description, Map<String, Integer> exits) {
        /** TODO
         * set the locationId and the description
         */
        this.LOCATION_ID = locationId;
        this.DESCRIPTION = description;

        /** TODO
         * if exits are not null, set the exit
         * Note that exits should be of type LinkedHashMap to maintain the insertion order
         * otherwise, set the exits LinkedHashMap to (Q,0)
         */
        if (exits != null) {
            this.EXITS = new LinkedHashMap<String, Integer>();
            for (Map.Entry<String, Integer> e : exits.entrySet()) {
                EXITS.put(e.getKey(), e.getValue());
            }
        } else {
            this.EXITS = new LinkedHashMap<String, Integer>();
            this.EXITS.put("Q", 0);
        }
    }

    protected void addExit(String direction, int location) {
        /** TODO
         * put the direction and the location in the exits LinkedHashMap
         */
        EXITS.put(direction, location);

    }

    public int getLocationId() {
        /** TODO
         * complete getter to return the location id
         */
        return LOCATION_ID;
    }

    public String getDescription() {
        /** TODO
         * complete getter to return the description
         */
        return DESCRIPTION;
    }

    public Map<String, Integer> getExits() {
        /** TODO
         * complete getter to return a copy of exits
         * (preventing modification of exits from outside the Location instance)
         */
        Map<String, Integer> copyOfMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> e : EXITS.entrySet()) {
            copyOfMap.put(e.getKey(), e.getValue());
        }
        return copyOfMap;
    }
}
