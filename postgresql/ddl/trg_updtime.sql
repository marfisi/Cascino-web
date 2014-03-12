CREATE FUNCTION updtime_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	BEGIN
	   NEW.updtime = now(); 
	   RETURN NEW;
	END;
	$$;


ALTER FUNCTION cascino_schema.updtime_column() OWNER TO cascino_user_db;