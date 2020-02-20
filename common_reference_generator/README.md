# Common Reference Generator
 
This sub-module contains logic to generate reference of 10 alphanumeric characters [ 0 to 9 and A to Z] using 
SecureRandom.

Use below code snippet to generate reference : 
String reference = ReferenceGenerator.generateReference(10); // 10 is size of the reference 

Expected format of reference will be mixed of numbers between 0 to 9 and A to Z depending on 
the given size.

e.g.  reference = 0E1567C0B2