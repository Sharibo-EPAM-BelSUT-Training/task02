package gmail.alexejkrawez;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Sentence {

    /**
     * List of splits words.
     */
    private ArrayList<ArrayList<String>> wordsList = new ArrayList<ArrayList<String>>();

    /**
     * Get list of splits words.
     *
     * @return list of splits words.
     */
    public ArrayList<ArrayList<String>> getWordsList() {
        return wordsList;
    }

    /**
     * Constructor of the sentence-object which include
     * the list of splits words that matches the sentences in a Text-object's list.
     *
     * @param inputSentences the array list of sentences to be split.
     */
    Sentence(ArrayList<String> inputSentences) {
        int sentenceIndex = 0;
        for (String inputSentence : inputSentences) {
            sentencesSplit(inputSentence, sentenceIndex);
            sentenceIndex++;
        }
    }

    /**
     * Split sentences in words and punctuation marks and write it in array list "wordsList".
     *
     * @param inputSentence the array list of sentences to be split.
     * @param sentenceIndex index of the sentence to be split.
     */
    public void sentencesSplit(String inputSentence, int sentenceIndex) {
        wordsList.add(new ArrayList<String>());
        Pattern pat = Pattern.compile("[A-Za-zА-Яа-я]+|[.?!,;:]+");
        Matcher mat = pat.matcher(inputSentence);

        while (mat.find()) {
            wordsList.get(sentenceIndex).add(mat.group());
        }
        /*Сделать первое слово предложения с малой буквы*/
        wordsList.get(sentenceIndex).set(0, wordsList.get(sentenceIndex).get(0).toLowerCase());
    }

    /**
     * Find out if the sentence contains the specified word. Word searches occur in sentence by index.
     *
     * @param sentenceIndex index of sentence in which search the word.
     * @param word specified word.
     * @return true when sentence have specified word.
     */
    public boolean isWordInSentence(int sentenceIndex, String word) {
        if (wordsList.get(sentenceIndex).contains(word)) {
            return true;
        }

        return false;
    }

    /**
     * Find out if another sentences contain the words from first sentence.
     * Word searches occur in all sentences exclude the sentence, from that is a word.
     *
     * @return ArrayList which include true-answers when one from another sentences have words
     * and false-answers when don't have.
     */
    public ArrayList<String> isWordInAnotherSentences() {
        ArrayList<String> s = new ArrayList<String>();

        for (int i = 0; i < wordsList.get(0).size(); i++) {
            int sentencesCount = 0;

            for (ArrayList<String> internalList : wordsList) {
                if (sentencesCount != 0 & internalList.contains(wordsList.get(0).get(i))) {
                    s.add(i + ": true");
                    break;
                } else if (sentencesCount == wordsList.size() - 1) {
                    s.add(i + ": false");
                }

                ++sentencesCount;
            }
        }

        return s;
    }

    /**
     * Swap 2 words in all exclamatory sentences.
     *
     * @param inputSentences the array list of sentences words of which to be swapped.
     */
    public void swapWordsInExclamatorySentences(ArrayList<String> inputSentences) {
        int sentenceIndex = 0;

        for (ArrayList<String> internalList : wordsList) {

            if (internalList.get(internalList.size() - 1).matches("^!$|!!!")) {
                int wordIndex1 = 0;
                int wordIndex2 = internalList.size() - 2;

                String word1 = wordsList.get(sentenceIndex).get(wordIndex1);
                wordsList.get(sentenceIndex).set(wordIndex1, wordsList.get(sentenceIndex).get(wordIndex2));
                wordsList.get(sentenceIndex).set(wordIndex2, word1);

                sentenceFromWords(inputSentences, sentenceIndex);
            }

            sentenceIndex++;
        }
    }

    /**
     * Delete the longest substring from all declarative sentences.
     *
     * @param inputSentences the array list of sentences to be delete the longest substring.
     * @param letter1 the first letter from which will be delete the substring.
     * @param letter2 the last letter to which will be delete the substring.
     */
    public void deleteTheLongestSubstring(ArrayList<String> inputSentences, char letter1, char letter2) {
        int sentenceIndex = 0;

        for (ArrayList<String> internalList : wordsList) {
            if (internalList.get(internalList.size() - 1).matches("\\.\\.\\.|\\.")) {
                int letterIndex1 = inputSentences.get(sentenceIndex).indexOf(letter1);
                int letterIndex2 = inputSentences.get(sentenceIndex).lastIndexOf(letter2);

                if (letterIndex1 != -1 & letterIndex2 != -1 & letterIndex1 < letterIndex2) { // сложный вариант
                    inputSentences.set(sentenceIndex, inputSentences.get(sentenceIndex). /*перенос кишки на другую строку*/
                            replace(inputSentences.get(sentenceIndex).substring(letterIndex1, letterIndex2 + 1), ""));
                }

//                if (letterIndex1 != -1 & letterIndex2 != -1 & letterIndex1 < letterIndex2) { // более простой вариант
//                    String sentencePart = inputSentences.get(sentenceIndex).substring(0, letterIndex1);
//                    sentencePart += inputSentences.get(sentenceIndex).substring(letterIndex2 + 1);
//                    inputSentences.set(sentenceIndex, sentencePart);
//                }

                /*Работали со списком предложений, дублируем в список слов*/
                Pattern pat = Pattern.compile("[A-Za-zА-Яа-я]+|[.?!,;:]+");
                Matcher mat = pat.matcher(inputSentences.get(sentenceIndex));
                wordsList.set(sentenceIndex, new ArrayList<String>());

                while (mat.find()) {
                    wordsList.get(sentenceIndex).add(mat.group());
                }
                /*Сделать первое слово предложения с малой буквы*/
                wordsList.get(sentenceIndex).set(0, wordsList.get(sentenceIndex).get(0).toLowerCase());
            }

            sentenceIndex++;
        }
    }

    /**
     * Make from words a sentence and write this sentence in list of sentences.
     *
     * @param inputSentences the array list of sentences which sentence to be overwritten.
     * @param sentenceIndex index of sentence which to be overwritten.
     */
    public void sentenceFromWords(ArrayList<String> inputSentences, int sentenceIndex) {
        /*Сделать первое слово предложения с большой буквы*/
        char letter = wordsList.get(sentenceIndex).get(0).toUpperCase().charAt(0); // Пока достаём первую букву, делаем её большой
        String word = wordsList.get(sentenceIndex).get(0).substring(1); // Достаём слово без первой буквы.
        word = letter + word;
        wordsList.get(sentenceIndex).set(0, word);

        inputSentences.set(sentenceIndex, String.join(" ", wordsList.get(sentenceIndex))); // склеиваем слова в предложение как есть

        Pattern pat = Pattern.compile(" (!!!|!|\\?!|\\?|\\.\\.\\.|\\.)"); // убираем пробел у окончания предложения
        Matcher mat = pat.matcher(inputSentences.get(sentenceIndex));
        while (mat.find()) {
            inputSentences.set(sentenceIndex, mat.replaceAll(mat.group().replace(" ", "")));
        }

        pat = Pattern.compile(" (,|;|:)"); // убираем пробел у знаков препинания
        mat = pat.matcher(inputSentences.get(sentenceIndex));
        while (mat.find()) {
            inputSentences.set(sentenceIndex, mat.replaceAll(mat.group().replace(" ", "")));
        }

    }

}
