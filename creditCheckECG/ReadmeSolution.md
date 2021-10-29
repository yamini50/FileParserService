

Assumptions -

1. there will be legacy system or a third party which will provide us client data in excel,csv,prn format
   I have assumed these files will only have one sheet/tab per file. 
2. field names will be same but format can be different
3. source can upload the files or send the uploaded link via some message queue for our system to consume and download.
    for simplicity, I have used a driver class which will load these files from a folder and start parsing
4. For DB, I have used inbuilt DB- H2 database
5. I have used spring boot framework to implement
6. IMP - I have assumed the phone number and date of birth  will never be empty and there combination will always be unique for a person


Solution-

1. on startup, driver class will start parsing files present in location we have given and according the extension of file, parsing strategy will be applied
2. once parsed, data will be collected and passed to db to get persisted
3. equals and hash code is implemented to avoid duplicate entries to be added
4. 2 apis are created to fetch all clients details or to fetch a client detail by phone and dob.
5. test cases and custom exceptions are applied


Steps to run- 

1. Files with data will be read from a driver class to parse and save data in h2 db
3. There are 2 rest apis exposed to get client details- get all clients and get clients by unique keys
    Both can be tested using unit tests. For sample data , there is a sql file added with some sample data which will be inserted once tests starts to run
   


Multiple ways to handle upload of data -
    1. using a driver class method for simplicity which will be called on startup of application  --- Implemented
    2. can add a scheduler or watcher as well which will upload the changes in the repository on scheduled time
    3. if this is being uploaded on s3, lambda function can be used
    4. if its uploaded by some third party, may be they can pass this as async message or upload it and pass the location as message in queue
     our service will consume and start processing
