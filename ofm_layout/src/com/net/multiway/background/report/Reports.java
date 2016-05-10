/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.report;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author phelipe
 */
public class Reports {

    public void gerarRelatorio(String nomeRelatorio,
            HashMap paramRel) throws SQLException, JRException {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletResponse response
//                = (HttpServletResponse) context.getExternalContext()
//                .getResponse();
//        ServletContext sc
//                = (ServletContext) context.getExternalContext().getContext();

//        String relPath = sc.getRealPath("/");
//        String imagemLogo
//                = relPath + "resources/imagens/logo_mmo.jpg";
//        paramRel.put("imagemLogo", imagemLogo);
        paramRel.put("NomeSistema", "OFM");
        paramRel.put("REPORT_LOCALE", new Locale("pt", "BR"));

        try {
            JasperPrint print = null;
            String url = "jdbc:h2:/home/phelipe/Projeto/ofm/db/ofm";
            String user = "sa";
            String pass = "";
            Connection connection
                    = DriverManager.getConnection(url, user, pass);
            URL arquivo = getClass().getResource("ExportReport.jrxml");	    
	 //   JasperReport jasperReport = (JasperReport) JRLoader.loadObject( arquivo );
            JasperReport report = JasperCompileManager.compileReport("file:ExportReport.jrxml");
	    JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(report, paramRel);

            JasperViewer jasperViewer = new JasperViewer( jasperPrint, false );
	    jasperViewer.setVisible( true );
//            response.setContentType("application/pdf");
            //            response.addHeader("Content-disposition",	"attachment;	
            //												filename =\""	+	nomeRelatorio + ".pdf\""
            //);
//            JasperExportManager.exportReportToPdfStream(print,
//                    response.getOutputStream());
//            ServletOutputStream responseStream
//                    = response.getOutputStream();
//            responseStream.flush();
//            responseStream.close();
//            FacesContext.getCurrentInstance().renderResponse();
//            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
