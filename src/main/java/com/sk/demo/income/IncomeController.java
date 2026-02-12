package com.sk.demo.income;

import com.sk.demo.income.dto.IncomeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @PostMapping("/summary")
    public ResponseEntity<Object> getSummary(@RequestBody IncomeRequest request){
        var response=incomeService.calculateIncomeSummary(request);
        return ResponseEntity.ok(response);

    }
}
