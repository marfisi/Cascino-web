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
-- Name: produttori; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE produttori (
    id integer NOT NULL,
    nome character varying(80) NOT NULL,
    dati character varying(50),
    foto integer DEFAULT 1 NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.produttori OWNER TO cascino_user_db;

--
-- Name: produttori_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE produttori_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.produttori_id_seq OWNER TO cascino_user_db;

--
-- Name: produttori_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE produttori_id_seq OWNED BY produttori.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY produttori ALTER COLUMN id SET DEFAULT nextval('produttori_id_seq'::regclass);


--
-- Data for Name: produttori; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO produttori VALUES (1, 'n.d.', 'n.d.', '2014-04-04 13:26:42.731+02', 1);

--
-- Name: produttori_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('produttori_id_seq', 1, true);


--
-- Name: produttori_nome_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY produttori
    ADD CONSTRAINT produttori_nome_unqk UNIQUE (nome);


--
-- Name: produttori_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY produttori
    ADD CONSTRAINT produttori_prik PRIMARY KEY (id);


--
-- Name: updtime_produttori_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_produttori_trg BEFORE INSERT OR UPDATE ON produttori FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: produttori_marchio_fkey; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY produttori
    ADD CONSTRAINT produttori_foto_fonk FOREIGN KEY (foto) REFERENCES foto(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

