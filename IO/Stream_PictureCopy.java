package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Stream_PictureCopy {
    private String rawPath ;
    private String targetPath ;
    private FileInputStream fis = null;
    private FileOutputStream fos = null;
    private byte [] bytes = null;
    private int byteCount = 0;

    public Stream_PictureCopy(String rawPath,String targetPath) throws Exception{
        this.rawPath =rawPath;
        this.targetPath =targetPath;

        fis = new FileInputStream(rawPath);//这个是读取，从文件中读取到内存，是input
        fos = new FileOutputStream(targetPath);//这个是写入，从内存中写入到文件中，是output

        bytes = new byte[1024];
        while ((byteCount=fis.read(bytes))!=-1){
            fos.write(bytes);
        }

        System.out.println(byteCount);

        fis.close();
        fos.close();

    }

    public static void main(String[] args) {
        try {
            Stream_PictureCopy s = new Stream_PictureCopy("D:\\drink.jpg","D:\\图片\\drink.jpg");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
