INSERT INTO engine(id, name) VALUES (1, 'Motor 1');
INSERT INTO engine(id, name) VALUES (2, 'Motor 2');

INSERT INTO transmission(id, name) VALUES (1, 'Trans 1');
INSERT INTO transmission(id, name) VALUES (2, 'Trans 2');

INSERT INTO carbody(id, name) VALUES (1, 'Kuzov 1');
INSERT INTO carbody(id, name) VALUES (2, 'Kuzov 2');
INSERT INTO carbody(id, name) VALUES (3, 'Kuzov 3');

INSERT INTO car(id, name, engine_id, transmission_id, carbody_id)
VALUES (1, 'Subaru', 1, 2, 3);