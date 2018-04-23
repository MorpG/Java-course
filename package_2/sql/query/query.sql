-- 1. Написать запрос получение всех продуктов с типом "СЫР"

SELECT *
FROM product
WHERE type_id = (
  SELECT type.id
  FROM type
  WHERE name = 'Сыр');

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное
SELECT *
FROM product
WHERE name LIKE '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT *
FROM product
WHERE expired_date BETWEEN date('now', 'start of month', '+1 month') AND date('now', 'start of month', '+2 month');

-- 4. Написать запрос, который вывод самый дорогой продукт.
SELECT *
FROM product
WHERE price = (
  SELECT MAX(price)
  FROM product
);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(type_id)
FROM product
WHERE type_id = (
  SELECT type.id
  FROM type
  WHERE type.name = 'Сыр'
);

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT *
FROM product
WHERE type_id = (
  SELECT type.id
  FROM type
  WHERE type.name = 'Сыр') OR type_id = (
  SELECT type.id
  FROM type
  WHERE type.name = 'Молоко');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT type.name
FROM product
  INNER JOIN type ON product.type_id = type.id
GROUP BY type_id
HAVING count(*) < 10;

-- 8. Вывести все продукты и их тип.

SELECT *
FROM product
  INNER JOIN type ON product.type_id = type.id;