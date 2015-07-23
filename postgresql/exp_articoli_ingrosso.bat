pg_dump -U cascino_user_db -h localhost -n articoli_parte_di -F p -C --inserts -t articoli_parte_di.articoli_ingrosso -f .\bck_articoli_ingrosso.sql cascino_db
