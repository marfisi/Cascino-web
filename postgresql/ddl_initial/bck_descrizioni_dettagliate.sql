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
-- Name: descrizioni_dettagliate; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE descrizioni_dettagliate (
    id integer NOT NULL,
    articolo integer NOT NULL,
    descrizione character varying(500) NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.descrizioni_dettagliate OWNER TO cascino_user_db;

--
-- Name: descrizioni_dettagliate_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE descrizioni_dettagliate_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.descrizioni_dettagliate_id_seq OWNER TO cascino_user_db;

--
-- Name: descrizioni_dettagliate_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE descrizioni_dettagliate_id_seq OWNED BY descrizioni_dettagliate.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY descrizioni_dettagliate ALTER COLUMN id SET DEFAULT nextval('descrizioni_dettagliate_id_seq'::regclass);


--
-- Data for Name: descrizioni_dettagliate; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--



--
-- Name: descrizioni_dettagliate_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('descrizioni_dettagliate_id_seq', 1, false);


--
-- Name: descrizioni_dettagliate_articolo_descrizione_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY descrizioni_dettagliate
    ADD CONSTRAINT descrizioni_dettagliate_articolo_descrizione_unqk UNIQUE (articolo, descrizione);


--
-- Name: descrizioni_dettagliate_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY descrizioni_dettagliate
    ADD CONSTRAINT descrizioni_dettagliate_prik PRIMARY KEY (id);


--
-- Name: updtime_descrizioni_dettagliate_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_descrizioni_dettagliate_trg BEFORE INSERT OR UPDATE ON descrizioni_dettagliate FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: descrizioni_dettagliate_articolo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY descrizioni_dettagliate
    ADD CONSTRAINT descrizioni_dettagliate_articolo_fonk FOREIGN KEY (articolo) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

