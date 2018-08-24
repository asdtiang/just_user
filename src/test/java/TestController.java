

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public class TestController {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser(); //创建打印作业
        String pdfStr = "Microsoft Print to PDF";
        int state = fileChooser.showOpenDialog(null);
        if(state == fileChooser.APPROVE_OPTION){
           // File file = new File("D:/haha.txt"); //获取选择的文件
            File file = fileChooser.getSelectedFile();//获取选择的文件
            //构建打印请求属性集
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            //设置打印格式，因为未确定类型，所以选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            //查找所有的可用的打印服务
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            PrintService defaultService =null;
            for(PrintService p : printService ) {
            	System.out.println(p.getName());
            	if(pdfStr.equals(p.getName().trim())) {
            		defaultService = p;
            	}
            }
            //定位默认的打印服务
            if(defaultService != null){
                try {
                    DocPrintJob job = defaultService.createPrintJob(); //创建打印作业
                    FileInputStream fis = new FileInputStream(file); //构造待打印的文件流
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
            	System.out.println("nulllllllll!!!!!!!!!");
            }
        }
    }
}