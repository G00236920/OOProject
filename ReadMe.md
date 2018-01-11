
Author - Michael Kidd\
Student No. - G00236920\
\
Software Development Year 3\
\
\
\
2017 Object Oriented Project\
\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\
\
Objective:\
\
To make a java program that can approximate the Jaccard Distance of two documents.\
Testing how similar these two documents are to each other.\
\
\
Fundamentals of the Program:\
\
The program is intended to detect how similar one document is to another document, It does this by using the Jaccard Index.\
\
The user will be Presented with a menu\
The user inputs 1 to change the file path to Document 1\
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0
\cf0 The user inputs 1 to change the file path to Document 2,\
This way the user can edit the path to the files separately.\
\
The user then inputs \'933\'94 to start the parse process,\
Two threads are started and each one reads a single document, The program reads these documents through a buffered reader. \
\
As it is being read, the program reads each character at a time, it then separates the word based one-aces and special characters, these words are Concatenated to single string, which is then passed to a Shingle object and Stored on a Blocking queue, While the details of the Shingle are being added, each shingle is changed to its dashcode value. Then a third thread is created with a consumer. The consumer creates worker threads using a pool executor and the threads begin to each taking a shingle from the blocking queue, it then XOR each shingle cycling through 200 random Minhash values, so that we can find the lowest 200 minhash values in both documents and compare the two lists to see the commonality, using the jaccard equation.\
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0
\cf0 \
\
How to Download:\
\
\
First install GIT on the machine you are using.\
CD to the Directory you wish to store the files in.\
To download the files simply open up command prompt or terminal and type the following:\
\
\'93Git clone https://github.com/G00236920/OOProject\'94\
\
\
\
How to run the program:\
\
\
Either: \
Compile the program using Javac in Command prompt / Terminal.\
Open the project in Eclipse, netbeans or another IDE.\
\
\
\
Result:\
\
\
My Assignment didn\'92t go to well, At first it worked to a Degree and after making changes, Realising I had the wrong approach to the assignment, It no longer worked and I ran out of time trying to find the issue and still have not found the root of the problem.\
\
\
\
Possible reasons for the Program not running as expected:\
\
\
My own limited understanding of how the threads are working in this program or how the pool threads operate.  There are no extra functions in the program. I am Disappointed  that I couldn\'92t finish the project completely, I know the issue will either be something simple I have overlooked or a condition that I just have no knowledge of, Short of rewriting the entire program there is not much I can do for now.}