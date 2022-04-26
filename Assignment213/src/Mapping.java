import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /**
     * TODO
     * create a static LocationMap object
     */
    public static LocationMap lm = new LocationMap();


    /**
     * TODO
     * create a vocabulary HashMap to store all directions a user can go
     */
    HashMap<String, String> vocabulary = new HashMap<>();


    /**
     * TODO
     * create a FileLogger object
     */
    FileLogger fl = new FileLogger();

    /**
     * TODO
     * create a ConsoleLogger object
     */
    ConsoleLogger cl = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("QUIT", "Q");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");


    }

    public static void main(String[] args) {
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping game = new Mapping();
        game.mapping();

    }

    public void mapping() {

        /** TODO
         * create a Scanner object
         */

        Scanner input = new Scanner(System.in);

        /**
         * initialise a location variable with the INITIAL_LOCATION
         */
        int start = INITIAL_LOCATION;
        Map<String, Integer> mapOfExit;
        String inputedWords;
        String[] words;
        String validWord = "";
        int validWordIndex = 0;
        boolean isPreviousWordValid;
        while (true) {

            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            fl.log("\n" + lm.get(start).getDescription());
            cl.log("\n" + lm.get(start).getDescription());

            /** TODO
             * verify if the location is exit
             */
            if (start == 0) {
                break;
            }

            /** TODO
             * get a map of the exits for the location
             */
            mapOfExit = lm.get(start).getExits();

            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            StringBuilder exits = new StringBuilder();
            for (Map.Entry<String, Integer> e : mapOfExit.entrySet()) {
                exits.append(e.getKey() + ", ");
            }
            fl.log("Available exits are " + exits);
            cl.log("Available exits are " + exits);

            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            inputedWords = input.nextLine().toUpperCase();

            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            words = inputedWords.split(" ");
            if (words.length == 1) {
                if (vocabulary.containsKey(words[0])) {
                    validWord = vocabulary.get(words[0]);
                }
                if (vocabulary.containsValue(words[0])) {
                    validWord = words[0];
                }
            } else if (words.length == 0) {
                validWord = "";
            } else {
                for (int i = 0; i < words.length; i++) {
                    if (vocabulary.containsKey(words[i])) {
                        validWordIndex = i;
                        validWord = vocabulary.get(words[i]);
                    }
                }

                if (validWordIndex > 0) {
                    isPreviousWordValid = vocabulary.containsKey(words[validWordIndex - 1]);
                    if (isPreviousWordValid && !words[validWordIndex - 1].equals(words[validWordIndex]))
                        validWord = "";

                }

            }

            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */

            if (!validWord.equals("") && mapOfExit.containsKey(validWord)) {
                start = mapOfExit.get(validWord);
            } else {
                fl.log("\nYou cannot go in that direction");
                cl.log("\nYou cannot go in that direction");
            }
            validWordIndex = 0;
            validWord = "";

        }

    }

}
