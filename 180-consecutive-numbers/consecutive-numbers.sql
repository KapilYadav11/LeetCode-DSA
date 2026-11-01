# Write your MySQL query statement below
SELECT DISTINCT l1.num AS ConsecutiveNums
FROM (
    SELECT num,
           LEAD(num, 1) OVER (ORDER BY id) AS next1,
           LEAD(num, 2) OVER (ORDER BY id) AS next2
    FROM Logs
) l1
WHERE l1.num = l1.next1 AND l1.num = l1.next2;