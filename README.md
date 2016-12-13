<center><h1>API Contracts</h1></center>

<center><h2>User interactions</h2></center>
<ul>
    <li> Create new user (POST to <i>APPLICATION_URL/users</i>)
    <p> Request example: </p>
    <pre>
    {
    	"forename":"UserFirstName",
    	"surname":"UserLastName",
    	"email":"user.email@mail.com",
    	"token":"phone_token_for_push"
    }
    </pre>
    <p> Success response example: </p>
    <pre>
    HTTP Status: 201
    {
      "id": "5850038a444482331830ecfe",
      "forename": "UserFirstName",
      "surname": "UserLastName",
      "email": "user.email@mail.com",
      "token": "phone_token_for_push"
    }
    </pre>
    <p> Unsuccess response example: </p>
    <pre>
    HTTP Status: 409 (Conflict)
    {
        "message": "Rejected. User with given email address has already been registered"
    }
    </pre>
    <li> Update user's credential (PATCH to <i>APPLICATION_URL/users/id/{id}</i>)
    <p> <b>WARNING:</b> if one or more field stay the same, need to send it too
    <p> Request example: </p>
    <pre>
    {
    	"id":"5850038a444482331830ecfe",
    	"forename":"UserFirstName",
    	"surname":"UserLastName",
    	"email":"new.user.email@mail.com",
    	"token":"phone_token_for_push"
    }
    </pre>
    <p> Successful request example: </p>
        <pre>
        HTTP Status: 200
        {
        	"id":"5850038a444482331830ecfe",
        	"forename":"UserFirstName",
        	"surname":"UserLastName",
        	"email":"new.user.email@mail.com",
        	"token":"phone_token_for_push"
        }
        </pre>
    <li> Delete user
    <li> Get all users
    <li> Get user by ID
    <li> Get user by e-mail address
</ul>

