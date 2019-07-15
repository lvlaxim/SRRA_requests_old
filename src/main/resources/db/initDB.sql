DROP TABLE IF EXISTS requests.executors;

CREATE TABLE requests.executors
(
    executor_id serial NOT NULL,
    executor character varying(25) NOT NULL,
    is_active boolean NOT NULL,
    job character varying(30),
    phone_number character varying(14),
    "e-mail" character varying(25),
    PRIMARY KEY (executor_id)
)