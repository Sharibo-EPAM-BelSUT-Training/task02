package gmail.alexejkrawez;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    /**
     * The original text.
     */
    private String text = "Текст, который будет разрезан, преобразован и собран обратно!!!" +
            " На выходе: собранный из элементов текст должен будет совпадать с изначальным?!" +
            " Хмм..." + " Давайте попробуем решить эту на первый взгляд сложную задачу." +
            " Сделаем так, чтобы она правильно работала!";

    /**
     * List of splits sentences.
     */
    private ArrayList<String> sentencesList = new ArrayList<String>();

    /**
     * Get the original text.
     *
     * @return the original text.
     */
    public String getText() {
        return text;
    }

    /**
     * Get sentences list.
     *
     * @return list of splits sentences.
     */
    public ArrayList<String> getSentencesList() {
        return sentencesList;
    }

    /**
     * Constructor of the text-object which include the original text
     * and list of splits sentences.
     */
    Text() {
        textSplit();
    }

    /**
     * Split text in sentencesList and write it in array list "sentencesList".
     */
    public void textSplit() {
        Pattern pat = Pattern.compile("[A-ZА-Я].+?(!!!|!|\\?|\\?!|\\.\\.\\.|\\.)");
        Matcher mat = pat.matcher(text);

        while (mat.find()) {
            sentencesList.add((mat.group()).trim());
        }
    }

    /**
     * Make from sentences a text.
     *
     * @return new text.
     */
    public String textFromSentences() {
        String newText = String.join(" ", sentencesList);
        return newText;
    }

}