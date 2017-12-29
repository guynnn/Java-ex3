# Java-ex3


=============================
=      File description     =
=============================
README - this file

RESULTS - a text file contains the running-time-results of the different collections.

ClosedHashSet.java - contains the class ClosedHashSet, which represents a hash set, based on array that
                     changes its length depends on its size.

OpenHashSet.java - contains the class OpenHashSet, which represents a hash set that based on array that
                   changes its length depends on its size, and each cell is basically a LinkedList

SimpleHashSet.java - contains the abstract class SimpleHashSet, which has some simple methods to implement
                     a hash set. both open and closed hash set are extend it.

ListWrapper.java - contains the class ListWrapper, that has one field which is a LinkedList. helping
                   OpenHashSet to run.

CollectionFacadeSet.java - wraps the java's collection, so they would have similar API as our close and
                           open hash set implementation.

SimpleSetPerformanceAnalyzer.java - has a main method. Does some running time test to all the collections.


=============================
=          Design           =
=============================
The program is an implementation for two kinds of hash sets: close and open. both open and close extend
SimpleHashSet, that has basic method for a hash set. because they are sets, duplicates of strings are not
allowed. they both has lower and upper bounds for loadFactor (elements / size), that when they pass those
bounds they make their hash table x2 bigger of smaller. open set has an array of ListWrapper as its hash
table, and every ListWrapper has a LinkedList, that why we don't worry about collision. we also have the
facade class, which wrap java's collection and our open and closed hash sets. it makes the testing we did
in the analyzer class very easy because it makes their API similar to our purpose.

=============================
=  Implementation details   =
=============================
we can't use an array of LinkedLists in java, so for the open set I chose to use a helper class (ListWrapper)
which has a linkedList as a field and few method that delegates it because it is very easy to code it and
very readable. closed set, on the other hand, has an array of Strings however collision may
happen. for this problem we try to enter it to another cell until we succeed (using a mathematics formula that
guarantee we would put it eventually in an empty cell). for the deleting issue in closed hash set i chose
to have a constant String called DELETED using as a flag, and put it whenever we delete a string from the
set. that's way, when using the operator == (and not equals) we know that this cell in the table has been
used before, and thus act accordingly.
=============================
=    Answers to questions   =
=============================
Running-Time results:

1. Comparing the initializing time (in ns) of data1:
OpenHashSet      49924
ClosedHashSet    169885
TreeSet          117
LinkedList       53414
**HashSet**      ** 31 **

2. Comparing the initializing time (in ns) of data2:
OpenHashSet      101
ClosedHashSet    92
TreeSet          117
LinkedList       39072
**HashSet**      ** 47 **

3. Comparing the initializing time (in ns) of data1 Vs data2:
                 data1      data2
OpenHashSet      49924      101
ClosedHashSet    169885     92
TreeSet          117        117
LinkedList       53414      39072
HashSet          131        47

4. Comparing the time for contains("hi") (in ms) after data1 initialization:
OpenHashSet      42
ClosedHashSet    35
TreeSet          195
LinkedList       899174
**HashSet**      ** 38 **

5. Comparing the time for contains("-13170890158") (in ms) after data1 initialization:
OpenHashSet      990376
ClosedHashSet    2935088
TreeSet          345
LinkedList       1018731
**HashSet**      ** 87 **

6. Comparing the time for contains("hi") (in ms) Vs contains("-13170890158") after data1 initialization:
                 "hi"       "-13170890158"
OpenHashSet      42         990376
ClosedHashSet    35         2935088
TreeSet          195        345
LinkedList       899174     1018731
HashSet          38         87

7. Comparing the time for contains("23") (in ms) after data2 initialization:
**OpenHashSet**  ** 58 **
ClosedHashSet    47
TreeSet          126
LinkedList       528
**HashSet**      80

8. Comparing the time for contains("hi") (in ms) after data2 initialization:
**OpenHashSet**  ** 49 **
ClosedHashSet    96
TreeSet          200
LinkedList       772808
HashSet          195

9. Comparing the time for contains("23") (in ms) Vs contains("hi") after data2 initialization:
                 "23"       "hi"
OpenHashSet      58         49
ClosedHashSet    47         96
TreeSet          126        200
LinkedList       528        772808
HashSet          80         195

Summarize of the running-Tests:

OpenHashSet: very strong for all purpose of a basic set, unless someone is adding element with the same
             hashCode on purpose. Better use this one if I think someone is to add bad hashCode elements.

ClosedHashSet: very strong for all purpose of a basic set unless someone is adding element with the same
               hashCode on purpose. When this case happen, the closed type is even worse than the open type.
               Would use this only when I'm sure the element's hashCode is evenly distributed.

TreeSet: does not care for the hashCode distribution of the elements. doing a pretty good job overall, but in
         the average case (no one is adding problematic elements on purpose) it is suggested to use the
         hash set. Would use this one I want to use all the functions of a set, including running through
         the data.

LinkedList: its running time in the average case are the worst (no one is adding problematic elements on
            purpose). However it is very easy to code it. better to use this one only for small data.

HashSet: Won in almost every category in the tests. even when given problematic elements it is doing great
         job. Would use this one if I do not need to travel through the data, and only need the methods
         add/delete/contains.


OpenHashSet / ClosedHashSet comparison:
As long as the user is not adding bad elements (same hashCode over and over again) the closed doing slightly a
better job. However when the user is adding bad elements, the open doing much better.
However, the java's hashSet is almost always better no matter what are the hashCode of the elements.
Only case our implementation is better, is when the elements are evenly distributed in the hash table and
we call the contains method.

Surprising results:
I was surprised that our open hash set won a few category in the tests. Thought java's collection would win
every possible test.
I also find it really odd that the built-in hashSet only needed far less time to initialize the set with
data1. I think java's hashSet does not use array as a hash table, and that's why is far better that ours.
