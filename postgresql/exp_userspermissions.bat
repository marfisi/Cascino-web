pg_dump -U cascino_user_db -h localhost -n cascino_schema -F p -C --inserts -t userspermissions -f .\bck_userspermissions.sql cascino_db