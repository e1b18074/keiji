INSERT INTO userInfo (number, name, password, role) SELECT * FROM (SELECT 1, 'tabata',  '$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC', 'ROLE_ADMIN')
WHERE NOT EXISTS (SELECT * FROM userInfo WHERE name = 'tabata');
INSERT INTO userInfo (number, name, password, role) SELECT * FROM (SELECT 2,  'tanaka',  '$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC', 'ROLE_ADMIN')
WHERE NOT EXISTS (SELECT * FROM userInfo WHERE name = 'tanaka');
INSERT INTO userInfo (number, name, password, role) SELECT * FROM (SELECT 3, 'fukuoka',  '$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC', 'ROLE_ADMIN')
WHERE NOT EXISTS (SELECT * FROM userInfo WHERE name = 'fukuoka');
INSERT INTO userInfo (number, name, password, role) SELECT * FROM (SELECT 4, 'nagai',  '$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC', 'ROLE_ADMIN')
WHERE NOT EXISTS (SELECT * FROM userInfo WHERE name = 'nagai');
INSERT INTO userInfo (number, name, password, role) SELECT * FROM (SELECT 5, 'user1',  '$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC', 'ROLE_USER')
WHERE NOT EXISTS (SELECT * FROM userInfo WHERE name = 'user1');
