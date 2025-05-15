package com.tcc.blogperiferico.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.relatorios.RelatorioDoacaoDTO;
import com.tcc.blogperiferico.repository.DoacaoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatoriosService {
	
	@Autowired
	DoacaoRepository doacaoRepository;
	
	public void gerarRelatorioDoacao(String caminho) throws JRException {
		// Busca todos os pedidos do banco de dados
		List<Doacao> doacoes = doacaoRepository.findAll();

		//Convertendo a lista de pedidos para relatorioPedidosDTO
		List<RelatorioDoacaoDTO> doar = doacoes.stream().map(RelatorioDoacaoDTO::new)
				.collect(Collectors.toList());

		//Instanciei a lista de relatorios e passou tudo para escanor
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(doar);

		//Criei o titulo
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("titulo", "Relatórios de Doações");

		//falando da onde ele vai puxar o molde e criar um relatoria da forma do molde
		JasperReport jasperReport = JasperCompileManager
				.compileReport(getClass().getResourceAsStream("/relatorios/relatorio_doacao.jrxml"));

		//Ele mostra o relatorio
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

		//Ele transforma em pdf
		JasperExportManager.exportReportToPdfFile(jasperPrint, caminho);

	}

}
