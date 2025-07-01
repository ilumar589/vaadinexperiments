WITH fk_columns AS (
    SELECT
        kcu.table_name AS source_table,
        kcu.column_name AS source_column,
        ccu.table_name AS target_table,
        ccu.column_name AS target_column
    FROM
        information_schema.table_constraints AS tc
            JOIN information_schema.key_column_usage AS kcu
                 ON tc.constraint_name = kcu.constraint_name AND tc.table_schema = kcu.table_schema
            JOIN information_schema.constraint_column_usage AS ccu
                 ON ccu.constraint_name = tc.constraint_name AND ccu.table_schema = tc.table_schema
    WHERE
        tc.constraint_type = 'FOREIGN KEY' AND tc.table_schema = :schemaName
)
SELECT
    c.table_name,
    c.column_name,
    c.data_type,
    fk.target_table,
    fk.target_column
FROM
    information_schema.columns c
        LEFT JOIN
    fk_columns fk
    ON c.table_name = fk.source_table AND c.column_name = fk.source_column
WHERE
    c.table_schema = :schemaName
ORDER BY
    c.table_name, c.column_name;



