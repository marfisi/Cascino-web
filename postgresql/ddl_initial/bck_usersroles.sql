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
-- Name: usersroles; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE usersroles (
    id integer NOT NULL,
    "user" integer NOT NULL,
    role integer NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.usersroles OWNER TO cascino_user_db;

--
-- Name: usersroles_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE usersroles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.usersroles_id_seq OWNER TO cascino_user_db;

--
-- Name: usersroles_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE usersroles_id_seq OWNED BY usersroles.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY usersroles ALTER COLUMN id SET DEFAULT nextval('usersroles_id_seq'::regclass);


--
-- Data for Name: usersroles; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO usersroles VALUES (1, 1, 1, '2015-01-15 09:52:00.522+01');
INSERT INTO usersroles VALUES (2, 2, 1, '2015-01-15 10:14:38.533+01');


--
-- Name: usersroles_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('usersroles_id_seq', 2, true);


--
-- Name: usersroles_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY usersroles
    ADD CONSTRAINT usersroles_prik PRIMARY KEY (id);


--
-- Name: usersroles_user_role_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY usersroles
    ADD CONSTRAINT usersroles_user_role_unqk UNIQUE ("user", role);


--
-- Name: fki_usersroles_role_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_usersroles_role_fonk ON usersroles USING btree (role);


--
-- Name: fki_usersroles_user_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_usersroles_user_fonk ON usersroles USING btree ("user");


--
-- Name: updtime_usersroles_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_usersroles_trg BEFORE INSERT OR UPDATE ON usersroles FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: usersroles_role_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY usersroles
    ADD CONSTRAINT usersroles_role_fonk FOREIGN KEY (role) REFERENCES usersrolenames(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: usersroles_user_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY usersroles
    ADD CONSTRAINT usersroles_user_fonk FOREIGN KEY ("user") REFERENCES users(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

