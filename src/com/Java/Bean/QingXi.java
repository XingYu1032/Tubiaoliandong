package com.Java.Bean;


import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class QingXi {



    public static String parseDate(String dateStr){

        SimpleDateFormat input_date = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat output_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String finalDate = "";
        try {
            Date parse_date = input_date.parse(dateStr);
            finalDate = output_date.format(parse_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  finalDate;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        File file=new File("D:\\Users\\cuixingyu\\result.txt");
        BufferedReader bf;
        String r=null;
        String [] arr=null;
        //HbaseAPI hb=new HbaseAPI();
        java.sql.Connection con;
        //jdbc驱动
        String driver="com.mysql.cj.jdbc.Driver";
        //这里我的数据库是cxxt
        String url="jdbc:mysql://localhost:3306/homework?&useSSL=false&serverTimezone=UTC";
        String user="root";
        String password="101032";

        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);



        int a=1000;
        {
            try {
                bf = new BufferedReader(new FileReader(file));
                while((r=bf.readLine())!=null){
                    arr = r.split("[\\,\\;]");
                    String ip=arr[0];
                    String date=parseDate(arr[1]);
                    String day=arr[2];
                    String traffic=arr[3];
                    String type=arr[4];
                    String id=arr[5];
                    String rowkey=String.valueOf(a);
                /*    hb.putData("shuju",rowkey,"info","ip",ip);
                    hb.putData("shuju",rowkey,"info","date",date);
                    hb.putData("shuju",rowkey,"info","day",day);
                    hb.putData("shuju",rowkey,"info","traffic",traffic);
                    hb.putData("shuju",rowkey,"info","type",type);
                    hb.putData("shuju",rowkey,"info","id",id);*/
                   // a++;
              //  String sql="insert into "
                    String sql = "insert into info(ip,date,day,traffic,type,id) values ('"+ip+"', '"+date+"','"+day+"','"+traffic+"','"+type+"','"+id+"')";
                    con.prepareStatement(sql).execute();
            }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            con.close();
        }
    }

}

