## Проект “Телеграм-бот NASA”

***Задачи:***
- Необходимо скачать картинку с сайта NASA
- Telegram-бот должен опубликовать фотографию по запросу

***Используемые технологии:***
- Java JDK 11, Git, Github, IntelliJ IDEA, Maven, Yandex Cloud, библиотеки Jackson, Apache HttpComponents и Telegram Bots

***Результаты:***
- С помощью ключа API NASA сделал запрос на сайт api.nasa.gov
- Для парсинга json-ответа я использовал библиотеку Jackson
- Из поля url получил ссылку на скачивание фотографии, которую сохранил на диск, имя файла также взял из этого поля
- Зарегистрировал нового бота в Telegram, написал код бота и интегрировал его в приложение
- На базе Yandex Cloud создал виртуальную машину для запуска Telegram-бота, чтобы приложение стало автономным
