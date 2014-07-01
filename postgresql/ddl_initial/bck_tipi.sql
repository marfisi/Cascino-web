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
-- Name: tipi; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE tipi (
    id integer NOT NULL,
    nome character varying(30) NOT NULL,
    descrizione character varying(70),
    padre integer DEFAULT 1 NOT NULL,
    foto integer DEFAULT 1 NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.tipi OWNER TO cascino_user_db;

--
-- Name: tipi_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE tipi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.tipi_id_seq OWNER TO cascino_user_db;

--
-- Name: tipi_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE tipi_id_seq OWNED BY tipi.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY tipi ALTER COLUMN id SET DEFAULT nextval('tipi_id_seq'::regclass);


--
-- Data for Name: tipi; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO tipi VALUES (1, 'nd', 'nd', 1, 1, '2014-04-05 10:12:59.344+02');

--
-- Name: tipi_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('tipi_id_seq', 1, true);


--
-- Name: tipi_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY tipi
    ADD CONSTRAINT tipi_prik PRIMARY KEY (id);


--
-- Name: fki_tipi_padre_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_tipi_padre_fonk ON tipi USING btree (padre);


--
-- Name: updtime_tipi_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_tipi_trg BEFORE INSERT OR UPDATE ON tipi FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: tipi_foto_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY tipi
    ADD CONSTRAINT tipi_foto_fonk FOREIGN KEY (foto) REFERENCES foto(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: tipi_padre_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY tipi
    ADD CONSTRAINT tipi_padre_fonk FOREIGN KEY (padre) REFERENCES tipi(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

