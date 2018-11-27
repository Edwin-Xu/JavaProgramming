package DataStructureAndAlgorithm.recursionPractice;
/**
 * 打印一个目录下的所有文件；
 *
 * 关于File类的几个获取路径方法：
 * getPath():
 *
 * 返回的是定义时的路径，可能是相对路径，也可能是绝对路径，这个取决于定义时用的是相对路径还是绝对路径。如果定义时用的是绝对路径，那么使用getPath()返回的结果跟用getAbsolutePath()返回的结果一样
 *
 * getAbsolutePath():
 *
 * 返回的是定义时的路径对应的相对路径，但不会处理“.”和“..”的情况
 *
 * getCanonicalPath():
 *
 * 返回的是规范化的绝对路径，相当于将getAbsolutePath()中的“.”和“..”解析成对应的正确的路径
 */


/**
 * 问题： 怎样按树型结构打印出目录？？？？？？？？？？？？？？？？？？？？？？
 */

import java.io.File;
import java.io.IOException;

public class printContents {
    private String filePath = "";
    private File file = null;
    private File [] files = null;
    private String sonPath ="";
    private int count =2;


    public printContents(){
        filePath = "D:\\JavaFiles\\高级";
        print(filePath);
    }
    private  void print(String filePath){
        try {
            file = new File(filePath);
//            System.out.println("getPath():"+ file.getPath());
//            System.out.println("getAbsolutePath():"+file.getAbsolutePath());
//            try {
//                System.out.println("getCanonicalPath():"+file.getCanonicalPath());
//                //canonical:依教规的；圣典的；权威的；牧师的
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            files = file.listFiles();
            for (File file_: files) {

                if (file_.isFile()) {
                    System.out.println(file_.getAbsolutePath());
//                        System.out.print("--");
//                    System.out.println(file_.getAbsolutePath().split("\\\\")[file_.getAbsolutePath().split("\\\\").length-1]);
                }
                else {
//                    System.out.print("--");
//                    System.out.println(file_.getAbsolutePath().split("\\\\")[file_.getAbsolutePath().split("\\\\").length-1]);
                    sonPath = file_.getAbsolutePath();
                    print(sonPath);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new printContents();
    }
}
