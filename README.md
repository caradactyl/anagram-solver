# Anagrams

## Problem Description
The goal is to find all anagrams in the dictionary. Write a program that reads in a "dictionary" of words made up of lower-case letters, one word per line, and computes all anagram classes. For example, the word *pots* is an anagram of the word *stop* because the letters in one are a permutation of the letters in the other. The words *tops*, *opts*, *post*, and *spot* are in the same anagram class. The output file should contain an anagram class on each line. Note that each anagram class contains 1 or more words in it. Two files containing two dictionaries are provided, **dict1** and **dict2**. The corresponding output files should be named **anagram1** and **anagram2** respectively.

**Note:** Please note that the purpose of this assignment is to implement whatever algorithms and data structures are necessary from the basics. Therefore, do not use library functions for things such as sorting, hash tables, etc. *They must be implemented from scratch.*

## Algorithm Description
Implemented a hash table, resulting in *O(n)* runtime where *n* is the number of words in the dictionary.

1. Read in the word.
2. Sort the word in alphabetical to produce the key.
3. Hash the key into the table to find an empty slot to store it in.
4. If collision occurs verify if current word is an anagram of the word in that array location.
5. If step 4 fails move up +1 to a new index until an anagram or a empty slot is found

## Solution
**dict1** : 67,605 anagram classes in 80 ms

**dict2** : 320,750 anagram classes in 7958 ms

(CPU: i7-2700k 4.40GHz; Memory: 16GB 2133MHz)

## Goals for the Future
* Generalize the Dictionary class as much as possible
* Support removal from the Dictionary
* Modify Dictionary to implement Java's Map interface instead of my own
* Devise a better hashing function and track collision progress
* Switch from quicksort to counting sort
* More detailed analysis of runtime
