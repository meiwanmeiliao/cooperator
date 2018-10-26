
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/26
 * @description: ${description}
 **/
public class Doc2HtmlUtil {

    private static Doc2HtmlUtil doc2HtmlUtil;

    /**
     * 获取Doc2HtmlUtil实例
     */
    public static synchronized Doc2HtmlUtil getDoc2HtmlUtilInstance() {
        if (doc2HtmlUtil == null) {
            doc2HtmlUtil = new Doc2HtmlUtil();
        }
        return doc2HtmlUtil;
    }

    /**
     * 转换文件成pdf
     *
     * @param fromFileInputStream:
     * @throws IOException
     */
    public String file2pdf(InputStream fromFileInputStream, String toFilePath, String originalName) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timesuffix = sdf.format(date);
        String htmFileName = timesuffix + ".pdf";
        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + originalName);
        if (htmlOutputFile.exists()) {
            htmlOutputFile.delete();
        }
        htmlOutputFile.createNewFile();
        if (docInputFile.exists()) {
            docInputFile.delete();
        }
        docInputFile.createNewFile();
        /**
         * 由fromFileInputStream构建输入文件
         */
        try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        }
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        }
        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        return htmFileName;
    }




}
