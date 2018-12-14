-- ROLE
INSERT INTO role_(id, name) VALUES (1, 'Administrator');
INSERT INTO role_(id, name) VALUES (2, 'user');

-- USER
INSERT INTO user_(id, name, role_) VALUES (1, 'Admin', 1);
INSERT INTO user_(id, name, role_) VALUES (2, 'User', 2);

-- RULE
INSERT INTO rule_(id, name) VALUES (1, 'Rule 1');
INSERT INTO rule_(id, name) VALUES (2, 'Rule 2');

-- ROLE_RULE
INSERT INTO role__rules (role_, rule_) VALUES (1, 2);
INSERT INTO role__rules (role_, rule_) VALUES (2, 1);

-- CATEGORY
INSERT INTO category_(id, name) VALUES (1, 'A');
INSERT INTO category_(id, name) VALUES (2, 'B');
INSERT INTO category_(id, name) VALUES (3, 'C');

-- STATE
INSERT INTO state_(id, name) VALUES (1, 'Open');
INSERT INTO state_(id, name) VALUES (2, 'Close');

-- ITEM
INSERT INTO item_(id, name, category_, state_, user_) VALUES (1, 'Item #1', 1, 1, 2);

-- COMMENT
INSERT INTO comment_(id, message, item_) VALUES (1, 'First comment', 1);
INSERT INTO comment_(id, message, item_) VALUES (2, 'Second comment', 1);

-- ATTACH
INSERT INTO attach_(id, path, item_) VALUES (1, '/folder/img1.png', 1);
INSERT INTO attach_(id, path, item_) VALUES (2, '/folder/img2.jpeg', 1);
