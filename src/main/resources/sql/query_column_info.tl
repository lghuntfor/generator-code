SELECT TABLE_NAME, COLUMN_NAME ,DATA_TYPE, COLUMN_COMMENT FROM information_schema.COLUMNS  WHERE TABLE_NAME IN (${tableNames})
@if(isNotEmpty(dbName)) {
AND TABLE_SCHEMA = '${dbName}'
@}