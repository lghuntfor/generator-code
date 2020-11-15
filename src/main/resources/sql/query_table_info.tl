SELECT `TABLE_NAME`, `TABLE_COMMENT` FROM information_schema.TABLES WHERE TABLE_NAME IN (${tableNames})
@if(isNotEmpty(dbName)) {
AND TABLE_SCHEMA = '${dbName}'
@}