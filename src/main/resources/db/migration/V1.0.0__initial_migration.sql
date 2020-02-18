create sequence equipment_sequence start 1 increment 1;
create table equipment (
    id bigint primary key default nextval('equipment_sequence'),
    pei varchar(255),
    supi varchar(255),
    gpsi varchar(255),
    status integer
);
