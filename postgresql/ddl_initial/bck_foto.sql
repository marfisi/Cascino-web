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
-- Name: foto; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE foto (
    id integer NOT NULL,
    path character varying(100) DEFAULT 'c:\dev\foto'::character varying,
    originale character varying(100) NOT NULL,
    grande character varying(100),
    grande_watermark character varying(100),
    thumbnail character varying(100),
    thumbnail_watermark character varying(100),
    colore1 character varying(6) DEFAULT 'ind'::character varying,
    colore2 character varying(6) DEFAULT 'ind'::character varying
    forma character varying(3) DEFAULT 'ind'::character varying,
    cosa_mostra character varying(1) DEFAULT 'I'::character varying,
    tag character varying(200),
    updtime timestamp with time zone,
);


ALTER TABLE cascino_schema.foto OWNER TO cascino_user_db;

--
-- Name: foto_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE foto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.foto_id_seq OWNER TO cascino_user_db;

--
-- Name: foto_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE foto_id_seq OWNED BY foto.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY foto ALTER COLUMN id SET DEFAULT nextval('foto_id_seq'::regclass);


--
-- Data for Name: foto; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO foto VALUES (1, 'c:\dev\foto', 'n.d..jpg', 'n.d..jpg', 'n.d..jpg', 'n.d..jpg', 'n.d..jpg', 'ind', 'ind', 'ind', 'I', NULL, '2014-10-31 10:46:23.197+01');

--
-- Name: foto_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('foto_id_seq', 1, true);


--
-- Name: foto_path_originale_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY foto
    ADD CONSTRAINT foto_path_originale_unqk UNIQUE (path, originale);


--
-- Name: foto_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY foto
    ADD CONSTRAINT foto_prik PRIMARY KEY (id);


--
-- Name: updtime_foto_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_foto_trg BEFORE INSERT OR UPDATE ON foto FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- PostgreSQL database dump complete
--

