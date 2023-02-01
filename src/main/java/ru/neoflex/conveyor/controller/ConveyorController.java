package ru.neoflex.conveyor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Кредитный конвейер",
        description = "Микросервис для формирования предложений и подсчета параметров для кредита")
public class ConveyorController {

    private final OrderService orderService;
    private final CreditService creditService;

    @PostMapping(value = "/offers")
    @Operation(summary = "Получение предложений по кредиту")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кредитные предложения получены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoanOfferDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Данные не прошли прескоринг",
                    content = @Content)})
    public List<LoanOfferDTO> getOffers(@RequestBody @Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return orderService.getOffers(loanApplicationRequestDTO);
    }

    @Operation(summary = "Рассчет параметров кредита")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кредит рассчитан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreditDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Данные введены некорректно",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Отказ в получении кредита, так как параметры не подходят" +
                    " для его получения",
                    content = @Content),})
    @PostMapping(value = "/calculation")
    public CreditDTO calculate(@RequestBody @Valid ScoringDataDTO scoringDataDTO) {
        return creditService.createCreditDTO(scoringDataDTO);
    }
}
