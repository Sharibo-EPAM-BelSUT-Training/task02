package gmail.alexejkrawez;

import java.util.ArrayList;

public class Word {

    /**
     * Show size of word which was specified.
     *
     * @param inputWordsList list of words in which was a search.
     * @param sentenceIndex index of sentence who is specified word.
     * @param wordIndex index of specified word.
     * @return size of specified word.
     */
    public static int getWordSize(ArrayList<ArrayList<String>> inputWordsList, int sentenceIndex, int wordIndex) {
        return inputWordsList.get(sentenceIndex).get(wordIndex).length();
    }

    /**
     * Remove first letter in specified word.
     *
     * @param inputSentences list of sentences to be modified according to the modified word.
     * @param inputSentenceObject sentence-object who is specified word.
     * @param sentenceIndex  index of sentence who is specified word.
     * @param wordIndex index of specified word.
     */
    public static void removeFirstLetter(ArrayList<String> inputSentences,
                                         Sentence inputSentenceObject, int sentenceIndex, int wordIndex) {
        String shortenedWord = inputSentenceObject.getWordsList().get(sentenceIndex).get(wordIndex).substring(1);
        inputSentenceObject.getWordsList().get(sentenceIndex).set(wordIndex, shortenedWord);

        inputSentenceObject.sentenceFromWords(inputSentences, sentenceIndex);
    }

    /**
     * Remove all matches with the first letter of word in all words interrogative sentence's.
     *
     * @param inputSentences list of sentences, in which interrogative sentences
     *                       will remove matches with first letter of words.
     * @param inputSentenceObject sentence-object who is list of sentences.
     */
    public static void removeMatchesWithFirstLetter(ArrayList<String> inputSentences,
                                                    Sentence inputSentenceObject) {
        int wordIndex = 0;
        int sentenceIndex = 0;

        for (ArrayList<String> internalList : inputSentenceObject.getWordsList()) {

            if (internalList.get(internalList.size() - 1).matches("\\?!|\\?")) {
                while (wordIndex < inputSentenceObject.getWordsList().get(sentenceIndex).size()) {
                    String firstLetter = inputSentenceObject.getWordsList().
                            get(sentenceIndex).get(wordIndex).substring(0, 1);
                    String word = inputSentenceObject.getWordsList().
                            get(sentenceIndex).get(wordIndex).replace(firstLetter , "");
                    word = firstLetter + word;

                    inputSentenceObject.getWordsList().get(sentenceIndex).set(wordIndex, word);
                    wordIndex++;
                }

                inputSentenceObject.sentenceFromWords(inputSentences, sentenceIndex);
            }

            sentenceIndex++;
        }
    }

}