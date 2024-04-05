Assignment:
Create a CRUD application for Customer.
Database: Mysql
Backend: JSp Servlet/ Spring Boot
Frontend : HTML/ CSS/ JS (or anything you are familiar with)
Backend should have API for:
- Create a customer
- Update a customer
- Get a list of customer (API with pagination sorting and searching )
- Get a single customer based on ID
- Delete a customer
Authentication: For authentication implement JWT authentication
This is what an example customer object looks like:
{
"first_name": "Jane",
"last_name": "Doe",
"street": "Elvnu Street",
"address": "H no 2 ",
"city": "Delhi",
"state": "Delhi",
"email": "sam@gmail.com",
"phone": "12345678"

}





2
nd phase:
In Second phase, Add a button named sync on the customer list screen. you need to call a
remote API to fetch the customer list and save those customers in your database. If the
customer already exists in your database then instead of inserting, update it in your
database.
1. Authenticate user using the credentials specified. API uses Bearer authentication to
authenticate further API calls. Authentication API will return a bearer token which you will
have to pass in subsequent API calls.
Path: https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp
Method: POST
Body:
{
"login_id" : "test@sunbasedata.com",
"password" :"Test@123"
}
This will return you a token, that you will have to pass in as Bearer token for further calls.
Get customer list:
Path: https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp
Method: GET
Parameters:
cmd : get_customer_listHeader:
Authorization: Bearer token_recieved_in_authentication_API_call
Response: 200
[{
“uuid” :”tytyytytyyyy345ryeyey”,
"first_name": "Jane",
"last_name": "Doe",
"street": "Elvnu Street",
"address": "H no 2 ",
"city": "Delhi",
"state": "Delhi",
"email": "sam@gmail.com",
"phone": "12345678"
}]
