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
-- Name: userloginrole; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE userloginrole (
    id integer NOT NULL,
    login character varying(30) DEFAULT 'undefined'::character varying NOT NULL,
    password character varying(30) DEFAULT 'noaccess'::character varying NOT NULL,
    role character varying(5) DEFAULT 'none'::character varying NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.userloginrole OWNER TO cascino_user_db;

--
-- Name: userloginrole_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE userloginrole_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.userloginrole_id_seq OWNER TO cascino_user_db;

--
-- Name: userloginrole_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE userloginrole_id_seq OWNED BY userloginrole.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY userloginrole ALTER COLUMN id SET DEFAULT nextval('userloginrole_id_seq'::regclass);


--
-- Data for Name: userloginrole; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--



--
-- Name: userloginrole_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('userloginrole_id_seq', 1, false);


--
-- Name: userloginrole_login_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY userloginrole
    ADD CONSTRAINT userloginrole_login_unqk UNIQUE (login);


--
-- Name: userloginrole_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY userloginrole
    ADD CONSTRAINT userloginrole_prik PRIMARY KEY (id);


--
-- Name: updtime_userloginrole_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_userloginrole_trg BEFORE INSERT OR UPDATE ON userloginrole FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- PostgreSQL database dump complete
--

