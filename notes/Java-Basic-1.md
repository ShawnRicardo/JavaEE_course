# Java-Basic-1

## 1、String & StringBuilder

Java的String含有很多的函数，举几个例子

1. 比较

    - `==`表示的是引用的相同（地址），不是内容相同 优先级大于内容

    - `str1.equals(str2)`表示的就是简单的值相同

    - `str1.compareTo(str2)`表示从头开始比较字典序，比较ASCII码是否相同，相同为0，不相同为差

2. 匹配

    - `str1.indexOf(str2)`首字母相同位置，-1则没有
    - `str1.startsWith(str2)`是否有以`str2`开头子串（bool）
    - `str1.endsWith(str2)`是否有以`str2`结尾的子串（bool）

3. 子串分割

    - `int i = str1.indexOf(str2)`(int)，然后用`substring`连接，`str3=str1.substring(0,i)+str2.substring(i+1)`这样就会把`i`位置的元素去掉

4. 替换

5. 大量字符串`StringBuilder`

    - `sb.append(str)`连接

```Java
package edu.whu;

public class StringDemo {
    public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = "hello world";
        Object str3= str1;
        String str4 = "Hello world";

        //字符串比较
        System.out.println("----------1、字符串比较----------");
        System.out.println( str1 == str2);//referential equality instead of comparing content
        System.out.println( str1 == str3);//true
        System.out.println( str1.equals(str2)); //true comparing content
        System.out.println( str1.compareTo(str2)); //0 比较字典序 从头字母开始 比较ASCII码
        System.out.println( str1.compareTo(str4));//32 h - H = 32
        //字符串匹配
        System.out.println("----------2、字符串匹配----------");
        System.out.println(str1.indexOf("world")); //6 str数组的6号位
        System.out.println(str1.indexOf("haha")); //-1 没有这个字符串
        System.out.println(str1.startsWith("hello")); //true 字符串是否以指定字符串开头
        System.out.println(str1.endsWith("hello")); //false 字符串是否以指定字符串结尾
        System.out.println(str1.contains(" ")); //true
        //子串
        System.out.println("----------3、子串----------");
        int i=str1.indexOf(" ");
        String str5= str1.substring(0,i)+str1.substring(i+1);
        System.out.println(str5);

        //字符串替换
        System.out.println("----------4、字符串替换----------");
        String str6= str1.replace("o","x");//基于子字符串的字符串替换
        System.out.println(str6);
        String str7= str1.replaceFirst("\\W","-");//基于正则表达式，把第一个空白字符替换为-
        System.out.println(str7);
        String str8= str1.replaceAll("\\W","-"); //基于正则表达式，把所有空白字符替换为-
        System.out.println(str8);

        //大量的字符串连接，使用StringBuilder来提高效率,https://www.runoob.com/java/java-stringbuffer.html
        System.out.println("----------5、大量字符串连接----------");
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        for(char c='A';c<='Z';c++){
            stringBuilder.append(c);
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder);
    }
}
```

## 2、Array & foreach & Iterator

关注一下迭代器`iterator`的写法

```Java
Iterator<String> itr = Arrays.stream(cars).iterator();
while(itr.hasNext()){
    System.out.println(itr.next());
}
```

> 提示一下，Java这些标准库都必须是封装好的类型，例如 String、Integar、Float等，int、float等基本的不行

左边固定：迭代器+类型+名字

右侧固定：Arrays.stream(类型).interator()

迭代固定：while(itr.hasNext()) { }

```java
package edu.whu;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDemo {
    public static void main(String[] args) {
        String[] bikes;
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        int[] myNum = {10, 20, 30, 40};
        System.out.println(cars.length);

        //for-each循环
        for (String car : cars) {
            System.out.println(car);
        }
        //for-each循环和下面的迭代器循环是等价的
        Iterator<String> itr = Arrays.stream(cars).iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        //二维数组，实际上是数组的数组
        int[][] myNumbers = { {1, 2, 3, 4}, {5, 6,7} };
        System.out.println("myNumbers.length："+myNumbers.length);
        for (int[] lines:myNumbers) {
            for (int num:lines) {
                System.out.println(num);
            }
        }
    }
}
```

## 3、IO

### 1、FileDemo有点小意思

下面这个代码是打印当前工程下的所有文件和文件夹

在`listFiles()`函数中使用了递归，不断读取此文件下的文件夹，直到读取到文件为止

```Java
package edu.whu.io;

import java.io.File;

public class FileDemo {

    public static void main(String[] args) {
        String dirname = ".";
        File f1 = new File(dirname);
        if(!f1.exists()) {return;}
        listFiles(f1);
    }

    public static void listFiles(File file) {
        if (!file.isDirectory()) {
            System.out.println(file.getPath() + " is a file");
            return;
        }
        System.out.println(file.getPath() + " is a directory");
        
        for (File sub : file.listFiles()) {
            listFiles(sub);  // 这是一个递归，就是不断读取此文件下的文件夹，直到读取到文件为止
        }
    }
}
```

### 2、读写文件(FileReader & FileWriter)

关注一下`fr.read(a)`

获取要读取的文件的对象后，创建一个字符数组a，然后把fr对象的所有元素都传到a中，再输出a即可

```Java
package edu.whu.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderAndWritter {

    public static void main(String[] args) {

        File file = new File("Hello1.txt");

        try(FileWriter writer = new FileWriter(file)){
            writer.write("This\n is\n an\n example\n");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(FileReader fr = new FileReader(file)){
            char[] a = new char[50];
            fr.read(a);
            for (char c : a){
                System.out.print(c);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3、文件流（OutputStream & InputStream）

***以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件***

```Java
package edu.whu.io;

import java.io.*;

/**
 * 使用OutputStream和InputStream进行文件读写
 */
public class FileStreamTest {

    public static void main(String[] args) {
        //写入文件
        try(OutputStream os = new FileOutputStream("test.txt")) {
            byte[] bWrite = {96, 97, 93, 90, 95};  // ASCII码写文件
            for (int x = 0; x < bWrite.length; x++) {
                os.write(bWrite[x]);
            }
            os.flush(); //文件是异步写入的。flush将buffer中的数据全部写入文件中。
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //读取文件
        try ( InputStream is = new FileInputStream("test.txt")){
            int size = is.available();
            for (int i = 0; i < size; i++) {
                System.out.print((char) is.read() + "  ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 4、最常用（BufferedReader 和 BufferedWriter）

```Java
/**
 * 以行为单位读取文件，常用于读面向行的格式化文件
 */
public static void readWithBufferedReader(String fileName) {

    File file = new File(fileName);
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            System.out.println("line " + line + ": " + tempString);
            line++;
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
    }
}

/**
 * 以行为单位写文件，常用于写面向行的格式化文件
 */
public static void readAndWrite() {
    try {
        //缓冲System.in输入流
        //System.in是位流，可以通过InputStreamReader将其转换为字符流
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        //缓冲FileWriter
        BufferedWriter bufWriter = 
            new BufferedWriter(new FileWriter("/sdcard/log/test.txt"));

        String input = null;
        //每读一行进行一次写入动作
        while(!(input = bufReader.readLine())) {
            bufWriter.write(input);
            //newLine()方法写入与操作系统相依的换行字符，依执行环境当时的OS来决定该输出那种换行字符
            bufWriter.newLine();
        }
        bufReader.close();
        bufWriter.close();
    } catch(ArrayIndexOutOfBoundsException e) {
        System.out.println("没有指定文件");
    } catch(IOException e) {
        e.printStackTrace();
    }
}
```



## 4、OO

## 5、Exception

#### 1、TryCatchDemo

```Java
package edu.whu.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryResourceDemo {

    public static void main(String[] args) {

        String line;
        //在try(resource)中resource，无论是否发生异常，结束后都会自动释放
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Line =>" + line);
            }
        }
        catch (IOException e) {
            System.out.println("IOException in try block =>" + e.getMessage());
        }

        //如果不使用try(resource)，需要这么写代码，在finally中释放资源
        BufferedReader br = null;
        String line2;
        try {
            br = new BufferedReader(new FileReader("test.txt"));
            while ((line2 = br.readLine()) != null) {
                System.out.println("Line =>" + line2);
            }
        }
        catch (IOException e) {
            System.out.println("IOException in try block =>" + e.getMessage());
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("IOException in finally block =>" + e.getMessage());
            }
        }
    }
}
```

#### 2、Try(Resource)

`Try(Resource)` 这个语法好像还是非常好用的，平常的`try-catch`如果在大括号中写的话，大括号中的文件对象必须得在`Finally`中手动释放，很麻烦；而如果使用`Try(Resource)`的话，在`()`中创建的文件对象`br`可以在执行完后被自动释放，减少代码量。

`try (BufferedReader br = new BufferedReader(new FileReader("test.txt")))`

```Java
package edu.whu.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryResourceDemo {

    public static void main(String[] args) {

        String line;
        //在try(resource)中resource，无论是否发生异常，结束后都会自动释放
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Line =>" + line);
            }
        }
        catch (IOException e) {
            System.out.println("IOException in try block =>" + e.getMessage());
        }
        //如果不使用try(resource)，需要这么写代码，在finally中释放资源
        BufferedReader br = null;
        String line2;
        try {
            br = new BufferedReader(new FileReader("test.txt"));
            while ((line2 = br.readLine()) != null) {
                System.out.println("Line =>" + line2);
            }
        }
        catch (IOException e) {
            System.out.println("IOException in try block =>" + e.getMessage());
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("IOException in finally block =>" + e.getMessage());
            }
        }
    }
}
```

