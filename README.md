# drug_app
Intern second year

1.Download drugbank.xml from the website https://www.drugbank.ca/

2.Run drug.java on the xml file to generate modified drugbank.xml
(which contains only the useful information)

3.<entity> tag has to be added at the start of modified file because drugbank used some not UTF-8 encoded characters 
hence if u used the output of drug.java then ur tables will have some unrecognised characters
hence some of them are added(searched manually for the unrecognised characters ....u get ? in tables if u populate )
one way is u can search for ? in table and replace that entity with its code.

copy_mod_drug_bank.xml ----> is the same modified file with entity tag added

4.table_name.java helps you populate drug_info table

5.drug_int.java helps populate drug_intr table


6.The file otc.java -->parses the modified drugbank.xml and makes an xml file of only otc drugs 
//otc drugs should only be prescribed since we are not medical students
the output of otc.java would be otc_drugs.xml

then we did manually read the xml file and made otc_drugs.csv file 
//lot of manual work..actually we required a one line symptom from the indication tag
just open xml and csv file .you ll probably understand what i m saying 

then imported this csv file into mysql table otc 

7. all the table details are in tables folder

8.At this step you will have all tables ready


/*******Working in android studio ********************/
1.Started with main activity, created activities which will call servlets for data from databases
Basic knowledge of android is sufficient for understanding code.

Before running it change the variable ip in strings.xml of values folder to the local ip of your machine
and make sure tomcat server is on.

/********Servlets for connection**************/
1.Four java servlets were used .I used get method in servlets but it is 
not as secure as post method (Security is not our concern then)
a. first_aid_1 is a servlet which populates all the symptoms for which
otc drugs can be prescribed
b. first_aid is a servlet which will take symotoms from user 
and suggest drugs on max intersections .
c. drug_intr is a servlet which takes two drugs and tells if they 
interact
d. drug_info is a servlet which will give all info about 
one particular drug 
