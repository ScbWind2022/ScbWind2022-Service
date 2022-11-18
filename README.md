# ScbWind2022-Service
1////////////////////
http://localhost:8083/api/v1/user/login
Request:
{
   "email":"email",
   "password":"password"
}
Response:
{
    "accessToken": "accessToken",
    "refreshToken": null
}
2//////////////////////////////////
http://localhost:8083/api/v1/user/register
Request:
{
   "email":"email24",
   "password":"password",
   "firstName":"firstName"
   тут скорее всего еще будут поля
}
Response:
User register или какой то эксепшен по дефолту 500 потом может другой будет
3////////////////////////////////////
http://localhost:8083/api/v1/admin/get/not-accepted-user
Request: нету но метод  задуман только для админа
Response:
[
    {
        "id": "3",
        "email": "email2",
        "password": null,
        "firstName": null,
        "accepted": false,
        "banned": false,
        "roles": null
    },
    {
        "id": "4",
        "email": "email24",
        "password": null,
        "firstName": null,
        "accepted": false,
        "banned": false,
        "roles": null
    }
]
4////////////////////////////////////////////////
