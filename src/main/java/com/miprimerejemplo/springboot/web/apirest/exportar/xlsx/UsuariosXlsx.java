package com.miprimerejemplo.springboot.web.apirest.exportar.xlsx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.miprimerejemplo.springboot.web.apirest.models.entity.Usuario;
public class UsuariosXlsx {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "Id Usuario", "Nick", "Nombre", "Apellidos", "Email", "Fecha de nacimiento", "Foto"};
  static String SHEET = "Usuarios";
  public static ByteArrayInputStream usuariosToExcel(List<Usuario> usuarios) {
    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);
      // Header
      Row headerRow = sheet.createRow(0);
      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }
      int rowIdx = 1;
      for (Usuario usuario : usuarios) {
        Row row = sheet.createRow(rowIdx++);
        row.createCell(0).setCellValue(usuario.getIdUsuario());
        row.createCell(1).setCellValue(usuario.getNick());
        row.createCell(2).setCellValue(usuario.getNombre());
        row.createCell(3).setCellValue(usuario.getApellidos());
        row.createCell(4).setCellValue(usuario.getEmail());
        row.createCell(5).setCellValue(usuario.getFecha_nac());
        row.createCell(6).setCellValue(usuario.getFoto());
      }
      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }
}
