CREATE SCHEMA IF NOT EXISTS task_mgmt;

ALTER TABLE category SET SCHEMA task_mgmt;
ALTER TABLE task SET SCHEMA task_mgmt;
