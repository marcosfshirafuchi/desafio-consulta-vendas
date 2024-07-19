package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;
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

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportMinDTO>> getReport(
			@RequestParam(value = "minDate", required = false, defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", required = false, defaultValue = "") String maxDate,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			Pageable pageable) {
		Page<ReportMinDTO> dto = service.findByReport(minDate,maxDate,name,pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryMinDTO>> getSummary(
			@RequestParam(name = "minDate", defaultValue = "")String minDate,
			@RequestParam(name = "maxDate", defaultValue = "")String maxDate) {
		List<SummaryMinDTO> dto = service.findBySummary(minDate,maxDate);
		return ResponseEntity.ok(dto);
	}
}
