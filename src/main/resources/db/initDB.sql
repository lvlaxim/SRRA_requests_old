DROP TABLE IF EXISTS requests.requests;
DROP TABLE IF EXISTS requests.departments;
DROP TABLE IF EXISTS requests.executors;
DROP TABLE IF EXISTS requests.payments;
DROP TABLE IF EXISTS requests.prices;
DROP TABLE IF EXISTS requests.rubrics;
DROP TABLE IF EXISTS requests.sources;
DROP TABLE IF EXISTS requests.themes;


CREATE TABLE requests.departments
(
    department_id smallserial NOT NULL,
    department_head character varying(20) COLLATE pg_catalog."default" NOT NULL,
    department character varying(50) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (department_id)
);

CREATE TABLE requests.executors
(
    executor_id smallserial NOT NULL,
    executor character varying(25) NOT NULL,
    is_active boolean NOT NULL DEFAULT true,
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
    price NUMERIC(1000,2) NOT NULL,
    PRIMARY KEY (price_id)
);

CREATE TABLE requests.rubrics
(
    rubric_id smallserial NOT NULL,
    rubric_code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    rubric character varying(100) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (rubric_id)
);

CREATE TABLE requests.sources
(
    source_id smallserial NOT NULL,
    sources character varying(15) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (source_id)
);

CREATE TABLE requests.themes
(
    theme_id smallserial NOT NULL,
    theme character varying(130) COLLATE pg_catalog."default" NOT NULL,
    PRIMARY KEY (theme_id)
);

CREATE TABLE requests.requests
(
    request_id serial NOT NULL,
    rubric_id smallint REFERENCES requests.rubrics,
    theme_id smallint REFERENCES requests.themes,
    subject character varying(300) COLLATE pg_catalog."default",
    short_request text COLLATE pg_catalog."default",
    short_answer text COLLATE pg_catalog."default",
    source_id smallint REFERENCES requests.sources,
    is_urgent boolean DEFAULT false,
    is_gpw boolean DEFAULT false,
    is_entity boolean DEFAULT false,
    is_consular boolean DEFAULT false,
    receiver smallint,
    receipt_date date NOT NULL DEFAULT CURRENT_DATE,
    department_id smallint REFERENCES requests.departments,
    working_by smallint REFERENCES requests.executors,
    start_date date,
    executor_id smallint REFERENCES requests.executors,
    end_date date,
    smav smallint,
    reg_number smallint,
    in_number character varying(20) COLLATE pg_catalog."default",
    in_num_from_org character varying(50) COLLATE pg_catalog."default",
    in_date date,
    copy_number smallint DEFAULT 0,
    payment_id smallint REFERENCES requests.payments,
    PRIMARY KEY (request_id)
);

