package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();

        String[] wordSplit = keyPhrase.split("\\s+");
        List<List<Integer>> keyLocation = index.get(keyPhrase);

        if (wordSplit.length == 1) {
            if (index.containsKey(wordSplit[0]) == true) {
                for (int i = 0; i < keyLocation.size(); i++) {
                    if (keyLocation.get(i).size() == 0) {
                        continue;
                    }
                    result.add(i);
                }
            }
        }

        else {
            if (index.containsKey(wordSplit[0]) == true) {
                for (int i = 0; i < index.get(wordSplit[0]).size(); i++) {
                    List<List<Integer>> location = new ArrayList<>(wordSplit.length);

                    for (String input : wordSplit) {
                        location.add(index.get(input).get(i));
                    }

                    if (checksIfInOrder(location) == true) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

    private boolean checksIfInOrder(List<List<Integer>> position) {
        for (int i = 0; i < position.get(0).size(); i++) {
            boolean helperResult = helper(position, 1, position.get(0).get(i));
            if(helperResult) {
                return true;
            }
        }
        return false;
    }

    private boolean helper(List<List<Integer>> position, int index, int prevPosition) {
        if (index < position.size()) {
            for (Integer currPosition : position.get(index)) {
                if (currPosition == prevPosition + 1) {
                    return helper(position, index + 1, prevPosition + 1);
                }
                continue;
            }
        }
        else {
            return true;
        }
        return false;
    }
}
        //comments from lecture
        // Step 0: split keyPhrases into words by spaces

        // Step 1: get the documents that contain all the words in the given phrases
        // [0,1,3,4,5], [0,2,3,4,5]
        // get the common number (document id) of both lists --> [0,3,4,5]

        // step 2: for each common document, get the location idx of each word in the search phrase.
        // "is here already"
        // document 2: {1}, {2}, {3}

        // determine whether search words are in the correct order right next to each other
        // if yes doc 2 is one of the answers

        // do the location math and return the documents that have common index after the calculation

        //[3,4,1,2] [1,3,2] [2,7,8,3,5] [8,3,10,2]

        // how to get the intersection of all the arrays?

        // [2,3] ??

        // for each of the numbers in [2,7,8,3,5],
        // see if it exists in the other array

        //[2,3]

        // hashmap
        // key: numbers
        // value: counts
        // 3 -> 4
        // 4 -> 1
        // 1 -> 2
        // 2 -> 4


