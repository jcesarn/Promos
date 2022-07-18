package progweb.promos.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {

	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorioSimples() {
		InputStream arquivoJasper = getClass()
			.getResourceAsStream("/relatorios/RelatorioSimples.jasper");
		try (Connection conexao = dataSource.getConnection()){
			try {
				JasperPrint jasperPrint = JasperFillManager.fillReport(arquivoJasper, null, conexao);
				return JasperExportManager.exportReportToPdf(jasperPrint);
			} catch (JRException e) {
			}
		} catch (SQLException e) {
		}

		return null;
	}
	
	public byte[] gerarRelatorioComplexo() {
		try (Connection conexao = dataSource.getConnection()) {
				try {
					ClassPathResource cpr = new ClassPathResource("relatorios/RelatorioComplexo.jasper");
					InputStream arquivoJasper = cpr.getInputStream();

					String urlRelatorio = cpr.getURL().toString();
					String diretorioRelatorios = urlRelatorio.substring(0,
							urlRelatorio.lastIndexOf("/") + 1);

					Map<String, Object> parametros = new HashMap<>();
					parametros.put("SUBREPORT_DIR", diretorioRelatorios);

					JasperPrint jasperPrint = JasperFillManager.fillReport(arquivoJasper, parametros, conexao);
					return JasperExportManager.exportReportToPdf(jasperPrint);
				} catch (java.io.IOException e) {
				} catch (JRException e) {
				}
			} catch (SQLException e) {
			}
			return null;
	}

}
