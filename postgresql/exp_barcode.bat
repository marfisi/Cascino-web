pg_dump -U cascino_user_db -h localhost -n cascino_schema -F p -C --inserts -t barcode -f .\bck_barcode.sql cascino_db