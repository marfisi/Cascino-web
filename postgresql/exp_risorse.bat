pg_dump -U cascino_user_db -h localhost -n cascino_schema -F p -C --inserts -t risorse -f .\bck_risorse.sql cascino_db