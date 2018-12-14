-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT t.*, p.name FROM type AS t
INNER JOIN product AS p ON p.type_id = t.id
WHERE t.name = 'Сыр';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product AS p WHERE p.name like '%Мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product AS p WHERE p.expired_date between current_date and  p.expired_date + interval '1 month';

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product as p
where p.price = (SELECT max(price) FROM product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, COUNT(p.type_id)
FROM product AS p
INNER JOIN TYPE AS t ON t.id = p.type_id
GROUP BY
t.name,
p.type_id;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.id, p.name, t.name FROM product AS p
INNER JOIN type AS t ON t.id = p.type_id AND t.name IN ('Молоко', 'Сыр');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name, p.type_id, COUNT(p.type_id)
FROM product AS p
INNER JOIN TYPE AS t ON t.id = p.type_id
GROUP BY
t.name,
p.type_id
HAVING COUNT(p.type_id) < 10;

-- 8. Вывести все продукты и их тип.
SELECT p.*, t.name FROM product AS p
INNER JOIN TYPE AS t ON t.id = p.type_id;
