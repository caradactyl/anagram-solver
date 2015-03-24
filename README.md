     _|     _|     _|     _|       _|_|_|  _|_|_|       _|     _|    _|
   _|  _|   _|_|   _|   _|  _|   _|        _|    _|   _|  _|   _|_|_|_|
  _|    _|  _|  _| _|  _|    _|  _|  _|_|  _|_|_|    _|    _|  _| _| _|
  _|_|_|_|  _|   _|_|  _|_|_|_|  _|    _|  _|   _|   _|_|_|_|  _|    _|
  _|    _|  _|     _|  _|    _|    _|_|_|  _|    _|  _|    _|  _|    _|
**************************************************************************

## Problem Description
The goal is to find all anagrams in the dictionary. Write a program that reads in a "dictionary" of words made up of lower-case letters, one word per line, and computes all anagram classes. For example, the word *pots* is an anagram of the word *stop* because the letters in one are a permutation of the letters in the other. The words *tops*, *opts*, *post*, and *spot* are in the same anagram class. The output file should contain an anagram class on each line. Note that each anagram class contains 1 or more words in it. Two files containing two dictionaries are provided, **dict1** and **dict2**. The corresponding output files should be named **anagram1** and **anagram2** respectively.

### Note
Please note that the purpose of this assignment is to implement whatever algorithms and data structures are necessary from the basics. Therefore, do not use library functions for things such as sorting, hash tables, etc. *You must implement them from scratch.*

## My Solution
My solution runs in *O(n)* time where *n* is the number of words in the dictionary.

**dict1** : 67,605 anagram classes in 80 ms
**dict2** : 320,750 anagram classes in 7958 ms

System Information
CPU:	i7-2700k 4.40GHz
Memory:	16GB 2133MHz