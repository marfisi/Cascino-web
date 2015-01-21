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
-- Name: tipi; Type: TABLE; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE TABLE tipi (
    id integer NOT NULL,
    nome character varying(30) NOT NULL,
    descrizione character varying(70),
    padre integer DEFAULT 1 NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.tipi OWNER TO postgres;

--
-- Name: tipi_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: postgres
--

CREATE SEQUENCE tipi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.tipi_id_seq OWNER TO postgres;

--
-- Name: tipi_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: postgres
--

ALTER SEQUENCE tipi_id_seq OWNED BY tipi.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY tipi ALTER COLUMN id SET DEFAULT nextval('tipi_id_seq'::regclass);


--
-- Data for Name: tipi; Type: TABLE DATA; Schema: cascino_schema; Owner: postgres
--

INSERT INTO tipi VALUES (1, 'n.d.', 'n.d.', 1, '2014-02-22 09:00:00+01');


--
-- Name: tipi_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: postgres
--

SELECT pg_catalog.setval('tipi_id_seq', 1, true);


--
-- Name: tipi_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipi
    ADD CONSTRAINT tipi_prik PRIMARY KEY (id);


--
-- Name: fki_tipi_padre_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_tipi_padre_fonk ON tipi USING btree (padre);


--
-- Name: updtime_tipi_trg; Type: TRIGGER; Schema: cascino_schema; Owner: postgres
--

CREATE TRIGGER updtime_tipi_trg BEFORE INSERT OR UPDATE ON tipi FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: tipi_padre_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY tipi
    ADD CONSTRAINT tipi_padre_fonk FOREIGN KEY (padre) REFERENCES tipi(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

