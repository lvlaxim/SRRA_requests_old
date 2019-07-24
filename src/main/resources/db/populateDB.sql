DELETE
FROM requests.requests;
DELETE
FROM requests.departments;
DELETE
FROM requests.executors;
DELETE
FROM requests.payments;
DELETE
FROM requests.prices;
DELETE
FROM requests.rubrics;
DELETE
FROM requests.sources;
DELETE
FROM requests.themes;


ALTER SEQUENCE requests.departments_department_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.executors_executor_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.payments_payment_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.prices_price_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.rubrics_rubric_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.sources_source_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.themes_theme_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.requests_request_id_seq RESTART WITH 1;


INSERT INTO requests.executors(executor, is_active, job, phone_number, email)
VALUES ('ИсполнительБ', true, 'ДолжностьБ', '222-22-22', 'executorB@gosarhro.ru'),
       ('ИсполнительА', true, 'ДолжностьА', '111-11-11', 'executorA@gosarhro.ru'),
       ('ИсполнительВ', false, 'ДолжностьВ', '333-33-33', 'executorV@gosarhro.ru');

INSERT INTO requests.departments(department_head, department)
VALUES ('НачальникА', 'ОтделА'),
       ('НачальникБ', 'ОтделБ');

INSERT INTO requests.payments(payment)
VALUES ('Платный'),
       ('Частично платный'),
       ('Бесплатный');

INSERT INTO requests.prices(work_type, unit, price)
VALUES ('Копать', 'Штык', 1000),
       ('Кидать', 'Ящик', 999.99);

INSERT INTO requests.rubrics(rubric_code, rubric)
VALUES ('Код1', 'Рубрика1'),
       ('Код2', 'Рубрика2');

INSERT INTO requests.sources(sources)
VALUES ('Источник1'),
       ('Источник2');

INSERT INTO requests.themes(theme)
VALUES ('Тема1'),
       ('Тема2');

INSERT INTO requests.requests(rubric_id, theme_id, subject, short_request, short_answer, source_id, is_urgent, is_gpw,
                              is_entity, is_consular, receiver, receipt_date, department_id, working_by, start_date,
                              executor_id, end_date, smav, reg_number, in_number, in_num_from_org, in_date, copy_number,
                              payment_id)
VALUES (1, 1, 'Вопрос1', 'Коротко1', 'Ответ1', 1, false, false, false, false, 1, '2019-07-01', 1, 1, '2019-07-01', 1,
        '2019-07-02', 1, 1, 1, 1, '2019-07-01', 1, 1),
       (2, 2, 'Вопрос2', 'Коротко2', 'Ответ2', 2, false, false, false, false, 2, '2019-07-02', 2, 2, '2019-07-02', 2,
        '2019-07-03', 2, 2, 2, 2, '2019-07-02', 2, 2),
       (1, 2, 'Вопрос3', 'Коротко3', 'Ответ3', 1, false, false, false, false, 3, '2019-07-03', 2, 3, '2019-07-03', 3,
        '2019-07-04', 3, 3, 3, 3, '2019-07-03', 3, 3),
       (2, 1, 'Срочный4', 'Срочный4', 'Срочный4', 2, true, false, false, false, 1, '2019-07-03', 1, 2, '2019-07-03', 3,
        '2019-07-05', 4, 4, 4, 4, '2019-07-03', 4, 3),
       (1, 1, 'Вопрос1', 'Коротко1', 'Ответ1', 1, false, false, false, false, 1, '2019-07-01', 1, 1, '2019-07-01', 1,
        '2019-07-02', 1, 1, 1, 1, '2019-07-01', 1, 1),
       (2, 2, 'Вопрос2', 'Коротко2', 'Ответ2', 2, false, false, false, false, 2, '2019-07-02', 2, 2, '2019-07-02', 2,
        '2019-07-03', 2, 2, 2, 2, '2019-07-02', 2, 2),
       (1, 2, 'Вопрос3', 'Коротко3', 'Ответ3', 1, false, false, false, false, 3, '2019-07-03', 2, 3, '2019-07-03', 3,
        '2019-07-04', 3, 3, 3, 3, '2019-07-03', 3, 3),
       (2, 1, 'Срочный4', 'Срочный4', 'Срочный4', 2, true, false, false, false, 1, '2019-07-03', 1, 2, '2019-07-03', 3,
        '2019-07-05', 4, 4, 4, 4, '2019-07-03', 4, 3);

