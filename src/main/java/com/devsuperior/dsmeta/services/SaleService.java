package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

/**
 * <h1> DEVSUPERIOR - Java Spring Professional - Capítulo: JPA, consultas SQL e JPQL</h1>
 * DESAFIO: Consulta vendas
 * <p>
 * <b>Note:</b> Desenvolvido na linguagem Java.
 *
 * @author  Marcos Ferreira Shirafuchi
 * @version 1.0
 * @since   18/07/2024
 */

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public List<SummaryMinDTO> findBySummary(String dataInicial, String dataFinal) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate dataMaxima = (dataFinal == null || dataFinal.equals("")) ? today : LocalDate.parse(dataFinal);
        LocalDate dataMinima = (dataInicial == null || dataInicial.equals("")) ? dataMaxima.minusYears(1L) : LocalDate.parse(dataInicial);
        return repository.searchBySummary(dataMinima, dataMaxima);
    }

    public Page<ReportMinDTO> findByReport(String dataInicial, String dataFinal, String name, Pageable pageable){
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate dataMaxima = (dataFinal == null || dataFinal.equals("")) ? today : LocalDate.parse(dataFinal);
        LocalDate dataMinima = (dataInicial == null || dataInicial.equals("")) ? dataMaxima.minusYears(1L) : LocalDate.parse(dataInicial);
        Page<Sale> result = repository.searchByReport(dataMinima, dataMaxima, name, pageable);
        return result.map(x -> new ReportMinDTO(x));
    }
}
