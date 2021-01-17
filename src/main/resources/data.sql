INSERT INTO userInfo (number, name, password, role) SELECT * FROM (SELECT 0, 'master',  '$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC', 'ROLE_MASTER')
WHERE NOT EXISTS (SELECT * FROM userInfo WHERE name = 'master');
