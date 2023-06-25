create schema e3test;

use e3test;

CREATE TABLE company (
                         id INT AUTO_INCREMENT NOT NULL,
                         name VARCHAR(100),
                         PRIMARY KEY (id)
) ENGINE = InnoDB ROW_FORMAT = DEFAULT;

CREATE TABLE employee (
                          id INT AUTO_INCREMENT NOT NULL,
                          first_name VARCHAR(100),
                          last_name VARCHAR(100),
                          PRIMARY KEY (id)
) ENGINE = InnoDB ROW_FORMAT = DEFAULT;



insert into employee values (1, 'Joe', 'Jones');
insert into employee values (2, 'Bob', 'Brown');
insert into employee values (3, 'Annie', 'Armstrong');
insert into employee values (4, 'Susan', 'Smith');


insert into company values (1, 'Kineo');
insert into company values (2, 'Hungry Jacks');

create user 'e3testuser'@'%' identified by 'e3testpassword';
grant all privileges on e3test.* to 'e3testuser'@'%' with grant option;
flush privileges;

ALTER TABLE employee
ADD COLUMN company_id INT NOT NULL
DEFAULT 1;

ALTER TABLE employee
ADD CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES company(id);