# publications
Workshop on JPA

INTRODUCTION
============
This is a sample project created to demonstrate JPA, JPQL usage.
The project contains Author, Publication, Book, BlogPost and Publisher beans.
User is requested to look into their relationships

[Here is the Entity Relation Diagram](src/main/resources/Publications-Entity-Relation.png)

Please change the sample application yml config to application.yml with appropriate configuration.

To participate in the workshop participants are requested take a look into -
AuthorTestsWithCRUDRepository, AuthorTestsWithEntityManager and AuthorTestsWithEntityManagerSelects.
In these test classes several techniques to perform CRUD operations have been demonstrated. 

Now please try implement -

	1. Create a test class to persist, find, merge and remove BlogPosts using entityManager.
	2. Create a CRUDRepository for BlogPost.
	3. Write test cases to save, find, update and delete using CRUDRepository.
	4. Create a namedQuery to find BlogPost by subject.
	5. Create a method to find BlogPost with published, subject, about, wordcount.
	6. Create a method to find the author of a blogpost by keywords.
	
	

