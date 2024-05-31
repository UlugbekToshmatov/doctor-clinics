CREATE SEQUENCE seq_doctor_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE doctor
(
    doctor_id         NUMBER DEFAULT SEQ_DOCTOR_ID.NEXTVAL CONSTRAINT pk_doctor_id PRIMARY KEY,
    pinfl             VARCHAR2(14) NOT NULL,
    first_name        VARCHAR2(30) NOT NULL,
    last_name         VARCHAR2(30) NOT NULL,
    patronymic        VARCHAR2(30),
    date_of_birth     DATE NOT NULL,
    picture_url       VARCHAR2(100) NOT NULL,
    phone_number      VARCHAR2(20) NOT NULL,
    email             VARCHAR2(30),
    telegram          VARCHAR2(30),
    scientific_degree VARCHAR2(50),
    created_date      DATE   DEFAULT SYSDATE,
    updated_date      DATE   DEFAULT SYSDATE,
    deleted           VARCHAR2(1) DEFAULT 'F' NOT NULL,
    CONSTRAINT uq_doctor_pinfl UNIQUE (pinfl),
    CONSTRAINT ck_phone_number CHECK (REGEXP_LIKE(phone_number, '^\+\d{2,3}[\s.-]?\d{2,3}[\s.-]?\d{3}[\s.-]?\d{2}[\s.-]?\d{2}$'))
);