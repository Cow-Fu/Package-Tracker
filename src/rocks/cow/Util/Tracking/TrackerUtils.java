package rocks.cow.Util.Tracking;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TrackerUtils {

    public static ArrayList<String> fillBlanks(ArrayList<String> list) {
        ArrayList<String> temp = new ArrayList<>();
        Pattern emptyStr = Pattern.compile("^([ ]*)$");

        for (int i = 0; i < list.size() - 1; ++i) {
            if (emptyStr.matcher(list.get(i)).find()) {
                temp.add(temp.get(i - 1));
                continue;
            }
            temp.add(list.get(i));
        }
        return temp;
    }

    public static ArrayList<String> formatText(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (String text: list) {
            text = text.trim();

            newList.add(text);
        }
        return newList;
    }

}
