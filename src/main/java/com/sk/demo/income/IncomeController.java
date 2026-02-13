package com.sk.demo.income;

import com.sk.demo.common.WealthConstants;
import com.sk.demo.income.dto.IncomeRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@Slf4j
@CrossOrigin(origins = "*")
public class IncomeController {
    private static final Logger logger = LoggerFactory.getLogger(WealthConstants.class);
    @Autowired
    IncomeService incomeService;

    @PostMapping("/summary")
    public ResponseEntity<Object> getSummary(@RequestBody IncomeRequest request){
        logger.info("Received income summary request: {}", request);
        var response=incomeService.calculateIncomeSummary(request);
        return ResponseEntity.ok(response);

    }
}
