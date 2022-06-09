package kr.co.EZHOME.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import controller.DefaultFileRenamePolicy;
import controller.MultipartRequest;
import dao.ItemDAO;
import dao.PostScriptDAO;
import dto.ItemVO;
import dto.PostScriptVO;

@Controller
public class TestController {
	
	@GetMapping("/itemListDo")
	public String itemListDo() {
		return "forward:/itemList";
	}
	
	@GetMapping("/itemList")
	public String itemList() {
		return "itemList";
	}
	
	@GetMapping("/itemWriteDo")
	public String itemWriteDoGet() {
		return "forward:/itemWrite";
	}
	
	@PostMapping("/itemWriteDo")
	public String itemWriteDoPost(HttpServletRequest request) {
		request.setCharacterEncoding("utf-8");
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 20 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
				encType, new DefaultFileRenamePolicy());

		String item_category = multi.getParameter("item_category");
		String item_name = multi.getParameter("item_name");
		String item_content = multi.getParameter("item_content");
		String item_price = multi.getParameter("item_price");
		String item_quantity = multi.getParameter("item_quantity");
		String item_total = multi.getParameter("item_total");
		String item_time = multi.getParameter("item_time");
		String item_main = multi.getParameter("item_main");
		String item_sales = multi.getParameter("item_sales");
		String item_discount = multi.getParameter("item_discount");
		String item_starsAvg = multi.getParameter("item_starsAvg");
		String item_pictureUrl1 = multi.getFilesystemName("item_pictureUrl1");
		String item_pictureUrl2 = multi.getFilesystemName("item_pictureUrl2");

		ItemVO iVo = new ItemVO();
		iVo.setItem_category(item_category);
		iVo.setItem_name(item_name);
		iVo.setItem_content(item_content);
		iVo.setItem_price(Integer.parseInt(item_price));
		iVo.setItem_quantity(Integer.parseInt(item_quantity));
		iVo.setItem_total(item_total);
		iVo.setItem_time(item_time);
		iVo.setItem_main(item_main);
		iVo.setItem_sales(Integer.parseInt(item_sales));
		iVo.setItem_discount(Double.parseDouble(item_discount));
		iVo.setItem_starsAvg(Double.parseDouble(item_starsAvg));
		
		iVo.setItem_pictureUrl1(item_pictureUrl1);
		iVo.setItem_pictureUrl2(item_pictureUrl2);
		
		ItemDAO iDao = ItemDAO.getInstance();
		iDao.insertItem(iVo);

		return "redirect:/itemListDo";
	}
	
	@GetMapping("/itemUpdateDo")
	public String itemUpdateDoGet(HttpServletRequest request) {
		String item_num = request.getParameter("item_num");

		ItemDAO iDao = ItemDAO.getInstance();
		ItemVO iVo = iDao.selectItemByItem_num(item_num);

		request.setAttribute("item", iVo);
		
		return "forward:/itemUpdate";
	}
	
	@PostMapping("/itemUpdateDo")
	public String itemUpdateDoPost(HttpServletRequest request) {
		request.setCharacterEncoding("utf-8");
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 20 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());

		String item_category = multi.getParameter("item_category");
		String item_name = multi.getParameter("item_name");
		String item_content = multi.getParameter("item_content");
		String item_price = multi.getParameter("item_price");
		String item_quantity = multi.getParameter("item_quantity");
		String item_total = multi.getParameter("item_total");
		String item_time = multi.getParameter("item_time");
		String item_main = multi.getParameter("item_main");
		String item_sales = multi.getParameter("item_sales");
		String item_discount = multi.getParameter("item_discount");
		String item_starsAvg = multi.getParameter("item_starsAvg");
		String item_pictureUrl1 = multi.getFilesystemName("item_pictureUrl1");
		String item_pictureUrl2 = multi.getFilesystemName("item_pictureUrl2");
		String item_num = multi.getParameter("item_num");

		if (item_pictureUrl1 == null) {
			item_pictureUrl1 = multi.getParameter("nonmakeImg1");
		}
		
		if (item_pictureUrl2 == null) {
			item_pictureUrl2 = multi.getParameter("nonmakeImg2");
		}

		ItemVO iVo = new ItemVO();
		iVo.setItem_category(item_category);
		iVo.setItem_name(item_name);
		iVo.setItem_content(item_content);
		iVo.setItem_price(Integer.parseInt(item_price));
		iVo.setItem_quantity(Integer.parseInt(item_quantity));
		iVo.setItem_total(item_total);
		iVo.setItem_time(item_time);
		iVo.setItem_main(item_main);
		iVo.setItem_sales(Integer.parseInt(item_sales));
		iVo.setItem_discount(Double.parseDouble(item_discount));
		iVo.setItem_starsAvg(Double.parseDouble(item_starsAvg));
		iVo.setItem_pictureUrl1(item_pictureUrl1);
		iVo.setItem_pictureUrl2(item_pictureUrl2);
		iVo.setItem_num(Integer.parseInt(item_num));

		ItemDAO iDao = ItemDAO.getInstance();
		iDao.updateItem(iVo);
		
		return "redirect:/itemListDo";
	}
	
	@GetMapping("/itemDeleteDo")
	public String itemDeleteDoGet(HttpServletRequest request) {
		String str = "itemDelete";
		String item_num = request.getParameter("item_num");

		ItemDAO iDao = ItemDAO.getInstance();

		if (item_num == null) {
			iDao.deleteAllItems();
			str = "itemList";
		}

		ItemVO iVo = iDao.selectItemByItem_num(item_num);
		request.setAttribute("item", iVo);
	
		return "forward:/" + str;
	}
	
	@PostMapping("/itemDeleteDo")
	public String itemDeleteDoPost(HttpServletRequest request) {
		String item_num = request.getParameter("item_num");

		ItemDAO iDao = ItemDAO.getInstance();
		iDao.deleteItem(item_num);
		
		return "redirect:/itemListDo";
	}
	
	@GetMapping("/deleteAll")
	public String deleteAll() {
		return "deleteAll";
	}
	
	@GetMapping("/postScript")
	public String postScript() {
		return "postScript";
	}
	
	@PostMapping("/postWriteDo")
	public String postWriteDoPost(HttpServletRequest request) {
		request.setCharacterEncoding("utf-8");
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("images/product");
		String encType = "utf-8";
		int sizeLimit = 20 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
				encType, new DefaultFileRenamePolicy());
		String post_subject = multi.getParameter("post_subject");
		String post_writer = multi.getParameter("post_writer");
		double post_stars = Double.parseDouble(multi.getParameter("post_stars"));
		String post_content = multi.getParameter("post_content");

		String post_image = multi.getFilesystemName("post_image");
		
		PostScriptVO pVo = new PostScriptVO();
		pVo.setPost_subject(post_subject);
		pVo.setPost_writer(post_writer);
		pVo.setPost_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		pVo.setPost_help(0);
		pVo.setPost_hits(0);
		pVo.setPost_stars(post_stars);
		pVo.setPost_content(post_content);
		pVo.setPost_image(post_image);

		PostScriptDAO pDao = PostScriptDAO.getInstance();
		pDao.insertPostScript(pVo);
		
		return "redirect:/postScript";		
	}
	
	@GetMapping("/postScriptContent")
	public String postScriptContent() {
		return "postScriptContent";
	}
	
	@GetMapping("/updateHelp")
	public String updateHelp() {
		return "updateHelp";
	}
	
	@GetMapping("/postDelete")
	public String postDelete() {
		return "postDelete";
	}
	
}
