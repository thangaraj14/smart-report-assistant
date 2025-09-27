package org.dev.smartassistantreport.controller;

import lombok.extern.slf4j.Slf4j;
import org.dev.smartassistantreport.texttosql.SqlExecutor;
import org.dev.smartassistantreport.texttosql.SqlCoderGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
class QueryController {

    private final SqlExecutor sqlExecutor;
    private final SqlCoderGenerator sqlCoderGenerator;

    QueryController(SqlExecutor sqlExecutor, SqlCoderGenerator sqlCoderGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.sqlCoderGenerator = sqlCoderGenerator;
    }

    @PostMapping(value = "/query")
    ResponseEntity<QueryResponse> query(@RequestBody QueryRequest queryRequest) {
        log.info("Input: {}", queryRequest.question);
        String sqlQuery = sqlCoderGenerator.generateSql(queryRequest.question());
        log.info("SQL: {}", sqlQuery);
        sqlQuery = sqlQuery.replaceAll("(?s).*?(SELECT|INSERT|UPDATE|DELETE)", "$1")
                .replaceAll(";$.*", ";")
                .trim();
        List<?> result = sqlExecutor.execute(sqlQuery);
        log.info("Result: {}", result);
        return ResponseEntity.ok(new QueryResponse(result));
    }

    record QueryRequest(String question) {
    }

    record QueryResponse(List<?> result) {
    }

}