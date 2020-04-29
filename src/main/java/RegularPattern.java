import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularPattern {

    Pattern patternMultiplesDelimiters = Pattern.compile("(?<=\\[).+?(?=\\])"); // multi delimiter regex
    Pattern patternSingleDelimiter = Pattern.compile("(?<=\\/\\/).+?(?=\\\\n)"); // single delimiter regex
    Pattern numberPattern = Pattern.compile("-\\d+|\\d+");                      // negative and positive numbers regex
    Pattern correctFormat = Pattern.compile("\\d+.+\\d+$|\\d+$");           // valid format regex
    int[] negatives = {};           // an array to put in the negative numbers;
    String string = null;           // to be used to in the process of deleting delimiter.
    int total = 0;                  // to be used to calculate the sum.

    public int add(String string) { // takes a string as a parameter and calculates the sum of the integers.

        Matcher matcher = numberPattern.matcher(reformatString(string));

        while (matcher.find()) {        // find the sum

            int matched = Integer.parseInt(matcher.group());

            if (matched < 1000 && matched >= 0) {
                total += matched;
            }

            if (matched < 0) {
                negatives = Arrays.copyOf(negatives,negatives.length+1);
                negatives[negatives.length-1] = matched;
            }
        }

        errors(string);

        return total;
    }

    private String reformatString(String string) { // convert a string to this format 1, 2, 3, 4

        this.string=string;

        Matcher matcherMulti = patternMultiplesDelimiters.matcher(this.string);

        while (matcherMulti.find()) { // replace multi delimiters with a comma
            this.string = this.string.replace(matcherMulti.group(),",");
        }

        Matcher matcherSingle = patternSingleDelimiter.matcher(this.string);

        while (matcherSingle.find()) { // replace sing delimiters with a comma
            this.string = this.string.replace(matcherSingle.group(),",");
        }

        this.string = this.string.replace("//,\\n","");
        this.string = this.string.replace("\\n",",");

        return this.string;
    }

    private void errors(String string) { // check if a string is in a required format and throw exceptions

        Matcher matcher = correctFormat.matcher(this.string);

        try{
           try {
               if (negatives.length > 0) {
                    throw new Exception();
               }
           } catch (Exception e1) {
               System.out.println("ERROR: negatives not allowed " + Arrays.toString(negatives).replace("[","").replace("]",""));
           }
           if ((!string.startsWith("//") && string.indexOf("/") > 0) | (!matcher.find() && total > 0)) {
               throw new Exception();
           }
        } catch (Exception e2) {
            System.out.println("ERROR: invalid input");
        }
    }
}
