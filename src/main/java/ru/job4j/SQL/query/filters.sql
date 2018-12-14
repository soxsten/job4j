-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product AS p WHERE p.type_id = 2;

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product AS p WHERE p.name like '%Мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product AS p WHERE p.expired_date between '2018-12-30' and  '2019-01-30';

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product as p
where p.price = (SELECT max(price) FROM product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT count(*) FROM product AS p WHERE p.type_id = 2;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product AS p WHERE p.type_id in (1, 2);

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
SELECT t.name, p.type_id, COUNT(p.type_id)
FROM product AS p
INNER JOIN TYPE AS t ON t.id = p.type_id
GROUP BY
t.name,
p.type_id
HAVING COUNT(p.type_id) = 2;

-- 8. Вывести все продукты и их тип.
SELECT p.*, t.name FROM product AS p
INNER JOIN TYPE AS t ON t.id = p.type_id;
