insert into dragon (firstname, creation_date, age, color, type, dragon_character)
VALUES ('drag1', now(), 1, 'BLACK', 'AIR', 'EVIL'),
       ('drag2', now(), 2, 'GREEN', 'WATER', 'EVIL'),
       ('drag3', now(), 3, 'ORANGE', 'AIR', 'EVIL');

insert into coordinates (dragon_id, x, y)
VALUES ((select id from dragon where dragon.firstname = 'drag1'),
        1, 1),
       ((select id from dragon where dragon.firstname = 'drag2'),
        2, 2),
       ((select id from dragon where dragon.firstname = 'drag3'),
        3, 3);

insert into person (dragon_id, firstname, height, weight, eye_color, hair_color)
VALUES ((select id from dragon where dragon.firstname = 'drag1'),
        'Yarik', 10, 10, 'BLACK', 'BLACK'),
       ((select id from dragon where dragon.firstname = 'drag2'),
        'Dmit', 20, 20, 'GREEN', 'BLACK'),
       ((select id from dragon where dragon.firstname = 'drag3'),
        'Gey', 30, 30, 'BLACK', 'BLACK');