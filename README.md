Цель проекта:

    Разработка приложения для получения данных из базы MySQL в формате JSON.  
    Обобщенные данные за день, месяц или определенный период, для сверки отчетных форм.

Имя базы: 

    db_trday

Имя таблицы: 

    Все таблицы названы номером года отчетного периода (данные за 2022 год находятся в таблице '2022').


Описание SQL таблицы:
    
| №№  | Атрибут   | Тип данных | Описание атрибута                                       |
|-----|-----------|------------|---------------------------------------------------------|
| 1   | PTP_NAME  | TEXT       | Наименование перевозчика                                |
| 2   | PTP_ID    | FLOAT      | код перевозчика                                         |
| 3   | DT        | DATE       | дата обработки поездки                                  |
| 4   | DT1       | DATE       | дата совершения поездки                                 |
| 5   | TERM_ID   | TEXT       | код терминала                                           |
| 6   | CONDUCTOR | TEXT       | фамилия кондуктора                                      |
| 7   | ROUTE_NUM | TEXT       | номер маршрута                                          |
| 8   | TTYPE     | FLOAT      | код типа транспорта                                     |
| 9   | TARIF     | FLOAT      | стоимость проезда                                       |
| 10  | PRTYPE    | TEXT       | код типа платежа<br/> (льготники, банк, студент и т.д.) |
| 11  | SUMM      | FLOAT      | сумма                                                   |
| 12  | CNT       | FLOAT      | количество транзакций                                   |
| 13  | QCNT      | FLOAT      | сомнительные транзакции                                 |
| 14  | LEVEL     | TEXT       | Описание кода из PRTYPE                                 |


Описание структуры JSON файла:

| №№  | Атрибут                                                                         | Элемент  | Тип данных |
|-----|---------------------------------------------------------------------------------|----------|------------|
| 1   | Дата поездки {                                                                  | Date     | Date       |
| 2   | ____ID перевозчика {                                                            | ptpId    | int        |
| 3   | ________Наименование перевозчика                                                | ptpName  | String     |
| 4   | ________Тариф, стоимость проезда {                                              | tarif    | Double     |
| 5   | ____________Номер маршрута {                                                    | routeNum | String     |
| 6   | ________________Тип оплаты, код типа карты {                                    | prType   | Double     |
| 7   | ____________________Сумма, рубль                                                | summ     | Double     |
| 8   | ____________________Количество транзакций (пассажиров)                          | cnt      | int        |
| 9   | ____________________Количество сомнительных, <br/>спорных транзакций <br/>}}}}} | qCnt     | int        |
