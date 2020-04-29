import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    Pattern delimter1 = Pattern.compile("//;\n(.*);(.*)");
    Pattern delimter2 = Pattern.compile("//4\n(.*+)4(.*)");




    public int add(String num) {
        String SingleTrim = num.trim();
        int answer;
        if(SingleTrim.isEmpty()){
            answer = 0;
        }else if(SingleTrim.length()==1){
                answer = Integer.parseInt(SingleTrim);
        }else{
            answer = addingTowNumbers(SingleTrim);
        }

        return answer;
    }
    int addingTowNumbers(String strNum){

       int answer =0;
        String strNumWithOut1000 = demiterWith1000(strNum);
        for(int i = 0; i < delimeterHandler(strNumWithOut1000).length; ++i){
           String multiTrim = delimeterHandler(strNumWithOut1000)[i].trim();
           if(i < 1 && !(delimeterHandler(strNumWithOut1000)[i].trim().length() ==2)){
                if(Integer.parseInt(multiTrim)>=1000){
                    answer = 0;
                } else {
                    answer = answer + Integer.parseInt(multiTrim);
                }

           }else if(i < 1 && !(delimeterHandler(strNumWithOut1000)[i].trim().length() ==2) && Integer.parseInt(multiTrim)<100){
               answer = answer + Integer.parseInt(multiTrim);
           }else if(Integer.parseInt(multiTrim)<1000){
               try {
                   if (Integer.parseInt(multiTrim) < 0) {
                       throw new IllegalArgumentException();
                   }else {
                       answer = answer + Integer.parseInt(multiTrim);
                   }
                    }catch (IllegalArgumentException e){
                   System.out.println("ERROR: negatives not allowed");
               }
           }
        }

        return answer;
    }

    String demiterWith1000(String strNum){
        Matcher m = delimter1.matcher(strNum);
        Matcher m2 = delimter2.matcher(strNum);
        boolean b1 = m.matches();
        boolean b2 = m2.matches();


        String strNumWithOut1000 = strNum;

        if(b1){
            strNumWithOut1000= strNum.replace(',',';');
        }else if(b2) {
            strNumWithOut1000= strNum.replace(',','4');
        }
        return strNumWithOut1000;

    }


    private String[] delimeterHandler(String strNum){
        Matcher m = delimter1.matcher(strNum);
        Matcher m2 = delimter2.matcher(strNum);
        boolean b1 = m.matches();
        boolean b2 = m2.matches();

        String[] valueSaver ;

        if(b1){
            valueSaver = strNum.split(";",10);
            List<String> list = new ArrayList<>(Arrays.asList(valueSaver));
            list.remove("//");
            valueSaver = list.toArray(new String[0]);
        }else if(b2) {
            valueSaver = strNum.split("4",10);
            List<String> list = new ArrayList<>(Arrays.asList(valueSaver));
            list.remove("//");
            valueSaver = list.toArray(new String[0]);
        }else{
            valueSaver = strNum.split(",",10);
        }
        return valueSaver;

    }
    static void regex(){
        Pattern delimter = Pattern.compile(".+(?=\n)");
        String b = "//;\n10089;1,2";
        Matcher m = delimter.matcher(b);

        while (m.find()){
            System.out.println(m.group());
        }
    }


    public static void main(String[] args) {
        regex();
    }
//    String removeWord(String string, String word)
//    {
//        if (string.contains(word)) {
//
//            String tempWord = word + "";
//            string = string.replaceAll(tempWord, "");
//            tempWord = "" + word;
//            string = string.replaceAll(tempWord, "");
//        }
//        return string;
//    }
}
