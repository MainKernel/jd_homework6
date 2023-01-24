create table if not exists worker (
                        id identity primary key,
                        name varchar(1000) not null,
                        birthday date,
                        level varchar (100) not null,
                        salary int,
                        constraint name_length
                            check (length(name) between 2 and 1000),
                        constraint level_const_values
                            check(level in ('Trainee', 'Junior', 'Middle', 'Senior' )),
                        constraint salary_const_value
                            check(salary between 100 and 100000),
                        constraint birthday_value
                            check(birthday > '1900-01-01')
);

create table if not exists client (
                        id identity primary key,
                        name varchar(1000)
                            constraint name_const_length
                                check(length(name) between 2 and 1000));


create table if not exists project (
                         id identity primary key,
                         client_id int,
                         start_date date,
                         finish_date date
);

create table if not exists project_worker (
                               project_id int,
                               worker_id int,
                               constraint project_id_fk foreign key(project_id)
                                   references project(id),
                               constraint worker_id_fk foreign key(worker_id)
                                   references worker(id)
);