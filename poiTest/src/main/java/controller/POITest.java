package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.entity.vo.MapExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pojo.Product;
import pojo.User;

@Controller
public class POITest {

	/**
	 * Map方式导出one
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping("/MapExportExcel")
    public String exportMerchantProfitQuery(ModelMap modelMap, HttpServletRequest request) {
	        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
	        entityList.add(new ExcelExportEntity("用户ID", "id", 35));
	        entityList.add(new ExcelExportEntity("用户名", "name", 15));
	        entityList.add(new ExcelExportEntity("用户年龄", "age", 15));
	        List<Map<String, String>> dataResult = getData();
	
	        modelMap.put(MapExcelConstants.ENTITY_LIST, entityList);
	        modelMap.put(MapExcelConstants.MAP_LIST, dataResult);
	        modelMap.put(MapExcelConstants.FILE_NAME, "商户利润");
	        Date now = new Date();
	        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("商户利润详情", "创建时间" + now.toLocaleString(), "商户"));
	        return MapExcelConstants.JEECG_MAP_EXCEL_VIEW;
    }
	
	
	public void test(){
		
		System.out.println("ssss");
	}
	
	/**
	 * Map方式导出two
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping("/MapExportExcelByMap")
    public void exportExcelByMap() {
		try {
	        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
	        entityList.add(new ExcelExportEntity("用户ID", "id", 35));
	        entityList.add(new ExcelExportEntity("用户名", "name", 15));
	        entityList.add(new ExcelExportEntity("用户名", "name", 15));
	        entityList.add(new ExcelExportEntity("用户年龄", "age", 15));
	        List<Map<String, String>> dataResult = getData();
	        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工通讯", "通讯"), entityList, dataResult);
	        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\2.xls"));
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private List<Map<String, String>> getData() {
		
		
		System.out.println("sdhchsiwshi");
		
		
		System.out.println("swss");
		System.out.println("swss");
		
	        List<Map<String, String>> dataResult = new ArrayList<Map<String, String>>();
	        Map<String, String> u1 = new LinkedHashMap<String, String>();
	        u1.put("id", "1");
	        u1.put("xxx", "cydff");
	        u1.put("agesdfsfs", "21");
	        Map<String, String> u2 = new LinkedHashMap<String, String>();
	        u2.put("id", "2");
	        u2.put("name", "cy");
	        u2.put("age", "22");
	        dataResult.add(u1);
	        dataResult.add(u2);
	        return dataResult;
    }
	
	/**
	 * 注解导出one
	 * @param map1
	 * @return
	 */
	@RequestMapping("/excelAnno")
    public String excelAnno(ModelMap map1) {
        List<User> list = getUsers();
        map1.put(NormalExcelConstants.CLASS, User.class);
        map1.put(NormalExcelConstants.FILE_NAME, "用户导出测试");
        ExportParams ep = new ExportParams("sd", "第一个Sheet");
        ep.setExclusions(new String[] {});
        map1.put(NormalExcelConstants.PARAMS, ep);
        map1.put(NormalExcelConstants.DATA_LIST, list);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }
	
	/**
	 * 注解导出two
	 * @param map1
	 * @return
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("/exportExcelByAnno")
    public void exportExcelByAnno() throws Exception {
        List<User> list = getUsers();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("旋哥", "帅"), User.class, list);
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\3.xls"));
		workbook.write(out);
    }

	private List<User> getUsers() {
        Product p1 = new Product();
        Product p2 = new Product();
        p1.setId(1);
        p1.setName("apple");
        System.out.println("shduigcuios");
        p1.setPrice(10);
        p1.setTime(new Date());

        p2.setId(2);
        p2.setName("pear");
        p2.setPrice(30);
        p2.setTime(new Date());

        User u1 = new User();
        u1.setAge(21);
        u1.setId(Long.parseLong("1"));
        u1.setName("cyf");
        u1.setProduct(p1);
        u1.setSex(1);
        List<Product> products = new ArrayList<Product>();
        products.add(p2);
        products.add(p1);
    

        User u2 = new User();
        u2.setAge(23);
        u2.setId(Long.parseLong("2"));
        u2.setName("cy");
        u2.setProduct(p2);
        u2.setSex(1);
        u2.setProducts(products);
        u2.setTime(new Date());

        List<User> users = new ArrayList<User>();
        users.add(u1);
        users.add(u2);

        return users;
    }
	/**
	 * 多sheet导出
	 * @param req
	 * @param resp
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/multiplyexcelAnno")
    public void multiplyexcelAnno(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        Map<String, Object> map1 = getTestMap();
        Map<String, Object> map2 = getTestMap();
        List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
        list1.add(map1);
        list1.add(map2);
        Workbook workbook = exportExcel(list1, ExcelType.HSSF);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/x-download");
        String filedisplay = "旋哥.xls";
        filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);

        try {
        	int i =3;
        	
        	i=i+1;
            OutputStream out = resp.getOutputStream();
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private Map<String, Object> getTestMap() {
        Map<String,Object> map1  = new LinkedHashMap<String,Object>();
        List<User> list = getUsers();
        map1.put(NormalExcelConstants.CLASS, User.class);
        map1.put(NormalExcelConstants.FILE_NAME, "用户导出测试");
        ExportParams ep = new ExportParams("历史总包滚存分析1", "111"+(1000*Math.random()));
        ep.setExclusions(new String[] { "年龄" });// 这里填替换后的
        map1.put(NormalExcelConstants.PARAMS, ep);
        map1.put(NormalExcelConstants.DATA_LIST, list);
        return map1;
    }

    public static Workbook exportExcel(List<Map<String, Object>> list, ExcelType type) {
        Workbook workbook;
        if (ExcelType.HSSF.equals(type)) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        for (Map<String, Object> map : list) {
            ExcelExportServer server = new ExcelExportServer();
            ExportParams params = (ExportParams) map.get("params");
            Class<?> entry = (Class<?>) map.get("entity");
            Collection<?> data = (Collection<?>) map.get("data");
            server.createSheet(workbook, params,entry ,data);
        }
        return workbook;
    }
    @RequestMapping("/inputExcle")
    public void importExcelTest() throws Exception{
    	
    	try {
    		 ImportParams params = new ImportParams();
             params.setTitleRows(1);
             params.setHeadRows(2);
             params.setNeedSave(true);
			FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\3.xls"));
			List<User> importExcel = ExcelImportUtil.importExcel(inputstream , User.class,params);
			for (User user : importExcel) {
				
				System.out.println(user);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}
