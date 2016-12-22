<details>
    <summary> TODO </summary>
- [ ] Обновить документацию
- [ ] Добавить функционал для работы с тэгами (для пользователя тоже)
- [x] Добавить функционал для добавления пользователя к событию
- [ ] Переделать временной функционал (через расписание, а не таймстампы)
- [ ] Глобальные события ? Добавить дополнительное boolean поле - для удобства поиска. Глобальным считается событие в пределах города, например, или страны
- [ ] Сделать регистрацию по номеру телефона. Добавить поле номера + статус пользователя. Если пользователь банится в случае каких-либо нарушений, то он просто помечается в базе и повторная регистрация невозможна.
</details>

<b>
    <h1 align="center"> API Contracts </h1>
</b>

<b>
    <h2 align="center"> User interactions </h2>
</b>

<details>
    <summary> Get user by ID </summary>
    <p> Request: GET to <i>APPLICATION_URL/users/{id}</i>
    <p> Successful response example:
    <pre>
    {
        "id": "584f063a20379b0b84cd2b1c",
        "forename": "UserFirstName",
        "surname": "UserLastName",
        "email": "user.email@mail.com",
        "token": "phone_token_for_push"
    }
    </pre>
</details>

<details>
    <summary> Get all users </summary>
    <p> Request: GET to <i>APPLICATION_URL/users</i>
    <p> Successful response example:
    <pre>
    [
        {
            "id": "584f061820379b0b84cd2b1b",
            "forename": "UserFirstName1",
            "surname": "UserLastName1",
            "email": "user1.email@mail.com",
            "token": "phone_token_for_push1"
        },
        {
            "id": "584f063a20379b0b84cd2b1c",
            "forename": "UserFirstName2",
            "surname": "UserLastName2",
            "email": "user2.email@mail.com",
            "token": "phone_token_for_push2"
        },
        {
            "id": "584f066a20379b0b84cd2b1d",
            "forename": "UserFirstName3",
            "surname": "UserLastName3",
            "email": "user3.email@mail.com",
            "token": "phone_token_for_push3"
        }
    ]
    </pre>
</details>

<details>
    <summary> Create new user </summary>
    <p> Request : POST to <i>APPLICATION_URL/users</i>:
    <pre>
    {
        "forename":"UserFirstName",
    	"surname":"UserLastName",
    	"email":"user.email@mail.com",
    	"token":"phone_token_for_push"
    }
    </pre>
    <p> Successful response example:
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
    <p> Unsuccessful response example:
    <pre>
    HTTP Status: 409 (Conflict)
    {
        "message": "Rejected. User with given email address has already been registered"
    }
    </pre>
</details>

<details>
    <summary> Update user's credential </summary>
    <p> Request : PATCH to <i>APPLICATION_URL/users/{id}</i>:
    <pre>
    {
        "id":"5850038a444482331830ecfe",
        "forename":"UserFirstName",
        "surname":"UserLastName",
        "email":"new.user.email@mail.com",
        "token":"phone_token_for_push"
    }
    </pre>
    <p> Successful response example:
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
</details>

<details>
    <summary> Delete user </summary>
    <p> Request: DELETE to <i>APPLICATION_URL/users/{id}</i>
    <p> Successful response example:
    <pre>
    HTTP Status: 200
    </pre>
</details>

<b>
    <h2 align="center">Event interactions</h2>
</b>

<details>
    <summary> Get event by ID </summary>
    <p> Request: GET to <i>APPLICATION_URL/events/{id}</i>
    <p> Successful response example:
    <pre>
    {
        "id": "584f063a20379b0b84cd2b1c",
        "forename": "UserFirstName",
        "surname": "UserLastName",
        "email": "user.email@mail.com",
        "token": "phone_token_for_push"
    }
    </pre>
</details>

<details>
    <summary> Get events by CreatorId </summary>
    <p> Request: GET to <i>APPLICATION_URL/events/creator/{creatorId}</i>
    <p> Successful response example:
    <pre>
    [
        {
            "id": "585a7ff44444821734e47313",
            "title": "MarketPlace",
            "creatorId": "584fe19a4444822a640c0266",
            "point": {
              "x": 30.931415,
              "y": 52.407222
            },
            "creationTime": "2016-12-21T16:13:24.986",
            "eventTime": "2016-12-27T09:00",
            "members": [
              "584fe19a4444822a640c0266"
            ],
            "tags": null
        },
        {
            "id": "585a80194444821734e47314",
            "title": "EPAM Smoking Party",
            "creatorId": "584fe19a4444822a640c0266",
            "point": {
              "x": 30.921132,
              "y": 52.404988
            },
            "creationTime": "2016-12-21T16:14:01.834",
            "eventTime": "2016-12-27T09:00",
            "members": [
              "584fe19a4444822a640c0266"
            ],
            "tags": null
        }
    ]
    </pre>
</details>

<details>
    <summary> Create new event </summary>
    <p> Request: POST to <i>APPLICATION_URL/events</i>
    <pre>
    {
        "title":"EPAM Smoking Party",
        "creatorId": "584fe19a4444822a640c0266",
        "point": {
          "x": "30.921132",
          "y": "52.404988"
        },
        "eventTime":"2016-12-27T09:00:00"
    }
    </pre>
    <p> Successful response example:
    <pre>
    {
        "id": "585a80194444821734e47314",
        "title": "EPAM Smoking Party",
        "creatorId": "584fe19a4444822a640c0266",
        "point": {
            "x": 30.921132,
            "y": 52.404988
        },
        "creationTime": "2016-12-21T16:14:01.834",
        "eventTime": "2016-12-27T09:00",
        "members": [
            "584fe19a4444822a640c0266"
        ],
        "tags": null
    }
    </pre>
</details>

<details>
    <summary> Get nearest events </summary>
    <p> Request: GET to <i>APPLICATION_URL/events</i> with parameters
    <p> Example: <i>APPLICATION_URL/events/search?x=30.921019&y=52.405134&distance=1</i>
    <p> where x - Longitude , y - Latitude
    <p> Successful response example:
    <pre>
    [
        {
            "id": "585a80194444821734e47314",
            "title": "EPAM Smoking Party",
            "creatorId": "584fe19a4444822a640c0266",
            "point": {
                "x": 30.921132,
                "y": 52.404988
            },
            "creationTime": "2016-12-21T16:14:01.834",
            "eventTime": "2016-12-27T09:00",
            "members": [
                "584fe19a4444822a640c0266"
            ],
            "tags": null
        },
        {
            "id": "585a7ff44444821734e47313",
            "title": "MarketPlace",
            "creatorId": "584fe19a4444822a640c0266",
            "point": {
                "x": 30.931415,
                "y": 52.407222
            },
            "creationTime": "2016-12-21T16:13:24.986",
            "eventTime": "2016-12-27T09:00",
            "members": [
                "584fe19a4444822a640c0266"
            ],
            "tags": null
        }
    ]
    </pre>
</details>

<details>
    <summary> Add user to event </summary>
    <p> Request: POST to <i>APPLICATION_URL/{eventId}/members/{memberId}</i>
    <p> Successful response example:
    <pre>
    {
        "id": "585a80194444821734e47314",
        "title": "EPAM Smoking Party",
        "creatorId": "584fe19a4444822a640c0266",
        "point": {
            "x": 30.921132,
            "y": 52.404988
        },
        "creationTime": "2016-12-21T16:14:01.834",
        "eventTime": "2016-12-27T09:00",
        "members": [
            "584fe19a4444822a640c0266"
        ],
        "tags": null
    }
    </pre>
</details>