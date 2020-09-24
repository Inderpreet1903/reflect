package jp.co.reflect.tests.helper;

public class CommonFunctions {

    public static String getRandomString(int n) {
        String alphaNum = "abcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int) (alphaNum.length()
                * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNum
                .charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomNumber(int n) {
        String alphaNum = "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int) (alphaNum.length()
                * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNum
                .charAt(index));
        }
        return sb.toString();
    }

}
