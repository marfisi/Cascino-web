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
-- Name: users; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    login character varying(30) DEFAULT 'guest'::character varying NOT NULL,
    password character varying(64) DEFAULT 'noaccess'::character varying NOT NULL,
    nome character varying(30) DEFAULT 'anonimo'::character varying NOT NULL,
    cognome character varying(30) DEFAULT 'anonimo'::character varying NOT NULL,
    stato character varying(10) DEFAULT 'disable'::character varying NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.users OWNER TO cascino_user_db;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.users_id_seq OWNER TO cascino_user_db;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: users; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO users VALUES (1, 'ric', 'b45317ab1b522fe48f5b5bb921b075f1a19a09ca96afb9b900b05bd5fbbd8d64', 'riccardo', 'marfisi', 'ok', '2015-01-07 12:58:08.191+01');
INSERT INTO users VALUES (2, 'ago', '1d8e08fd632682840b4159dd52d36b0d1c55cf6cac21097076474e5b43608cb4', 'agostino', 'aglieri rinella', 'ok', '2015-01-13 11:49:40.791+01');
INSERT INTO users VALUES (3, 'ale', '5c85bb36f3869809fb738a3ba6f990aedbfeca3df2dc1a997fa49c50d0eed8e6', 'alessandra', 'cascino', 'ok', '2015-01-14 12:32:36.06+01');
INSERT INTO users VALUES (4, 'nic', '0e1733efb669979a709f05ba0a8d2725095193cf81fd05b75b7cefe5c48e03f9', 'nicola', 'cascino', 'ok', '2015-01-14 12:33:14.94+01');


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('users_id_seq', 4, true);


--
-- Name: users_login_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_login_unqk UNIQUE (login);


--
-- Name: users_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_prik PRIMARY KEY (id);


--
-- Name: updtime_users_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_users_trg BEFORE INSERT OR UPDATE ON users FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- PostgreSQL database dump complete
--

