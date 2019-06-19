
# Service Wordladder
## API
You can access the service through `/wordladder/word1/word2`
## Result
The service will perform a bunch of checks before actually finding the ladder. Those checks are to make sure both words
are of the same length and both exist in the default dictionary. It will return a string telling the which check fails.

If both words are valid, then the service will find the ladders and return all of them in a form of double array or
return a string telling no chain were found.