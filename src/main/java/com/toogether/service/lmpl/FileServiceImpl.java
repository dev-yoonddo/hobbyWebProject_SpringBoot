package com.toogether.service.lmpl;

import com.toogether.repo.BoardRepo;
import com.toogether.repo.FileRepo;
import com.toogether.service.FileService;
import com.toogether.vo.BoardVO;
import com.toogether.vo.FileVO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;


public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepo fileRepo;
    @Value("${file.dir}")
    private String fileDir;
    @Override
    public BoardVO fileupload(MultipartFile file) throws IOException {
        System.out.println("파일 존재");
        String filename = file.getOriginalFilename();
        String fileRealname = generateUniqueFileName(filename);
        String path = fileDir + fileRealname;
        System.out.println("파일 경로" + path);
        //파일업로드
        file.transferTo(new File(path));
        //파일 이름 반환
        return new BoardVO(filename, fileRealname);
    }
    @Override
    public void filesave(FileVO vo){
        fileRepo.save(vo);
    }
    // 유니크한 파일이름을 생성한다.
    private String generateUniqueFileName(String filename) {
        String baseName = filename.substring(0, filename.lastIndexOf("."));
        String fileExtension = filename.substring(filename.lastIndexOf("."));
        String uniqueID = UUID.randomUUID().toString();
        return baseName + "_" + uniqueID + fileExtension;
    }

    @Override
    public int fileDownload(int boardID, HttpServletRequest request, HttpServletResponse response){

        int result = 0;
        System.out.println("다운 :" + boardID);

        // 다운로드를 위한 경로에는 fileRealName을 사용하고 파일 다운로드 완료 후 화면에 보이는 파일 이름은 따로 가져온 뒤 사용한다.
        String filename = fileRepo.getById(boardID).getFileName();
        System.out.println(filename);
        String fileRealName = request.getParameter("file");
//		다운로드 할 파일이 저장된 실제 물리적 디렉토리 경로를 다운로드 할 파일 이름과 연결한다.
        // 1. JSP프로젝트의 fileupload 폴더 경로 직접 지정 > String uploadDirectoryByJSP =
        // "C:/gookbiProject/JSP/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/hobbyWebProject/fileupload/"
        // + filename;
        // 2. 배포한 war파일의 fileupload 폴더 경로 직접 지정 > String uploadDirectory =
        // "/home/tomcat/apache-tomcat-8.5.88/webapps/hobbyWebProject/fileupload/" +
        // filename; // 여기선 hobbyWebProject/fileupload 경로를 사용했지만 밑에선 업데이트시 파일이 삭제되는걸
        // 방지하기 위해 외부폴더를 사용했다.
        // 3. 해당 프로젝트 경로를 가져온다. + 파일이름 > String uploadDirectory =
        // request.getContextPath() + "/fileupload/"; //배포프로젝트에선 작동하지 않는다.
        // 4. fileupload폴더의 진짜 경로를 가져온다. > String uploadDirectory =
        // application.getRealPath("/fileupload/"); //ServletContext에도 있기때문에
        // deprecated된다.
        // 5. 직접 경로를 지정하지 않고 사용하기 제일 적절한 방법이다. > String uploadDirectory =
        // request.getSession().getServletContext().getRealPath("/fileupload/" +
        // filename); //배포페이지와 localhost페이지에서 각각 다른 경로로 업로드,다운로드 된다.
        // System.out.println(uploadDirectory);

        // --------------------------------------------------------------------------------------------------------
        // String filePath =
        // request.getSession().getServletContext().getRealPath("/fileupload/" +
        // filename);
        // File file = new File(filePath); //각 결과에서 저장된 경로로 객체를 생성한다.
        // 위 두 줄의 코드만 사용해서 해당하는 운영체제의 경로를 받아와도 되지만 위 코드는 프로젝트 내부의 fileupload경로를 불러오는데
        // 프로젝트 업데이트시 파일이 삭제되기 때문에 외부에 파일을 만들어 경로를 따로 지정해준다.
        // 사진 다운로드가 가능하도록 하기 위해 각 경로가 존재하는지 확인하고 해당하는 경로로 객체를 생성한다.
        String filePath = "C:/fileupload/"+fileRealName;
        // String awsPath = "/home/tomcat/apache-tomcat-8.5.88/webapps/fileupload/" +
        // filename;

//		if (new File(jspPath).exists()) { // jsp경로가 존재하면 jsp에서 프로젝트를 실행했음을 의미하고
//			filePath = jspPath;
//		} else { // 존재하지 않으면 서버에서 프로젝트를 실행했음을 의미하므로 해당하는 경로를 변수에 저장한다.
//			filePath = awsPath;
//		}
        File file = new File(filePath); // 각 결과에서 저장된 경로로 객체를 생성한다.

        // --------------------------------------------------------------------------------------------------------
        // File file = new File(uploadDirectory);
        try {
            String mimeType = request.getSession().getServletContext().getMimeType(file.toString());
            if (mimeType == null) {
                response.setContentType("application/octet-stream");
            }
            if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
                filename = new String(filename.getBytes("UTF-8"), "8859_1");
            } else {
                filename = new String(filename.getBytes("EUC-KR"), "8859_1");
            }
            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\";");

            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int data = 0;
            while ((data = fileInputStream.read(b, 0, b.length)) != -1) {
                outputStream.write(b, 0, data);
            }
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
            result = 1;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
		/*/ 다운로드가 완료되면 다운로드 횟수를 증가시킨다.
		service.download(request, response);
		response.sendRedirect("view?boardID=" + boardID);
		 */
    }
}
