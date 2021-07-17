package gmail.alexejkrawez;

/**
 * @name Java.SE.02
 * @package gmail.alexejkrawez;
 * @file Main.java
 * @author Alexej Krawez
 * @email AlexejKrawez@gmail.com
 * @created 28.12.2018
 * @version 1.0
 * */

import static gmail.alexejkrawez.Word.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Задача 1:");
        System.out.println("В каждом вопросительном предложении у каждого слова повторение его первой буквы," +
                " если она дальше встречается в слове, удаляется.");
        Text text1 = new Text();
        Sentence sentences1 = new Sentence(text1.getSentencesList());

        System.out.println(text1.getText());
        System.out.println(text1.getSentencesList());
        System.out.println(sentences1.getWordsList());
        System.out.println();

        removeMatchesWithFirstLetter(text1.getSentencesList(), sentences1); //текст → текс

        System.out.println(text1.getSentencesList());
        System.out.println(sentences1.getWordsList());
        System.out.println(text1.textFromSentences());
        System.out.println();



        System.out.println("Задача 2:");
        System.out.println("Найти все слова в первом предложении," +
                " которых нет ни в одном из остальных предложений.");
        Text text2 = new Text();
        Sentence sentences2 = new Sentence(text2.getSentencesList());

        System.out.println(text2.getText());
        System.out.println(text2.getSentencesList());
        System.out.println(sentences2.getWordsList());
        System.out.println();

        System.out.println(sentences2.isWordInAnotherSentences()); // Истина, если в других предложениях присутствуют.

        System.out.println(text2.getSentencesList());
        System.out.println(sentences2.getWordsList());
        System.out.println(text2.textFromSentences());
        System.out.println();



        System.out.println("Задача 3:");
        System.out.println("В каждом восклицательном предложении поменять первое слово с последним.");
        Text text3 = new Text();
        Sentence sentences3 = new Sentence(text3.getSentencesList());

        System.out.println(text3.getText());
        System.out.println(text3.getSentencesList());
        System.out.println(sentences3.getWordsList());
        System.out.println();

        sentences3.swapWordsInExclamatorySentences(text3.getSentencesList());

        System.out.println(text3.getSentencesList());
        System.out.println(sentences3.getWordsList());
        System.out.println(text3.textFromSentences());
        System.out.println();



        System.out.println("Задача 4:");
        System.out.println("В каждом повествовательном предложении текста исключить подстроку" +
                " максимальной длины, начинающуюся и заканчивающуюся заданными символами.");
        Text text4 = new Text();
        Sentence sentences4 = new Sentence(text4.getSentencesList());

        System.out.println(text4.getText());
        System.out.println(text4.getSentencesList());
        System.out.println(sentences4.getWordsList());
        System.out.println();

        sentences4.deleteTheLongestSubstring(text4.getSentencesList(), 'а', 'н'); //→ Дую задачу.

        System.out.println(text4.getSentencesList());
        System.out.println(sentences4.getWordsList());
        System.out.println(text4.textFromSentences());
        System.out.println();

        System.out.println("============================================================================");


        System.out.println("Неиспользованные методы:");

        Text text5 = new Text();
        Sentence sentences5 = new Sentence(text5.getSentencesList());

        System.out.println("1:");
        System.out.println(getWordSize(sentences5.getWordsList(), 1, 3));
        System.out.println();

        System.out.println("2:");
        removeFirstLetter(text5.getSentencesList(), sentences5, 3, 1); //попробуем → опробуем
        System.out.println(text5.getSentencesList());
        System.out.println(sentences5.getWordsList());
        System.out.println(text5.textFromSentences());
        System.out.println();

        System.out.println("3:");
        System.out.println(sentences5.isWordInSentence(0, "который"));
        System.out.println();

    }
}