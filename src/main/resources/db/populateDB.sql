DELETE
FROM requests.executors;

ALTER SEQUENCE requests.executors_executor_id_seq RESTART WITH 1;

INSERT INTO requests.executors(executor, is_active, job, phone_number, "e-mail")
VALUES ('Исполнитель1', true, 'Должность1', '111-11-11', 'executor1@gosarhro.ru'),
       ('Исполнитель2', true, 'Должность2', '222-22-22', 'executor2@gosarhro.ru'),
       ('Исполнитель3', false, 'Должность3', '333-33-33', 'executor3@gosarhro.ru');
