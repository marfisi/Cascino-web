--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = cascino_schema, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: usersrolenames; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE usersrolenames (
    id integer NOT NULL,
    role character varying(45) DEFAULT 'nessuno'::character varying NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.usersrolenames OWNER TO cascino_user_db;

--
-- Name: usersrolenames_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE usersrolenames_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.usersrolenames_id_seq OWNER TO cascino_user_db;

--
-- Name: usersrolenames_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE usersrolenames_id_seq OWNED BY usersrolenames.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY usersrolenames ALTER COLUMN id SET DEFAULT nextval('usersrolenames_id_seq'::regclass);


--
-- Data for Name: usersrolenames; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO usersrolenames VALUES (1, 'admin', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (2, 'articoliRead', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (3, 'articoliWrite', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (4, 'articoliDelete', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (5, 'fotoRead', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (6, 'fotoWrite', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (7, 'fotoDelete', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (8, 'tipiRead', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (9, 'tipiWrite', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (10, 'tipiDelete', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (11, 'produttoriRead', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (12, 'produttoriWrite', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (13, 'produttoriDelete', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (14, 'caratteristicheRead', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (15, 'caratteristicheWrite', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (16, 'caratteristicheDelete', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (17, 'descrizioniRead', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (18, 'descrizioniWrite', '2015-01-15 09:36:04.063+01');
INSERT INTO usersrolenames VALUES (19, 'descrizioniDelete', '2015-01-15 09:36:04.063+01');


--
-- Name: usersrolenames_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('usersrolenames_id_seq', 19, true);


--
-- Name: usersrolenames_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY usersrolenames
    ADD CONSTRAINT usersrolenames_prik PRIMARY KEY (id);


--
-- Name: usersrolenames_role_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY usersrolenames
    ADD CONSTRAINT usersrolenames_role_unqk UNIQUE (role);


--
-- Name: updtime_usersrolenames_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_usersrolenames_trg BEFORE INSERT OR UPDATE ON usersrolenames FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- PostgreSQL database dump complete
--

