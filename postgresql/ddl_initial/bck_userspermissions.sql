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
-- Name: userspermissions; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE userspermissions (
    id integer NOT NULL,
    role integer NOT NULL,
    azione character varying(80) DEFAULT 'nessuno'::character varying NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.userspermissions OWNER TO cascino_user_db;

--
-- Name: userspermissions_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE userspermissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.userspermissions_id_seq OWNER TO cascino_user_db;

--
-- Name: userspermissions_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE userspermissions_id_seq OWNED BY userspermissions.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY userspermissions ALTER COLUMN id SET DEFAULT nextval('userspermissions_id_seq'::regclass);


--
-- Data for Name: userspermissions; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO userspermissions VALUES (1, 1, 'articoli:read', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (2, 1, 'articoli:write:save', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (3, 1, 'articoli:write:delete', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (4, 1, 'foto:read', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (5, 1, 'foto:write:save', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (6, 1, 'foto:write:delete', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (7, 1, 'tipi:read', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (8, 1, 'tipi:write:save', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (9, 1, 'tipi:write:delete', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (10, 1, 'produttori:read', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (11, 1, 'produttori:write:save', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (12, 1, 'produttori:write:delete', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (13, 1, 'caratteristiche:read', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (14, 1, 'caratteristiche:write:save', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (15, 1, 'caratteristiche:write:delete', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (16, 1, 'descrizioni:read', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (17, 1, 'descrizioni:write:save', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (18, 1, 'descrizioni:write:delete', '2015-01-15 09:51:18.975+01');
INSERT INTO userspermissions VALUES (19, 2, 'articoli:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (20, 3, 'articoli:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (21, 3, 'articoli:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (22, 4, 'articoli:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (23, 4, 'articoli:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (24, 4, 'articoli:write:delete', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (25, 5, 'foto:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (26, 6, 'foto:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (27, 6, 'foto:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (28, 7, 'foto:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (29, 7, 'foto:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (30, 7, 'foto:write:delete', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (31, 8, 'tipi:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (32, 9, 'tipi:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (33, 9, 'tipi:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (34, 10, 'tipi:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (35, 10, 'tipi:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (36, 10, 'tipi:write:delete', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (37, 11, 'produttori:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (38, 12, 'produttori:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (39, 12, 'produttori:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (40, 13, 'produttori:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (41, 13, 'produttori:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (42, 13, 'produttori:write:delete', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (43, 14, 'caratteristiche:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (44, 15, 'caratteristiche:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (45, 15, 'caratteristiche:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (46, 16, 'caratteristiche:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (47, 16, 'caratteristiche:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (48, 16, 'caratteristiche:write:delete', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (49, 17, 'descrizioni:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (50, 18, 'descrizioni:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (51, 18, 'descrizioni:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (52, 19, 'descrizioni:read', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (53, 19, 'descrizioni:write:save', '2015-01-15 12:33:19.983+01');
INSERT INTO userspermissions VALUES (54, 19, 'descrizioni:write:delete', '2015-01-15 12:33:19.983+01');


--
-- Name: userspermissions_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('userspermissions_id_seq', 54, true);


--
-- Name: userspermissions_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY userspermissions
    ADD CONSTRAINT userspermissions_prik PRIMARY KEY (id);


--
-- Name: userspermissions_role_azione_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY userspermissions
    ADD CONSTRAINT userspermissions_role_azione_unqk UNIQUE (role, azione);


--
-- Name: fki_userspermissions_role_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_userspermissions_role_fonk ON userspermissions USING btree (role);


--
-- Name: updtime_userspermissions_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_userspermissions_trg BEFORE INSERT OR UPDATE ON userspermissions FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: userspermissions_role_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY userspermissions
    ADD CONSTRAINT userspermissions_role_fonk FOREIGN KEY (role) REFERENCES usersrolenames(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

