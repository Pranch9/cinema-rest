
# Cinema-rest

Тестовое задание по реализации REST API для кинотеатров.
```
Необходимо написать REST сервис для бронирования мест в кинотеатрах. Сервис должен иметь следующую функциональность:
    1.  Получение списка мест в зале со статусами свободно/забронировано.
    2.  Бронирование одного или нескольких свободных мест в зале кинотеатра.

Примечания:
    1.  Добавить недостающие по Вашему мнению методы в API (например: список кинотеатров, список залов в кинотеатре и прочее).
    2.  Для реализации API можно использовать любой фреймворк.
    3.  Для хранения данных использовать PostgreSQL или embedded db c SQL-синтаксисом.
    4.  При работе с базой использовать JDBC (не использовать ORM).
    5.  Продемонстрировать работу API с помощью тестов.
    6.  Обеспечить максимально простое развёртывание приложения (например docker-контейнер).
    7.  Исходный код выложить на github или bitbucket.
```

## Используемый стек технологий

- Java 17
- Spring-boot
- Jdbi
- PostgreSQL
- Flyway
- Gradle
- Docker
- Junit 5

## Запуск приложения

1. Для начала необходимо склонировать проект из репозитория:
    ```shell
    git clone https://github.com/Pranch9/cinema-rest.git
    ```
    Либо скачать .zip архив с проектом и разархивировать его.

2. Запустить приложения в Docker можно командой:

   ```shell
   docker-compose up
   ```
3. После запуска приложения доступно по адресу:
   - http://localhost:8081

    Swagger:
   -  http://localhost:8081/swagger-ui.html

## Api 

Реализованы следующие контроллеры:

- Контроллер по взаимодествию с кинотеатром, создание, редактирование, удаление, получение информации: по кинотеатру, кинозалу, местам.
- Контроллер по взаимодействию с сеансами.
- Контроллер по взаимодействию с фильмами.
- Контроллер по взаимодействию с пользователями.
- Контроллер по взаимодействию с билетами.

1. Получить список мест в зале со статусами свободно/забронировано:
```http request
GET http://localhost:8080/api/v1/tickets/bookings/{sessionId}
```
или
```http request
GET http://localhost:8080/api/v1/cinemas/seats/{cinemaHallId}
```

2. Бронирование одного или нескольких свободных мест в зале кинотеатра.
```http request
POST http://localhost:8080/api/v1/tickets/control
Content-Type: application/json'

RequestBody:
{
  "sessionId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "status": "UNCONFIRMED",
  "price": 0,
  "seats": [
    {
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "rowNumber": 0,
      "place": 0,
      "booked": true,
      "cinemaHallId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    }
  ],
  "user": {
    "username": "string",
    "mail": "string",
    "password": "string"
  }
}
```

## Примечание

Вместо ORM использовался JDBI и нативные sql запросы для взаимодействия с базой данный.

Если смотреть в сторону дороботки приложения, то я бы ответил следующие пункты 
- Полное покрытие тестами, а не только часть с контроллеми
- Добавить тесты по взаимодействию АПИ между собой в связки
- Добавить авторизацию
- Обработка ошибок
- Рефакторинг сервисов и работа с базой данных
- Для тестов использовал PostgreSQL,но хотелось бы подключить in-memory DB H2. Быстро этого сделать не вышло, возникли проблемы с маппингом, которая ввозникает только на H2. Быстро разобраться не вышло



  