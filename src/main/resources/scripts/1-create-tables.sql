create table dragon
(
    id               bigint primary key auto_increment unique,
    firstname        text   not null check ( firstname != '' ),
    creation_date    date   not null,
    age              bigint not null check (age > 0),
    color            text   not null,
    type             text   not null,
    dragon_character text   not null
);

create table person
(
    id         bigint primary key auto_increment unique,
    dragon_id  bigint,
    firstname  text not null check ( firstname != '' ),
    height     real not null check ( height > 0 ),
    weight     real not null check ( weight > 0 ),
    eye_color  text not null,
    hair_color text not null,
    foreign key (dragon_id) references dragon (id) on delete cascade
);

create table coordinates
(
    id        bigint primary key auto_increment unique,
    dragon_id bigint,
    x         real not null,
    y         long not null check ( y > 0 and y < 903 ),
    foreign key (dragon_id) references dragon (id) on delete cascade
);