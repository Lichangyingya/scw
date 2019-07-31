package cn.jxau.servlet;

import cn.jxau.pojo.CrmClass;
import cn.jxau.pojo.Lesson;
import cn.jxau.pojo.PageInfo;
import cn.jxau.service.ClassService;
import cn.jxau.service.Impl.ClassServiceImpl;
import cn.jxau.vo.ClassVo;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ClassController",
        urlPatterns ={"/showClass","/addClass","/delClass","/updateClass","/getClass","/queryClass","/isExistClass","/ajaxGetClasses","/selByCondition","/uploadCourse","/selClassVo","/classNames","/downloadCourse"})
public class ClassController extends HttpServlet {
    private ClassService classService = new ClassServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        if (requestURI.contains("addClass")){
            addClass(request,response);
        }
        if (requestURI.contains("delClass")){
            delClass(request,response);

        }
        if (requestURI.contains("getClass")){
            getClass(request,response);
        }
        if (requestURI.contains("queryClass")){
            queryClass(request,response);
        }
        if (requestURI.contains("updateClass")){
            updateClass(request,response);
        }
        if (requestURI.contains("showClass")){

            showClass(request,response);
        }
        if (requestURI.contains("isExistClass")){

            isExistClass(request,response);
        }
        if (requestURI.contains("selByCondition")){

            selByCondition(request,response);

        }
        if (requestURI.contains("ajaxGetClasses")){
            ajaxGetClasses(request,response);

        }
        if (requestURI.contains("uploadCourse")){
            uploadCourse(request,response);

        }
        if (requestURI.contains("selClassVo")){
            selClassVo(request,response);

        }
        if (requestURI.contains("classNames")){
            classNames(request,response);

        }
        if (requestURI.contains("downloadCourse")){
            downloadCourse(request,response);

        }

    }

    private void downloadCourse(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        CrmClass crmClass = classService.selById(id);
        String filename = this.getServletContext().getRealPath(crmClass.getUploadPath());
        String mimeType = this.getServletContext().getMimeType(filename);
        String contentDisposition = "attachment;filename ="+crmClass.getUploadFileName();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            response.setHeader("Content-Disposition",contentDisposition);
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(fileInputStream,outputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void classNames(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<CrmClass> classes = classService.getClasses();
        JSONArray jsonArray = new JSONArray();
        for (CrmClass crmClass:classes) {
            jsonArray.add(crmClass);
        }
        String s = jsonArray.toString();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript");
        response.getWriter().print(s);


    }

    private void uploadCourse(HttpServletRequest request, HttpServletResponse response) {
        String id1 = request.getParameter("id");
        System.out.println(id1);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = null;
        try {
            fileItems = servletFileUpload.parseRequest(request);
            FileItem fileItem = fileItems.get(0);
            FileItem fileItem1 = fileItems.get(1);
            String name = fileItem1.getFieldName();
            int id = Integer.parseInt(fileItem1.getString("utf-8"));
            String root = this.getServletContext().getRealPath("/WEB-INF/file");
            String fileName = fileItem.getName();
            int index = fileName.lastIndexOf("\\");
            if(index != -1){
                fileName = fileName.substring(index+1);
            }
            String saveName = UUID.randomUUID()+"_"+fileName;

            int hashCode = fileName.hashCode();

            String s = Integer.toHexString(hashCode);

            File dirfile = new File(root,s.charAt(0)+"/"+s.charAt(2));

            dirfile.mkdirs();
            
            File file = new File(dirfile,saveName);

            String uploadPath = "/WEB-INF/file"+"/"+s.charAt(0)+"/"+s.charAt(2)+"/"+saveName;

            fileItem.write(file);
            int i = classService.uploadFile(saveName, uploadPath, id);
            if (i == 1){
                request.setAttribute("msg","上传课表成功");
                showClass(request,response);
            } else{
                request.setAttribute("msg","上传课表失败");
                showClass(request,response);
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void selClassVo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        ClassVo classVo = classService.getClass(id);
        System.out.println(classVo);
        request.setAttribute("classVo",classVo);
        request.getRequestDispatcher("course_upload.jsp").forward(request,response);
    }


    private void ajaxGetClasses(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<CrmClass> crmClasses = classService.getAllClass();
        JSONArray jsonArray = new JSONArray();
        for (CrmClass crmClass:crmClasses) {
            jsonArray.add(crmClass);
        }
        String s = jsonArray.toString();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript");
        response.getWriter().print(s);
    }

    private void selByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *
         cstate:
         (empty)
         stratTime:
         (empty)
         jobTime:
         */
        int pageNumber = 1;//默认显示第一页
        int pageSize = 5;//默认显示3条数据
        String pageNumberString = request.getParameter("pageNumber");
        String pageSzieString = request.getParameter("pageSize");
        if(pageNumberString != null){
            pageNumber = Integer.parseInt(pageNumberString);
        }
        if (pageSzieString != null){
            pageSize = Integer.parseInt(pageSzieString);
        }
        String status = request.getParameter("cstate");
        String startTime = request.getParameter("stratTime");
        String endTime = request.getParameter("jobTime");
        request.getSession().setAttribute("cstate",status);
        request.getSession().setAttribute("stratTime",startTime);
        request.getSession().setAttribute("jobTime",endTime);
        PageInfo pageInfo = classService.selByCondition(status, startTime, endTime,pageNumber,pageSize);
        if (pageInfo.getList().size()>0) {
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("query_class.jsp").forward(request, response);
        }else {
            request.setAttribute("pageInfo",pageInfo);
            request.setAttribute("msg","没有查询到符合条件的数据！");
            request.getRequestDispatcher("query_class.jsp").forward(request, response);
        }

        System.out.println(pageInfo);

    }
    private void isExistClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String className = request.getParameter("className");
        int existClass = classService.isExistClass(className);
        System.out.println(existClass);
        response.getWriter().print(existClass);

    }

    private void queryClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        ClassVo classVo= classService.getClass(id);
        request.setAttribute("classVo",classVo);
        request.getRequestDispatcher("class_list.jsp").forward(request,response);
    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CrmClass crmClass = new CrmClass();
        crmClass.setId(Integer.parseInt(request.getParameter("id")));
        crmClass.setClassName(request.getParameter("className"));
        crmClass.setLessonTypeID(Integer.parseInt(request.getParameter("course")));
        crmClass.setBeginTime(request.getParameter("startTime"));
        crmClass.setEndTime(request.getParameter("endTime"));
        crmClass.setRemark(request.getParameter("mark"));
        int i = classService.update(crmClass);
        if (i == 1) {
            showClass(request,response);
        }else{
            request.setAttribute("msg","修改失败！");
            showClass(request,response);
        }

    }

    private void getClass(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        ClassVo classVo = classService.getClass(id);
        request.setAttribute("crmClass",classVo);
        request.getRequestDispatcher("do_class.jsp").forward(request,response);

    }

    private void delClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        int i = classService.delClass(id);
        if (i == 1) {
            showClass(request,response);
        }else{
            request.setAttribute("msg","删除失败！");
            showClass(request,response);
        }

    }

    private void showClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;//默认显示第一页
        int pageSize = 4;//默认显示6条数据
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        if (request.getParameter("pageSize") != null){
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        PageInfo pageInfo = classService.showPage(pageNumber, pageSize);
        if (pageInfo.getList().size() > 0) {
            request.setAttribute("pageInfo",pageInfo);
            request.getRequestDispatcher("class_manage.jsp").forward(request, response);
        }else{
            request.setAttribute("msg","没有数据！");
            request.getRequestDispatcher("class_manage.jsp").forward(request, response);
        }
    }

    private void addClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*className: 1期JavaEE
        course: 2
        startTime: 2019-02-03
        endTime: 2019-06-03
        mark:*/
        CrmClass crmClass = new CrmClass();
        crmClass.setClassName(request.getParameter("className"));
        crmClass.setLessonTypeID(Integer.parseInt(request.getParameter("course")));
        crmClass.setBeginTime(request.getParameter("startTime"));
        crmClass.setEndTime(request.getParameter("endTime"));
        crmClass.setRemark(request.getParameter("mark"));
        int i = classService.addClass(crmClass);
        if (i == 1) {
            showClass(request,response);
        }else{
            request.setAttribute("msg","添加失败！");
            showClass(request,response);
        }

    }
}
