package util.template.tomcat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhangsx on 2017/11/22.
 */
public class MainClazz {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException, NamingException {
        WebappClassLoader webappClassLoader=new WebappClassLoader(ClassLoader.getSystemClassLoader());
        webappClassLoader.addRepository("/Users/zhangsx/Desktop/",new File("/Users/zhangsx/Desktop/"));
        FileDirContext resources=new FileDirContext();
//        resources.setDocBase("/Users/zhangsx/Desktop/");
        webappClassLoader.setResources(resources);
        Class cla=webappClassLoader.loadClass("Test");
        Method m=cla.getMethod("func",null);
        m.invoke(null,null);
    }

    static class persons  implements Serializable {
        String Name = "";
        String Age =""  ;
        public persons () {
        }
        //构造函数,用于给变量赋值
        public persons (String namePara,String age) {
            Name = namePara;
            Age = age;
        }
        //用于返回变量Name的值
        public String getName() {
            return Name;
        }
        //用于返回变量Age的值
        public String getAge () {
            return  Age;
        }
    }

    public static void bind(){
        //创建Hashtable以存储JNDI将用于连接目录服务的环境变量
        Hashtable hs = new Hashtable();
        //设置连接LDAP的实现工厂
        hs.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        // 指定LDAP服务器的主机名和端口号
        hs.put(Context.PROVIDER_URL, "ldap://localhost:3890 ");
        //给环境提供认证方法,有SIMPLE、SSL/TLS和SASL
        hs.put(Context.SECURITY_AUTHENTICATION, "simple");
        //指定进入的目录识别名DN
        hs.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
        //进入的目录密码
        hs.put(Context.SECURITY_CREDENTIALS, "password");
        try {
            // 得到初始目录环境的一个引用
            DirContext ctx = new InitialDirContext(hs);
            // 新建一个对象
            persons perObj = new persons("jordan","40");
            //绑定对象
            ctx.rebind ("uid = Jordan,ou = Bull,o = NBA ",perObj);
            System.out.println("bind object object success " );
             /*实例化一个属性集合*/
            Attributes  attrs =  new BasicAttributes(true);
             /*建立一个属性,其属性名为"mail"*/
            Attribute  personMail  = new BasicAttribute("mail");
            //设置属性"mail"的值为"xie@113.com"、"liu@sina.com.cn"、"xyh@powerise.com.cn"
            personMail.add("xie@113.com");
            personMail.add("liu@sina.com.cn");
            personMail.add("xyh@powerise.com.cn");
            attrs.put(personMail);
             /*建立一个属性,其属性名为"uid",值为001*/
            attrs.put("uid","001");
            /*建立一个属性,其属性名为"cn",值为jordan1*/
            attrs.put("cn","jordan1");
            /*建立一个属性,其属性名为"sn",值为NBA */
            attrs.put("sn","NBA");
            /*建立一个属性,其属性名为"ou",值为bull */
            attrs.put("ou","bull");
            System.out.println("bind object object success " );
            /* 在识别名为DN的目录中增加一个条目*/
            ctx.createSubcontext("uid = Jordan, ou = Wizzard,o=NBA",attrs);
            //关闭初始目录环境
            ctx.close();
        } catch (NamingException ex) {
            System.err.println("bind object fail: " + ex.toString());
        }
    }

    public static void lookup(){
        //创建Hashtable以存储JNDI将用于连接目录服务的环境变量
        Hashtable hs = new Hashtable();
        //设置连接Ldap的实现工厂
        hs.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        // 指定LDAP服务器IP地址为本机及端口号为3890
        hs.put(Context.PROVIDER_URL, "ldap://localhost:3890");
        try {
            // 得到初始目录环境的一个引用
            DirContext ctx = new InitialDirContext(hs);
            //利用lookup查找返回指定DN的条目对象
            persons pers =(persons)ctx.lookup("uid=Jordan,ou=Bull,o=NBA");
            // 利用远程对象调用远程方法,返回Age变量的值
            String  age    =  pers.getAge();
            // 利用远程对象调用远程方法,返回Name变量的值
            String  name  =  pers.getName();
            //输出Name的值
            System.out.println("name is :" +  name );
       /*根据结点的DN来查找它的所有属性, 然后再从属性中得到所有的值,注意一个属性可
           以有多个值*/
            Attributes attrs=ctx.getAttributes("uid=Jordan,ou=Wizzard,o=NBA");
            //循环获取并输出这个属性的所有属性值
            for(NamingEnumeration ae = attrs.getAll();ae.hasMore();){
                //获取一个属性
                Attribute attr = (Attribute)ae.next();
                System.out.println("Attribute : " + attr.getID());
                //循环取得输出这个属性的所有属性值
                for(NamingEnumeration ve = attr.getAll(); ve.hasMore();){
                    System.out.println("  Value : " + ve.next());
                }
            }
            //成功打印提示信息
            System.out.println("find object success " );
            //调用该对象的函数
            pers.toString();

            //关闭初始目录环境
            ctx.close();
        } catch (NamingException ex) {
            System.err.println(ex.toString());
        }
    }
}
