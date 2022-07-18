package progweb.promos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import progweb.promos.service.RelatorioService;

@Controller
public class RelatoriosController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/gerarrelatoriosimples")
	public ResponseEntity<byte[]> gerarRelatorioSimples() {
		byte[] relatorio = relatorioService.gerarRelatorioSimples();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RelatorioSimples.pdf")
				.body(relatorio);
	}
	
	@GetMapping("/gerarrelatoriocomplexo")
	public ResponseEntity<byte[]> gerarRelatorioComplexo() {
		
		byte[] relatorio = relatorioService.gerarRelatorioComplexo();
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RelatorioComplexo.pdf")
				.body(relatorio);
	}
	
}
