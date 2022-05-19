package in.ashokit.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.report.ExcelGenerator;
import in.ashokit.report.PDFGenerator;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;
import in.ashokit.service.ReportService;

@RestController
public class EligibilyCheckRestController {
    @Autowired
	private ReportService service;
    
    @GetMapping("/plans")
    public List<String> getplanNames(){
    	return service.getPlanNames();
    }
    
    @GetMapping("/status")
    public List<String> getplanStaus(){
    	return service.getplanStatus();
    }
    @PostMapping("/search")
    public List<SearchResponse> search(@RequestBody SearchRequest  request){
    	return service.searchPlans(request);
    }
    @GetMapping("/excel")
    public void GenerateExcel(HttpServletResponse response)throws Exception {
    	
    	response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Plans.xls";
        response.setHeader(headerKey, headerValue);
        
    	List<SearchResponse>record=service.searchPlans(null);
    	ExcelGenerator excel=new ExcelGenerator();
    	excel.generateExcelreport(record, response);
    }
    
    @GetMapping("/pdf")
    public void generatePdf(HttpServletResponse httpResponse) throws Exception {

      httpResponse.setContentType("application/pdf");
      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=Plans.pdf";
      httpResponse.setHeader(headerKey, headerValue);

      List<SearchResponse> records = service.searchPlans(null);
      PDFGenerator pdfGen = new PDFGenerator();
      pdfGen.generatePDF(records, httpResponse);
    }
	
}
