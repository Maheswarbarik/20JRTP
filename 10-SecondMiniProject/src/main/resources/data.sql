Insert Into COUNTRY_MASTER  (COUNTRY_ID,COUNTRY_NAME) values(1,'India');
Insert Into COUNTRY_MASTER  (COUNTRY_ID,COUNTRY_NAME) values(2,'USA');

Insert Into STATE_MASTER  (STATE_ID,COUNTRY_ID,STATE_NAME) values(1,1,'Andhra Pradesh');
Insert Into STATE_MASTER  (STATE_ID,COUNTRY_ID,STATE_NAME) values(2,1,'Karnataka');
Insert Into STATE_MASTER  (STATE_ID,COUNTRY_ID,STATE_NAME) values(3,2,'New Jersey');
Insert Into STATE_MASTER  (STATE_ID,COUNTRY_ID,STATE_NAME) values(4,2,'Ohio');

Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(1,1,'Vizag');
Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(2,1,'Guntur');
Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(3,2,'Bangalore');
Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(4,2,'Mysore');
Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(5,3,'Maywood');
Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(6,3,'Westwood');
Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(7,4,'Oakwood');
Insert Into CITY_MASTER  (CITY_ID,STATE_ID,CITY_NAME) values(8,4,'Cuyahoga County');