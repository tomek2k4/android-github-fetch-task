# android-github-fetch-task


Github fetch task: 
- pobierz listę userów z API i pokaż w aplikacji jako listę 
- kliknięcie w usera na liście powinno otworzyć web browser ze stroną z profilem usera 
- aplikacja nie powinna przycinać gdy userzy są pobierani (async data fetching) 
- baza danych, żeby nie pobierać tych samych danych wiele razy 
- pobieranie danych w serwisie - content provider + sync adapter? 
- graceful quota handling 
- github api ogranicza ilość requestów na godzinę 
- związane z poprzednim: wznawianie downloadu userów po odnowieniu quoty (alarm manager + wakefulbroadcastreceiver).
