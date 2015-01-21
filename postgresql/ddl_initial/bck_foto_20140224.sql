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
-- Name: foto; Type: TABLE; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE TABLE foto (
    id integer NOT NULL,
    path character varying(80) DEFAULT '.\'::character varying,
    originale character varying(50) NOT NULL,
    grande character varying(50),
    grande_watermark character varying(50),
    thumbnail character varying(50),
    thumbnail_watermark character varying(50),
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.foto OWNER TO postgres;

--
-- Name: foto_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: postgres
--

CREATE SEQUENCE foto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.foto_id_seq OWNER TO postgres;

--
-- Name: foto_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: postgres
--

ALTER SEQUENCE foto_id_seq OWNED BY foto.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY foto ALTER COLUMN id SET DEFAULT nextval('foto_id_seq'::regclass);


--
-- Data for Name: foto; Type: TABLE DATA; Schema: cascino_schema; Owner: postgres
--



--
-- Name: foto_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: postgres
--

SELECT pg_catalog.setval('foto_id_seq', 1, false);


--
-- Name: foto_originale_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY foto
    ADD CONSTRAINT foto_originale_unqk UNIQUE (originale);
	
--
-- Name: foto_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY foto
    ADD CONSTRAINT foto_prik PRIMARY KEY (id);


--
-- Name: updtime_foto_trg; Type: TRIGGER; Schema: cascino_schema; Owner: postgres
--

CREATE TRIGGER updtime_foto_trg BEFORE INSERT OR UPDATE ON foto FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- PostgreSQL database dump complete
--

