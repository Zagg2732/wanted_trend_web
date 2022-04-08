package com.wanted.wantedtrend.utils;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wanted.wantedtrend.web.dto.PostResDto;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public String getPath() {
        String rootPath = System.getProperty("user.dir");
        String path = rootPath + "/excel/";

        return path;
    }

    public String getFilename(String date){
        return date + ".xlsx";	//파일명 설정
    }


    public List<PostResDto> readExcel(String path, String filename) {
        List<PostResDto> postResDtoList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(path + filename);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            LocalDate myObj = LocalDate.now(); // Crawl Date
            String crawlDate = String.valueOf(myObj);


            int rowindex=0;
            int columnindex=0;
            //시트 수 (첫번째에만 존재하므로 0을 준다)
            //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet=workbook.getSheetAt(0);
            //행의 수
            int rows=sheet.getPhysicalNumberOfRows();
            for(rowindex=1; rowindex<rows ;rowindex++){

                PostResDto postResDto = new PostResDto();

                //행을읽는다
                XSSFRow row=sheet.getRow(rowindex);
                if(row !=null){
                    //셀의 수
                    int cells=row.getPhysicalNumberOfCells();
                    for(columnindex=0; columnindex<=cells; columnindex++){
                        //셀값을 읽는다
                        XSSFCell cell=row.getCell(columnindex);
                        String value="";
                        //셀이 빈값일경우를 위한 널체크
                        if(cell==null){
                            continue;
                        }else{
                            //타입별로 내용 읽기
                            switch (cell.getCellType()){
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    value=cell.getCellFormula();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    value=cell.getNumericCellValue()+"";
                                    break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    value=cell.getStringCellValue()+"";
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    value=cell.getBooleanCellValue()+"";
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value=cell.getErrorCellValue()+"";
                                    break;
                            }
                        }


                        List<String> langs = new ArrayList<>();

                        switch (columnindex) {
                            case 0: // url
                                postResDto.setUrl(value);
                                break;
                            case 1: // 공고 작성일
                                postResDto.setDate(value);
                                break;
                            case 2: // 지원상태
                                postResDto.setStatus(value);
                                break;
                            case 3: // 회사이름
                                postResDto.setCompanyName(value);
                                break;
                            case 4: // 지역
                                postResDto.setLocation(value);
                                break;
                            case 5: // 주요업무
                                postResDto.setMainTask(value);
                                break;
                            case 6: // 주요업무(언어)
                                langs = Arrays.asList(value.split(", "));
                                postResDto.setMainLang(langs);
                                break;
                            case 7: // 자격요건
                                postResDto.setRequirement(value);
                                break;
                            case 8: // 자격요건(언어)
                                langs = Arrays.asList(value.split(", "));
                                postResDto.setReqLang(langs);
                                break;
                            case 9: // 우대사항
                                postResDto.setPrefer(value);
                                break;
                            case 10: // 우대사항(언어)
                                langs = Arrays.asList(value.split(", "));
                                postResDto.setPreferLang(langs);
                                break;
                        }
                    }

                }
                postResDto.setCrawlDate(crawlDate);
                postResDtoList.add(postResDto);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return postResDtoList;
    }
}