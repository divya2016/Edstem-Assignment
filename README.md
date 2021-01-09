# Edstem-Assignment
How to deploy the Contact Management System (CMS)- 

What is CMS?

CMS provides the following feature - 

1. It can be used to create a new contact.
2. It can be used to update an existing contact.
3. It can be used to delete the contact.
4. It can be used to fetch all the available contacts.

Getting Started :

Steps : 

1. Clone the CMS repo to the host where we want to deploy the code-

  git clone https://github.com/divya2016/Edstem-Assignment.git

2. Edit the application.properties file to match your desired host and domain.
3. Create a MongoDB database service instance and bind it.
4. Update application. properties with MongoDB credentials.
5. Run Maven Build Command i.e. - mvn clean build.
6. This will create the WAR File, deploy the war file on server.

And you should have a working CMS.
