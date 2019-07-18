DELETE FROM requests.executors;
DELETE FROM requests.departments;
DELETE FROM requests.payments;

ALTER SEQUENCE requests.executors_executor_id_seq RESTART WITH 1;
ALTER SEQUENCE requests.departments_department_id_seq RESTART WITH 1;
-- ALTER SEQUENCE requests. RESTART WITH 1;

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
