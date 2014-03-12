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
-- Name: fornitori; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE fornitori (
    id integer NOT NULL,
    nome character varying(80) NOT NULL,
    dati character varying(50),
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.fornitori OWNER TO cascino_user_db;

--
-- Name: fornitori_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE fornitori_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.fornitori_id_seq OWNER TO cascino_user_db;

--
-- Name: fornitori_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE fornitori_id_seq OWNED BY fornitori.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY fornitori ALTER COLUMN id SET DEFAULT nextval('fornitori_id_seq'::regclass);


--
-- Data for Name: fornitori; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--



--
-- Name: fornitori_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('fornitori_id_seq', 1, false);


--
-- Name: fornitori_nome_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY fornitori
    ADD CONSTRAINT fornitori_nome_unqk UNIQUE (nome);


--
-- Name: fornitori_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY fornitori
    ADD CONSTRAINT fornitori_prik PRIMARY KEY (id);


--
-- Name: updtime_fornitori_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_fornitori_trg BEFORE INSERT OR UPDATE ON fornitori FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- PostgreSQL database dump complete
--

