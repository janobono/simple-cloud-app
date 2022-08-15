DROP DATABASE IF EXISTS customer_service;
CREATE DATABASE customer_service CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL ON customer_service.* TO 'app'@'%';

DROP DATABASE IF EXISTS room_service;
CREATE DATABASE room_service CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL ON room_service.* TO 'app'@'%';

DROP DATABASE IF EXISTS reservation_service;
CREATE DATABASE reservation_service CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL ON reservation_service.* TO 'app'@'%';
