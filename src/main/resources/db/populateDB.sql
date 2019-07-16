DELETE
FROM requests.executors;

ALTER SEQUENCE requests.executors_executor_id_seq RESTART WITH 1;
-- SELECT setval('requests.executors_executor_id_seq', 1, FALSE);

INSERT INTO requests.executors(executor, is_active, job, phone_number, email)
VALUES ('ИсполнительБ', true, 'ДолжностьБ', '222-22-22', 'executorB@gosarhro.ru'),
       ('ИсполнительА', true, 'ДолжностьА', '111-11-11', 'executorA@gosarhro.ru'),
       ('ИсполнительВ', false, 'ДолжностьВ', '333-33-33', 'executorV@gosarhro.ru');
