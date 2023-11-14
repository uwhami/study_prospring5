insert into singer (first_name, last_name, birth_date) values ('John', 'Mayer', '1977-10-16');
insert into singer (first_name, last_name, birth_date) values ('Eric', 'Clapton', '1945-03-30');
insert into singer (first_name, last_name, birth_date) values ('John', 'Butler', '1975-04-01');

insert into album (singer_id, title, release_date) values (1, 'The Search For Everything', '2017-01-20');
insert into album (singer_id, title, release_date) values (1, 'Battle Studies', '2009-11-17');
insert into album (singer_id, title, release_date) values (2, ' From The Cradle ', '1994-09-13');


-- Added in chapter7.
insert into instrument (instrument_id) values ('Guitar');
insert into instrument (instrument_id) values ('Piano');
insert into instrument (instrument_id) values ('Voice');
insert into instrument (instrument_id) values ('Drums');
insert into instrument (instrument_id) values ('Synthesizer');

insert into singer_instrument(singer_id, instrument_id) values (1, 'Guitar');
insert into singer_instrument(singer_id, instrument_id) values (1, 'Piano');
insert into singer_instrument(singer_id, instrument_id) values (2, 'Guitar');

-- Added in chapter8.8.1 : Tracking changes in the entity class
insert into singer_audit (first_name, last_name, birth_date) values ('John', 'Mayer', '1977-10-16');
insert into singer_audit (first_name, last_name, birth_date) values ('Eric', 'Clapton', '1945-03-30');
insert into singer_audit (first_name, last_name, birth_date) values ('John', 'Butler', '1975-04-01');

insert into singer_audit_h (first_name, last_name, birth_date, AUDIT_REVISION) values ('John', 'Mayer', '1977-10-16', 1);
insert into singer_audit_h (first_name, last_name, birth_date, AUDIT_REVISION) values ('Eric', 'Clapton', '1945-03-30', 1);
insert into singer_audit_h (first_name, last_name, birth_date, AUDIT_REVISION) values ('John', 'Butler', '1975-04-01', 1);