-- 1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.id, c.name, e.name, t.name, b.name FROM car AS c
LEFT OUTER JOIN engine AS e ON c.engine_id = e.id
LEFT OUTER JOIN transmission AS t ON c.transmission_id = t.id
LEFT OUTER JOIN carbody AS b ON c.carbody_id = b.id;

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT e.name FROM engine AS e
LEFT OUTER JOIN car AS c ON c.engine_id = e.id
WHERE c.id is null;

SELECT t.name FROM transmission AS t
LEFT OUTER JOIN car AS c ON c.transmission_id = t.id
WHERE c.id is null;

SELECT b.name FROM carbody AS b
LEFT OUTER JOIN car AS c ON c.carbody_id = b.id
WHERE c.id is null;