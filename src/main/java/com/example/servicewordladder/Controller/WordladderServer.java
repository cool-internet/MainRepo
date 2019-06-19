package com.example.servicewordladder.Controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
public class WordladderServer {
    ArrayList<String> dict = new ArrayList<String>();

    @GetMapping("/wordladder")
    @RequestMapping(path="/wordladder/{word1}/{word2}", method = RequestMethod.GET)
    public String index(@PathVariable String word1, @PathVariable String word2){
        System.out.println(dict.size());
        String result = "";
        if (word1.length() != word2.length()) {
            return "The two word must be of the same length.";
        } else if (dict.indexOf(word1) == -1){
            return "The first word is not in the dictionary.";
        } else if (dict.indexOf(word2) == -1){
            return "The second word is not in the dictionary.";
        }
        List<List<String>> ladder = findLadder(word1, word2);

        result = ladder.toString();

        if (result == ""){
            return "No chain found";
        } else {
            return result;
        }
    }

    private WordladderServer() {
        // Read dictionary file
        BufferedReader reader;

        //get the absolute path to the current project
        String filePath = new File("").getAbsolutePath();
        System.out.print(filePath);

        try {
            reader = new BufferedReader(new FileReader(filePath + "/src/main/java/com/example/servicewordladder/Dictionary/dictionary.txt"));
            String line = reader.readLine();
            while (line != null) {
                dict.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<List<String>> findLadder(String startWord, String endWord) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashSet<String> unvisited = new HashSet<>();
        unvisited.addAll(dict);
        LinkedList<Node> queue = new LinkedList<>();
        Node node = new Node(startWord, 0, null);
        queue.offer(node);

        int minLen = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node top = queue.poll();

            //top if have shorter result already
            if (result.size() > 0 && top.depth > minLen) {
                return result;
            }

            for (int i = 0; i < top.word.length(); i++) {
                char c = top.word.charAt(i);
                char[] arr = top.word.toCharArray();
                for (char j = 'z'; j >= 'a'; j--) {
                    if (j == c) {
                        continue;
                    }
                    arr[i] = j;
                    String t = new String(arr);

                    if (t.equals(endWord)) {
                        //add to result
                        List<String> aResult = new ArrayList<>();
                        aResult.add(endWord);
                        Node p = top;
                        while (p != null) {
                            aResult.add(p.word);
                            p = p.prev;
                        }

                        Collections.reverse(aResult);
                        result.add(aResult);

                        //stop if get shorter result
                        if (top.depth <= minLen) {
                            minLen = top.depth;
                        } else {
                            return result;
                        }
                    }

                    if (unvisited.contains(t)) {
                        Node n = new Node(t, top.depth + 1, top);
                        queue.offer(n);
                        unvisited.remove(t);
                    }
                }
            }
        }

        return result;
    }
}
