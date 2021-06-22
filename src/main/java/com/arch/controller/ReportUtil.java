package com.arch.controller;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable {

	/*Retorna em PDF em Byte para download no navegador  */
	
	public byte[] generateReport (List listData, 
			String report, ServletContext servletContext) 
			throws Exception{
		
		/* cria a lista de dados para o relatorio com a lista de objetos para imprimir */
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listData);
		
		
		/*carrega o caminho do arquivo jasper compilado  */
		String caminhoJasper = servletContext.getRealPath("reports") 
				+ File.separator + report + ".jasper";
		
		/*Carrega o arquivo Jasper passando os dados */
		JasperPrint printJasper = JasperFillManager
				.fillReport(caminhoJasper, new HashMap(),
						jrbcds);
		
		/*exporta para bytes e fazer download do pdf */
		return JasperExportManager.exportReportToPdf(printJasper);
	}
	
	
}
