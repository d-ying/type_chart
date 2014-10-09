type_chart
==========

This Java program reads data from a .csv file and allows you to quickly see 
the strengths and weaknesses of a specified type. The provided table.csv 
file contains the type chart (and is optimized) for Pokemon though anything 
that deals with types can theoretically be used with this program.

As noted above, this program contains a table.csv file of a table of all the
Pokemon types and their effectivenss against other types. If you wish to
change the input file, please either modify the provided table.csv file or
make sure that you name the new file table.csv as this program will only look
for the file named "table.csv" (This can of course be changed in the source
files). When making a new table with values ensure that the formatting is
correct in that values are seperated by only a comma and no space and the top 
left corner is left blank. Anything else can be freely modifed and this 
program can support an very large number of types before the runtime becomes 
too slow.

This program is a .jar file, but unfortuantely has no UI so to run it please 
use the terminal/command prompt and navigate to the location of the .jar file 
(using cd) and then run it using the command "java -jar StringTruncate.jar". 
On windows please ensure that the Java path is set correctly before running the
program.
