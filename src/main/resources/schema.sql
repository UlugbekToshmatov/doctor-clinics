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
    created_date      TIMESTAMP DEFAULT SYSDATE,
    updated_date      TIMESTAMP DEFAULT SYSDATE,
    deleted           VARCHAR2(1) DEFAULT 'F' NOT NULL,
    CONSTRAINT uq_doctor_pinfl UNIQUE (pinfl),
    CONSTRAINT ck_phone_number CHECK (REGEXP_LIKE(phone_number, '^\+\d{2,3}[\s.-]?\d{2,3}[\s.-]?\d{3}[\s.-]?\d{2}[\s.-]?\d{2}$'))
);


CREATE SEQUENCE seq_edu_id
    MINVALUE 1
    MAXVALUE 1000
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE education (
    edu_id              NUMBER DEFAULT SEQ_EDU_ID.NEXTVAL CONSTRAINT pk_edu_id PRIMARY KEY,
    institution_name    VARCHAR2(256) NOT NULL,
    CONSTRAINT uq_edu_institution_name UNIQUE (institution_name)
);


CREATE SEQUENCE seq_spec_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE specialization (
    spec_id     NUMBER DEFAULT SEQ_SPEC_ID.NEXTVAL CONSTRAINT pk_spec_id PRIMARY KEY,
    field       VARCHAR2(256) NOT NULL,
    degree      VARCHAR2(30) NOT NULL,
    CONSTRAINT uq_spec_field_degree UNIQUE (field, degree),
    CONSTRAINT ck_spec_degree CHECK (degree in ('BACHELOR', 'MASTER', 'PHD', 'PROFESSOR'))
);


CREATE TABLE doctor_spec (
    doctor_id    NUMBER NOT NULL,
    spec_id      NUMBER NOT NULL,
    edu_id       NUMBER NOT NULL,
    start_date   DATE NOT NULL,
    end_date     DATE NOT NULL,
    CONSTRAINT   fk_doctor_spec_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),
    CONSTRAINT   fk_doctor_spec_spec_id FOREIGN KEY (spec_id) REFERENCES specialization (spec_id),
    CONSTRAINT   fk_doctor_spec_edu_id FOREIGN KEY (edu_id) REFERENCES education (edu_id)
);