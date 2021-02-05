-- TRANSFER TO
INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (2, 2, ?, ?, ?);

-- UPDATING BALANCE TABLE
UPDATE accounts
SET balance = balance - ?
WHERE account_id = ?;

UPDATE accounts
SET balance = balance + ?
WHERE account_id = ?;

SELECT *
FROM transfers;

select *
from accounts;
