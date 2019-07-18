DROP TABLE IF EXISTS requests.departments;
DROP TABLE IF EXISTS requests.executors;
DROP TABLE IF EXISTS requests.payments;
DROP TABLE IF EXISTS requests.prices;

CREATE TABLE requests.departments
(
    department_id smallserial NOT NULL,
    department_head character varying(20) COLLATE pg_catalog."default" NOT NULL,
    department character varying(50) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (department_id)
);

CREATE TABLE requests.executors
(
    executor_id serial NOT NULL,
    executor character varying(25) NOT NULL,
    is_active boolean NOT NULL,
    job character varying(30),
    phone_number character varying(14),
    email character varying(25),
    PRIMARY KEY (executor_id)
);

CREATE TABLE requests.payments
(
    payment_id smallserial NOT NULL,
    payment character varying(20) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (payment_id)
);

CREATE TABLE requests.prices
(
    price_id smallserial NOT NULL,
    work_type character varying(150) COLLATE pg_catalog."default" NOT NULL,
    unit character varying(20) COLLATE pg_catalog."default" NOT NULL,
    price money NOT NULL,
    PRIMARY KEY (price_id)
);