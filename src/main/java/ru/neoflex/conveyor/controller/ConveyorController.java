package ru.neoflex.conveyor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.conveyor.data.dto.CreditDTO;
import ru.neoflex.conveyor.data.dto.LoanApplicationRequestDTO;
import ru.neoflex.conveyor.data.dto.LoanOfferDTO;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.service.credit.CreditService;
import ru.neoflex.conveyor.service.order.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/conveyor")
public class ConveyorController {

    private final OrderService orderService;
    private final CreditService creditService;

    @PostMapping(value = "/offers")
    public List<LoanOfferDTO> getOffers(@RequestBody @Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return orderService.getOffers(loanApplicationRequestDTO);
    }

    @PostMapping(value = "/calculation")
    public CreditDTO calculate(@RequestBody @Valid ScoringDataDTO scoringDataDTO) {
        return creditService.createCreditDTO(scoringDataDTO);
    }
}
