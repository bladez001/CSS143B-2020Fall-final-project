package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();

        for (int docsCounter = 0; docsCounter < docs.size(); docsCounter++) {
            String splitDoc = docs.get(docsCounter);
            String[] words = splitDoc.split("\\s+");
            int wordsCounter = 0;

            for (String word : words) {
                if (word.equals("")) {
                    continue;
                }
                if (!indexes.containsKey(word)) {
                    List<List<Integer>> docsList = new ArrayList<>();
                    for (int j = 0; j < docs.size(); j++) {
                        docsList.add(new ArrayList<>());
                    }
                    indexes.put(word, docsList);
                }
                List<List<Integer>> docsList = indexes.get(word);
                List<Integer> indexList = docsList.get(docsCounter);
                indexList.add(wordsCounter);
                wordsCounter++;
            }
        }
        return indexes;
    }
}