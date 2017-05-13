create table candidate(
      id int not null primary key,
      EMAIL varchar_ignorecase(50) ,
      TELEPHONE varchar_ignorecase(50) not null,
      NAME varchar_ignorecase(50) ,
      COMPANY varchar_ignorecase(50) ,
      COMMENTS varchar_ignorecase(500),
      CREATION_DATE varchar_ignorecase(50));